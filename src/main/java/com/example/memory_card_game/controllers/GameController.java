package com.example.memory_card_game.controllers;

import com.example.memory_card_game.Repository.PlayerRepository;
import com.example.memory_card_game.Repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameController {


    private final PlayerRepository playerRepository;

    private final ScoreRepository scoreRepository;


    public GameController(PlayerRepository playerRepository, ScoreRepository scoreRepository) {
        this.playerRepository = playerRepository;
        this.scoreRepository = scoreRepository;
    }

    @GetMapping("/game")

    public String displayGame() {

        // Logic to display the game
        return "game";

        // This refers to the game.html (or game template) file


    }




}
