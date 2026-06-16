package it.corsojava.progettodonazioni.common;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import static it.corsojava.progettodonazioni.costants.Costant.MANDATORY_FIELD;

@Data
public  abstract class PersonDTO extends BaseDTO{

    @NotEmpty(message = MANDATORY_FIELD)
    private String name;
    @NotEmpty(message = MANDATORY_FIELD)
    private String surname;
    @Email(message = "email non valida")
    @NotEmpty(message = MANDATORY_FIELD)
    private String email;
    @NotEmpty
    private String username;


}
