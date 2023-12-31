package com.example.memory_card_game.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;


    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;


    public User(User copy){

        this.id = copy.id;
        this.email = copy.email;
        this.email = copy.username;
        this.password = copy.password;
        this.firstName = copy.firstName;
        this.lastName = copy.lastName;



    }

    // A User can be associated with multiple Player entities. Hence, we can establish a one-to-many relationship between User and Player.


    @OneToMany(mappedBy = "user")
    private List<Player> players;





}

