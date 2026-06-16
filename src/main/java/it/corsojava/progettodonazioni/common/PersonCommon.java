package it.corsojava.progettodonazioni.common;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import static it.corsojava.progettodonazioni.costants.Costant.MANDATORY_FIELD;

/**
 * Pure data container holding human profile variables shared across boundaries.
 * Contains NO ID and NO password to guarantee perfect isolation.
 */
@Data
public abstract class PersonCommon {

    @NotEmpty(message = MANDATORY_FIELD)
    private String name;

    @NotEmpty(message = MANDATORY_FIELD)
    private String surname;

    @Email(message = "email non valida")
    @NotEmpty(message = MANDATORY_FIELD)
    private String email;

    @NotEmpty(message = MANDATORY_FIELD)
    private String username;
}