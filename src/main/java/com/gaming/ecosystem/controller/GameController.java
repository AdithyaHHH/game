package com.gaming.ecosystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.gaming.ecosystem.model.Game;
import com.gaming.ecosystem.repository.GameRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    // Get all games
    @GetMapping
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    // Get game by id
    @GetMapping("/{id}")
    public Optional<Game> getGameById(@PathVariable String id) {
        return gameRepository.findById(id);
    }

    // Add new game
    @PostMapping
    public Game addGame(@RequestBody Game game) {
        return gameRepository.save(game);
    }

    // Update game
    @PutMapping("/{id}")
    public Game updateGame(@PathVariable String id, @RequestBody Game updatedGame) {
        return gameRepository.findById(id)
                .map(game -> {
                    game.setName(updatedGame.getName());
                    game.setPrice(updatedGame.getPrice());
                    game.setDescription(updatedGame.getDescription());
                    return gameRepository.save(game);
                })
                .orElseGet(() -> {
                    updatedGame.setId(id);
                    return gameRepository.save(updatedGame);
                });
    }

    // Delete game
    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable String id) {
        gameRepository.deleteById(id);
    }
}
