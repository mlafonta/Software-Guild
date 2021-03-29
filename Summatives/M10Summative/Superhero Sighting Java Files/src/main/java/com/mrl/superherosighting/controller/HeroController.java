/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.superherosighting.controller;

import com.mrl.superherosighting.dao.HeroDao;
import com.mrl.superherosighting.dao.LocationDao;
import com.mrl.superherosighting.dao.OrganizationDao;
import com.mrl.superherosighting.dao.SightingDao;
import com.mrl.superherosighting.dao.SuperpowerDao;
import com.mrl.superherosighting.dto.Hero;
import com.mrl.superherosighting.dto.Location;
import com.mrl.superherosighting.dto.Organization;
import com.mrl.superherosighting.dto.Superpower;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author flafo
 */
@Controller
public class HeroController {

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

    @GetMapping("hero")
    public String displayHeroes(Model model) {
        List<Hero> heroes = heroDao.getAllHeroes();
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        List<Organization> organizations = organizationDao.getAllOrganizations();
        model.addAttribute("heroes", heroes);
        model.addAttribute("superpowers", superpowers);
        model.addAttribute("organizations", organizations);
        return "hero";
    }

    @PostMapping("addHero")
    public String addHero(Hero hero, HttpServletRequest request) {
        String[] organizationNames = request.getParameterValues("organizationName");
        String[] superpowerNames = request.getParameterValues("superpowerName");
        List<Organization> organizations = new ArrayList<>();
        if (organizationNames != null) {
            for (String organizationName : organizationNames) {
                organizations.add(organizationDao.getOrganizationByOrganizationName(organizationName));
            }
            hero.setOrganizations(organizations);
        }
        List<Superpower> superpowers = new ArrayList<>();
        if (superpowerNames != null) {
            for (String superpowerName : superpowerNames) {
                superpowers.add(superpowerDao.getSuperpowerBySuperpowerName(superpowerName));
            }
            hero.setSuperpowers(superpowers);
        }
        heroDao.addHero(hero);

        return "redirect:/hero";
    }

    @GetMapping("deleteHero")
    public String deleteHero(String heroName) {
        heroDao.deleteHeroByHeroName(heroName);
        return "redirect:/hero";
    }

    @GetMapping("heroDetail")
    public String heroDetail(String heroName, Model model) {
        Hero hero = heroDao.getHeroByHeroName(heroName);
        List<Location> sightingLocations = locationDao.getLocationsForHero(hero);
        model.addAttribute("hero", hero);
        model.addAttribute("sightingLocations", sightingLocations);
        return "heroDetail";
    }

    @GetMapping("editHero")
    public String editHero(String heroName, Model model) {
        Hero hero = heroDao.getHeroByHeroName(heroName);
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        List<Organization> organizations = organizationDao.getAllOrganizations();
        model.addAttribute("hero", hero);
        model.addAttribute("superpowers", superpowers);
        model.addAttribute("organizations", organizations);
        return "editHero";
    }

    @PostMapping("editHero")
    public String performEditHero(Hero hero, HttpServletRequest request) {
        String[] organizationNames = request.getParameterValues("organizationName");
        String[] superpowerNames = request.getParameterValues("superpowerName");
        List<Organization> organizations = new ArrayList<>();
        if (organizationNames != null) {
            for (String organizationName : organizationNames) {
                organizations.add(organizationDao.getOrganizationByOrganizationName(organizationName));
            }
            hero.setOrganizations(organizations);
        }
        List<Superpower> superpowers = new ArrayList<>();
        if (superpowerNames != null) {
            for (String superpowerName : superpowerNames) {
                superpowers.add(superpowerDao.getSuperpowerBySuperpowerName(superpowerName));
            }
            hero.setSuperpowers(superpowers);
        }
        heroDao.updateHero(hero);

        return "redirect:/hero";
    }
}
