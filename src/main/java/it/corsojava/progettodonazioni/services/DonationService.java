package it.corsojava.progettodonazioni.services;

import it.corsojava.progettodonazioni.DTO.request.DonationRequestDTO;
import it.corsojava.progettodonazioni.DTO.response.DonationDTO;
import it.corsojava.progettodonazioni.common.BaseRestService;
import it.corsojava.progettodonazioni.entities.Donation;

import java.util.List;

public interface DonationService extends BaseRestService <DonationDTO, DonationRequestDTO> {
    DonationDTO getDonationByCompatible(long idReceiver);
    List<DonationDTO> findAllByIdDoctor(long id);
    List<DonationDTO> findAllByIdDonor(long id);
    List<DonationDTO> findAllByDate();
}
