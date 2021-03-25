/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.superherosighting.dao;

import com.mrl.superherosighting.dao.HeroDaoDB.HeroMapper;
import com.mrl.superherosighting.dao.LocationDaoDB.LocationMapper;
import com.mrl.superherosighting.dto.Hero;
import com.mrl.superherosighting.dto.Location;
import com.mrl.superherosighting.dto.Organization;
import com.mrl.superherosighting.dto.Sighting;
import com.mrl.superherosighting.dto.Superpower;
import java.sql.Date;
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
public class SightingDaoDB implements SightingDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Sighting getSightingById(int sightingId) {
        try {
            final String SELECT_SIGHTING_BY_ID = "SELECT * FROM Sighting WHERE SightingId = ?";
            Sighting sighting = jdbc.queryForObject(SELECT_SIGHTING_BY_ID, new SightingMapper(), sightingId);
            sighting.setHero(getHeroForSighting(sightingId));
            sighting.setLocation(getLocationForSighting(sightingId));
            return sighting;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightings() {
        final String SELECT_ALL_SIGHTINGS = "SELECT * FROM Sighting";
        List<Sighting> sightings = jdbc.query(SELECT_ALL_SIGHTINGS, new SightingMapper());
        associateHeroAndLocation(sightings);
        return sightings;
    }

    @Override
    @Transactional
    public Sighting addSighting(Sighting sighting) {
        final String INSERT_SIGHTING = "INSERT INTO Sighting(Date, HeroName, LocationName) VALUES(?,?,?)";
        jdbc.update(INSERT_SIGHTING, Date.valueOf(sighting.getDate()), sighting.getHero().getHeroName(), sighting.getLocation().getLocationName());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setSightingId(newId);
        return sighting;
    }

    @Override
    public void updateSighting(Sighting sighting) {
        final String UPDATE_SIGHTING = "UPDATE Sighting SET Date = ?, HeroName = ?, LocationName = ? WHERE SightingId = ?";
        jdbc.update(UPDATE_SIGHTING, Date.valueOf(sighting.getDate()), sighting.getHero().getHeroName(), sighting.getLocation().getLocationName(), sighting.getSightingId());
    }

    @Override
    public void deleteSightingById(int sightingId) {
        final String DELETE_SIGHTING = "DELETE FROM Sighting WHERE SightingId = ?";
        jdbc.update(DELETE_SIGHTING, sightingId);
    }

    @Override
    public List<Sighting> getSightingsForHero(Hero hero) {
        final String SELECT_SIGHTINGS_FOR_HERO = "SELECT * FROM Sighting WHERE HeroName = ?";
        List<Sighting> sightings = jdbc.query(SELECT_SIGHTINGS_FOR_HERO, new SightingMapper(), hero.getHeroName());
        associateHeroAndLocation(sightings);
        return sightings;
    }

    @Override
    public List<Sighting> getSightingsForLocation(Location location) {
        final String SELECT_SIGHTINGS_FOR_LOCATION = "SELECT * FROM Sighting WHERE LocationName = ?";
        List<Sighting> sightings = jdbc.query(SELECT_SIGHTINGS_FOR_LOCATION, new SightingMapper(), location.getLocationName());
        associateHeroAndLocation(sightings);
        return sightings;
    }

    private Hero getHeroForSighting(int sightingId) {
        final String SELECT_HERO_FOR_SIGHTING = "SELECT h.* FROM Hero h JOIN Sighting s ON h.HeroName = s.HeroName WHERE s.SightingId = ?";
        Hero hero = jdbc.queryForObject(SELECT_HERO_FOR_SIGHTING, new HeroMapper(), sightingId);
        final String SELECT_SUPERPOWERS_FOR_HERO = "SELECT s.* FROM Superpower s JOIN Hero_Superpower hs ON s.SuperpowerName = hs.SuperpowerName WHERE hs.HeroName = ?";
        List<Superpower> superpowers = jdbc.query(SELECT_SUPERPOWERS_FOR_HERO, new SuperpowerDaoDB.SuperpowerMapper(), hero.getHeroName());
        hero.setSuperpowers(superpowers);
        final String SELECT_organIZATIONS_FOR_HERO = "SELECT o.* FROM organization o JOIN Hero_organization ho ON o.organizationName = ho.organizationName WHERE ho.HeroName = ?";
        List<Organization> organizations = jdbc.query(SELECT_organIZATIONS_FOR_HERO, new OrganizationDaoDB.OrganizationMapper(), hero.getHeroName());
        hero.setOrganizations(organizations);
        return hero;
    }

    private Location getLocationForSighting(int sightingId) {
        final String SELECT_LOCATION_FOR_SIGHTING = "SELECT l.* FROM Location l JOIN Sighting s ON l.LocationName = s.LocationName WHERE s.SightingId = ?";
        return jdbc.queryForObject(SELECT_LOCATION_FOR_SIGHTING, new LocationMapper(), sightingId);
    }

    private void associateHeroAndLocation(List<Sighting> sightings) {
        for (Sighting sighting : sightings) {
            sighting.setHero(getHeroForSighting(sighting.getSightingId()));
            sighting.setLocation(getLocationForSighting(sighting.getSightingId()));
        }
    }

    public static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingId((rs.getInt("SightingId")));
            sighting.setDate(rs.getDate("Date").toLocalDate());
            return sighting;
        }

    }
}
