package it.corsojava.progettodonazioni.DTO.response;

import it.corsojava.progettodonazioni.common.PersonDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReceiverDTO extends PersonDTO {

    private String bloodType;
    private String rh;
    private Long donationId;
    private String location;
    private String region;
    private String address;
    private String CAP;
}