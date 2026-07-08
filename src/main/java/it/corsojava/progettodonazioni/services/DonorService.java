package it.corsojava.progettodonazioni.services;

import it.corsojava.progettodonazioni.DTO.request.DonorRequestDTO;
import it.corsojava.progettodonazioni.DTO.response.DonationDTO;
import it.corsojava.progettodonazioni.DTO.response.DonorDTO;
import it.corsojava.progettodonazioni.common.BaseRestService;
import it.corsojava.progettodonazioni.entities.Donor;

import java.util.List;

public interface DonorService extends BaseRestService<DonorDTO, DonorRequestDTO> {
    List<DonorDTO> findDonorsAlphabetical();
    Donor findDonor(Long id);

}
