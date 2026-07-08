package it.corsojava.progettodonazioni.controller;

import it.corsojava.progettodonazioni.DTO.request.EmployeeRequestDTO;
import it.corsojava.progettodonazioni.DTO.response.EmployeeDTO;
import it.corsojava.progettodonazioni.common.BaseGenericRestController;
import it.corsojava.progettodonazioni.entities.Employee;
import it.corsojava.progettodonazioni.services.EmployeeService;
import it.corsojava.progettodonazioni.services.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController  extends BaseGenericRestController<EmployeeDTO, EmployeeRequestDTO, EmployeeService> {


    public EmployeeController(EmployeeService service) {
        super(service);
    }

    @GetMapping("/alphabetical")
    public ResponseEntity<List<EmployeeDTO>> getEmployees() {
        return new ResponseEntity<>(getService().findEmployeesAlphabetical(), HttpStatus.OK);
    }

}
