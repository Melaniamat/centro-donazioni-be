package it.corsojava.progettodonazioni.services;

import it.corsojava.progettodonazioni.DTO.request.DoctorRequestDTO;
import it.corsojava.progettodonazioni.DTO.response.DoctorDTO;
import it.corsojava.progettodonazioni.common.BaseRestService;
import it.corsojava.progettodonazioni.entities.Doctor;

import java.util.List;

public interface DoctorService extends BaseRestService<DoctorDTO, DoctorRequestDTO> {
    List<DoctorDTO> getDoctorsAlphabetical();

     // TODO: void assignDonation(long id);
}
