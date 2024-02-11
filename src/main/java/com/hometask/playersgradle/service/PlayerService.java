package com.hometask.playersgradle.service;

import com.hometask.playersgradle.dto.PlayerDto;

import java.util.List;

public interface PlayerService {
    List<PlayerDto> getAllPlayers();
    PlayerDto getPlayerById(String playerId);
}
