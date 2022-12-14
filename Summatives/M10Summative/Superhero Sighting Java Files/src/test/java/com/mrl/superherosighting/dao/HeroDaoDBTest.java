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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
public class HeroDaoDBTest {

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

    public HeroDaoDBTest() {
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
    public void testAddAndGetHero() {
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

        Hero fromDao = heroDao.getHeroByHeroName(hero.getHeroName());
        assertEquals(hero, fromDao);
    }

    @Test
    public void testGetAllHeroes() {
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

        List<Hero> heroes = heroDao.getAllHeroes();
        assertEquals(2, heroes.size());
        assertTrue(heroes.contains(hero));
        assertTrue(heroes.contains(hero2));
    }

    @Test
    public void testUpdateHero() {
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

        Hero fromDao = heroDao.getHeroByHeroName(hero.getHeroName());
        assertEquals(hero, fromDao);

        hero.setDescription("Updated Hero Description");
        Superpower superpower2 = new Superpower();
        superpower2.setSuperpowerName("Superpower name 2");
        superpower2.setDescription("Superpower description 2");
        superpower2 = superpowerDao.addSuperpower(superpower2);
        superpowers.add(superpower2);
        hero.setSuperpowers(superpowers);

        heroDao.updateHero(hero);
        assertNotEquals(hero, fromDao);
        fromDao = heroDao.getHeroByHeroName(hero.getHeroName());
        assertEquals(hero, fromDao);

    }

    @Test
    public void testDeleteHero() {
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

        Organization organization = new Organization();
        organization.setOrganizationName("Organzation name");
        organization.setDescription("Organization Description");
        organization.setAddress("Organization Address");
        organization.setCity("Organzation City");
        organization.setState("OS");
        organization.setZip("OZip");
        organization.setPhone("Org Phone");
        organization.setEmail("Organization email");
        organization.setHeroes(heroes);
        organization = organizationDao.addOrganization(organization);

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

        Hero fromDao = heroDao.getHeroByHeroName(hero.getHeroName());
        assertEquals(hero, fromDao);

        heroDao.deleteHeroByHeroName(hero.getHeroName());

        fromDao = heroDao.getHeroByHeroName(hero.getHeroName());
        assertNull(fromDao);
    }

    @Test
    public void testGetHeroesForOrganization() {
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

        Hero hero3 = new Hero();
        hero3.setHeroName("Hero Name 3");
        hero3.setDescription("Hero Description 3");
        hero3.setSuperpowers(superpowers);
        hero3 = heroDao.addHero(hero3);

        List<Hero> heroes1 = new ArrayList<>();
        heroes1.add(hero);
        heroes1.add(hero2);
        heroes1.add(hero3);

        List<Hero> heroes2 = new ArrayList<>();
        heroes2.add(hero);
        heroes2.add(hero3);

        Organization organization = new Organization();
        organization.setOrganizationName("Organzation name");
        organization.setDescription("Organization Description");
        organization.setAddress("Organization Address");
        organization.setCity("Organzation City");
        organization.setState("OS");
        organization.setZip("OZip");
        organization.setPhone("Org Phone");
        organization.setEmail("Organization email");
        organization.setHeroes(heroes2);
        organization = organizationDao.addOrganization(organization);

        List<Hero> heroes = heroDao.getHeroesForOrganization(organization);
        assertEquals(2, heroes.size());
        assertTrue(heroes.contains(hero));
        assertFalse(heroes.contains(hero2));
        assertTrue(heroes.contains(hero3));
    }

    @Test
    public void testGetHeroesForLocation() {
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

        Hero hero3 = new Hero();
        hero3.setHeroName("Hero Name 3");
        hero3.setDescription("Hero Description 3");
        hero3.setSuperpowers(superpowers);
        hero3 = heroDao.addHero(hero3);

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
        sighting3.setHero(hero3);
        sighting3.setLocation(location);
        sighting3 = sightingDao.addSighting(sighting3);

        List<Hero> heroes = heroDao.getHeroesForLocation(location);
        assertEquals(2, heroes.size());
        assertTrue(heroes.contains(hero));
        assertFalse(heroes.contains(hero2));
        assertTrue(heroes.contains(hero3));
    }

}
