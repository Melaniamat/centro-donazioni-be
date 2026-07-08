package it.corsojava.progettodonazioni.services;

import it.corsojava.progettodonazioni.DTO.request.DonationCenterRequestDTO;
import it.corsojava.progettodonazioni.DTO.response.DonationCenterDTO;
import it.corsojava.progettodonazioni.common.BaseRestService;
import it.corsojava.progettodonazioni.entities.DonationCenter;

import java.util.List;

public interface DonationCenterService extends BaseRestService <DonationCenterDTO, DonationCenterRequestDTO> {

    List<DonationCenterDTO> findDonationCenterListByLocation(String location);

    List<DonationCenterDTO> findDonationCenterListByRegion(String region);

    List<DonationCenterDTO> findDonationCenterListAlphabetical();

    DonationCenter findDonationCenterById(long donationCenterId);

    void saveDonationCenter(DonationCenter donationCenter);

    void addDonation(DonationCenter donationCenter);
}
