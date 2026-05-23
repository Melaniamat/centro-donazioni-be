package it.corsojava.progettodonazioni.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.corsojava.progettodonazioni.enumerator.BloodType;
import it.corsojava.progettodonazioni.enumerator.RH;
import it.corsojava.progettodonazioni.enumerator.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static it.corsojava.progettodonazioni.costants.Costant.NOT_AUTHORIZED;
import static it.corsojava.progettodonazioni.costants.Costant.NOT_FOUND;

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

    @Column
    private String location;
    @Column
    private String region;
    @Column
    private String address;
    @Column
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



