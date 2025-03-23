package it.corsojava.progettodonazioni.services;

import it.corsojava.progettodonazioni.entities.*;
import it.corsojava.progettodonazioni.enumerator.Role;
import it.corsojava.progettodonazioni.enumerator.Status;
import it.corsojava.progettodonazioni.entities.Donation;
import it.corsojava.progettodonazioni.entities.Donor;
import it.corsojava.progettodonazioni.entities.Employee;
import it.corsojava.progettodonazioni.entities.Receiver;
import it.corsojava.progettodonazioni.enumerator.*;
import it.corsojava.progettodonazioni.repositories.*;
import it.corsojava.progettodonazioni.request.DonationSaveRequest;
import it.corsojava.progettodonazioni.request.DonationUpdateRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

import static it.corsojava.progettodonazioni.costants.Costant.NOT_AUTHORIZED;
import static it.corsojava.progettodonazioni.costants.Costant.NOT_FOUND;

@Service
public class DonationService {

    @Autowired
    DonationRepository donationRepository;

    @Autowired
    DonorService donorService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    DonationCenterService donationCenterService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ReceiverService receiverService;

    public Donation saveDonation(DonationSaveRequest request) {
        Donation donation = new Donation();
        donation.setStatus(Status.SCHEDULED);
        donation.setDate(LocalDate.now());
        Donor donor = donorService.findDonorById(request.getDonorId());

        if ((donor.getSex().equals("M") && ChronoUnit.MONTHS.between(donor.getLastDonationDate(),LocalDate.now())<3) ||
                (donor.getSex().equals("F") && ChronoUnit.MONTHS.between(donor.getLastDonationDate(),LocalDate.now())<6)) {
            donation.setStatus(Status.REFUSED);
            return donationRepository.save(donation);
        } else {
            donation.setDonor(donorService.findDonorById(request.getDonorId()));
            donation.setDoctor(doctorService.findDoctorById(request.getDoctorId()));
            donation.setDonationCenter(donationCenterService.findDonationCenterById(request.getDonationCenterId()));
        }
        DonationCenter donationCenter = donationCenterService.findDonationCenterById(request.getDonationCenterId());
        int totalDonations = donationCenter.getTotalDonations();
        totalDonations++;
        donationCenter.setTotalDonations(totalDonations);
        donationCenterService.saveDonationCenter(donationCenter);
        return donationRepository.save(donation);
    }

    public Donation findDonationById(long id) {
        if (donationRepository.existsById(id)) {
            return donationRepository.getById(id);
        } else {
            throw new EntityNotFoundException(NOT_FOUND);
        }
    }

    public void getdonationBycompatible(long idReceiver) {
        Receiver receiver = receiverService.findReceiverById(idReceiver);
        RH recRh = receiver.getRh();
        BloodType recBloodType = receiver.getBloodType();
        List<Donation> allDonations = donationRepository.findAll();
        List<Donation> newList = new ArrayList<>();
        try {
        for (Donation donation : allDonations) {
            BloodType donBloodType = donation.getDonor().getBloodType();
            RH donRh = donation.getDonor().getRh();
            if (donation.isAvailability() && donation.getStatus().equals(Status.COMPLETED)) {
                if (recBloodType == donBloodType && recRh == donRh || recBloodType == donBloodType && donRh == RH.NEGATIVE) {
                    newList.add(donation);
                } else if (recBloodType == BloodType.AB && recRh == RH.POSITIVE ||
                        donBloodType == BloodType.O && donRh == RH.NEGATIVE) {
                    newList.add(donation);
                } else if (recBloodType == BloodType.AB && donRh == RH.NEGATIVE ||
                        donBloodType == BloodType.O && recRh == RH.POSITIVE) {
                    if (recRh == donRh) {
                        newList.add(donation);
                    }
                }
            } else {
                System.out.println(NOT_AUTHORIZED);
            }
            Donation donationCompatible = newList.getFirst();
            donationCompatible.setReceiver(receiver);
            donationCompatible.setStatus(Status.ASSIGNED);
            donationCompatible.setAvailability(false);
            donationRepository.save(donationCompatible);
        }
    } catch (Exception e) {
            System.out.println(NOT_FOUND);
        }
    }

    public Donation updateDonation(DonationUpdateRequest request) {
        Employee employee = employeeService.findEmployeeById(request.getEmployeeId());
        Donation donation = donationRepository.getById(request.getDonationId());
        if (employee.getRole().equals(Role.BASE) || donation.getStatus().equals(Status.REFUSED)) {
            return donation;
        } else {
            donation.setStatus(Status.COMPLETED);
            donation.setAvailability(true);
            Donor donor = donation.getDonor();
            donor.setNumberOfDonations(donor.getNumberOfDonations()+1);
            donor.setBadge(donor.calculateBadge());
            donorService.saveDonor(donor);
            return donationRepository.save(donation);
        }
    }

    public void deleteDonation(long id) {
        donationRepository.deleteById(id);
    }

    public List<Donation> findAllByIdDoctor(long id) {
        return donationRepository.findByDoctorId(id);
    }

    public List<Donation> findAllByIdDonor(long id) {
        return donationRepository.findByDonorId(id);
    }

    public List<Donation> findAllByIdDonationCenter(long id) {
        return donationRepository.findByDonationCenterId(id);
    }

    public List<Donation> findAllByDate() {
        List<Donation> donations = donationRepository.findAll();
        donations.sort(Comparator.comparing(Donation :: getDate));
        return donations;
    }
}
