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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author flafo
 */
@SpringBootTest
public class GuessTheNumberDatabaseGameDaoTest {

    @Autowired
    GuessTheNumberGameDao gameDao;

    @Autowired
    GuessTheNumberRoundDao roundDao;

    public GuessTheNumberDatabaseGameDaoTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        List<Game> games = gameDao.getAllGames();
        for (Game game : games) {
            gameDao.deleteGameById(game.getGameId());
        }
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testCreateGameFindGameById() {
        Game game = new Game();
        game.setAnswer("6969");
        game = gameDao.createGame(game);
        Game fromDao = gameDao.findGameById(game.getGameId());
        assertEquals(game, fromDao);
    }

    @Test
    public void testGetAllGame() {
        Game game = new Game();
        game.setAnswer("6969");
        gameDao.createGame(game);
        Game game2 = new Game();
        game2.setAnswer("0420");
        gameDao.createGame(game2);
        List<Game> games = gameDao.getAllGames();
        assertEquals(2, games.size());
        assertTrue(games.contains(game));
        assertTrue(games.contains(game2));
    }

    @Test
    public void testUpdateGame() {
        Game game = new Game();
        game.setAnswer("6969");
        gameDao.createGame(game);
        Game fromDao = gameDao.findGameById(game.getGameId());
        assertEquals(game, fromDao);
        game.setFinished(true);
        gameDao.updateGame(game);
        assertNotEquals(game, fromDao);
        fromDao = gameDao.findGameById(game.getGameId());
        assertEquals(game, fromDao);
    }

    @Test
    public void testDeleteGameById() {
        Game game = new Game();
        game.setAnswer("6969");
        gameDao.createGame(game);
        Round round = new Round();
        round.setGameId(game.getGameId());
        round.setGuess("6969");
        round.setResult("e:4:p:0");
        round.setTime(LocalDateTime.now());
        roundDao.addRound(round);
        boolean deleteSuccess = gameDao.deleteGameById(game.getGameId());
        assertTrue(deleteSuccess);
    }

}
