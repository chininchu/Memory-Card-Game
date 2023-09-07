package com.example.memory_card_game.model;

import jakarta.persistence.*;
import lombok.Data;

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

    @Column(name = "imageURL")
    private String imageURL;
    // to check if the card is flipped or not

    @Column(name = "isFlipped")
    private boolean isFlipped;

    @Column(name = "iconName")

    private String iconName;  // e.g., "favorite", "home", "star", etc.


}
