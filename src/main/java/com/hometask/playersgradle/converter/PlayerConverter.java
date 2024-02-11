package com.hometask.playersgradle.converter;

import com.hometask.playersgradle.dto.PlayerDto;
import com.hometask.playersgradle.model.Player;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerConverter {

    public PlayerDto toDto(Player player) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setPlayerID(player.getPlayerID());
        playerDto.setBirthYear(player.getBirthYear());
        playerDto.setBirthMonth(player.getBirthMonth());
        playerDto.setBirthDay(player.getBirthDay());
        playerDto.setBirthCountry(player.getBirthCountry());
        playerDto.setBirthState(player.getBirthState());
        playerDto.setDeathYear(player.getDeathYear());
        playerDto.setDeathMonth(player.getDeathMonth());
        playerDto.setDeathDay(player.getDeathDay());
        playerDto.setDeathCountry(player.getDeathCountry());
        playerDto.setDeathState(player.getDeathState());
        playerDto.setDeathCity(player.getDeathCity());
        playerDto.setNameFirst(player.getNameFirst());
        playerDto.setNameLast(player.getNameLast());
        playerDto.setNameGiven(player.getNameGiven());
        playerDto.setWeight(player.getWeight());
        playerDto.setHeight(player.getHeight());
        playerDto.setBats(player.getBats());
        playerDto.setThrows_(player.getThrows_());
        playerDto.setDebut(player.getDebut());
        playerDto.setFinalGame(player.getFinalGame());
        playerDto.setRetroID(player.getRetroID());
        playerDto.setBbrefID(player.getBbrefID());

        return playerDto;
    }

    public List<PlayerDto> toDto(List<Player> players) {
        List<PlayerDto> playersDto = new ArrayList<>();
        for (Player player: players) {
            PlayerDto playerDto = toDto(player);
            playersDto.add(playerDto);
        }
        return playersDto;
    }
}
