package it.corsojava.progettodonazioni.controller;

import it.corsojava.progettodonazioni.entities.Donor;
import it.corsojava.progettodonazioni.services.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donor")
public class DonorController {

    @Autowired
    DonorService donorService;

    @PostMapping("/save")
    public Donor saveDonor(@RequestBody Donor donor) {
        return donorService.saveDonor(donor);
    }

    @GetMapping("/getById/{id}")
    public Donor getDonorById(@PathVariable long id) {
        return donorService.findDonorById(id);
    }

    @GetMapping("/getAll")
    public List<Donor> getAllDonors() {
        return donorService.findAllDonors();
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
