/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.guessthenumber.data;

import com.mrl.guessthenumber.models.Game;
import com.mrl.guessthenumber.models.Round;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author flafo
 */
@SpringBootTest
public class GuessTheNumberDatabaseRoundDaoTest {

    @Autowired
    GuessTheNumberGameDao gameDao;

    @Autowired
    GuessTheNumberRoundDao roundDao;

    public GuessTheNumberDatabaseRoundDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        List<Game> games = gameDao.getAllGames();
        for (Game game : games) {
            gameDao.deleteGameById(game.getGameId());
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addRoundGetAllRoundsByGameId() {
        Game game = new Game();
        game.setAnswer("6969");
        game = gameDao.createGame(game);
        Round round = new Round();
        round.setGameId(game.getGameId());
        round.setGuess("6969");
        round.setResult("e:4:p:0");
        round.setTime(LocalDateTime.parse("2007-03-03T10:15:30"));
        roundDao.addRound(round);
        Round round2 = new Round();
        round2.setGameId(game.getGameId());
        round2.setGuess("0420");
        round2.setResult("e:0:p:0");
        round2.setTime(LocalDateTime.parse("2007-03-03T10:15:30"));
        roundDao.addRound(round2);
        List<Round> rounds = roundDao.getAllRoundsByGameId(game.getGameId());
        assertEquals(2, rounds.size());
        assertTrue(rounds.contains(round));
        assertTrue(rounds.contains(round2));
    }
}
