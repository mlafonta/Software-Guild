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
import java.util.ArrayList;
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
        int int1 = random.nextInt(10);
        int int2 = random.nextInt(10);
        while (int2 == int1) {
            int2 = random.nextInt(10);
        }
        int int3 = random.nextInt(10);
        while (int3 == int1 || int3 == int2) {
            int3 = random.nextInt(10);
        }
        int int4 = random.nextInt(10);
        while (int4 == int1 || int4 == int2 || int4 == int3) {
            int4 = random.nextInt(10);
        }
        String answer = (String.valueOf(int1) + String.valueOf(int2) + String.valueOf(int3) + String.valueOf(int4));
        game.setAnswer(answer);
        game.setFinished(false);
        gameDao.createGame(game);
        game.setAnswer("Answer hidden until guessed");
        return game;
    }

    @Override
    public Round makeGuess(Round round) {
        Game game = gameDao.findGameById(round.getGameId());
        int exactMatches = 0;
        int partialMatches = 0;
        String answer = game.getAnswer();
        for (int i = 0; i < answer.length(); i++) {
            String charAsString = String.valueOf(round.getGuess().charAt(i));
            if (round.getGuess().charAt(i) == answer.charAt(i)) {
                exactMatches++;
            } else if (answer.contains(charAsString) && round.getGuess().charAt(i) != answer.charAt(i)) {
                partialMatches++;
            }
        }
        if (exactMatches == answer.length()) {
            game.setFinished(true);
            gameDao.updateGame(game);
        }
        String results = "e:" + exactMatches + ":" + "p:" + partialMatches;
        round.setResult(results);
        round.setTime(LocalDateTime.now());
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
