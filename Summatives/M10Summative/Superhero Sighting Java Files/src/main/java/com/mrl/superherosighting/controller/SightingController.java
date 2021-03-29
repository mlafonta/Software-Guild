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
import com.mrl.superherosighting.dto.Sighting;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class SightingController {

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
    
    @GetMapping("sighting")
    public String displaySightings(Model model) {
        List<Sighting> sightings = sightingDao.getAllSightings();
        List<Hero> heroes = heroDao.getAllHeroes();
        List<Location> locations = locationDao.getAllLocations();
        model.addAttribute("sightings", sightings);
        model.addAttribute("heroes", heroes);
        model.addAttribute("locations", locations);
        return "sighting";
    }
    
    @PostMapping("addSighting")
    public String addSighting(HttpServletRequest request) {
        Sighting sighting = new Sighting();
        String dateString = request.getParameter("date");
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        sighting.setDate(date);
        String heroName = request.getParameter("heroName");
        sighting.setHero(heroDao.getHeroByHeroName(heroName));
        String locationName = request.getParameter("locationName");
        sighting.setLocation(locationDao.getLocationByLocationName(locationName));
        sightingDao.addSighting(sighting);
        return "redirect:/sighting";
    }
    
    @GetMapping("deleteSighting")
    public String deleteSighting(Integer sightingId) {
        sightingDao.deleteSightingById(sightingId);
        return "redirect:/sighting";
    }
    
    @GetMapping("editSighting")
    public String editSighting(Integer sightingId, Model model) {
        Sighting sighting = sightingDao.getSightingById(sightingId);
        List<Hero> heroes = heroDao.getAllHeroes();
        List<Location> locations = locationDao.getAllLocations();
        model.addAttribute("sighting", sighting);
        model.addAttribute("heroes", heroes);
        model.addAttribute("locations", locations);
        return "editSighting";
    }
    
    @PostMapping("editSighting")
    public String performEditSighting(Integer sightingId, HttpServletRequest request) {
        Sighting sighting = sightingDao.getSightingById(sightingId);
        String dateString = request.getParameter("date");
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        sighting.setDate(date);
        String heroName = request.getParameter("heroName");
        sighting.setHero(heroDao.getHeroByHeroName(heroName));
        String locationName = request.getParameter("locationName");
        sighting.setLocation(locationDao.getLocationByLocationName(locationName));
        sightingDao.updateSighting(sighting);
        return "redirect:/sighting";
    }
    
}
