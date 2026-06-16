package it.corsojava.progettodonazioni.controller;

import it.corsojava.progettodonazioni.entities.DonationCenter;
import it.corsojava.progettodonazioni.services.impl.DonationCenterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donationcenter")
public class DonationCenterController {

    @Autowired
    DonationCenterServiceImpl donationCenterService;

    @PostMapping("/save")
    public DonationCenter saveDonationCenter(@RequestBody DonationCenter donationCenter) {
        return donationCenterService.saveDonationCenter(donationCenter);
    }

    @GetMapping("/getById/{id}")
    public DonationCenter findDonationCenterById(@PathVariable long id) {
        return donationCenterService.findDonationCenterById(id);
    }

    @GetMapping("/getListByLocation/{location}")
    public List<DonationCenter> findDonationCenterListByLocation(@PathVariable String location) {
        return donationCenterService.findDonationCenterListByLocation(location);
    }

    @GetMapping("/getListByRegion/{region}")
    public List<DonationCenter> findDonationCenterListByRegion(@PathVariable String region) {
        return donationCenterService.findDonationCenterListByRegion(region);
    }

    @PutMapping("/update/{id}")
    public DonationCenter updateDonationCenter(@PathVariable long id, @RequestBody DonationCenter donationCenter) {
        return donationCenterService.updateDonationCenter(id,donationCenter);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCenter(@PathVariable long id) {
        donationCenterService.deleteDonationCenter(id);
    }

    @GetMapping("/getAllAlphabetical")
    public List<DonationCenter> findDonationCenters() {
        return donationCenterService.findDonationCenterListAlphabetical();
    }

}
