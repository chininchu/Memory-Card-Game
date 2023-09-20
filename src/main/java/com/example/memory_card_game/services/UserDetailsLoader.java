package com.example.memory_card_game.services;

import com.example.memory_card_game.Repository.UserRepository;
import com.example.memory_card_game.model.User;
import com.example.memory_card_game.model.UserWithRoles;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsLoader implements UserDetailsService {

    private final UserRepository users;

    public UserDetailsLoader(UserRepository users) {
        this.users = users;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = users.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + username);
        }

        return new UserWithRoles(user);


    }


}
