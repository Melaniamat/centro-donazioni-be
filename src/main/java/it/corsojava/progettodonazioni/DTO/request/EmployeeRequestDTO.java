package it.corsojava.progettodonazioni.DTO.request;

import it.corsojava.progettodonazioni.common.PersonRequestDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static it.corsojava.progettodonazioni.costants.Costant.MANDATORY_FIELD;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmployeeRequestDTO extends PersonRequestDTO {

    @NotBlank(message = MANDATORY_FIELD)
    private String role;
}