package it.corsojava.progettodonazioni.DTO.response;

import it.corsojava.progettodonazioni.common.PersonDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmployeeDTO extends PersonDTO {

    private String role;
}