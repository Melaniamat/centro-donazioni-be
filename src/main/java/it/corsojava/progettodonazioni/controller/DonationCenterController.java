package it.corsojava.progettodonazioni.controller;

import it.corsojava.progettodonazioni.DTO.request.DonationCenterRequestDTO;
import it.corsojava.progettodonazioni.DTO.response.DonationCenterDTO;
import it.corsojava.progettodonazioni.common.BaseGenericRestController;
import it.corsojava.progettodonazioni.entities.DonationCenter;
import it.corsojava.progettodonazioni.services.DonationCenterService;
import it.corsojava.progettodonazioni.services.impl.DonationCenterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donationcenter")
public class DonationCenterController  extends BaseGenericRestController<DonationCenterDTO, DonationCenterRequestDTO, DonationCenterService> {


    public DonationCenterController(DonationCenterService service) {
        super(service);
    }



    @GetMapping("/location/{location}")
    public ResponseEntity<List<DonationCenterDTO>> findDonationCenterListByLocation(@PathVariable String location) {
        return new ResponseEntity<>(getService().findDonationCenterListByLocation(location), HttpStatus.OK);
    }

    @GetMapping("/region/{region}")
    public ResponseEntity<List<DonationCenterDTO>> findDonationCenterListByRegion(@PathVariable String region) {
        return new ResponseEntity<>(getService().findDonationCenterListByRegion(region), HttpStatus.OK);
    }



    @GetMapping("/alphabetical")
    public ResponseEntity<List<DonationCenterDTO>> findDonationCenters() {
        return new ResponseEntity<>(getService().findDonationCenterListAlphabetical(),HttpStatus.OK);
    }

}
