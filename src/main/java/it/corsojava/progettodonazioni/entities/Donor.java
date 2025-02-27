package it.corsojava.progettodonazioni.entities;

import it.corsojava.progettodonazioni.enumerator.Badge;
import it.corsojava.progettodonazioni.enumerator.BloodType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "donors")
public class Donor extends Person {

    @Column
    private char sex;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column
    private boolean idoneity;

    @Enumerated(EnumType.STRING)
    @Column(name = "blood_type")
    private BloodType bloodType;

    @Column(name = "last_donation_date")
    private LocalDate lastDonationDate;

    @Column(name = "donation_number")
    private int donationNumber;

    @Enumerated(EnumType.STRING)
    @Column
    private Badge badge;

    @OneToMany(mappedBy = "donor", fetch = FetchType.EAGER)
    private List<Donation> donations;

    public Donor(String name,String surname,String email,String username,String password,char sex, LocalDate birthDate,
                 LocalDate lastDonationDate, boolean idoneity,BloodType bloodType,int donationNumber,Badge badge) {
        super(name, surname, email, username, password);
        this.sex = sex;
        this.birthDate = birthDate;
        this.lastDonationDate = lastDonationDate;
        this.idoneity = idoneity;
        this.bloodType = bloodType;
        this.donationNumber = donationNumber;
        this.badge = badge;
    }

    public Donor() {
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isIdoneity() {
        return idoneity;
    }

    public void setIdoneity(boolean idoneity) {
        this.idoneity = idoneity;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public LocalDate getLastDonationDate() {
        return lastDonationDate;
    }

    public void setLastDonationDate(LocalDate lastDonationDate) {
        this.lastDonationDate = lastDonationDate;
    }

    public int getDonationNumber() {
        return donationNumber;
    }

    public void setDonationNumber(int donationNumber) {
        this.donationNumber = donationNumber;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    @Override
    public String calculateCode() {
        return "D-"+getId();
    }

}
