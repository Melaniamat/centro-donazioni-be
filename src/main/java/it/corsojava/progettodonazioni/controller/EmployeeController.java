package it.corsojava.progettodonazioni.controller;

import it.corsojava.progettodonazioni.entities.Employee;
import it.corsojava.progettodonazioni.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/save")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/getById/{id}")
    public Employee getEmployeeById(@PathVariable long id) {
        return employeeService.findEmployeeById(id);
    }

    @GetMapping("/getAll")
    public List<Employee> getAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @PutMapping("/update/{id}")
    public Employee updateEmployee(@PathVariable long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id,employee);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployee(id);
    }

}
