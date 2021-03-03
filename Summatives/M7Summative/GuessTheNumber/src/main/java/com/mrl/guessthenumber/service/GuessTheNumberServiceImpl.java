/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.guessthenumber.service;

import com.mrl.guessthenumber.data.GuessTheNumberGameDao;
import com.mrl.guessthenumber.data.GuessTheNumberRoundDao;
import com.mrl.guessthenumber.models.Game;
import com.mrl.guessthenumber.models.Round;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author flafo
 */
@Component
public class GuessTheNumberServiceImpl implements GuessTheNumberService {

    @Autowired
    private GuessTheNumberGameDao gameDao;

    @Autowired
    private GuessTheNumberRoundDao roundDao;

    @Override
    public Game createGame() {
        Random random = new Random();
        Game game = new Game();
        String answer = String.valueOf(random.nextInt(10000));
        game.setAnswer(answer);
        game.setFinished(false);
        gameDao.createGame(game);
        game.setAnswer("Answer hidden until guessed");
        return game;
    }

    @Override
    public Round makeGuess(String guess, int gameId) {
        Game game = gameDao.findGameById(gameId);
        int exactMatches = 0;
        int partialMatches = 0;
        StringBuilder sb = new StringBuilder(guess);
        String answer = game.getAnswer();
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == answer.charAt(i)) {
                exactMatches++;
                sb.deleteCharAt(i);
            }
        }
        if(exactMatches == answer.length()) {
            game.setFinished(true);
            gameDao.updateGame(game);    
        }
        for (int j = 0; j < sb.length(); j++) {
            String charAsString = String.valueOf(sb.charAt(j));
            if (answer.contains(charAsString)) {
                partialMatches++;
            }
        }
        String results = "e:" + exactMatches + "p:" + partialMatches;
        Round round = new Round();
        round.setGuess(guess);
        round.setResult(results);
        round.setTime(LocalDateTime.now());
        round.setGameId(gameId);
        roundDao.addRound(round);
        return round;

    }

    @Override
    public List<Game> listAllGames() {
        List<Game> gameList = gameDao.getAllGames();
        for (Game game : gameList) {
            if (!game.isFinished()) {
                game.setAnswer("Answer hidden until guessed");
            }
        }
        return gameList;
    }

    @Override
    public Game listGameById(int id) {
        Game game = gameDao.findGameById(id);
        if (!game.isFinished()) {
            game.setAnswer("Answer hidden until guessed");
        }
        return game;
    }

    @Override
    public List<Round> ListRoundsByGameId(int id) {
        return roundDao.getAllRoundsByGameId(id);
    }

}
