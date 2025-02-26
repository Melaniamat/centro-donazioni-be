package it.corsojava.progettodonazioni.entities;

import it.corsojava.progettodonazioni.enumerator.Status;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "donation_center_id")
    private DonationCenter donationCenter;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private Donor donor;

    @Column
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;

    public Donation(long id, Doctor doctor, DonationCenter donationCenter, LocalDate date, Status status) {
        this.id = id;
        this.doctor = doctor;
        this.donationCenter = donationCenter;
        this.date = date;
        this.status = status;
    }

    public Donation() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public DonationCenter getDonationCenter() {
        return donationCenter;
    }

    public void setDonationCenter(DonationCenter donationCenter) {
        this.donationCenter = donationCenter;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }
}
