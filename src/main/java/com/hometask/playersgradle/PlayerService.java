package com.hometask.playersgradle;

import com.hometask.playersgradle.model.Player;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlayerService {
    private Map<String, Player> playersById = new HashMap<>();

    public PlayerService() {
        loadPlayers();
    }

    private void loadPlayers() {
        try {
            InputStreamReader reader = new InputStreamReader(new ClassPathResource("players.csv").getInputStream());
            CsvToBean<Player> csvToBean = new CsvToBeanBuilder<Player>(reader)
                    .withType(Player.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<Player> players = csvToBean.parse();
            players.forEach(player -> playersById.put(player.getPlayerID(), player));
        } catch (IOException ex) {
            System.out.println("Couldn't load CSV");
        }
    }

    public List<Player> getAllPlayers() {
        return new ArrayList<>(playersById.values());
    }

    public Player getPlayerById(String playerId) {
        return playersById.get(playerId);
    }
}



