package it.corsojava.progettodonazioni.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "donation_centers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DonationCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String address;
    @Column
    private String CAP;

    @Column
    private String location;

    @Column
    private String phoneNumber;

    @OneToMany(mappedBy = "donationCenter", fetch = FetchType.EAGER)
    private List<Doctor> doctors;

    @OneToMany(mappedBy = "donationCenter", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Donation> donations;

    public DonationCenter(String name,String address,String phoneNumber,String CAP, String location) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.CAP = CAP;
        this.location = location;
    }

    public DonationCenter() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public String getCAP() {
        return CAP;
    }

    public void setCAP(String CAP) {
        this.CAP = CAP;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
