package it.corsojava.progettodonazioni.controller;

import it.corsojava.progettodonazioni.entities.Receiver;
import it.corsojava.progettodonazioni.services.impl.ReceiverServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receiver")
public class ReceiverController {

    @Autowired
    ReceiverServiceImpl receiverService;

    @PostMapping("/save")
    public Receiver saveReceiver(@RequestBody Receiver receiver) {
        return receiverService.saveReceiver(receiver);
    }

    @GetMapping("/getById/{id}")
    public Receiver findReceiverById(@PathVariable long id) {
        return receiverService.findReceiverById(id);
    }

    @GetMapping("/getAllAlphabetical")
    public List<Receiver> findReceivers() {
        return receiverService.findReceiversAlphabetical();
    }

    @PutMapping("/update/{id}")
    public Receiver updateReceiver(@PathVariable long id, @RequestBody Receiver receiver) {
        return receiverService.updateReceiver(id,receiver);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteReceiver(@PathVariable long id) {
        receiverService.deleteReceiver(id);
    }



}
