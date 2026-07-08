package it.corsojava.progettodonazioni.services;

import it.corsojava.progettodonazioni.DTO.request.EmployeeRequestDTO;
import it.corsojava.progettodonazioni.DTO.response.EmployeeDTO;
import it.corsojava.progettodonazioni.common.BaseRestService;
import it.corsojava.progettodonazioni.entities.Employee;

import java.util.List;

public interface EmployeeService extends BaseRestService<EmployeeDTO, EmployeeRequestDTO>{
    List<EmployeeDTO> findEmployeesAlphabetical();
}
