/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.guessthenumber.controllers;

import com.mrl.guessthenumber.models.Game;
import com.mrl.guessthenumber.models.Round;
import com.mrl.guessthenumber.service.GuessTheNumberService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author flafo
 */
@RestController
@RequestMapping("/api/guessthenumber")
public class GuessTheNumberController {
    
    private final GuessTheNumberService service;
    
    public GuessTheNumberController(GuessTheNumberService service) {
        this.service = service;
    }
    
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public Game begin() {
        return service.createGame();
    }
    
    @PostMapping("/guess/{id}")
    public Round guess(@RequestBody String guess, @PathVariable int id) {
        return service.makeGuess(guess, id);
    }
    
    @GetMapping("/game")
    public List<Game> listOfGames() {
        return service.listAllGames();
    }
    
    @GetMapping("/game/{id}")
    public Game gameById(@PathVariable int id) {
        return service.listGameById(id);
    }
    
    @GetMapping("/rounds/{id}")
    public List<Round> getRoundsByGameId(@PathVariable int id) {
        return service.ListRoundsByGameId(id);
    }
}
