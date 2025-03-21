package it.corsojava.progettodonazioni.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.corsojava.progettodonazioni.enumerator.Badge;
import it.corsojava.progettodonazioni.enumerator.BloodType;
import it.corsojava.progettodonazioni.enumerator.RH;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Donor extends Person {
    @Column
    private String sex;
    @Enumerated(EnumType.STRING)
    @Column
    private BloodType bloodType;
    @Column
    private LocalDate birthdate;
    @Column
    private String address;
    @Column
    private String CAP;
    @Column
    private String location;
    @Column
    private LocalDate lastDonationDate;
    @Column
    private boolean abilitated;
    @Column
    private int numberOfDonations;

    @Enumerated(EnumType.STRING)
    @Column
    private Badge badge;

    @Column
    private double weight;

    @Column
    private LocalDate subscriptionDate;

    @OneToMany(mappedBy = "donor", fetch = FetchType.EAGER)
    List<Donation> donationList;

    @Enumerated
    @Column (name = "RH")
    private RH rh;


    public Donor(BloodType bloodType, String name, String surname, String email, String username, String password, String sex,
                 LocalDate birthdate, LocalDate lastDonationDate, boolean abilitated, int numberOfDonations, double weight,
                 Badge badge,RH rh,String address, String CAP, String location) {
        super(name, surname, email, username, password);
        this.bloodType = bloodType;
        this.sex = sex;
        this.birthdate = birthdate;
        this.lastDonationDate = lastDonationDate;
        this.abilitated = abilitated;
        this.numberOfDonations = numberOfDonations;
        this.badge = badge;
        this.weight = weight;
        this.rh=rh;
        this.address=address;
        this.CAP=CAP;
        this.location=location;

    }


    public Donor() {
    }



    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public LocalDate getBirthDate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public LocalDate getLastDonationDate() {
        return lastDonationDate;
    }

    public void setLastDonationDate(LocalDate lastDonationDate) {
        this.lastDonationDate = lastDonationDate;
    }

    public boolean isAbilitated() {
        return abilitated;
    }

    public void setAbilitated(boolean abilitated) {
        this.abilitated = abilitated;
    }

    public List<Donation> getDonationList() {
        return donationList;
    }

    public void setDonationList(List<Donation> donationList) {
        this.donationList = donationList;
    }

    public int getNumberOfDonations() {
        return numberOfDonations;
    }

    public void setNumberOfDonations(int numberOfDonations) {
        this.numberOfDonations = numberOfDonations;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public LocalDate getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(LocalDate subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public RH getRh() {
        return rh;
    }

    public void setRh(RH rh) {
        this.rh = rh;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @Override
    public String calculateCode() {
        return this.getSurname() + this.getId();
    }

    public Badge calculateBadge() {
        if (Period.between(this.subscriptionDate, LocalDate.now()).getYears() >= 3 && this.numberOfDonations >= 6
                || this.numberOfDonations >= 8) {
            this.setBadge(Badge.COPPER);
        } else if (Period.between(this.subscriptionDate, LocalDate.now()).getYears() >= 5 && this.numberOfDonations >= 12
                || this.numberOfDonations >= 16) {
            this.setBadge(Badge.SILVER);
        } else if (Period.between(this.subscriptionDate, LocalDate.now()).getYears() >= 10 && this.numberOfDonations >= 24
                || this.numberOfDonations >= 36) {
            this.setBadge(Badge.GOLD);
        } else if (Period.between(this.subscriptionDate, LocalDate.now()).getYears() >= 20 && this.numberOfDonations >= 40
                || this.numberOfDonations >= 50) {
            this.setBadge(Badge.PLATINUM);
        } else if (Period.between(this.subscriptionDate, LocalDate.now()).getYears() >= 30 && this.numberOfDonations >= 60
                || this.numberOfDonations >= 75) {
            this.setBadge(Badge.RUBY);
        } else if (Period.between(this.subscriptionDate, LocalDate.now()).getYears() >= 40 && this.numberOfDonations >= 80
                || this.numberOfDonations >= 100) {
            this.setBadge(Badge.EMERALD);
        } else if (Period.between(this.birthdate, LocalDate.now()).getYears() > 60 && this.numberOfDonations >= 120) {
            this.setBadge(Badge.DIAMOND);
            this.setAbilitated(false);
        }else {
            this.setBadge(Badge.ND);
        }
        return badge;
    }
}

