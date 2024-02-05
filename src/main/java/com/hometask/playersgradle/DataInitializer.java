package com.hometask.playersgradle;

import com.hometask.playersgradle.service.CsvDataLoaderService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationRunner {
    private final CsvDataLoaderService csvDataLoaderService;

    public DataInitializer(CsvDataLoaderService csvDataLoaderService) {
        this.csvDataLoaderService = csvDataLoaderService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.csvDataLoaderService.loadPlayersInBatches();
    }
}
