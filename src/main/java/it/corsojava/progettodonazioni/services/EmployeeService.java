package it.corsojava.progettodonazioni.services;

import it.corsojava.progettodonazioni.entities.Employee;
import it.corsojava.progettodonazioni.repositories.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static it.corsojava.progettodonazioni.costants.Costant.NOT_FOUND;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        Employee employeeSaved = employeeRepository.save(employee);
        employeeSaved.setCode(employee.calculateCode());
        return updateEmployee(employeeSaved.getId(),employeeSaved);
    }

    public Employee updateEmployee(long id,Employee employee) {
        if (employeeRepository.existsById(id)) {
            Employee updatedEmployee = employeeRepository.getById(id);
            if (employee.getEmail() != null) {
                updatedEmployee.setEmail(employee.getEmail());
            }
            if (employee.getEmail() != null) {
                updatedEmployee.setUsername(employee.getUsername());
            }
            return employeeRepository.save(updatedEmployee);
        } else {
            throw new EntityNotFoundException(NOT_FOUND);
        }
    }

    public Employee findEmployeeById(long id) {
        if (employeeRepository.existsById(id)) {
            return employeeRepository.getById(id);
        } else {
            throw new EntityNotFoundException(NOT_FOUND);
        }
    }

    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> findEmployeesAlphabetical() {
        List<Employee> employees = employeeRepository.findAll();
        employees.sort(Comparator.comparing(Employee :: getSurname));
        return employees;
    }

}
