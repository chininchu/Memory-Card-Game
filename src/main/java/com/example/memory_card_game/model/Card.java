package com.example.memory_card_game.model;

import lombok.Data;

@Data
public class Card {

    private int id;
    // could represent the card value or name
    private String value;

    // could represent the card's image if any
    private String imageURL;
    // to check if the card is flipped or not
    private boolean isFlipped;


}
