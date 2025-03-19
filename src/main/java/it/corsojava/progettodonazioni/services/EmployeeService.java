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
        Employee employeeSaved = employeeRepository.save(employee);
        employeeSaved.setCode(employee.calculateCode());
        return updateEmployee(employeeSaved.getId(),employeeSaved);
    }

    public Employee updateEmployee(long id,Employee employee) {
        Employee updatedEmployee = employeeRepository.getById(id);
        updatedEmployee.setName(employee.getName());
        updatedEmployee.setSurname(employee.getSurname());
        return employeeRepository.save(updatedEmployee);
    }

    public Employee findEmployeeById(long id) {
        return employeeRepository.getById(id);
    }

    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

}
