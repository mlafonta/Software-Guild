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
public class SightingDaoDBTest {

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

    public SightingDaoDBTest() {
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
    public void testAddAndGetSighting() {
        Superpower superpower = new Superpower();
        superpower.setSuperpowerName("Superpower name");
        superpower.setDescription("Superpower description");
        superpower = superpowerDao.addSuperpower(superpower);

        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);

        Organization organization = new Organization();
        organization.setOrganizationName("Organzation name");
        organization.setDescription("Organization Description");
        organization.setAddress("Organization Address");
        organization.setCity("Organzation City");
        organization.setState("OS");
        organization.setZip("OZip");
        organization.setPhone("Org Phone");
        organization.setEmail("Organization email");
        organization = organizationDao.addOrganization(organization);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);

        Hero hero = new Hero();
        hero.setHeroName("Hero Name");
        hero.setDescription("Hero Description");
        hero.setOrganizations(organizations);
        hero.setSuperpowers(superpowers);
        hero = heroDao.addHero(hero);

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

        Sighting fromDao = sightingDao.getSightingById(sighting.getSightingId());
        assertEquals(sighting, fromDao);
    }

    @Test
    public void testGetAllSightings() {
        Superpower superpower = new Superpower();
        superpower.setSuperpowerName("Superpower name");
        superpower.setDescription("Superpower description");
        superpower = superpowerDao.addSuperpower(superpower);

        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);

        Organization organization = new Organization();
        organization.setOrganizationName("Organzation name");
        organization.setDescription("Organization Description");
        organization.setAddress("Organization Address");
        organization.setCity("Organzation City");
        organization.setState("OS");
        organization.setZip("OZip");
        organization.setPhone("Org Phone");
        organization.setEmail("Organization email");
        organization = organizationDao.addOrganization(organization);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);

        Hero hero = new Hero();
        hero.setHeroName("Hero Name");
        hero.setDescription("Hero Description");
        hero.setOrganizations(organizations);
        hero.setSuperpowers(superpowers);
        hero = heroDao.addHero(hero);

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

        Sighting sighting2 = new Sighting();
        sighting2.setDate(LocalDate.of(1990, Month.MARCH, 2));
        sighting2.setHero(hero);
        sighting2.setLocation(location);
        sighting2 = sightingDao.addSighting(sighting2);

        List<Sighting> sightings = sightingDao.getAllSightings();
        assertEquals(2, sightings.size());
        assertTrue(sightings.contains(sighting));
        assertTrue(sightings.contains(sighting2));
    }

    @Test
    public void testUpdateSighting() {
        Superpower superpower = new Superpower();
        superpower.setSuperpowerName("Superpower name");
        superpower.setDescription("Superpower description");
        superpower = superpowerDao.addSuperpower(superpower);

        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);

        Organization organization = new Organization();
        organization.setOrganizationName("Organzation name");
        organization.setDescription("Organization Description");
        organization.setAddress("Organization Address");
        organization.setCity("Organzation City");
        organization.setState("OS");
        organization.setZip("OZip");
        organization.setPhone("Org Phone");
        organization.setEmail("Organization email");
        organization = organizationDao.addOrganization(organization);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);

        Hero hero = new Hero();
        hero.setHeroName("Hero Name");
        hero.setDescription("Hero Description");
        hero.setOrganizations(organizations);
        hero.setSuperpowers(superpowers);
        hero = heroDao.addHero(hero);

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

        Sighting fromDao = sightingDao.getSightingById(sighting.getSightingId());
        assertEquals(sighting, fromDao);

        Location location2 = new Location();
        location2.setLocationName("Location Name 2");
        location2.setDescription("Location Description");
        location2.setAddress("Location Address");
        location2.setCity("Location City");
        location2.setState("LS");
        location2.setZip("LZip");
        location2.setLatitude("L Lat");
        location2.setLongitude("L Lon");
        location2 = locationDao.addLocation(location2);

        sighting.setLocation(location2);
        sighting.setDate(LocalDate.of(1990, Month.MARCH, 8));
        assertNotEquals(sighting, fromDao);

        sightingDao.updateSighting(sighting);
        fromDao = sightingDao.getSightingById(sighting.getSightingId());
        assertEquals(sighting, fromDao);
    }

    @Test
    public void testDeleteSighting() {
        Superpower superpower = new Superpower();
        superpower.setSuperpowerName("Superpower name");
        superpower.setDescription("Superpower description");
        superpower = superpowerDao.addSuperpower(superpower);

        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);

        Organization organization = new Organization();
        organization.setOrganizationName("Organzation name");
        organization.setDescription("Organization Description");
        organization.setAddress("Organization Address");
        organization.setCity("Organzation City");
        organization.setState("OS");
        organization.setZip("OZip");
        organization.setPhone("Org Phone");
        organization.setEmail("Organization email");
        organization = organizationDao.addOrganization(organization);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);

        Hero hero = new Hero();
        hero.setHeroName("Hero Name");
        hero.setDescription("Hero Description");
        hero.setOrganizations(organizations);
        hero.setSuperpowers(superpowers);
        hero = heroDao.addHero(hero);

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
        Sighting fromDao = sightingDao.getSightingById(sighting.getSightingId());
        assertEquals(sighting, fromDao);

        sightingDao.deleteSightingById(sighting.getSightingId());
        fromDao = sightingDao.getSightingById(sighting.getSightingId());
        assertNull(fromDao);

    }

    @Test
    public void testGetSightingsForHero() {
        Superpower superpower = new Superpower();
        superpower.setSuperpowerName("Superpower name");
        superpower.setDescription("Superpower description");
        superpower = superpowerDao.addSuperpower(superpower);

        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);

        Organization organization = new Organization();
        organization.setOrganizationName("Organzation name");
        organization.setDescription("Organization Description");
        organization.setAddress("Organization Address");
        organization.setCity("Organzation City");
        organization.setState("OS");
        organization.setZip("OZip");
        organization.setPhone("Org Phone");
        organization.setEmail("Organization email");
        organization = organizationDao.addOrganization(organization);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);

        Hero hero = new Hero();
        hero.setHeroName("Hero Name");
        hero.setDescription("Hero Description");
        hero.setOrganizations(organizations);
        hero.setSuperpowers(superpowers);
        hero = heroDao.addHero(hero);

        Hero hero2 = new Hero();
        hero2.setHeroName("Hero Name 2");
        hero2.setDescription("Hero Description");
        hero2.setOrganizations(organizations);
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

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.of(1990, Month.MARCH, 2));
        sighting.setHero(hero);
        sighting.setLocation(location);
        sighting = sightingDao.addSighting(sighting);

        Sighting sighting2 = new Sighting();
        sighting2.setDate(LocalDate.of(1990, Month.MARCH, 2));
        sighting2.setHero(hero2);
        sighting2.setLocation(location);
        sighting2 = sightingDao.addSighting(sighting2);

        Sighting sighting3 = new Sighting();
        sighting3.setDate(LocalDate.of(1990, Month.MARCH, 2));
        sighting3.setHero(hero);
        sighting3.setLocation(location);
        sighting3 = sightingDao.addSighting(sighting3);

        List<Sighting> sightings = sightingDao.getSightingsForHero(hero);
        assertEquals(2, sightings.size());
        assertTrue(sightings.contains(sighting));
        assertFalse(sightings.contains(sighting2));
        assertTrue(sightings.contains(sighting3));
    }

    @Test
    public void testGetSightingsForLocation() {
        Superpower superpower = new Superpower();
        superpower.setSuperpowerName("Superpower name");
        superpower.setDescription("Superpower description");
        superpower = superpowerDao.addSuperpower(superpower);

        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);

        Organization organization = new Organization();
        organization.setOrganizationName("Organzation name");
        organization.setDescription("Organization Description");
        organization.setAddress("Organization Address");
        organization.setCity("Organzation City");
        organization.setState("OS");
        organization.setZip("OZip");
        organization.setPhone("Org Phone");
        organization.setEmail("Organization email");
        organization = organizationDao.addOrganization(organization);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);

        Hero hero = new Hero();
        hero.setHeroName("Hero Name");
        hero.setDescription("Hero Description");
        hero.setOrganizations(organizations);
        hero.setSuperpowers(superpowers);
        hero = heroDao.addHero(hero);

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
        location2.setDescription("Location Description");
        location2.setAddress("Location Address");
        location2.setCity("Location City");
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
        sighting2.setHero(hero);
        sighting2.setLocation(location2);
        sighting2 = sightingDao.addSighting(sighting2);

        Sighting sighting3 = new Sighting();
        sighting3.setDate(LocalDate.of(1990, Month.MARCH, 2));
        sighting3.setHero(hero);
        sighting3.setLocation(location);
        sighting3 = sightingDao.addSighting(sighting3);

        List<Sighting> sightings = sightingDao.getSightingsForLocation(location);
        assertEquals(2, sightings.size());
        assertTrue(sightings.contains(sighting));
        assertFalse(sightings.contains(sighting2));
        assertTrue(sightings.contains(sighting3));
    }

}
