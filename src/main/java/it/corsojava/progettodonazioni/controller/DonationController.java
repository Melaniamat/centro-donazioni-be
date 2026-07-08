package it.corsojava.progettodonazioni.controller;

import it.corsojava.progettodonazioni.DTO.request.DonationRequestDTO;
import it.corsojava.progettodonazioni.DTO.response.DonationDTO;
import it.corsojava.progettodonazioni.common.BaseGenericRestController;
import it.corsojava.progettodonazioni.entities.Donation;
import it.corsojava.progettodonazioni.services.DonationService;
import it.corsojava.progettodonazioni.services.impl.DonationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donation")
public class DonationController extends BaseGenericRestController<DonationDTO, DonationRequestDTO, DonationService> {


    public DonationController(DonationService service) {
        super(service);
    }

    @GetMapping("date")
    public ResponseEntity<List<DonationDTO>> findDonationsByDate() {
        return new ResponseEntity<>(getService().findAllByDate(), HttpStatus.OK);
    }

    @GetMapping("doctorID/{id}")
    public ResponseEntity<List<DonationDTO>> findAllByIdDoctor(@PathVariable long id) {
        return new ResponseEntity<>(getService().findAllByIdDoctor(id), HttpStatus.OK);
    }

    @GetMapping("/donorId/{id}")
    public ResponseEntity<List<DonationDTO>> findAllByDonorId(@PathVariable long id) {
        return new ResponseEntity<>(getService().findAllByIdDonor(id), HttpStatus.OK);
    }


    @GetMapping("/compatible/{id}")
    public ResponseEntity<DonationDTO> getDonationByCompatible(@PathVariable long id){
        return new ResponseEntity<> (getService().getDonationByCompatible(id), HttpStatus.OK);

    }

}

