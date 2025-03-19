package it.corsojava.progettodonazioni.services;

import it.corsojava.progettodonazioni.entities.Doctor;
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
        if (ChronoUnit.YEARS.between(donor.getBirthDate(), LocalDate.now())<18) {
            return null;
        } else {
            Donor donorSaved = donorRepository.save(donor);
            donorSaved.setCode(donorSaved.calculateCode());
            donorSaved.setBadge(donorSaved.calculateBadge());
            return donorRepository.save(donorSaved);
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

    public void deleteDonor(Donor donor) {
        donorRepository.delete(donor);
    }

    public List<Donor> findAllDonors() {
        return donorRepository.findAll();
    }

}
