/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.guessthenumber.data;

import com.mrl.guessthenumber.models.Game;
import com.mrl.guessthenumber.models.Round;
import java.util.List;

/**
 *
 * @author flafo
 */
public interface GuessTheNumberGameDao {
    Game createGame(Game game);
    
    List<Game> getAllGames();
    
    Game findGameById(int id);
    
    boolean updateGame(Game game);
    
    boolean deleteGameById(int id);
    
}
