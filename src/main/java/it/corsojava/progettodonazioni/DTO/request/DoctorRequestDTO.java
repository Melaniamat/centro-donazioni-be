package it.corsojava.progettodonazioni.DTO.request;

import it.corsojava.progettodonazioni.common.PersonRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import static it.corsojava.progettodonazioni.costants.Costant.MANDATORY_FIELD;

@Data
@EqualsAndHashCode(callSuper = true)
public class DoctorRequestDTO extends PersonRequestDTO {

    @NotBlank(message = MANDATORY_FIELD)
    @Length(min = 10, max = 10)
    private String phoneNumber;

    @NotNull(message = MANDATORY_FIELD)
    private Long donationCenterId;
}