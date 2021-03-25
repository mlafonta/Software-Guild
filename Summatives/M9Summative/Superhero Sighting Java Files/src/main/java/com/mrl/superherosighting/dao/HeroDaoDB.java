/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.superherosighting.dao;

import com.mrl.superherosighting.dao.OrginizationDaoDB.OrginizationMapper;
import com.mrl.superherosighting.dao.SuperpowerDaoDB.SuperpowerMapper;
import com.mrl.superherosighting.dto.Hero;
import com.mrl.superherosighting.dto.Location;
import com.mrl.superherosighting.dto.Orginization;
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
public class HeroDaoDB implements HeroDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Hero getHeroByHeroName(String heroName) {
        try {
            final String SELECT_HERO_BY_NAME = "SELECT * FROM Hero WHERE HeroName =? ";
            Hero hero = jdbc.queryForObject(SELECT_HERO_BY_NAME, new HeroMapper(), heroName);
            hero.setOrginizations(getOrginizationsForHero(heroName));
            hero.setSuperpowers(getSuperpowersForHero(heroName));
            return hero;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Hero> getAllHeroes() {
        final String SELECT_ALL_HEROES = "SELECT * FROM Hero";
        List<Hero> heroes = jdbc.query(SELECT_ALL_HEROES, new HeroMapper());
        associateOrginizationsAndSuperpowers(heroes);
        return heroes;
    }

    @Override
    @Transactional
    public Hero addHero(Hero hero) {
        final String INSERT_HERO = "INSERT INTO Hero(HeroName, Description) VALUES(?,?)";
        jdbc.update(INSERT_HERO, hero.getHeroName(), hero.getDescription());
        insertHeroOrginization(hero);
        insertHeroSuperpower(hero);
        return hero;
    }

    @Override
    @Transactional
    public void updateHero(Hero hero) {
        final String UPDATE_HERO = "UPDATE Hero SET Description = ? WHERE HeroName = ?";
        jdbc.update(UPDATE_HERO, hero.getDescription(), hero.getHeroName());
        final String DELETE_HERO_ORGINIZATION = "DELETE FROM Hero_Orginization WHERE HeroName = ?";
        jdbc.update(DELETE_HERO_ORGINIZATION, hero.getHeroName());
        insertHeroOrginization(hero);
        final String DELETE_HERO_SUPERPOWER = "DELETE FROM Hero_Superpower WHERE HeroName = ?";
        jdbc.update(DELETE_HERO_SUPERPOWER, hero.getHeroName());
        insertHeroSuperpower(hero);
    }

    @Override
    @Transactional
    public void deleteHeroByHeroName(String heroName) {
        final String DELETE_HERO_ORGINIZATION = "DELETE FROM Hero_Orginization WHERE HeroName = ?";
        jdbc.update(DELETE_HERO_ORGINIZATION, heroName);
        final String DELETE_HERO_SUPERPOWER = "DELETE FROM Hero_Superpower WHERE HeroName = ?";
        jdbc.update(DELETE_HERO_SUPERPOWER, heroName);
        final String DELETE_SIGHTING = "DELETE FROM Sighting WHERE HeroName = ?";
        jdbc.update(DELETE_SIGHTING, heroName);
        final String DELETE_HERO = "DELETE FROM Hero WHERE HeroName = ?";
        jdbc.update(DELETE_HERO, heroName);
    }

    @Override
    public List<Hero> getHeroesForOrginization(Orginization orginization) {
        final String SELECT_HEROES_FOR_ORGINIZATION = "Select h.* FROM Hero h JOIN Hero_Orginization ho ON ho.HeroName = h.HeroName WHERE ho.OrginizationName = ?";
        List<Hero> heroes = jdbc.query(SELECT_HEROES_FOR_ORGINIZATION, new HeroMapper(), orginization.getOrginizationName());
        associateOrginizationsAndSuperpowers(heroes);
        return heroes;
    }

    @Override
    public List<Hero> getHeroesForLocation(Location location) {
        final String SELECT_HEROES_FOR_LOCATION = "SELECT h.* FROM Hero h JOIN Sighting s ON s.HeroName = h.HeroName WHERE s.LocationName = ?";
        List<Hero> heroes = jdbc.query(SELECT_HEROES_FOR_LOCATION, new HeroMapper(), location.getLocationName());
        associateOrginizationsAndSuperpowers(heroes);
        return heroes;
    }

    private List<Orginization> getOrginizationsForHero(String heroName) {
        final String SELECT_ORGINIZATIONS_FOR_HERO = "SELECT o.* FROM Orginization o JOIN Hero_Orginization ho ON o.OrginizationName = ho.orginizationName WHERE ho.HeroName = ?";
        return jdbc.query(SELECT_ORGINIZATIONS_FOR_HERO, new OrginizationMapper(), heroName);
    }

    private List<Superpower> getSuperpowersForHero(String heroName) {
        final String SELECT_SUPERPOWERS_FOR_HERO = "SELECT s.* FROM Superpower s JOIN Hero_Superpower hs ON s.SuperpowerName = hs.SuperpowerName WHERE hs.HeroName = ?";
        return jdbc.query(SELECT_SUPERPOWERS_FOR_HERO, new SuperpowerMapper(), heroName);
    }

    private void associateOrginizationsAndSuperpowers(List<Hero> heroes) {
        for (Hero hero : heroes) {
            hero.setOrginizations(getOrginizationsForHero(hero.getHeroName()));
            hero.setSuperpowers(getSuperpowersForHero(hero.getHeroName()));

        }
    }

    private void insertHeroOrginization(Hero hero) {
        final String INSERT_HERO_ORGINIZATION = "INSERT INTO Hero_Orginization(HeroName, OrginizationName) VALUES(?,?)";
        for (Orginization orginization : hero.getOrginizations()) {
            jdbc.update(INSERT_HERO_ORGINIZATION, hero.getHeroName(), orginization.getOrginizationName());
        }
    }

    private void insertHeroSuperpower(Hero hero) {
        final String INSERT_HERO_SUPERPOWER = "INSERT INTO Hero_Superpower(HeroName, SuperpowerName) VALUES(?,?)";
        for (Superpower superpower : hero.getSuperpowers()) {
            jdbc.update(INSERT_HERO_SUPERPOWER, hero.getHeroName(), superpower.getSuperpowerName());
        }
    }

    public static final class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet rs, int i) throws SQLException {
            Hero hero = new Hero();
            hero.setHeroName(rs.getString("HeroName"));
            hero.setDescription(rs.getString("Description"));
            return hero;
        }

    }
}
