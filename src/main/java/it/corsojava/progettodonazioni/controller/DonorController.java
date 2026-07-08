package it.corsojava.progettodonazioni.controller;

import it.corsojava.progettodonazioni.DTO.request.DonorRequestDTO;
import it.corsojava.progettodonazioni.DTO.response.DonorDTO;
import it.corsojava.progettodonazioni.common.BaseGenericRestController;
import it.corsojava.progettodonazioni.entities.Donor;
import it.corsojava.progettodonazioni.services.DonorService;
import it.corsojava.progettodonazioni.services.impl.DonorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donor")
public class DonorController extends BaseGenericRestController<DonorDTO, DonorRequestDTO, DonorService> {



    public DonorController(DonorService service) {
        super(service);
    }


    @GetMapping("/alphabetical")
    public ResponseEntity<List<DonorDTO>> findDonors() {
        return new ResponseEntity<>(getService().findDonorsAlphabetical(), HttpStatus.OK);
    }




}
