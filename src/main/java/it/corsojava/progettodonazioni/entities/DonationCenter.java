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


    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String location;

    @Column
    private String CAP;

    @Column
    private String region;

    @Column
    private String phoneNumber;

    @Column(name = "total_donations")
    private int totalDonations;

    @OneToMany(mappedBy = "donationCenter", fetch = FetchType.EAGER)
    private List<Doctor> doctors;

    @OneToMany(mappedBy = "donationCenter", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Donation> donations;

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
