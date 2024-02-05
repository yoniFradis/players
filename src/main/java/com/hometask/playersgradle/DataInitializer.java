package com.hometask.playersgradle;

import com.hometask.playersgradle.model.Player;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class DataInitializer implements ApplicationRunner {
    private final PlayerRepository playerRepository;
    private static final int BATCH_SIZE = 100;

    public DataInitializer(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (playerRepository.count() == 0) {
            loadPlayers();
        }
    }

    private void loadPlayers() {
        try (InputStreamReader reader = new InputStreamReader(new ClassPathResource("players.csv").getInputStream());
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
