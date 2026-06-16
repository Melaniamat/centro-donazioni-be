package it.corsojava.progettodonazioni.controller;

import it.corsojava.progettodonazioni.entities.Donor;
import it.corsojava.progettodonazioni.services.impl.DonorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donor")
public class DonorController {

    @Autowired
    DonorServiceImpl donorService;

    @PostMapping("/save")
    public Donor saveDonor(@RequestBody Donor donor) {
        return donorService.saveDonor(donor);
    }

    @GetMapping("/getById/{id}")
    public Donor getDonorById(@PathVariable long id) {
        return donorService.findDonorById(id);
    }

    @GetMapping("/getAllAlphabetical")
    public List<Donor> finnDonors() {
        return donorService.findDonorsAlphabetical();
    }

    @PutMapping("/update/{id}")
    public Donor updateDonor(@PathVariable long id, @RequestBody Donor donor) {
        return donorService.updateDonor(id,donor);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDonor(@PathVariable long id) {
        donorService.deleteDonor(id);
    }



}
