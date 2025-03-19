package it.corsojava.progettodonazioni.services;

import it.corsojava.progettodonazioni.entities.Doctor;
import it.corsojava.progettodonazioni.entities.DonationCenter;
import it.corsojava.progettodonazioni.repositories.DoctorRepository;
import it.corsojava.progettodonazioni.repositories.DonationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    DonationCenterRepository donationCenterRepository;

    public Doctor saveDoctor(long id, Doctor doctor) {
        DonationCenter donationCenter = donationCenterRepository.getById(id);
        Doctor doctorSaved = doctorRepository.save(doctor);
        doctorSaved.setCode(doctorSaved.calculateCode());
        doctorSaved.setDonationCenter(donationCenter);
        return doctorRepository.save(doctorSaved);
    }

    public Doctor findDoctorById(long id) {
        if (doctorRepository.existsById(id)) {
            return doctorRepository.getById(id);
        } else {
            return null;
        }
    }

}
