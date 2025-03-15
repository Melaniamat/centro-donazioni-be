package it.corsojava.progettodonazioni.controller;

import it.corsojava.progettodonazioni.entities.DonationCenter;
import it.corsojava.progettodonazioni.services.DonationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/donationcenter")
public class DonationCenterController {

    @Autowired
    DonationCenterService donationCenterService;

    @PostMapping("/save")
    public DonationCenter saveDonationCenter(@RequestBody DonationCenter donationCenter) {
        return donationCenterService.saveDonationCenter(donationCenter);
    }

    @GetMapping("/getById/{id}")
    public DonationCenter findById(@PathVariable long id) {
        return donationCenterService.findDonationCenterById(id);
    }

    @PutMapping("/update/{id}")
    public DonationCenter updateDonationCenter(@PathVariable long id,@RequestBody DonationCenter donationCenter) {
        return donationCenterService.updateDonationCenter(id,donationCenter);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDonationCenter(@PathVariable long id) {
        donationCenterService.deleteDonationCenter(donationCenterService.findDonationCenterById(id));
    }

}
