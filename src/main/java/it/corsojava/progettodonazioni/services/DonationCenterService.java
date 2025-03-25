package it.corsojava.progettodonazioni.services;

import it.corsojava.progettodonazioni.entities.DonationCenter;
import it.corsojava.progettodonazioni.repositories.DonationCenterRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static it.corsojava.progettodonazioni.costants.Costant.NOT_FOUND;

@Service
public class DonationCenterService {

    @Autowired
    DonationCenterRepository donationCenterRepository;

    public DonationCenter saveDonationCenter(DonationCenter donationCenter) {
        return donationCenterRepository.save(donationCenter);
    }

    public DonationCenter findDonationCenterById(long id) {
        if (donationCenterRepository.existsById(id)) {
            return donationCenterRepository.getById(id);
        } else {
            throw new EntityNotFoundException(NOT_FOUND);
        }
    }

    public DonationCenter updateDonationCenter(long id, DonationCenter donationCenter) {
        if (donationCenterRepository.existsById(id)) {
            DonationCenter donationCenterUpdate = donationCenterRepository.getById(id);
            if (donationCenter.getPhoneNumber() != null) {
                donationCenterUpdate.setPhoneNumber(donationCenter.getPhoneNumber());
            }
            if (donationCenter.getAddress() != null) {
                donationCenterUpdate.setAddress(donationCenter.getAddress());
            }
            return donationCenterRepository.save(donationCenterUpdate);
        } else {
            throw new EntityNotFoundException(NOT_FOUND);
        }
    }

    public void deleteDonationCenter(long id) {
        DonationCenter donationCenterToDelete = donationCenterRepository.getById(id);
        donationCenterRepository.delete(donationCenterToDelete);
    }

    public List<DonationCenter> findDonationCenterListByLocation(String location) {
        return donationCenterRepository.findListByLocation(location);
    }

    public List<DonationCenter> findDonationCenterListByRegion(String region) {
        return donationCenterRepository.findListByRegion(region);
    }

    public List<DonationCenter> findDonationCenterListAlphabetical() {
        List<DonationCenter> donationCenters = donationCenterRepository.findAll();
        donationCenters.sort(Comparator.comparing(DonationCenter :: getName));
        return donationCenters;
    }
}
