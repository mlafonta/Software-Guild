/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.guessthenumber.service;

import com.mrl.guessthenumber.models.Game;
import com.mrl.guessthenumber.models.Round;
import java.util.List;

/**
 *
 * @author flafo
 */
public interface GuessTheNumberService {
    Game createGame();
    
    Round makeGuess(String guess, int gameId);
    
    List<Game> listAllGames();
    
    Game listGameById(int id);
    
    List<Round> ListRoundsByGameId(int id);
}
