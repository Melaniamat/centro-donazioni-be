package it.corsojava.progettodonazioni.services;

import it.corsojava.progettodonazioni.entities.Donation;
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
        return donationCenterRepository.getById(id);
    }

    public DonationCenter updateDonationCenter(long id, DonationCenter donationCenter) {
        DonationCenter donationCenterUpdate = donationCenterRepository.getById(id);
        donationCenterUpdate.setName(donationCenter.getName());
        donationCenterUpdate.setAdress(donationCenter.getAdress());
        return donationCenterRepository.save(donationCenterUpdate);
    }

    public void deleteDonationCenter(DonationCenter donationCenter) {
        donationCenterRepository.delete(donationCenter);
    }

}
