package it.corsojava.progettodonazioni.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.corsojava.progettodonazioni.enumerator.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "employees")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee extends Person {

    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    public Employee(String name, String surname, String email, String username, String password, Role role) {
        super(name, surname, email, username, password);
        this.role = role;
    }

    public Employee() {
    }

    @Override
    public String calculateCode() {
        return getSurname()+getId();
    }

}
