package it.corsojava.progettodonazioni.controller;

import it.corsojava.progettodonazioni.entities.Donation;
import it.corsojava.progettodonazioni.DTO.request.DonationSaveRequest;
import it.corsojava.progettodonazioni.DTO.request.DonationUpdateRequest;
import it.corsojava.progettodonazioni.services.impl.DonationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donation")
public class DonationController {

    @Autowired
    DonationServiceImpl donationService;

    @PostMapping("/save")
    public Donation saveDonation(@RequestBody DonationSaveRequest request) {
        return donationService.saveDonation(request);
    }

    @GetMapping("getById/{id}")
    public Donation findDonationById(@PathVariable long id) {
        return donationService.findDonationById(id);
    }

    @GetMapping("getAllByDate")
    public List<Donation> findDonations() {
        return donationService.findAllByDate();
    }

    @GetMapping("/getByIdDoctor/{id}")
    public List<Donation> findAllByIdDoctor(@PathVariable long id) {
        return donationService.findAllByIdDoctor(id);
    }

    @GetMapping("/getByIdDonor/{id}")
    public List<Donation> findAllByIdDonor(@PathVariable long id) {
        return donationService.findAllByIdDonor(id);
    }


    @PutMapping("/update")
    public Donation updateDonation(@RequestBody DonationUpdateRequest request) {
        return donationService.updateDonation(request);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDonation(@PathVariable long id) {
        donationService.deleteDonation(id);
    }

    @GetMapping("/getDonationByCompatible/{id}")
    public Donation getdonationBycompatible (@PathVariable long id){
        return donationService.getDonationByCompatible(id);

    }

}
