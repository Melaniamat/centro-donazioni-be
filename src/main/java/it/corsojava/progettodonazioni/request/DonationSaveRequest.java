package it.corsojava.progettodonazioni.request;

public class DonationSaveRequest {

    private long doctorId;
    private long donorId;
    private long centerId;

    public DonationSaveRequest(long doctorId, long donorId, long centerId) {
        this.doctorId = doctorId;
        this.donorId = donorId;
        this.centerId = centerId;
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

    public long getCenterId() {
        return centerId;
    }

    public void setCenterId(long centerId) {
        this.centerId = centerId;
    }

}
