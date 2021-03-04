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
        int answerWithoutLeadingZerosIfItsLessThanOneThousand = random.nextInt(10000);
        String answer = String.format("%04d", answerWithoutLeadingZerosIfItsLessThanOneThousand);
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
        String answer = game.getAnswer();
        for (int i = 0; i < answer.length(); i++) {
            String charAsString = String.valueOf(guess.charAt(i));
            if (guess.charAt(i) == answer.charAt(i)) {
                exactMatches++;
            } else if (answer.contains(charAsString) && guess.charAt(i) != answer.charAt(i)) {
                partialMatches++;
            }
        }
        if (exactMatches == answer.length()) {
            game.setFinished(true);
            gameDao.updateGame(game);
        }
        String results = "e:" + exactMatches + ":" + "p:" + partialMatches;
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
