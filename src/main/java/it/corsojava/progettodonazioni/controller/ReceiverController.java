package it.corsojava.progettodonazioni.controller;

import it.corsojava.progettodonazioni.DTO.request.ReceiverRequestDTO;
import it.corsojava.progettodonazioni.DTO.response.EmployeeDTO;
import it.corsojava.progettodonazioni.DTO.response.ReceiverDTO;
import it.corsojava.progettodonazioni.common.BaseGenericRestController;
import it.corsojava.progettodonazioni.entities.Receiver;
import it.corsojava.progettodonazioni.services.ReceiverService;
import it.corsojava.progettodonazioni.services.impl.ReceiverServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receiver")
public class  ReceiverController extends BaseGenericRestController<ReceiverDTO, ReceiverRequestDTO, ReceiverService> {


    public ReceiverController(ReceiverService service) {
        super(service);
    }

    @GetMapping("/alphabetical")
    public ResponseEntity<List<ReceiverDTO>> getEmployees() {
        return new ResponseEntity<>(getService().findReceiversAlphabetical(), HttpStatus.OK);
    }





}
