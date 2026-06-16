package it.corsojava.progettodonazioni.DTO.request;

import it.corsojava.progettodonazioni.common.BaseRequestDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static it.corsojava.progettodonazioni.costants.Costant.MANDATORY_FIELD;

@Data
@EqualsAndHashCode(callSuper = true)
public class DonationCenterRequestDTO extends BaseRequestDTO {

    @NotBlank(message = MANDATORY_FIELD)
    private String name;

    @NotBlank(message = MANDATORY_FIELD)
    private String address;

    @NotBlank(message = MANDATORY_FIELD)
    private String location;

    @NotBlank(message = MANDATORY_FIELD)
    private String CAP;

    @NotBlank(message = MANDATORY_FIELD)
    private String region;

    @NotBlank(message = MANDATORY_FIELD)
    private String phoneNumber;
}