package com.hometask.playersgradle.service;

import com.hometask.playersgradle.Repository.PlayerRepository;
import com.hometask.playersgradle.exceptions.PlayerNotFoundException;
import com.hometask.playersgradle.model.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers() {
        return this.playerRepository.findAll();
    }

    public Player getPlayerById(String playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("Player not found with ID: " + playerId));
    }

}



