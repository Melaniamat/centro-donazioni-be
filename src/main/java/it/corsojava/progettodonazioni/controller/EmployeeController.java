package it.corsojava.progettodonazioni.controller;

import it.corsojava.progettodonazioni.entities.Employee;
import it.corsojava.progettodonazioni.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/saveEmployee")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/getById/{id}")
    public Employee getEmployeeById(@PathVariable("id") long id) {
        return employeeService.findEmployeeById(id);
    }

    @GetMapping("/getAll")
    public List<Employee> getAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @PutMapping("/updateEmployee")
    public void updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/updateEmployee")
    public void deleteEmployee(@RequestBody Employee employee) {
        employeeService.deleteEmployee(employee);
    }

}
