package it.corsojava.progettodonazioni.services;

import it.corsojava.progettodonazioni.entities.Doctor;
import it.corsojava.progettodonazioni.entities.Donation;
import it.corsojava.progettodonazioni.entities.DonationCenter;
import it.corsojava.progettodonazioni.entities.Donor;
import it.corsojava.progettodonazioni.repositories.DoctorRepository;
import it.corsojava.progettodonazioni.repositories.DonationRepository;
import it.corsojava.progettodonazioni.repositories.DonorRepository;
import it.corsojava.progettodonazioni.request.DonationSaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonationService {

    @Autowired
    DonationRepository donationRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    DonorRepository donorRepository;

    @Autowired
    DonationRepository donationCenterRepository;

    public Donation saveDonation(DonationSaveRequest request, Donation donation) {

        Doctor doctor = doctorRepository.getById(request.getDoctorId());
        Donor donor = donorRepository.getById(request.getDonorId());
        DonationCenter center = donationCenterRepository.getById(request.getCenterId()).getDonationCenter();
        return donationRepository.save(donation);
    }

}
