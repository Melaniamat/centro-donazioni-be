package it.corsojava.progettodonazioni.DTO.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DonationSaveRequest {

    private long doctorId;
    private long donorId;
    private long donationCenterId;

    public DonationSaveRequest(long doctorId,long donorId,long donationCenterId) {
        this.doctorId = doctorId;
        this.donorId = donorId;
        this.donationCenterId = donationCenterId;
    }

}
