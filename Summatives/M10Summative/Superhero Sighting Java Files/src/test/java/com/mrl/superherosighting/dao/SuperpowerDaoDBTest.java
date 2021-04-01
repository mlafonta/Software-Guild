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
public class SuperpowerDaoDBTest {

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

    public SuperpowerDaoDBTest() {
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
    public void testAddAndGetSuperpower() {
        Superpower superpower = new Superpower();
        superpower.setSuperpowerName("Superpower name");
        superpower.setDescription("Superpower description");
        superpower = superpowerDao.addSuperpower(superpower);

        Superpower fromDao = superpowerDao.getSuperpowerBySuperpowerName(superpower.getSuperpowerName());
        assertEquals(superpower, fromDao);
    }

    @Test
    public void testGetAllSuperpowers() {
        Superpower superpower = new Superpower();
        superpower.setSuperpowerName("Superpower name");
        superpower.setDescription("Superpower description");
        superpower = superpowerDao.addSuperpower(superpower);

        Superpower superpower2 = new Superpower();
        superpower2.setSuperpowerName("Superpower name 2");
        superpower2.setDescription("Superpower description");
        superpower2 = superpowerDao.addSuperpower(superpower2);

        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        assertEquals(2, superpowers.size());
        assertTrue(superpowers.contains(superpower));
        assertTrue(superpowers.contains(superpower2));
    }

    @Test
    public void testUpdateSuperpower() {
        Superpower superpower = new Superpower();
        superpower.setSuperpowerName("Superpower name");
        superpower.setDescription("Superpower description");
        superpower = superpowerDao.addSuperpower(superpower);

        Superpower fromDao = superpowerDao.getSuperpowerBySuperpowerName(superpower.getSuperpowerName());
        assertEquals(superpower, fromDao);

        superpower.setDescription("Updated Description");
        assertNotEquals(superpower, fromDao);

        superpowerDao.updateSuperpower(superpower);
        fromDao = superpowerDao.getSuperpowerBySuperpowerName(superpower.getSuperpowerName());
        assertEquals(superpower, fromDao);
    }

    @Test
    public void testDeleteSuperpower() {
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

        Superpower fromDao = superpowerDao.getSuperpowerBySuperpowerName(superpower.getSuperpowerName());
        assertEquals(superpower, fromDao);

        superpowerDao.deleteSuperpowerBySuperpowerName(superpower.getSuperpowerName());
        fromDao = superpowerDao.getSuperpowerBySuperpowerName(superpower.getSuperpowerName());
        assertNull(fromDao);
    }

    @Test
    public void testGetSuperpowersForHero() {
        Superpower superpower = new Superpower();
        superpower.setSuperpowerName("Superpower name");
        superpower.setDescription("Superpower description");
        superpower = superpowerDao.addSuperpower(superpower);

        Superpower superpower2 = new Superpower();
        superpower2.setSuperpowerName("Superpower name 2");
        superpower2.setDescription("Superpower description");
        superpower2 = superpowerDao.addSuperpower(superpower2);
        
        Superpower superpower3 = new Superpower();
        superpower3.setSuperpowerName("Superpower name 3");
        superpower3.setDescription("Superpower description");
        superpower3 = superpowerDao.addSuperpower(superpower3);
        
        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        superpowers.add(superpower2);
        superpowers.add(superpower3);
        
        List<Superpower> superpowers2 = new ArrayList<>();
        superpowers2.add(superpower);
        superpowers2.add(superpower3);
        
        Hero hero = new Hero();
        hero.setHeroName("Hero Name");
        hero.setDescription("Hero Description");
        hero.setSuperpowers(superpowers);
        hero = heroDao.addHero(hero);
        
        Hero hero2 = new Hero();
        hero2.setHeroName("Hero Name 2");
        hero2.setDescription("Hero Description");
        hero2.setSuperpowers(superpowers2);
        hero2 = heroDao.addHero(hero2);
        
        List<Superpower> superpowers4 = superpowerDao.getSuperpowersForHero(hero2);
        assertEquals(2, superpowers4.size());
        assertTrue(superpowers4.contains(superpower));
        assertFalse(superpowers4.contains(superpower2));
        assertTrue(superpowers4.contains(superpower3));
    }
}