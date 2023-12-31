package com.example.memory_card_game.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data

@Entity
@Table(name = "cards")
public class Card {





    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")

    private int id;
    // could represent the card value or name

    @Column(name = "value")


    private String value;

    // could represent the card's image if any


    // to check if the card is flipped or not

    @Column(name = "isFlipped")
    private Boolean isFlipped;

    @Column(name = "iconName")

    private String iconName;  // e.g., "favorite", "home", "star", etc.


}

