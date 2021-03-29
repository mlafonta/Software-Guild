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
import java.util.List;
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
public class LocationController {

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

    @GetMapping("location")
    public String displayLocations(Model model) {
        List<Location> locations = locationDao.getAllLocations();
        model.addAttribute("locations", locations);
        return "location";
    }

    @PostMapping("addLocation")
    public String addLocation(Location location) {
        locationDao.addLocation(location);
        return "redirect:/location";
    }

    @GetMapping("deleteLocation")
    public String deleteLocation(String locationName) {
        locationDao.deleteLocationByLocationName(locationName);
        return "redirect:/location";
    }

    @GetMapping("locationDetail")
    public String locationDetail(String locationName, Model model) {
        Location location = locationDao.getLocationByLocationName(locationName);
        List<Hero> sightingHeroes = heroDao.getHeroesForLocation(location);
        model.addAttribute("location", location);
        model.addAttribute("sightingHeroes", sightingHeroes);
        return "locationDetail";
    }

    @GetMapping("editLocation")
    public String editLocation(String locationName, Model model) {
        Location location = locationDao.getLocationByLocationName(locationName);
        model.addAttribute("location", location);
        return "editLocation";
    }

    @PostMapping("editLocation")
    public String performEditLocation(Location location) {
        locationDao.updateLocation(location);
        return "redirect:/location";
    }
}
