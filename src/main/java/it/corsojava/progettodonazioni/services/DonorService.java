package it.corsojava.progettodonazioni.services;

import it.corsojava.progettodonazioni.entities.Donor;
import it.corsojava.progettodonazioni.repositories.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;

@Service
public class DonorService {

    @Autowired
    DonorRepository donorRepository;

    public Donor saveDonor(Donor donor) {
        int age = Period.between(donor.getBirthDate(), LocalDate.now()).getYears();
        if (age >= 18 && age <= 60) {
            Donor donorSaved = donorRepository.save(donor);
            donorSaved.setCode(donorSaved.calculateCode());
            donorSaved.setBadge(donorSaved.calculateBadge());
            return donorRepository.save(donorSaved);
        } else {
            throw new IllegalArgumentException("Eta non valida");
        }
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

    public List<Donor> findDonorsAlphabetical() {
        List<Donor> donors = donorRepository.findAll();
        donors.sort(Comparator.comparing(Donor :: getSurname));
        return donors;
    }

}
