package it.corsojava.progettodonazioni.services;

import it.corsojava.progettodonazioni.entities.Donor;
import it.corsojava.progettodonazioni.repositories.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DonorService {

    @Autowired
    DonorRepository donorRepository;

    public Donor saveDonor(Donor donor) {
        Donor donorSaved = donorRepository.save(donor);
        donorSaved.setCode(donorSaved.calculateCode());
        if (donor.isIdoneity() || ChronoUnit.YEARS.between(donor.getBirthDate(),LocalDate.now())>=18) {
            return donorRepository.save(donorSaved);
        } else {
            donorRepository.delete(donorSaved);
            return null;
        }
    }

    public Donor findDonorById(long id) {
        if (donorRepository.existsById(id)) {
            return donorRepository.getById(id);
        } else {
            return null;
        }
    }

    public Donor updateDonor(long id, Donor donor) {
        if (donorRepository.existsById(id)) {
            Donor donorUpdated = donorRepository.getById(id);
            donorUpdated.setName(donor.getName());
            donorUpdated.setSurname(donor.getSurname());
            return donorRepository.save(donorUpdated);
        } else {
            return null;
        }
    }

    public void deleteDonor(Donor donor) {
        donorRepository.delete(donor);
    }

    public List<Donor> findAllDonors() {
        return donorRepository.findAll();
    }

}
