/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.superherosighting.dao;

import com.mrl.superherosighting.dto.Hero;
import com.mrl.superherosighting.dto.Superpower;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author flafo
 */
@Repository
public class SuperpowerDaoDB implements SuperpowerDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Superpower getSuperpowerBySuperpowerName(String superpowerName) {
        try {
            final String SELECT_SUPERPOWER_BY_NAME = "SELECT * FROM Superpower WHERE SuperpowerName =?";
            return jdbc.queryForObject(SELECT_SUPERPOWER_BY_NAME, new SuperpowerMapper(), superpowerName);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Superpower> getAllSuperpowers() {
        final String SELECT_ALL_SUPERPOWERS = "SELECT * FROM Superpower";
        return jdbc.query(SELECT_ALL_SUPERPOWERS, new SuperpowerMapper());
    }

    @Override
    public Superpower addSuperpower(Superpower superpower) {
        final String INSERT_SUPERPOWER = "INSERT INTO Superpower(SuperpowerName, Description) VALUES(?,?)";
        jdbc.update(INSERT_SUPERPOWER, superpower.getSuperpowerName(), superpower.getDescription());
        return superpower;
    }

    @Override
    public void updateSuperpower(Superpower superpower) {
        final String UPDATE_SUPERPOWER = "UPDATE Superpower SET Description = ? WHERE SuperpowerName = ?";
        jdbc.update(UPDATE_SUPERPOWER, superpower.getDescription(), superpower.getSuperpowerName());
    }

    @Override
    @Transactional
    public void deleteSuperpowerBySuperpowerName(String superpowerName) {
        final String DELETE_HERO_SUPERPOWER = "DELETE FROM Hero_Superpower WHERE SuperpowerName = ?";
        jdbc.update(DELETE_HERO_SUPERPOWER, superpowerName);
        
        final String DELETE_SUPERPOWER = "DELETE FROM Superpower WHERE SuperpowerName = ?";
        jdbc.update(DELETE_SUPERPOWER, superpowerName);
    }

    @Override
    public List<Superpower> getSuperpowersForHero(Hero hero) {
        final String SELECT_SUPERPOWERS_FOR_HERO = "SELECT s.* FROM Superpower s JOIN Hero_Superpower hs ON s.SuperpowerName = hs.SuperpowerName WHERE hs.HeroName = ?";
        List<Superpower> superpowers = jdbc.query(SELECT_SUPERPOWERS_FOR_HERO, new SuperpowerMapper(), hero.getHeroName());
        return superpowers;
    }
    
    public static final class SuperpowerMapper implements RowMapper<Superpower> {

        @Override
        public Superpower mapRow(ResultSet rs, int i) throws SQLException {
            Superpower superpower = new Superpower();
            superpower.setSuperpowerName(rs.getString("SuperpowerName"));
            superpower.setDescription(rs.getString("Description"));
            return superpower;
        }
        
    }
    
}
