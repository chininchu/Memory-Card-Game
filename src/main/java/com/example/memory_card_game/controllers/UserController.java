package com.example.memory_card_game.controllers;


import com.example.memory_card_game.Repository.PlayerRepository;
import com.example.memory_card_game.Repository.UserRepository;
import com.example.memory_card_game.model.Player;
import com.example.memory_card_game.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class UserController {

    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;

    private final PlayerRepository playerRepository;


    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, PlayerRepository playerRepository) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;

        this.playerRepository = playerRepository;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);


        userDao.save(user);

        // Create a new Player object and associate it with the User

        Player newPlayer = new Player();

        newPlayer.setUser(user);
        newPlayer.setUsername(user.getUsername());


        playerRepository.save(newPlayer);


        return "redirect:/login";

    }

//    @PostMapping("/LoginPage")
//    public String loginUser(@ModelAttribute User user, Model model, HttpServletRequest request) {
//        // Retrieve the user object from the database based on the provided username
//        User authenticatedUser = userDao.findByUsername(user.getUsername());
//        System.out.println("Username:" + authenticatedUser);
//        // Check if the user exists and the password matches
//        if (authenticatedUser != null && encoder.matches(user.getPassword(), authenticatedUser.getPassword())) {
//            // Authentication successful, set the user attribute in the session
//            request.getSession().setAttribute("user", authenticatedUser);
//            return "redirect:/profile";
//        }
//        // if Authentication failed, redirect back to the login page with an error message
//        return "redirect:LoginPage";
//    }


}
