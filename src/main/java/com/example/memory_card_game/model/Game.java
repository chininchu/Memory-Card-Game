package com.example.memory_card_game.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "games")


public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToMany
    @JoinTable(
            name = "game_card",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")

    )

    private List<Card> cards;

    @ManyToMany(mappedBy = "games")
    private List<Player> players;


    @OneToMany(mappedBy = "game")  // One game can have many scores
    private List<Score> scores;


}
