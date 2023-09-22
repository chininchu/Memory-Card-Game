package com.example.memory_card_game.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Locale;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "players")

public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "username")
    private String username;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    // Player and Score
    //A Player can have multiple scores, representing the results of multiple games. Therefore, we can set up a one-to-many relationship between Player and Score.
    //In the Score class, add a reference to the Player entity.


    @OneToMany(mappedBy = "player")
    private List<Score> scores;

    @ManyToMany
    @JoinTable(
            name = "player_game",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")


    )

    private List<Game> games;


}




