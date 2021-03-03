/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.guessthenumber.data;

import com.mrl.guessthenumber.models.Game;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author flafo
 */
@Repository
public class GuessTheNumberDatabaseGameDao implements GuessTheNumberGameDao {
    
    private final JdbcTemplate jdbc;
    
    @Autowired
    public GuessTheNumberDatabaseGameDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    
    @Override
    public Game createGame(Game game) {
        
        final String sql = "INSERT INTO game(answer) VALUES(?);";
        GeneratedKeyHolder kh = new GeneratedKeyHolder();
        
        jdbc.update((Connection conn) -> {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);            
            stmt.setString(1, game.getAnswer());
            return stmt;
        }, kh);
        game.setGameId(kh.getKey().intValue());
        
        return game;
    }

    @Override
    public List<Game> getAllGames() {
        final String sql = "SELECT gameId, answer, finished FROM game;";
        return jdbc.query(sql, new GameMapper());
    }

    @Override
    public Game findGameById(int id) {
        final String sql = "SELECT gameId, answer, finished FROM game WHERE gameId = ?;";
        return jdbc.queryForObject(sql, new GameMapper(), id);
    }

    @Override
    public boolean updateGame(Game game) {
        final String sql = "UPDATE game SET finished = ? WHERE id = ?;";
        return jdbc.update(sql, game.isFinished(), game.getGameId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteGameById(int id) {
        final String DELETE_ROUND_BY_GAME = "DELETE FROM round WHERE gameId = ?;";
        jdbc.update(DELETE_ROUND_BY_GAME, id);
        final String DELETE_GAME = "DELETE FROM game WHERE gameId = ?;";
        return jdbc.update(DELETE_GAME, id) > 0;
    }

    private static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int i) throws SQLException {
            Game game = new Game();
            game.setGameId(rs.getInt("gameId"));
            game.setAnswer(rs.getString("answer"));
            game.setFinished(rs.getBoolean("finished"));
            return game;
        }
    }
}
