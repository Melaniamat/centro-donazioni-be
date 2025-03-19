package it.corsojava.progettodonazioni.services;

import it.corsojava.progettodonazioni.entities.Doctor;
import it.corsojava.progettodonazioni.entities.Donor;
import it.corsojava.progettodonazioni.repositories.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonorService {

    @Autowired
    DonorRepository donorRepository;

    public Donor saveDonor(Donor donor) {
        Donor donorSaved = donorRepository.save(donor);
        donorSaved.setCode(donorSaved.calculateCode());
        return donorRepository.save(donorSaved);
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
        donorRepository.delete(donorRepository.getById(id));
    }

    public List<Donor> findAllDonors() {
        return donorRepository.findAll();
    }

}
