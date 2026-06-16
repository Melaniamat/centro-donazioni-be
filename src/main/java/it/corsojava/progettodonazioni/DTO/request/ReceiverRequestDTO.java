package it.corsojava.progettodonazioni.DTO.request;

import it.corsojava.progettodonazioni.common.PersonRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static it.corsojava.progettodonazioni.costants.Costant.MANDATORY_FIELD;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReceiverRequestDTO extends PersonRequestDTO {

    @NotBlank(message = MANDATORY_FIELD)
    private String bloodType;

    @NotBlank(message = MANDATORY_FIELD)
    private String rh;

    @NotBlank(message = MANDATORY_FIELD)
    private String location;

    @NotBlank(message = MANDATORY_FIELD)
    private String region;

    @NotBlank(message = MANDATORY_FIELD)
    private String address;

    @NotBlank(message = MANDATORY_FIELD)
    private String CAP;

    @NotNull(message = MANDATORY_FIELD)
    private Long donationId;
}