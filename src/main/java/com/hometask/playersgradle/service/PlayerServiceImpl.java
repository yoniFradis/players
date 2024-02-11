package com.hometask.playersgradle.service;

import com.hometask.playersgradle.Repository.PlayerRepository;
import com.hometask.playersgradle.converter.PlayerConverter;
import com.hometask.playersgradle.dto.PlayerDto;
import com.hometask.playersgradle.model.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerConverter playerConverter;

    public PlayerServiceImpl(PlayerRepository playerRepository, PlayerConverter playerConverter) {
        this.playerRepository = playerRepository;
        this.playerConverter = playerConverter;
    }

    public List<PlayerDto> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        return playerConverter.toDto(players);
    }

    public PlayerDto getPlayerById(String playerId) {
        Player player = playerRepository.findById(playerId).orElse(null);
        if (player != null) {
            return playerConverter.toDto(player);
        }
        return new PlayerDto();
    }

}



