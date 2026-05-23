package it.corsojava.progettodonazioni.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "doctors")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Doctor extends Person {

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "donation_center_id")
    @JsonIgnore
    private DonationCenter donationCenter;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Donation> donations;

    public Doctor(String name,String surname,String email,String username,String password,String phoneNumber) {
        super(name, surname, email, username, password);
        this.phoneNumber = phoneNumber;
    }

    public Doctor() {
    }

    @Override
    public String calculateCode() {
        return getSurname()+getId();
    }

}
