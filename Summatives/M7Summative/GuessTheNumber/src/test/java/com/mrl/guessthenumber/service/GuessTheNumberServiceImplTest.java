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
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author flafo
 */
@SpringBootTest
public class GuessTheNumberServiceImplTest {

    @Autowired
    GuessTheNumberService service;

    @Autowired
    GuessTheNumberGameDao gameDao;

    @Autowired
    GuessTheNumberRoundDao roundDao;

    public GuessTheNumberServiceImplTest() {
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
    public void testCreateGameListAllGames() {
        Game game = service.createGame();
        Game game2 = service.createGame();
        List<Game> games = service.listAllGames();;
        assertEquals(2, games.size());
        assertEquals(games.get(1).getGameId(), game2.getGameId());
    }

    @Test
    public void testMakeGuess() {
        Game game = new Game();
        game.setAnswer("1234");
        game = gameDao.createGame(game);
        Round round = service.makeGuess("1400", game.getGameId());
        assertEquals("e:1:p:1", round.getResult());
        Round round2 = service.makeGuess("1234", game.getGameId());
        assertEquals("e:4:p:0", round2.getResult());
        assertTrue(service.listGameById(game.getGameId()).isFinished());
    }

    @Test
    public void testListGameById() {
        Game game = service.createGame();
        Game fromDao = service.listGameById(game.getGameId());
        assertEquals(game, fromDao);
    }

    @Test
    public void testListRoundsByGameId() {
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
        List<Round> rounds = service.ListRoundsByGameId(game.getGameId());
        assertEquals(2, rounds.size());
        assertTrue(rounds.contains(round));
        assertTrue(rounds.contains(round2));
    }
}
