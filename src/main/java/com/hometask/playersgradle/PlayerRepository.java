package com.hometask.playersgradle;

import com.hometask.playersgradle.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository extends MongoRepository<Player, String> {
}
