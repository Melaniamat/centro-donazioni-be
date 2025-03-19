package it.corsojava.progettodonazioni.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public class DonationUpdateRequest {

    private long employeeId;
    private long donationId;

    public DonationUpdateRequest(long employeeId, long donationId) {
        this.employeeId = employeeId;
        this.donationId = donationId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getDonationId() {
        return donationId;
    }

    public void setDonationId(long donationId) {
        this.donationId = donationId;
    }
}
