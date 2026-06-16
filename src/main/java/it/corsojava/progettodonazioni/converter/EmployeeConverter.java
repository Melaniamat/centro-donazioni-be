package it.corsojava.progettodonazioni.converter;

import it.corsojava.progettodonazioni.DTO.request.EmployeeRequestDTO;
import it.corsojava.progettodonazioni.DTO.response.EmployeeDTO;
import it.corsojava.progettodonazioni.common.BaseConverter;
import it.corsojava.progettodonazioni.entities.Employee;
import it.corsojava.progettodonazioni.enumerator.Role;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter extends BaseConverter<Employee, EmployeeDTO, EmployeeRequestDTO> {

    public EmployeeConverter() {
        super(Employee.class, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO toDto(Employee entity) {
        if (entity == null) return null;
        EmployeeDTO dto = super.toDto(entity);

        if (entity.getRole() != null) {
            dto.setRole(entity.getRole().name());
        }
        return dto;
    }

    @Override
    public Employee requestToEntity(EmployeeRequestDTO request) {
        if (request == null) return null;
        Employee entity = super.requestToEntity(request);

        if (request.getRole() != null) {
            entity.setRole(Role.valueOf(request.getRole().toUpperCase()));
        }
        return entity;
    }

    @Override
    public Employee toEntity(EmployeeDTO dto) {
        if (dto == null) return null;
        Employee entity = super.toEntity(dto);

        if (dto.getRole() != null) {
            entity.setRole(Role.valueOf(dto.getRole().toUpperCase()));
        }
        return entity;
    }
}