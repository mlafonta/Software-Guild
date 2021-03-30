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
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
        Hero hero = new Hero();
        model.addAttribute("heroes", heroes);
        model.addAttribute("superpowers", superpowers);
        model.addAttribute("hero", hero);
        return "hero";
    }

    @PostMapping("addHero")
    public String addHero(@Valid Hero hero, BindingResult result, HttpServletRequest request, Model model) {
        String[] superpowerNames = request.getParameterValues("superpowerName");
        List<Superpower> superpowers = new ArrayList<>();
        if (superpowerNames != null) {
            for (String superpowerName : superpowerNames) {
                superpowers.add(superpowerDao.getSuperpowerBySuperpowerName(superpowerName));
            }
            hero.setSuperpowers(superpowers);
        } else {
            FieldError error = new FieldError("hero", "superpowers", "Must include at least one superpower");
            result.addError(error);
        }
        if (result.hasErrors()) {
            List<Hero> heroes = heroDao.getAllHeroes();
            List<Superpower> superpowers2 = superpowerDao.getAllSuperpowers();
            model.addAttribute("heroes", heroes);
            model.addAttribute("superpowers", superpowers2);
            model.addAttribute("hero", hero);
            return "hero";
        }
        heroDao.addHero(hero);

        return "redirect:/hero";
    }

    @GetMapping("deleteHeroConfirm")
    public String confirmDeleteHero(String heroName, Model model) {
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        model.addAttribute("superpowers", superpowers);
        model.addAttribute("heroName", heroName);
        return "deleteHeroConfirm";
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
        List<Organization> organizations = organizationDao.getOrganizationsForHero(hero);
        model.addAttribute("hero", hero);
        model.addAttribute("sightingLocations", sightingLocations);
        model.addAttribute("organizations", organizations);
        model.addAttribute("heroName", heroName);
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
    public String performEditHero(@Valid Hero hero, BindingResult result, HttpServletRequest request, Model model) {
        String[] superpowerNames = request.getParameterValues("superpowerName");
        List<Superpower> superpowers = new ArrayList<>();

        if (superpowerNames != null) {
            for (String superpowerName : superpowerNames) {
                superpowers.add(superpowerDao.getSuperpowerBySuperpowerName(superpowerName));
            }
            hero.setSuperpowers(superpowers);
        } else {
            FieldError error = new FieldError("hero", "superpowers", "Must include at least one superpower");
            result.addError(error);
        }
        if (result.hasErrors()) {
            List<Hero> heroes = heroDao.getAllHeroes();
            List<Superpower> superpowers2 = superpowerDao.getAllSuperpowers();
            List<Organization> organizations = organizationDao.getAllOrganizations();
            List<Superpower> superpowers3 = superpowerDao.getSuperpowersForHero(hero);
            hero.setSuperpowers(superpowers3);
            model.addAttribute("heroes", heroes);
            model.addAttribute("superpowers", superpowers2);
            model.addAttribute("organizations", organizations);
            model.addAttribute("hero", hero);
            return "editHero";
        }
        heroDao.updateHero(hero);
        String heroName = hero.getHeroName();
        List<Location> sightingLocations = locationDao.getLocationsForHero(hero);
        List<Organization> organizations = organizationDao.getOrganizationsForHero(hero);
        model.addAttribute("heroName", heroName);
        model.addAttribute("sightingLocations", sightingLocations);
        model.addAttribute("organizations", organizations);
        return "heroDetail";
    }
}
