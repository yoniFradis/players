package com.hometask.playersgradle;

import com.hometask.playersgradle.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers() {
        return this.playerRepository.findAll();
    }

    public Player getPlayerById(String playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found with ID: " + playerId));
    }

}



