package it.corsojava.progettodonazioni.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class DonationUpdateRequest {

    private long employeeId;
    private long donationId;
    
}
