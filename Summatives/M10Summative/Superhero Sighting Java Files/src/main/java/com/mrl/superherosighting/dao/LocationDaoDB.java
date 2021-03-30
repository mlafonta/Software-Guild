/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.superherosighting.dao;

import com.mrl.superherosighting.dto.Hero;
import com.mrl.superherosighting.dto.Location;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
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
public class LocationDaoDB implements LocationDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Location getLocationByLocationName(String locationName) {
        try {
            final String SELECT_LOCATION_BY_NAME = "SELECT * FROM Location WHERE LocationName = ?";
            return jdbc.queryForObject(SELECT_LOCATION_BY_NAME, new LocationMapper(), locationName);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        final String SELECT_ALL_LOCATIONS = "SELECT * FROM Location";
        return jdbc.query(SELECT_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    public Location addLocation(Location location) {
        final String INSERT_LOCATION = "INSERT INTO Location(LocationName, Description, Address, City, State, ZIP, Latitude, Longitude) VALUES(?,?,?,?,?,?,?,?)";
        jdbc.update(INSERT_LOCATION, location.getLocationName(), location.getDescription(), location.getAddress(), location.getCity(), location.getState(), location.getZip(), location.getLatitude(), location.getLongitude());
        return location;
    }

    @Override
    public void updateLocation(Location location) {
        final String UPDATE_LOCATION = "UPDATE Location SET Description = ?, Address = ?, City = ?, State = ?, ZIP = ?, latitude = ?, longitude = ? WHERE LocationName = ?";
        jdbc.update(UPDATE_LOCATION, location.getDescription(), location.getAddress(), location.getCity(), location.getState(), location.getZip(), location.getLatitude(), location.getLongitude(), location.getLocationName());
    }

    @Override
    @Transactional
    public void deleteLocationByLocationName(String locationName) {
        final String DELETE_SIGHTING = "DELETE FROM Sighting WHERE LocationName = ?";
        jdbc.update(DELETE_SIGHTING, locationName);
        final String DELETE_LOCATION = "DELETE FROM Location WHERE LocationName = ?";
        jdbc.update(DELETE_LOCATION, locationName);
    }

    @Override
    public List<Location> getLocationsForHero(Hero hero) {
        final String SELECT_LOCATIONS_FOR_HERO = "SELECT l.* FROM Location l JOIN Sighting s ON s.LocationName = l.LocationName WHERE s.HeroName = ?";
        List<Location> locations = jdbc.query(SELECT_LOCATIONS_FOR_HERO, new LocationMapper(), hero.getHeroName());
        return locations.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location location = new Location();
            location.setLocationName(rs.getString("LocationName"));
            location.setDescription(rs.getString("Description"));
            location.setAddress(rs.getString("Address"));
            location.setCity(rs.getString("City"));
            location.setState(rs.getString("State"));
            location.setZip(rs.getString("ZIP"));
            location.setLatitude(rs.getString("Latitude"));
            location.setLongitude(rs.getString("longitude"));
            return location;
        }

    }
}
