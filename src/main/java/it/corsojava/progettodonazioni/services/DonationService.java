package it.corsojava.progettodonazioni.services;

import it.corsojava.progettodonazioni.entities.Donation;
import it.corsojava.progettodonazioni.entities.Donor;
import it.corsojava.progettodonazioni.entities.Employee;
import it.corsojava.progettodonazioni.enumerator.*;
import it.corsojava.progettodonazioni.repositories.*;
import it.corsojava.progettodonazioni.request.DonationSaveRequest;
import it.corsojava.progettodonazioni.request.DonationUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DonationService {

    @Autowired
    DonationRepository donationRepository;

    @Autowired
    DoctorService doctorService;

    @Autowired
    DonorService donorService;

    @Autowired
    DonationCenterService donationCenterService;

    @Autowired
    EmployeeService employeeService;

    public Donation saveDonation(DonationSaveRequest request) {
        Donation donation = new Donation();
        donation.setDate(LocalDate.now());
        Donor donor = donorService.findDonorById(request.getDonorId());
        if ((donor.getSex()=='M' && ChronoUnit.MONTHS.between(donor.getLastDonationDate(),LocalDate.now())<3) ||
                (donor.getSex()=='F' && ChronoUnit.MONTHS.between(donor.getLastDonationDate(),LocalDate.now())<6)) {
            donation.setStatus(Status.REFUSED);
            return donationRepository.save(donation);
        } else {
            donation.setStatus(Status.SCHEDULED);
            donation.setDonor(donorService.findDonorById(request.getDonorId()));
            donation.setDoctor(doctorService.findDoctorById(request.getDoctorId()));
            donation.setDonationCenter(donationCenterService.findDonationCenterById(request.getCenterId()));
            return donationRepository.save(donation);
        }
    }

    public Donation updateDonation(DonationUpdateRequest request) {
        Employee employee = employeeService.findEmployeeById(request.getEmployeeId());
        Donation donation = donationRepository.getById(request.getDonationId());
        if (employee.getRole().equals(Role.BASE)) {
            return donation;
        } else {
            donation.setStatus(Status.COMPLETED);
            Donor donor = donation.getDonor();
            donor.setDonationNumber(donor.getDonationNumber()+1);
            if (donor.getDonationNumber() == 10) {
                donor.setBadge(Badge.SILVER);
            } else if (donor.getDonationNumber() == 20) {
                donor.setBadge(Badge.GOLD);
            }
            donorService.saveDonor(donor);
            return donationRepository.save(donation);
        }
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

}
