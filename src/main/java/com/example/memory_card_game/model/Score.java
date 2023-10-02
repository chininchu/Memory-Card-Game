package com.example.memory_card_game.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "scores")


public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(nullable = false)

//    time taken to match all cards, in seconds
    private Long timeTaken;

    @Column(nullable = false)

//    when the score was recorded


    private LocalTime dateRecorded = LocalTime.now();

    // Many scores can belong to one player, hence the many-to-one relationship.

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;


    // Many scores can belong to one game
    @ManyToOne
    private Game game;

    // New field for storing points

    @Column(nullable = false)
    private int points = 0; // Initialized to 0










}

