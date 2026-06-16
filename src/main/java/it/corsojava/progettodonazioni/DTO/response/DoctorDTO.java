package it.corsojava.progettodonazioni.DTO.response;

import it.corsojava.progettodonazioni.common.PersonDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class DoctorDTO extends PersonDTO {
    private String phoneNumber;
    private Long donationCenterId;
    private List<Long> donationIds;
}