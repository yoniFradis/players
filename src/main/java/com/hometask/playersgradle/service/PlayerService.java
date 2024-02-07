package com.hometask.playersgradle.service;

import com.hometask.playersgradle.model.Player;

import java.util.List;

public interface PlayerService {
    List<Player> getAllPlayers();
    Player getPlayerById(String playerId);
}
