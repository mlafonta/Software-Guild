/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.superherosighting.dao;

import com.mrl.superherosighting.dao.HeroDaoDB.HeroMapper;
import com.mrl.superherosighting.dto.Hero;
import com.mrl.superherosighting.dto.Orginization;
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
public class OrginizationDaoDB implements OrginizationDao{
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Orginization getOrginizationByOrginizationName(String orginizationName) {
        try {
            final String SELECT_ORGINIZATION_BY_NAME = "SELECT * FROM Originization WHERE OrginizationName = ?";
            Orginization orginization = jdbc.queryForObject(SELECT_ORGINIZATION_BY_NAME, new OrginizationMapper(), orginizationName);
            orginization.setHeroes(getHeroesForOrginization(orginizationName));
            return orginization;
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Orginization> getAllOrginizations() {
        final String SELECT_ALL_ORGINIZATIONS = "SELECT * FROM Orginization";
        List<Orginization> orginizations = jdbc.query(SELECT_ALL_ORGINIZATIONS, new OrginizationMapper());
        associateHeroes(orginizations);
        return orginizations;
    }

    @Override
    @Transactional
    public Orginization addOrginization(Orginization orginization) {
        final String INSERT_ORGINIZATION = "INSERT INTO Organization(OrganizationName, Description, Address, City, State, ZIP, Phone, Email) VALUES (?,?,?,?,?,?,?,?)";
        jdbc.update(INSERT_ORGINIZATION, orginization.getOrginizationName(), orginization.getDescription(), orginization.getAddress(), orginization.getCity(), orginization.getState(), orginization.getZip(), orginization.getPhone(), orginization.getEmail());
        insertHeroOrginization(orginization);
        return orginization;
    }

    @Override
    @Transactional
    public void updateOrginization(Orginization orginization) {
        final String UPDATE_ORGINIZATION = "UPDATE Orginization SET Description = ?, Address = ?, City = ?, State = ?, ZIP = ?, Phone = ?, Email = ? WHERE OrginizationName = ?";
        jdbc.update(UPDATE_ORGINIZATION, orginization.getDescription(), orginization.getAddress(), orginization.getCity(), orginization.getState(), orginization.getZip(), orginization.getPhone(), orginization.getEmail(), orginization.getOrginizationName());
        final String DELETE_HERO_ORGINIZATION = "DELETE FROM Hero_Orginization WHERE OrginizationName = ?";
        jdbc.update(DELETE_HERO_ORGINIZATION, orginization.getOrginizationName());
        insertHeroOrginization(orginization);
        
    }

    @Override
    @Transactional
    public void deleteOrginizationByOrginzationName(String orginizationName) {
        final String DELETE_HERO_ORGINIZATION = "DELETE FROM Hero_Orginization WHERE OrginizationName = ?";
        jdbc.update(DELETE_HERO_ORGINIZATION, orginizationName);
        final String DELETE_ORGINIZATION = "DELETE FROM Orginization WHERE OrginizationName = ?";
        jdbc.update(DELETE_ORGINIZATION, orginizationName);
    }

    @Override
    public List<Orginization> getOrginizationsForHero(Hero hero) {
        final String SELECT_ORGINIZATIONS_FOR_HERO = "SELECT o.* FROM Orginization o JOIN Hero_Orginization ho ON o.OrginizationName = ho.OrginizationName WHERE ho.HeroName = ?";
        List<Orginization> orginizations = jdbc.query(SELECT_ORGINIZATIONS_FOR_HERO, new OrginizationMapper(), hero.getHeroName());
        associateHeroes(orginizations);
        return orginizations;
    }

    private List<Hero> getHeroesForOrginization(String orginizationName) {
        final String SELECT_HEROES_FOR_ORGINIZATION = "SELECT h.* FROM Hero h JOIN Hero_Orginization ho ON ho.HeroName = h.HeroName WHERE ho.OrginizationName = ?";
        return jdbc.query(SELECT_HEROES_FOR_ORGINIZATION, new HeroMapper(), orginizationName);
    }

    private void associateHeroes(List<Orginization> orginizations) {
        for (Orginization orginization : orginizations) {
            orginization.setHeroes(getHeroesForOrginization(orginization.getOrginizationName()));
        }
    }

    private void insertHeroOrginization(Orginization orginization) {
        final String INSERT_HERO_ORGINIZATION = "INSERT INTO Hero_Orginization(HeroName, OrginizationName) VALUES(?,?)";
        for(Hero hero : orginization.getHeroes()) {
            jdbc.update(INSERT_HERO_ORGINIZATION, hero.getHeroName(), orginization.getOrginizationName());
        }
    }
    
    public static final class OrginizationMapper implements RowMapper<Orginization> {

        @Override
        public Orginization mapRow(ResultSet rs, int i) throws SQLException {
            Orginization orginization = new Orginization();
            orginization.setOriginizationName(rs.getString("OrginizationName"));
            orginization.setDescription(rs.getString("Description"));
            orginization.setAddress(rs.getString("Address"));
            orginization.setCity(rs.getString("City"));
            orginization.setState(rs.getString("State"));
            orginization.setZip(rs.getString("ZIP"));
            orginization.setPhone(rs.getString("Phone"));
            orginization.setEmail(rs.getString("Email"));
            return orginization;
        }
        
    }
}
