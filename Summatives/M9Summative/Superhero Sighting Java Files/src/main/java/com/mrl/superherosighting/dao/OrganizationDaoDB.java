/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.superherosighting.dao;

import com.mrl.superherosighting.dao.HeroDaoDB.HeroMapper;
import com.mrl.superherosighting.dto.Hero;
import com.mrl.superherosighting.dto.Organization;
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
public class OrganizationDaoDB implements OrganizationDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Organization getOrganizationByOrganizationName(String organizationName) {
        try {
            final String SELECT_ORGANIZATION_BY_NAME = "SELECT * FROM Organization WHERE OrganizationName = ?";
            Organization organization = jdbc.queryForObject(SELECT_ORGANIZATION_BY_NAME, new OrganizationMapper(), organizationName);
            organization.setHeroes(getHeroesForOrganization(organizationName));
            return organization;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        final String SELECT_ALL_ORGANIZATIONS = "SELECT * FROM Organization";
        List<Organization> organizations = jdbc.query(SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());
        associateHeroes(organizations);
        return organizations;
    }

    @Override
    @Transactional
    public Organization addOrganization(Organization organization) {
        final String INSERT_ORGANIZATION = "INSERT INTO Organization(OrganizationName, Description, Address, City, State, ZIP, Phone, Email) VALUES (?,?,?,?,?,?,?,?)";
        jdbc.update(INSERT_ORGANIZATION, organization.getOrganizationName(), organization.getDescription(), organization.getAddress(), organization.getCity(), organization.getState(), organization.getZip(), organization.getPhone(), organization.getEmail());
        insertHeroOrganization(organization);
        return organization;
    }

    @Override
    @Transactional
    public void updateOrganization(Organization organization) {
        final String UPDATE_ORGANIZATION = "UPDATE Organization SET Description = ?, Address = ?, City = ?, State = ?, ZIP = ?, Phone = ?, Email = ? WHERE OrganizationName = ?";
        jdbc.update(UPDATE_ORGANIZATION, organization.getDescription(), organization.getAddress(), organization.getCity(), organization.getState(), organization.getZip(), organization.getPhone(), organization.getEmail(), organization.getOrganizationName());
        final String DELETE_HERO_ORGANIZATION = "DELETE FROM Hero_Organization WHERE OrganizationName = ?";
        jdbc.update(DELETE_HERO_ORGANIZATION, organization.getOrganizationName());
        insertHeroOrganization(organization);

    }

    @Override
    @Transactional
    public void deleteOrganizationByOrganzationName(String organizationName) {
        final String DELETE_HERO_ORGANIZATION = "DELETE FROM Hero_Organization WHERE OrganizationName = ?";
        jdbc.update(DELETE_HERO_ORGANIZATION, organizationName);
        final String DELETE_ORGANIZATION = "DELETE FROM Organization WHERE OrganizationName = ?";
        jdbc.update(DELETE_ORGANIZATION, organizationName);
    }

    @Override
    public List<Organization> getOrganizationsForHero(Hero hero) {
        final String SELECT_ORGANIZATIONS_FOR_HERO = "SELECT o.* FROM Organization o JOIN Hero_Organization ho ON o.OrganizationName = ho.OrganizationName WHERE ho.HeroName = ?";
        List<Organization> organizations = jdbc.query(SELECT_ORGANIZATIONS_FOR_HERO, new OrganizationMapper(), hero.getHeroName());
        associateHeroes(organizations);
        return organizations;
    }

    private List<Hero> getHeroesForOrganization(String organizationName) {
        final String SELECT_HEROES_FOR_ORGANIZATION = "SELECT h.* FROM Hero h JOIN Hero_Organization ho ON ho.HeroName = h.HeroName WHERE ho.OrganizationName = ?";
        List<Hero> heroes = jdbc.query(SELECT_HEROES_FOR_ORGANIZATION, new HeroMapper(), organizationName);
        for (Hero hero : heroes) {
            final String SELECT_SUPERPOWERS_FOR_HERO = "SELECT s.* FROM Superpower s JOIN Hero_Superpower hs ON s.SuperpowerName = hs.SuperpowerName WHERE hs.HeroName = ?";
            List<Superpower> superpowers = jdbc.query(SELECT_SUPERPOWERS_FOR_HERO, new SuperpowerDaoDB.SuperpowerMapper(), hero.getHeroName());
            hero.setSuperpowers(superpowers);
        }
        return heroes;
    }

    private void associateHeroes(List<Organization> organizations) {
        for (Organization organization : organizations) {
            organization.setHeroes(getHeroesForOrganization(organization.getOrganizationName()));
        }
    }

    private void insertHeroOrganization(Organization organization) {
        if (organization.getHeroes() != null) {
            final String INSERT_HERO_ORGANIZATION = "INSERT INTO Hero_Organization(HeroName, OrganizationName) VALUES(?,?)";
            for (Hero hero : organization.getHeroes()) {
                jdbc.update(INSERT_HERO_ORGANIZATION, hero.getHeroName(), organization.getOrganizationName());
            }
        }
    }

    public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization organization = new Organization();
            organization.setOrganizationName(rs.getString("OrganizationName"));
            organization.setDescription(rs.getString("Description"));
            organization.setAddress(rs.getString("Address"));
            organization.setCity(rs.getString("City"));
            organization.setState(rs.getString("State"));
            organization.setZip(rs.getString("ZIP"));
            organization.setPhone(rs.getString("Phone"));
            organization.setEmail(rs.getString("Email"));
            return organization;
        }

    }
}
