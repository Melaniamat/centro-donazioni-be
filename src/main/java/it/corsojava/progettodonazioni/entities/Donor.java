package it.corsojava.progettodonazioni.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.corsojava.progettodonazioni.common.Person;
import it.corsojava.progettodonazioni.enumerator.Badge;
import it.corsojava.progettodonazioni.enumerator.BloodType;
import it.corsojava.progettodonazioni.enumerator.RH;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "donors")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Donor extends Person {

    @Column(nullable = false)
    private String sex;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BloodType bloodType;

    @Enumerated(EnumType.STRING)
    @Column (name = "RH",nullable = false)
    private RH rh;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column
    private String address;

    @Column(nullable = false)
    private String location;

    @Column(name = "CAP", nullable = false)
    private String CAP;

    @Column
    private LocalDate lastDonationDate;

    @Column(nullable = false)
    private boolean abilitated;

    @Column
    private int numberOfDonations;

    @Enumerated(EnumType.STRING)
    @Column
    private Badge badge;

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false)
    private LocalDate subscriptionDate;

    @OneToMany(mappedBy = "donor", fetch = FetchType.EAGER)
    @JsonIgnore
    List<Donation> donationList;




    public Donor(BloodType bloodType, String name, String surname, String email, String username, String password,
                 String sex, LocalDate birthdate, LocalDate lastDonationDate, boolean abilitated, int numberOfDonations,
                 double weight, RH rh, String address, String location,String CAP) {
        super(name, surname, email, username, password);
        this.bloodType = bloodType;
        this.sex = sex;
        this.birthdate = birthdate;
        this.lastDonationDate = lastDonationDate;
        this.abilitated = abilitated;
        this.numberOfDonations = numberOfDonations;
        this.weight = weight;
        this.rh=rh;
        this.address=address;
        this.CAP=CAP;
        this.location=location;


    }


    public Donor() {
    }


    public LocalDate getBirthDate() {
        return birthdate;
    }

    public Badge calculateBadge() {
        int yearsOfSubscription = Period.between(this.subscriptionDate,LocalDate.now()).getYears();
        if ((Period.between(this.birthdate, LocalDate.now()).getYears() == 60 && this.numberOfDonations >= 120)) {
            this.setBadge(Badge.DIAMOND);
            this.setAbilitated(false);
        } else if (yearsOfSubscription >= 40 && this.numberOfDonations >= 80
                || this.numberOfDonations >= 100) {
            this.setBadge(Badge.EMERALD);
        } else if (yearsOfSubscription >= 30 && this.numberOfDonations >= 60
                || this.numberOfDonations >= 75) {
            this.setBadge(Badge.RUBY);
        } else if (yearsOfSubscription >= 20 && this.numberOfDonations >= 40
                || this.numberOfDonations >= 50) {
            this.setBadge(Badge.PLATINUM);
        } else if (yearsOfSubscription >= 10 && this.numberOfDonations >= 24
                || this.numberOfDonations >= 36) {
            this.setBadge(Badge.GOLD);
        } else if (yearsOfSubscription >= 5 && this.numberOfDonations >= 12
                || this.numberOfDonations >= 16) {
            this.setBadge(Badge.SILVER);
        } else if (yearsOfSubscription >= 3 && this.numberOfDonations >= 6
                || this.numberOfDonations >= 8) {
            this.setBadge(Badge.COPPER);
        } else {
            this.setBadge(Badge.ND);
        }
        return badge;
    }


}

