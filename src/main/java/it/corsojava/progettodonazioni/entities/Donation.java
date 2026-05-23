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
    @JoinColumn(name = "receiver_id")
    @JsonIgnore
    private Receiver receiver;

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

    @Column
    private  boolean availability;

    public Donation(Doctor doctor, DonationCenter donationCenter, Donor donor, LocalDate date, Status status) {
        this.doctor = doctor;
        this.donationCenter = donationCenter;
        this.donor = donor;
        this.date = date;
        this.status = status;
    }



    public Donation() {
    }


}
