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
public class OrganizationDaoDBTest {

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

    public OrganizationDaoDBTest() {
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
    public void testAddAndGetOrganization() {
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
        organization.setOrganizationName("Organization name");
        organization.setDescription("Organization Description");
        organization.setAddress("Organization Address");
        organization.setCity("Organzation City");
        organization.setState("OS");
        organization.setZip("OZip");
        organization.setPhone("Org Phone");
        organization.setEmail("Organization email");
        organization.setHeroes(heroes);
        organization = organizationDao.addOrganization(organization);

        Organization fromDao = organizationDao.getOrganizationByOrganizationName(organization.getOrganizationName());
        assertEquals(organization, fromDao);
    }

    @Test
    public void testGetAllOrganizations() {
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
        organization.setOrganizationName("Organization name");
        organization.setDescription("Organization Description");
        organization.setAddress("Organization Address");
        organization.setCity("Organzation City");
        organization.setState("OS");
        organization.setZip("OZip");
        organization.setPhone("Org Phone");
        organization.setEmail("Organization email");
        organization.setHeroes(heroes);
        organization = organizationDao.addOrganization(organization);

        Organization organization2 = new Organization();
        organization2.setOrganizationName("Organization name 2");
        organization2.setDescription("Organization Description");
        organization2.setAddress("Organization Address");
        organization2.setCity("Organzation City");
        organization2.setState("OS");
        organization2.setZip("OZip");
        organization2.setPhone("Org Phone");
        organization2.setEmail("Organization email");
        organization2.setHeroes(heroes);
        organization2 = organizationDao.addOrganization(organization2);

        List<Organization> organizations = organizationDao.getAllOrganizations();
        assertEquals(2, organizations.size());
        assertTrue(organizations.contains(organization));
        assertTrue(organizations.contains(organization2));

    }

    @Test
    public void testUpdateOrganization() {
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
        hero2.setDescription("Hero Description");
        hero2.setSuperpowers(superpowers);
        hero2 = heroDao.addHero(hero2);

        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);

        Organization organization = new Organization();
        organization.setOrganizationName("Organization name");
        organization.setDescription("Organization Description");
        organization.setAddress("Organization Address");
        organization.setCity("Organzation City");
        organization.setState("OS");
        organization.setZip("OZip");
        organization.setPhone("Org Phone");
        organization.setEmail("Organization email");
        organization.setHeroes(heroes);
        organization = organizationDao.addOrganization(organization);

        Organization fromDao = organizationDao.getOrganizationByOrganizationName(organization.getOrganizationName());
        assertEquals(organization, fromDao);

        heroes.add(hero2);
        organization.setCity("Updated city");
        assertNotEquals(organization, fromDao);

        organizationDao.updateOrganization(organization);
        fromDao = organizationDao.getOrganizationByOrganizationName(organization.getOrganizationName());
        assertEquals(organization, fromDao);
    }

    @Test
    public void testDeleteOrganizations() {
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
        organization.setOrganizationName("Organization name");
        organization.setDescription("Organization Description");
        organization.setAddress("Organization Address");
        organization.setCity("Organzation City");
        organization.setState("OS");
        organization.setZip("OZip");
        organization.setPhone("Org Phone");
        organization.setEmail("Organization email");
        organization.setHeroes(heroes);
        organization = organizationDao.addOrganization(organization);

        Organization fromDao = organizationDao.getOrganizationByOrganizationName(organization.getOrganizationName());
        assertEquals(organization, fromDao);

        organizationDao.deleteOrganizationByOrganzationName(organization.getOrganizationName());
        fromDao = organizationDao.getOrganizationByOrganizationName(organization.getOrganizationName());
        assertNull(fromDao);
    }

    @Test
    public void testGetOrganizationsForHero() {
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
        hero2.setDescription("Hero Description");
        hero2.setSuperpowers(superpowers);
        hero2 = heroDao.addHero(hero2);

        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);
        heroes.add(hero2);

        List<Hero> heroes2 = new ArrayList<>();
        heroes2.add(hero2);

        Organization organization = new Organization();
        organization.setOrganizationName("Organization name");
        organization.setDescription("Organization Description");
        organization.setAddress("Organization Address");
        organization.setCity("Organzation City");
        organization.setState("OS");
        organization.setZip("OZip");
        organization.setPhone("Org Phone");
        organization.setEmail("Organization email");
        organization.setHeroes(heroes);
        organization = organizationDao.addOrganization(organization);

        Organization organization2 = new Organization();
        organization2.setOrganizationName("Organization name 2");
        organization2.setDescription("Organization Description");
        organization2.setAddress("Organization Address");
        organization2.setCity("Organzation City");
        organization2.setState("OS");
        organization2.setZip("OZip");
        organization2.setPhone("Org Phone");
        organization2.setEmail("Organization email");
        organization2.setHeroes(heroes2);
        organization2 = organizationDao.addOrganization(organization2);

        Organization organization3 = new Organization();
        organization3.setOrganizationName("Organization name 3");
        organization3.setDescription("Organization Description");
        organization3.setAddress("Organization Address");
        organization3.setCity("Organzation City");
        organization3.setState("OS");
        organization3.setZip("OZip");
        organization3.setPhone("Org Phone");
        organization3.setEmail("Organization email");
        organization3.setHeroes(heroes);
        organization3 = organizationDao.addOrganization(organization3);
        
        List<Organization> organizations = organizationDao.getOrganizationsForHero(hero);
        assertEquals(2, organizations.size());
        assertTrue(organizations.contains(organization));
        assertFalse(organizations.contains(organization2));
        assertTrue(organizations.contains(organization3));
    }

}
