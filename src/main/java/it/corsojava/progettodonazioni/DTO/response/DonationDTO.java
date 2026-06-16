package it.corsojava.progettodonazioni.DTO.response;

import it.corsojava.progettodonazioni.common.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class DonationDTO extends BaseDTO {

    private Long receiverId;
    private Long doctorId;
    private Long donorId;
    private String donationCenter;
    private LocalDate date;
    private String status;
    private boolean availability;
}