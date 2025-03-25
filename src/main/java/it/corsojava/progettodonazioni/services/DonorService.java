package it.corsojava.progettodonazioni.services;

import it.corsojava.progettodonazioni.entities.Doctor;
import it.corsojava.progettodonazioni.entities.Donor;
import it.corsojava.progettodonazioni.enumerator.BloodType;
import it.corsojava.progettodonazioni.enumerator.Status;
import it.corsojava.progettodonazioni.repositories.DonorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static it.corsojava.progettodonazioni.costants.Costant.NOT_AUTHORIZED;
import static it.corsojava.progettodonazioni.costants.Costant.NOT_FOUND;

@Service
public class DonorService {

    @Autowired
    DonorRepository donorRepository;

    public Donor saveDonor(Donor donor) {
        int age = Period.between(donor.getBirthDate(), LocalDate.now()).getYears();
        double weight = donor.getWeight();
        if (age >= 18 && age <= 60 && weight > 50) {
            Donor donorSaved = donorRepository.save(donor);
            donorSaved.setCode(donorSaved.calculateCode());
            donorSaved.setBadge(donorSaved.calculateBadge());
            return donorRepository.save(donorSaved);
        } else {
            throw new IllegalArgumentException(NOT_AUTHORIZED);
        }
    }


    public Donor findDonorById(long id) {
        if (donorRepository.existsById(id)) {
            return donorRepository.getById(id);
        } else {
            throw new EntityNotFoundException(NOT_FOUND);
        }
    }

    public Donor updateDonor(long id, Donor donor) {
        if (donorRepository.existsById(id)) {
            Donor donorUpdated = donorRepository.getById(id);
            if (donor.getNumberOfDonations() != 0) {
                donorUpdated.setNumberOfDonations(donor.getNumberOfDonations());
                donorUpdated.setBadge(donor.calculateBadge());
            }
            if (donor.getAddress() != null) {
                donorUpdated.setAddress(donor.getAddress());
            }
            if (donor.getRh() != null) {
                donorUpdated.setRh(donorUpdated.getRh());
            }
            if (donor.getLocation() != null) {
                donorUpdated.setLocation(donor.getLocation());
            }
            if (donor.getWeight() != 0) {
                donorUpdated.setWeight(donor.getWeight());
            }
            return donorRepository.save(donorUpdated);
        } else {
            throw new EntityNotFoundException(NOT_FOUND);
        }
    }

    public void deleteDonor(long id) {
        findDonorById(id);
        donorRepository.deleteById(id);
    }

        public List<Donor> findDonorsAlphabetical () {
            List<Donor> donors = donorRepository.findAll();
            donors.sort(Comparator.comparing(Donor::getSurname));
            return donors;
        }

}
