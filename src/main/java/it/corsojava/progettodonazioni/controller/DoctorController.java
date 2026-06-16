package it.corsojava.progettodonazioni.controller;

import it.corsojava.progettodonazioni.DTO.request.DoctorRequestDTO;
import it.corsojava.progettodonazioni.DTO.response.DoctorDTO;
import it.corsojava.progettodonazioni.common.BaseGenericRestController;
import it.corsojava.progettodonazioni.common.BaseRestService;
import it.corsojava.progettodonazioni.entities.Doctor;
import it.corsojava.progettodonazioni.services.DoctorService;
import it.corsojava.progettodonazioni.services.impl.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController extends BaseGenericRestController<DoctorDTO, DoctorRequestDTO,DoctorService> {


    public DoctorController(DoctorService service) {
        super(service);
    }

    @GetMapping("/getAllAlphabetical")
    public ResponseEntity<List<DoctorDTO>> getDoctors() {
        return new ResponseEntity<>(getService().getDoctorsAlphabetical(), HttpStatus.OK) ;
    }

}
