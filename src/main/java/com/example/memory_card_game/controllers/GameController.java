package com.example.memory_card_game.controllers;

import com.example.memory_card_game.Repository.*;
import com.example.memory_card_game.model.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
public class GameController {


    private final PlayerRepository playerRepository;

    private final ScoreRepository scoreRepository;

    private final CardRepository cardRepository;

    private final UserRepository userRepository;

    private final GameRepository gameRepository;


    private HttpSession httpSession;


    @Autowired

    public GameController(PlayerRepository playerRepository, ScoreRepository scoreRepository, CardRepository cardRepository, UserRepository userRepository, GameRepository gameRepository, HttpSession httpSession) {
        this.playerRepository = playerRepository;
        this.scoreRepository = scoreRepository;
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.httpSession = httpSession;
    }

    @PreAuthorize("isAuthenticated()")

    @GetMapping("/game")

    public String displayGame(Model model) {

        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Find the player associated with the authenticated user

        Player player = playerRepository.findPlayerByUsername(loggedIn.getUsername());

        //TODO: Add a code that handles if the player is not found.


        // Attributes of the model

        model.addAttribute("player", player);


        model.addAttribute("score", new Score());

//        List<Card> cards = cardRepository.findAll();

        List<Card> cards = (List<Card>) httpSession.getAttribute("cards");

        if (cards == null) {

            cards = cardRepository.findAll();
            httpSession.setAttribute("cards", cards);
        }


        for (Card card : cards) {
            card.setIsFlipped(false);
            cardRepository.save(card);  // Save each card back to the database



        }

        httpSession.setAttribute("cards",cards);


        model.addAttribute("cards", cards);


        // Logic to display the game
        return "game";

        // This refers to the game.html (or game template) file


    }

    // TODO: 1.When the "Start Game"  button is clicked, the form submits a POST request to the /score endpoint. 2. InGameController, you can initialize the game state, start the timer, and redirect the user back to the game page.


//    @PostMapping("/score")

//    public String saveScore(@Validated @ModelAttribute("score") Score score, BindingResult result, RedirectAttributes redirectAttributes) {
//
//        if (result.hasErrors()) {
//
//            redirectAttributes.addFlashAttribute("errorMessage", "There was an error saving the score.");
//            return "redirect:/game";
//
//
//        }
//
//
//        scoreRepository.save(score);
//        return "redirect:/highscores";
//
//
//    }

    @GetMapping("/highscores")

    public String displayHighScores(Model model) {

        model.addAttribute("scores", scoreRepository.findAll());

        return "highscores"; // This refers to the highscores.html file


    }


@PreAuthorize("isAuthenticated()")
    @PostMapping("/toggleCard")
    @ResponseBody

    public ResponseEntity<String> toggleCard(@RequestParam Long cardId) {

        Optional<Card> cardOptional = cardRepository.findById(cardId);


        if (cardOptional.isPresent()) {

            Card card = cardOptional.get();

            card.setIsFlipped(!card.getIsFlipped());

            cardRepository.save(card);

            return ResponseEntity.ok("Card flipped");


        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found");
        }


    }

    @GetMapping("/checkMatch")

    public ResponseEntity<Map<String, Boolean>> checkMatch(@RequestParam Long firstCardId, @RequestParam Long secondCardId) {

        // Stores the Id for the first card

        Card firstCard = cardRepository.findById(firstCardId).orElseThrow();

        // Stores the Id for the secondcard
        Card secondCard = cardRepository.findById(secondCardId).orElseThrow();

        boolean match = firstCard.getIconName().equals(secondCard.getIconName());

        Map<String, Boolean> response = new HashMap<>();

        response.put("match", match);

        return ResponseEntity.ok(response);


    }


    @PostMapping("/score")
    public String startGame(@ModelAttribute("score") Score score) {

        // Get the authenticated user


        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        // Find the player associated with the authenticated user


        Player currentPlayer = playerRepository.findPlayerByUsername(loggedIn.getUsername());


        // Initialize a new game


        Game newGame = new Game();

        newGame.setPlayers(Collections.singletonList(currentPlayer));

        gameRepository.save(newGame);

        // Initialize Score

        score.setPlayer(currentPlayer);
        score.setGame(newGame);
        score.setPoints(0);
        scoreRepository.save(score);


        // Save the new Game

        gameRepository.save(newGame);

        return "redirect:/game";


    }


}




