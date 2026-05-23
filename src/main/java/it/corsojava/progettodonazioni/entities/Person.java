package it.corsojava.progettodonazioni.entities;

import it.corsojava.progettodonazioni.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Person extends BaseEntity {

    @Column
    private String name;

    @Column
    private String surname;

    @Column(unique = true)
    private String email;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String code;

    public Person(String name, String surname, String email, String username, String password) {

        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    protected Person() {
    }


    public abstract String calculateCode();

}
