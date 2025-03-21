package it.corsojava.progettodonazioni.controller;

import it.corsojava.progettodonazioni.entities.Doctor;
import it.corsojava.progettodonazioni.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/save/{id}")
    public Doctor saveDoctor(@PathVariable long id, @RequestBody Doctor doctor) {
        return doctorService.saveDoctor(id,doctor);
    }

    @GetMapping("/get/{id}")
    public Doctor findDoctorById(@PathVariable long id) {
        return doctorService.findDoctorById(id);
    }

    @PutMapping("/update/{id}")
    public Doctor updateDoctor(@PathVariable long id, @RequestBody Doctor doctor) {
        return doctorService.updateDoctor(id,doctor);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDoctor(@PathVariable long id) {
        doctorService.deleteDoctor(id);
    }

    @GetMapping("/getAllAlphabetical")
    public List<Doctor> getDoctors() {
        return doctorService.getDoctorsAlphabetical();
    }

}
