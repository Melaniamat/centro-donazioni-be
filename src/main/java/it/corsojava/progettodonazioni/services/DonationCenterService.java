package it.corsojava.progettodonazioni.services;

import it.corsojava.progettodonazioni.entities.DonationCenter;
import it.corsojava.progettodonazioni.repositories.DonationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
          return null;
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
            return null;
        }
    }

    public void deleteDonationCenter(long id) {
        donationCenterRepository.deleteById(id);
    }

}
