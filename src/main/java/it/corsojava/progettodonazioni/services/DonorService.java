package it.corsojava.progettodonazioni.services;

import it.corsojava.progettodonazioni.entities.Doctor;
import it.corsojava.progettodonazioni.entities.Donor;
import it.corsojava.progettodonazioni.enumerator.Badge;
import it.corsojava.progettodonazioni.enumerator.Status;
import it.corsojava.progettodonazioni.repositories.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DonorService {

    @Autowired
    DonorRepository donorRepository;
    public Donor saveDonor (Donor donor) {
        LocalDate today = LocalDate.now();
        // Data di oggi
        int age = Period.between(donor.getBirthDate(), today).getYears();
        if (age >= 18 && age <= 60) {
            donor.setAbilitated(true);
        } else {
            donor.setAbilitated(false);
        }
        donor.setCode(donor.calculateCode());
        donor.setBadge(donor.calculateBadge());
        return donorRepository.save(donor);

    }

    public Donor findDonorById(long id) {
        return donorRepository.getById(id);
    }

    public Donor updateDonor(long id, Donor donor) {
        Donor donorUpdated = donorRepository.getById(id);
        donorUpdated.setName(donor.getName());
        donorUpdated.setSurname(donor.getSurname());
        return donorRepository.save(donorUpdated);
    }

    public void deleteDonor(long id) {
        findDonorById(id);
        donorRepository.deleteById(id);
    }

    public List<Donor> findAllDonors() {
        return donorRepository.findAll();
    }

}
