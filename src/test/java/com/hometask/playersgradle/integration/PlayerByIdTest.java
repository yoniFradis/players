package com.hometask.playersgradle.integration;

import com.hometask.playersgradle.model.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlayerByIdTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getPlayerByIdShouldReturnPlayerDetails() {
        String playerId = "overtdi01";
        ResponseEntity<Player> response = restTemplate.getForEntity("http://localhost:" + port + "/api/players/{playerId}", Player.class, playerId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response).isNotNull();
        assertThat(response.getBody().getPlayerID()).isEqualTo(playerId);
    }
}
