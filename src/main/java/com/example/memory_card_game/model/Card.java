package com.example.memory_card_game.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    // could represent the card value or name
    private String value;

    // could represent the card's image if any
    private String imageURL;
    // to check if the card is flipped or not
    private boolean isFlipped;

    private String iconName;  // e.g., "favorite", "home", "star", etc.



}
