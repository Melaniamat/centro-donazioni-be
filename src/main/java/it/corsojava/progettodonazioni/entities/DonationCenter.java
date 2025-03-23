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
    private String location;

    @Column
    private String region;

    @Column
    private String phoneNumber;

    @Column(name = "total_donations")
    private int totalDonations;

    @OneToMany(mappedBy = "donationCenter", fetch = FetchType.EAGER)
    private List<Doctor> doctorList;

    @OneToMany(mappedBy = "donationCenter", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Donation> donationList;

    public DonationCenter(String name,String address,String phoneNumber,String region, String location) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.region = region;
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

    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public List<Donation> getDonationList() {
        return donationList;
    }

    public void setDonationList(List<Donation> donationList) {
        this.donationList = donationList;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotalDonations() {
        return totalDonations;
    }

    public void setTotalDonations(int totalDonations) {
        this.totalDonations = totalDonations;
    }

}
