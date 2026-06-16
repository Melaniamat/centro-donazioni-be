package it.corsojava.progettodonazioni.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.corsojava.progettodonazioni.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table (name = "donation_centers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DonationCenter extends BaseEntity {


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String location;

    @Column(name = "CAP", nullable = false)
    private String CAP;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(name = "total_donations")
    private int totalDonations;

    @OneToMany(mappedBy = "donationCenter")
    private List<Doctor> doctors;


    public DonationCenter(String name,String address,String phoneNumber,String CAP,String region, String location) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.CAP = CAP;
        this.region=region;
        this.location = location;
    }

    public DonationCenter() {}



}
