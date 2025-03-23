package it.corsojava.progettodonazioni.request;

public class DonationSaveRequest {

    private long doctorId;
    private long donorId;
    private long donationCenterId;

    public DonationSaveRequest(long doctorId,long donorId,long donationCenterId) {
        this.doctorId = doctorId;
        this.donorId = donorId;
        this.donationCenterId = donationCenterId;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }

    public long getDonorId() {
        return donorId;
    }

    public void setDonorId(long donorId) {
        this.donorId = donorId;
    }

    public long getDonationCenterId() {
        return donationCenterId;
    }

    public void setDonationCenterId(long donationCenterId) {
        this.donationCenterId = donationCenterId;
    }

}
