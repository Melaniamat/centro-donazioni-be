package it.corsojava.progettodonazioni.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.corsojava.progettodonazioni.common.Person;
import it.corsojava.progettodonazioni.enumerator.BloodType;
import it.corsojava.progettodonazioni.enumerator.RH;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "receivers")
@JsonIgnoreProperties({"hibernteLazyInitializer", "handler"})
public class Receiver extends Person {

    @Enumerated(EnumType.STRING)
    @Column(name = "blood_type")
    private BloodType bloodType;

    @Enumerated(EnumType.STRING)
    @Column(name = "RH")
    private RH rh;

    @OneToOne
    @JoinColumn(name= "donation_id")
    @JsonIgnore
    Donation donation;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String address;

    @Column(name = "CAP", nullable = false)
    private String CAP;



    public Receiver(String name, String surname, String email, String username, String password, BloodType bloodType,RH rh, String location, String address,String CAP, String region ) {
        super(name, surname, email, username, password);
        this.bloodType = bloodType;
        this.rh=rh;
        this.region=region;
        this.address=address;
        this.CAP=CAP;
        this.location=location;
    }

    public Receiver() {
    }

    @Override
    public String calculateCode() {
        return getSurname()+getId();
    }

    public void receiveDonation(Donation donation) {


    }

}



