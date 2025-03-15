package it.corsojava.progettodonazioni.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.corsojava.progettodonazioni.enumerator.Role;
import jakarta.persistence.*;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String calculateCode() {
        return this.getUsername()+this.getId();
    }

}
