package com.hometask.playersgradle.service;

import com.hometask.playersgradle.Repository.PlayerRepository;
import com.hometask.playersgradle.exceptions.FailedToLoadCsvException;
import com.hometask.playersgradle.model.Player;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CsvDataLoaderService {

    private final PlayerRepository playerRepository;
    private static final int BATCH_SIZE = 100;
    private static final String PLAYERS_SCV = "players.csv";

    public CsvDataLoaderService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void loadPlayersInBatches() {
        try (InputStreamReader reader = new InputStreamReader(new ClassPathResource(PLAYERS_SCV).getInputStream());
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            CsvToBean<Player> csvToBean = new CsvToBeanBuilder<Player>(bufferedReader)
                    .withType(Player.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<Player> iterator = csvToBean.iterator();
            List<Player> batch = new ArrayList<>();

            while (iterator.hasNext()) {
                Player player = iterator.next();
                batch.add(player);

                if (batch.size() == BATCH_SIZE) {
                    playerRepository.saveAll(batch);
                    batch.clear();
                }
            }

            // Save any remaining records in the last batch
            if (!batch.isEmpty()) {
                playerRepository.saveAll(batch);
            }
        } catch (Exception e) {
            throw new FailedToLoadCsvException(e.getMessage());
        }
    }
}
