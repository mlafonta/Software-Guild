/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.guessthenumber.data;

import com.mrl.guessthenumber.models.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author flafo
 */
@Repository
public class GuessTheNumberDatabaseRoundDao implements GuessTheNumberRoundDao {

    private final JdbcTemplate jdbc;

    @Autowired
    public GuessTheNumberDatabaseRoundDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Round addRound(Round round) {
        final String sql = "INSERT INTO round(guess, result, time, gameId) VALUES (?,?,?,?);";
        GeneratedKeyHolder kh = new GeneratedKeyHolder();

        jdbc.update((Connection conn) -> {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, round.getGuess());
            stmt.setString(2, round.getResult());
            stmt.setTimestamp(3, Timestamp.valueOf(round.getTime()));
            stmt.setInt(4, round.getGameId());
            return stmt;
        }, kh);

        round.setRoundNumber(kh.getKey().intValue());

        return round;
    }

    @Override
    public boolean deleteRoundByNumber(int number) {
        final String sql = "DELETE FROM round WHERE roundNumber = ?;";
        return jdbc.update(sql, number) > 0;
    }

    @Override
    public List<Round> getAllRoundsByGameId(int id) {
        final String sql = "SELECT roundNumber, guess, result, time, gameId FROM round WHERE gameId = ? ORDER BY time;";
        return jdbc.query(sql, new RoundMapper(), id);
    }

    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int i) throws SQLException {
            Round round = new Round();
            round.setRoundNumber(rs.getInt("roundNumber"));
            round.setGuess(rs.getString("guess"));
            round.setTime(rs.getTimestamp("time").toLocalDateTime());
            round.setGameId(rs.getInt("gameId"));
            round.setResult(rs.getString("result"));
            return round;
        }
    }
}
