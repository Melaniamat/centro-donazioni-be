package it.corsojava.progettodonazioni.DTO.request;

import it.corsojava.progettodonazioni.common.PersonRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static it.corsojava.progettodonazioni.costants.Costant.MANDATORY_FIELD;
import static it.corsojava.progettodonazioni.costants.Costant.INVALID_DATE_FORMAT;

@Data
@EqualsAndHashCode(callSuper = true)
public class DonorRequestDTO extends PersonRequestDTO {

    @NotBlank(message = MANDATORY_FIELD)
    private String sex;

    @NotBlank(message = MANDATORY_FIELD)
    private String bloodType;

    @NotBlank(message = MANDATORY_FIELD)
    private String rh;

    @NotBlank(message = MANDATORY_FIELD)
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = INVALID_DATE_FORMAT)
    private String birthdate;

    private String address;

    @NotBlank(message = MANDATORY_FIELD)
    private String location;

    @NotBlank(message = MANDATORY_FIELD)
    private String CAP;

    @NotNull(message = MANDATORY_FIELD)
    private Double weight;
}