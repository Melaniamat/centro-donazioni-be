package it.corsojava.progettodonazioni.common;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import static it.corsojava.progettodonazioni.costants.Costant.MANDATORY_FIELD;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class PersonRequestDTO extends BaseRequestDTO {

    @NotEmpty(message = MANDATORY_FIELD)
    private String name;

    @NotEmpty(message = MANDATORY_FIELD)
    private String surname;

    @Email(message = "email non valida")
    @NotEmpty(message = MANDATORY_FIELD)
    private String email;

    @NotEmpty(message = MANDATORY_FIELD)
    private String username;

    @NotBlank(message = MANDATORY_FIELD)
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!_])(?=\\S+$).{8,20}$",
            message = "Password must contain at least 8 characters, including one uppercase, one lowercase, one digit, and one special character"
    )
    private String password;
}