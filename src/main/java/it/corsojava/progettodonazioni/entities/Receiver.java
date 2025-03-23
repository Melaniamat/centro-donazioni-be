package it.corsojava.progettodonazioni.entities;

import it.corsojava.progettodonazioni.enumerator.BloodType;
import it.corsojava.progettodonazioni.enumerator.RH;
import it.corsojava.progettodonazioni.enumerator.Status;
import jakarta.persistence.*;

import static it.corsojava.progettodonazioni.costants.Costant.NOT_AUTHORIZED;
import static it.corsojava.progettodonazioni.costants.Costant.NOT_FOUND;

@Entity
@Table(name = "receivers")
public class Receiver extends Person {

    @Enumerated(EnumType.STRING)
    @Column(name = "blood_type")
    private BloodType bloodType;

    @Enumerated(EnumType.STRING)
    @Column(name = "RH")
    private RH rh;

    @OneToOne
    @JoinColumn(name= "donation_id")
    Donation donation;

    @Column
    private String location;

    @Column
    private String region;

    @Column
    private String address;

    public Receiver(String name, String surname, String email, String username, String password,
                    BloodType bloodType,RH rh, String location, String address,String region) {
        super(name, surname, email, username, password);
        this.bloodType = bloodType;
        this.rh=rh;
        this.address=address;
        this.region=region;
        this.location=location;
    }

    public Receiver() {
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public RH getRh() {
        return rh;
    }

    public void setRh(RH rh) {
        this.rh = rh;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Donation getDonation() {
        return donation;
    }

    public void setDonation(Donation donation) {
        this.donation = donation;
    }

    @Override
    public String calculateCode() {
        return getSurname()+getId();
    }




    public void receiveDonation(Donation donation) {


    }

}



