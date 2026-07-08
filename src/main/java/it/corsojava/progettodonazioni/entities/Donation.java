package it.corsojava.progettodonazioni.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.corsojava.progettodonazioni.common.BaseEntity;
import it.corsojava.progettodonazioni.enumerator.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "donations")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Donation extends BaseEntity {



    @OneToOne
    @JoinColumn(name = "receiver_id",nullable = false)
    @JsonIgnore
    private Receiver receiver;

    @ManyToOne
    @JoinColumn(name = "doctor_id",nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "donor_id",nullable = false)
    private Donor donor;

    @Column(name = "donation_center",nullable = false)
    private String donationCenter;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private  boolean availability;

    public Donation(Doctor doctor, DonationCenter donationCenter, Donor donor, LocalDate date, Status status) {
        this.doctor = doctor;
        this.donor = donor;
        this.date = (date!= null) ? date : LocalDate.now();
        this.status = (status != null) ? status : Status.SCHEDULED;
    }



    public Donation() {
    }


}
