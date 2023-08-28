package com.example.memory_card_game.Repository;

import com.example.memory_card_game.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player findPlayerByUsername(String username);

}
