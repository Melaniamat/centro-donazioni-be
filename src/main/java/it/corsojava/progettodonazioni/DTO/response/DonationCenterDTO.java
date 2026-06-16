package it.corsojava.progettodonazioni.DTO.response;

import it.corsojava.progettodonazioni.common.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class DonationCenterDTO extends BaseDTO {

    private String name;
    private String address;
    private String location;
    private String CAP;
    private String region;
    private String phoneNumber;
    private int totalDonations;
    private List<DoctorDTO> doctors;
}