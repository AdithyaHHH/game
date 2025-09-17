package com.gaming.ecosystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.gaming.ecosystem.model.Game;

public interface GameRepository extends MongoRepository<Game, String> {
}
