/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.superherosighting.dao;

import com.mrl.superherosighting.dto.Hero;
import com.mrl.superherosighting.dto.Location;
import com.mrl.superherosighting.dto.Organization;
import com.mrl.superherosighting.dto.Sighting;
import com.mrl.superherosighting.dto.Superpower;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author flafo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationDaoDBTest {

    @Autowired
    HeroDao heroDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    SightingDao sightingDao;

    @Autowired
    SuperpowerDao superpowerDao;

    public LocationDaoDBTest() {
    }

    @BeforeEach
    public void setUp() {
        List<Hero> heroes = heroDao.getAllHeroes();
        for (Hero hero : heroes) {
            heroDao.deleteHeroByHeroName(hero.getHeroName());
        }

        List<Location> locations = locationDao.getAllLocations();
        for (Location location : locations) {
            locationDao.deleteLocationByLocationName(location.getLocationName());
        }

        List<Organization> organizations = organizationDao.getAllOrganizations();
        for (Organization organization : organizations) {
            organizationDao.deleteOrganizationByOrganzationName(organization.getOrganizationName());
        }

        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting sighting : sightings) {
            sightingDao.deleteSightingById(sighting.getSightingId());
        }

        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        for (Superpower superpower : superpowers) {
            superpowerDao.deleteSuperpowerBySuperpowerName(superpower.getSuperpowerName());
        }
    }

    @Test
    public void testAddAndGetLocation() {
        Location location = new Location();
        location.setLocationName("Location Name");
        location.setDescription("Location Description");
        location.setAddress("Location Address");
        location.setCity("Location City");
        location.setState("LS");
        location.setZip("LZip");
        location.setLatitude("L Lat");
        location.setLongitude("L Lon");
        location = locationDao.addLocation(location);

        Location fromDao = locationDao.getLocationByLocationName(location.getLocationName());
        assertEquals(location, fromDao);
    }

    @Test
    public void testGetAllLocations() {
        Location location = new Location();
        location.setLocationName("Location Name");
        location.setDescription("Location Description");
        location.setAddress("Location Address");
        location.setCity("Location City");
        location.setState("LS");
        location.setZip("LZip");
        location.setLatitude("L Lat");
        location.setLongitude("L Lon");
        location = locationDao.addLocation(location);

        Location location2 = new Location();
        location2.setLocationName("Location Name 2");
        location2.setDescription("Location Description 2");
        location2.setAddress("Location Address 2");
        location2.setCity("Location City 2");
        location2.setState("LS");
        location2.setZip("LZip");
        location2.setLatitude("L Lat");
        location2.setLongitude("L Lon");
        location2 = locationDao.addLocation(location2);

        List<Location> locations = locationDao.getAllLocations();
        assertEquals(2, locations.size());
        assertTrue(locations.contains(location));
        assertTrue(locations.contains(location2));

    }

    @Test
    public void testUpdateLocation() {
        Location location = new Location();
        location.setLocationName("Location Name");
        location.setDescription("Location Description");
        location.setAddress("Location Address");
        location.setCity("Location City");
        location.setState("LS");
        location.setZip("LZip");
        location.setLatitude("L Lat");
        location.setLongitude("L Lon");
        location = locationDao.addLocation(location);

        Location fromDao = locationDao.getLocationByLocationName(location.getLocationName());
        assertEquals(location, fromDao);

        location.setCity("updated city");
        locationDao.updateLocation(location);
        assertNotEquals(location, fromDao);
        fromDao = locationDao.getLocationByLocationName(location.getLocationName());
        assertEquals(location, fromDao);

    }

    @Test
    public void testDeleteLocation() {
        Superpower superpower = new Superpower();
        superpower.setSuperpowerName("Superpower name");
        superpower.setDescription("Superpower description");
        superpower = superpowerDao.addSuperpower(superpower);

        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);

        Hero hero = new Hero();
        hero.setHeroName("Hero Name");
        hero.setDescription("Hero Description");
        hero.setSuperpowers(superpowers);
        hero = heroDao.addHero(hero);
        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);
        

        Location location = new Location();
        location.setLocationName("Location Name");
        location.setDescription("Location Description");
        location.setAddress("Location Address");
        location.setCity("Location City");
        location.setState("LS");
        location.setZip("LZip");
        location.setLatitude("L Lat");
        location.setLongitude("L Lon");
        location = locationDao.addLocation(location);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.of(1990, Month.MARCH, 2));
        sighting.setHero(hero);
        sighting.setLocation(location);
        sighting = sightingDao.addSighting(sighting);

        Location fromDao = locationDao.getLocationByLocationName(location.getLocationName());
        assertEquals(location, fromDao);

        locationDao.deleteLocationByLocationName(location.getLocationName());
        fromDao = locationDao.getLocationByLocationName(location.getLocationName());
        assertNull(fromDao);

    }

    @Test
    public void testGetLocationsForHero() {
        Superpower superpower = new Superpower();
        superpower.setSuperpowerName("Superpower name");
        superpower.setDescription("Superpower description");
        superpower = superpowerDao.addSuperpower(superpower);

        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);

        Hero hero = new Hero();
        hero.setHeroName("Hero Name");
        hero.setDescription("Hero Description");
        hero.setSuperpowers(superpowers);
        hero = heroDao.addHero(hero);

        Hero hero2 = new Hero();
        hero2.setHeroName("Hero Name 2");
        hero2.setDescription("Hero Description 2");
        hero2.setSuperpowers(superpowers);
        hero2 = heroDao.addHero(hero2);

        Location location = new Location();
        location.setLocationName("Location Name");
        location.setDescription("Location Description");
        location.setAddress("Location Address");
        location.setCity("Location City");
        location.setState("LS");
        location.setZip("LZip");
        location.setLatitude("L Lat");
        location.setLongitude("L Lon");
        location = locationDao.addLocation(location);

        Location location2 = new Location();
        location2.setLocationName("Location Name 2");
        location2.setDescription("Location Description 2");
        location2.setAddress("Location Address 2");
        location2.setCity("Location City 2");
        location2.setState("LS");
        location2.setZip("LZip");
        location2.setLatitude("L Lat");
        location2.setLongitude("L Lon");
        location2 = locationDao.addLocation(location2);

        Location location3 = new Location();
        location3.setLocationName("Location Name 3");
        location3.setDescription("Location Description 3");
        location3.setAddress("Location Address 3");
        location3.setCity("Location City 3");
        location3.setState("LS");
        location3.setZip("LZip");
        location3.setLatitude("L Lat");
        location3.setLongitude("L Lon");
        location3 = locationDao.addLocation(location3);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.of(1990, Month.MARCH, 2));
        sighting.setHero(hero);
        sighting.setLocation(location);
        sighting = sightingDao.addSighting(sighting);

        Sighting sighting2 = new Sighting();
        sighting2.setDate(LocalDate.of(1990, Month.MARCH, 2));
        sighting2.setHero(hero2);
        sighting2.setLocation(location2);
        sighting2 = sightingDao.addSighting(sighting2);

        Sighting sighting3 = new Sighting();
        sighting3.setDate(LocalDate.of(1990, Month.MARCH, 2));
        sighting3.setHero(hero);
        sighting3.setLocation(location3);
        sighting3 = sightingDao.addSighting(sighting3);

        List<Location> locations = locationDao.getLocationsForHero(hero);
        assertEquals(2, locations.size());
        assertTrue(locations.contains(location));
        assertFalse(locations.contains(location2));
        assertTrue(locations.contains(location3));
    }

}
