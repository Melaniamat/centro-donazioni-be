package it.corsojava.progettodonazioni.services;

import it.corsojava.progettodonazioni.entities.Employee;
import it.corsojava.progettodonazioni.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findEmployeeById(long id) {
        return employeeRepository.getReferenceById(id);
    }

    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

}
