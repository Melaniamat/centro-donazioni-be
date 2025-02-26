package it.corsojava.progettodonazioni.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "doctors")
public class Doctor extends Person {

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "donation_center_id")
    private DonationCenter donationCenter;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
    private List<Donation> donations;

    public Doctor(String name,String surname,String email,String username,String password,String phoneNumber,
                  DonationCenter donationCenter) {
        super(name, surname, email, username, password);
        this.phoneNumber = phoneNumber;
        this.donationCenter = donationCenter;
    }

    public Doctor() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public DonationCenter getDonationCenter() {
        return donationCenter;
    }

    public void setDonationCenter(DonationCenter donationCenter) {
        this.donationCenter = donationCenter;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    @Override
    public String calculateCode() {
        return "";
    }

}
