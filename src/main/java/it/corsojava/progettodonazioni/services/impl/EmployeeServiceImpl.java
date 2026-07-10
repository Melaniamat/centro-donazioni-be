package it.corsojava.progettodonazioni.services.impl;

import it.corsojava.progettodonazioni.DTO.request.EmployeeRequestDTO;
import it.corsojava.progettodonazioni.DTO.response.EmployeeDTO;
import it.corsojava.progettodonazioni.common.BaseConverter;
import it.corsojava.progettodonazioni.common.BaseGenericRestService;
import it.corsojava.progettodonazioni.common.BasePersonService;
import it.corsojava.progettodonazioni.entities.Employee;
import it.corsojava.progettodonazioni.repositories.EmployeeRepository;
import it.corsojava.progettodonazioni.services.EmployeeService;
import it.corsojava.progettodonazioni.utils.RepositoryUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static it.corsojava.progettodonazioni.costants.Costant.NOT_FOUND;

@Service
public class EmployeeServiceImpl extends BasePersonService<Employee, EmployeeDTO, EmployeeRequestDTO,EmployeeRepository> implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    protected EmployeeServiceImpl(EmployeeRepository repository, BaseConverter<Employee, EmployeeDTO, EmployeeRequestDTO> converter) {
        super(repository, converter, Employee.class);
    }

    public Employee findEmployeeById(long id) {
        return RepositoryUtils.findOrThrow(getRepository(),id,getEntityClass());
    }


    @Override
    public List<EmployeeDTO> findEmployeesAlphabetical() {
        List<Employee> employees = employeeRepository.findAll();
        employees.sort(Comparator.comparing(Employee :: getSurname));
        return getConverter().toDtoList(employees);
    }

}
