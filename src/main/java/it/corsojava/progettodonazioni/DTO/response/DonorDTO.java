package it.corsojava.progettodonazioni.DTO.response;

import it.corsojava.progettodonazioni.common.PersonDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class DonorDTO extends PersonDTO {

    private String sex;
    private String bloodType;
    private String rh;
    private LocalDate birthdate;
    private String address;
    private String location;
    private String CAP;
    private LocalDate lastDonationDate;
    private boolean habilitated;
    private int numberOfDonations;
    private String badge;
    private double weight;
    private LocalDate subscriptionDate;
    private List<DonationDTO> donations;
}