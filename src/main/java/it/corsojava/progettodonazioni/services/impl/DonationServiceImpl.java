package it.corsojava.progettodonazioni.services.impl;

import it.corsojava.progettodonazioni.DTO.request.*;
import it.corsojava.progettodonazioni.DTO.response.DonationDTO;
import it.corsojava.progettodonazioni.common.BaseConverter;
import it.corsojava.progettodonazioni.common.BaseGenericRestService;
import it.corsojava.progettodonazioni.entities.*;
import it.corsojava.progettodonazioni.enumerator.Role;
import it.corsojava.progettodonazioni.enumerator.Status;
import it.corsojava.progettodonazioni.enumerator.*;
import it.corsojava.progettodonazioni.repositories.*;
import it.corsojava.progettodonazioni.services.DonationCenterService;
import it.corsojava.progettodonazioni.services.DonationService;
import it.corsojava.progettodonazioni.services.DonorService;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

import static it.corsojava.progettodonazioni.costants.Costant.NOT_AUTHORIZED;
import static it.corsojava.progettodonazioni.costants.Costant.NOT_FOUND;

@Service
public class DonationServiceImpl extends BaseGenericRestService<Donation, DonationDTO, DonationRequestDTO,DonationRepository> implements DonationService  {


    @Autowired
    DoctorServiceImpl doctorService;

    @Autowired
    EmployeeServiceImpl employeeService;

    @Autowired
    ReceiverServiceImpl receiverService;

    @Autowired
    DonationCenterService donationCenterService;

    @Autowired
    DonorService donorService;

    protected DonationServiceImpl(DonationRepository repository, BaseConverter<Donation, DonationDTO, DonationRequestDTO> converter) {
        super(repository, converter, Donation.class);
    }


    @Override
    public DonationDTO post(DonationRequestDTO dto) {
        return super.post(dto);
    }


    @Override
    public DonationDTO getDonationByCompatible(long idReceiver) {
        Receiver receiver = receiverService.findReceiverById(idReceiver);
        RH recRh = receiver.getRh();
        BloodType recBloodType = receiver.getBloodType();
        List<Donation> allDonations = getRepository().findAll();

        // Creiamo tre liste separate per gestire le priorità reali in ordine medico
        List<Donation> stessaSacca = new ArrayList<>();
        List<Donation> compatibiliStandard = new ArrayList<>();
        List<Donation> ultimaSpiaggiaGruppoO = new ArrayList<>();

        try {
            for (Donation donation : allDonations) {
                if (donation.isAvailability() && donation.getStatus().equals(Status.COMPLETED)) {
                    BloodType donBloodType = donation.getDonor().getBloodType();
                    RH donRh = donation.getDonor().getRh();

                    // PRIORITÀ 1: Identici
                    if (recBloodType == donBloodType && recRh == donRh) {
                        stessaSacca.add(donation);
                    }
                    // PRIORITÀ 2: Compatibili standard (es. AB riceve da A o B, oppure A+ riceve da A-)
                    else if ((recBloodType == donBloodType && donRh == RH.NEGATIVE) ||
                            (recBloodType == BloodType.AB && donRh == RH.NEGATIVE) ||
                            (recBloodType == BloodType.AB && recRh == RH.POSITIVE)) {
                        compatibiliStandard.add(donation);
                    }
                    // PRIORITÀ 3: Donatore Universale O
                    else if (donBloodType == BloodType.O) {
                        if (donRh == RH.NEGATIVE || recRh == RH.POSITIVE) {
                            ultimaSpiaggiaGruppoO.add(donation);
                        }
                    }
                }
            }

            List<Donation> newList = new ArrayList<>();
            newList.addAll(stessaSacca);
            newList.addAll(compatibiliStandard);
            newList.addAll(ultimaSpiaggiaGruppoO);

            if (newList.isEmpty()) {
                throw new EntityNotFoundException(NOT_FOUND);
            }
            Donation donationCompatible = newList.get(0);

            donationCompatible.setReceiver(receiver);
            donationCompatible.setStatus(Status.ASSIGNED);
            receiver.setDonation(donationCompatible);
            donationCompatible.setAvailability(false);

            getRepository().save(donationCompatible);
            return getConverter().toDto(donationCompatible);

        } catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }


    @Override
    public DonationDTO put(DonationRequestDTO dto,@Nonnull Long donationId) {
        Employee employee = employeeService.findEmployeeById(dto.getEmployeeId());
        if (employee.getRole().equals(Role.BASE) ) {
            throw new IllegalArgumentException(NOT_AUTHORIZED);
        } else {
             DonationDTO donationDTO = super.put(dto, donationId);

            if (dto.getDoctorId()!= null && donationDTO.getStatus().equalsIgnoreCase("completed")) {
                Doctor doc= doctorService.findDoctorById(dto.getDoctorId());
                donationCenterService.addDonation(doc.getDonationCenter());
                if (donationDTO.getStatus().equalsIgnoreCase("completed")) {
                   Donor donor= donorService.findDonor(donationDTO.getDonorId());
                   donor.setBadge(donor.calculateBadge());

                }

            }
            return donationDTO;

        }
    }




    @Override
    public List<DonationDTO> findAllByIdDoctor(long id) {
        return getConverter().toDtoList(getRepository().findByDoctorId(id));
    }

    @Override
    public List<DonationDTO> findAllByIdDonor(long id) {
        return  getConverter().toDtoList(getRepository().findByDonorId(id));
    }

    @Override
    public List<DonationDTO> findAllByDate() {
        List<Donation> donations = getRepository().findAll();
        donations.sort(Comparator.comparing(Donation :: getDate));
        return getConverter().toDtoList(donations);
    }

}




