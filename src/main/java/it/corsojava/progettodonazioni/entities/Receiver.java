package it.corsojava.progettodonazioni.entities;

import it.corsojava.progettodonazioni.enumerator.BloodType;
import jakarta.persistence.*;

@Entity
@Table(name = "receivers")
public class Receiver extends Person {

    @Enumerated(EnumType.STRING)
    @Column(name = "blood_type")
    private BloodType bloodType;

    public Receiver(String name, String surname, String email, String username, String password, BloodType bloodType) {
        super(name, surname, email, username, password);
        this.bloodType = bloodType;
    }

    public Receiver() {
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    @Override
    public String calculateCode() {
        return "";
    }

}
