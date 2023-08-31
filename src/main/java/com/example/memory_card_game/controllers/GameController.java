package com.example.memory_card_game.controllers;

import com.example.memory_card_game.Repository.CardRepository;
import com.example.memory_card_game.Repository.PlayerRepository;
import com.example.memory_card_game.Repository.ScoreRepository;
import com.example.memory_card_game.model.Card;
import com.example.memory_card_game.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class GameController {


    private final PlayerRepository playerRepository;

    private final ScoreRepository scoreRepository;

    private CardRepository cardRepository;

    @Autowired

    public GameController(PlayerRepository playerRepository, ScoreRepository scoreRepository, CardRepository cardRepository) {
        this.playerRepository = playerRepository;
        this.scoreRepository = scoreRepository;
        this.cardRepository = cardRepository;
    }

    @GetMapping("/game")

    public String displayGame(Model model) {

        List<Card> cards = cardRepository.findAll();

        model.addAttribute("cards", cards);


        // Logic to display the game
        return "game";

        // This refers to the game.html (or game template) file


    }

    @PostMapping("/score")

    public String saveScore(@Validated @ModelAttribute("score") Score score, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {

            redirectAttributes.addFlashAttribute("errorMessage", "There was an error saving the score.");
            return "redirect:/game";


        }


        scoreRepository.save(score);
        return "redirect:/highscores";


    }

    @GetMapping("/highscores")

    public String displayHighScores(Model model) {

        model.addAttribute("scores", scoreRepository.findAll());

        return "highscores"; // This refers to the highscores.html file


    }

    @GetMapping("/submitScore")

    public String showScoreForm(Model model){

        model.addAttribute("score", new Score());

        return  "submitScore";

    }


}


