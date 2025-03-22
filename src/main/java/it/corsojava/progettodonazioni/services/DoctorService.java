package it.corsojava.progettodonazioni.services;

import it.corsojava.progettodonazioni.entities.Doctor;
import it.corsojava.progettodonazioni.entities.DonationCenter;
import it.corsojava.progettodonazioni.repositories.DoctorRepository;
import it.corsojava.progettodonazioni.repositories.DonationCenterRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static it.corsojava.progettodonazioni.costants.Costant.NOT_FOUND;

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
            throw new EntityNotFoundException(NOT_FOUND);
        }
    }

    public Doctor updateDoctor(long id, Doctor doctor) {
        if (doctorRepository.existsById(id)) {
            Doctor doctorToUpdate = doctorRepository.getById(id);
            if (null != doctor.getEmail()) {
                doctorToUpdate.setEmail(doctor.getEmail());
            }
            if (null != doctor.getPhoneNumber()) {
                doctorToUpdate.setPhoneNumber(doctor.getPhoneNumber());
            }
            if (null != doctor.getUsername()) {
                doctorToUpdate.setUsername(doctor.getUsername());
            }
            return doctorRepository.save(doctorToUpdate);
        } else {
            throw new EntityNotFoundException(NOT_FOUND);
        }
    }

    public void deleteDoctor(long id) {
        Doctor doctorToDelete = doctorRepository.getById(id);
        doctorRepository.delete(doctorToDelete);
    }

    public List<Doctor> getDoctorsAlphabetical() {
        List<Doctor> doctors = doctorRepository.findAll();
        doctors.sort(Comparator.comparing(Doctor::getSurname));
        return doctors;
    }

}
