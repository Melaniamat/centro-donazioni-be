package it.corsojava.progettodonazioni.DTO.request;

import it.corsojava.progettodonazioni.common.BaseRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static it.corsojava.progettodonazioni.costants.Costant.MANDATORY_FIELD;
import static it.corsojava.progettodonazioni.costants.Costant.INVALID_DATE_FORMAT;

@Data
@EqualsAndHashCode(callSuper = true)
public class DonationRequestDTO extends BaseRequestDTO {

    @NotNull(message = MANDATORY_FIELD)
    private Long receiverId;

    @NotNull(message = MANDATORY_FIELD)
    private Long doctorId;

    @NotNull(message = MANDATORY_FIELD)
    private Long donorId;

    @NotBlank(message = MANDATORY_FIELD)
    private String donationCenter;

    @NotBlank(message = MANDATORY_FIELD)
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = INVALID_DATE_FORMAT)
    private String date;

    @NotBlank(message = MANDATORY_FIELD)
    private String status;

    @NotNull(message = MANDATORY_FIELD)
    private Boolean availability;
}