package com.travelagency.TravelAgency.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USERS")
public class User {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "FIRSTNAME")
    private String firstname;

    @NotNull
    @Column(name = "LASTNAME")
    private String lastname;

    @NotNull
    @Column(name = "USERNAME")
    private String username;

    @NotNull
    @Column(name = "BLOCKED")
    private boolean blocked;

    @OneToMany(
            targetEntity = Token.class,//
            mappedBy = "user",//
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY//
    )
    @NotNull
    @Column(name = "TOKENS")
    private Set<Token> tokens = new HashSet<>();

    public User(String firstname, String lastname, String username, boolean blocked, Set<Token> tokens) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.blocked = blocked;
        this.tokens = tokens;
    }
}




