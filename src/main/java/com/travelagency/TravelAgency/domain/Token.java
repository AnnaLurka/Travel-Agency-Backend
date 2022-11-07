package com.travelagency.TravelAgency.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TOKENS")
public class Token {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "VALUE")
    private String value;

    @NotNull
    @Column(name = "VALID_FROM")
    private LocalDateTime validFrom;

    @NotNull
    @Column(name = "VALID_TO")
    private LocalDateTime validTo;

    @ManyToOne
    @JoinColumn(name = "USER_ID")//
    private User user;

    public Token(String value, LocalDateTime validFrom, LocalDateTime validTo) {
        this.value = value;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }
}