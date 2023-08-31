package com.example.memory_card_game.controllers;

import com.example.memory_card_game.Repository.PlayerRepository;
import com.example.memory_card_game.Repository.ScoreRepository;
import com.example.memory_card_game.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GameController {


    private final PlayerRepository playerRepository;

    private final ScoreRepository scoreRepository;

    @Autowired

    public GameController(PlayerRepository playerRepository, ScoreRepository scoreRepository) {
        this.playerRepository = playerRepository;
        this.scoreRepository = scoreRepository;
    }

    @GetMapping("/game")

    public String displayGame(Model model) {




        // Logic to display the game
        return "game";

        // This refers to the game.html (or game template) file


    }

    @PostMapping("/score")

    public String saveScore(Score score) {

        scoreRepository.save(score);
        return "redirect:/highscores";


    }

    @GetMapping("/highscores")

    public String displayHighScores(Model model) {

        model.addAttribute("scores", scoreRepository.findAll());

        return "highscores"; // This refers to the highscores.html file


    }


}


