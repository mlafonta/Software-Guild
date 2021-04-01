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
        Location location = new Location();
        model.addAttribute("locations", locations);
        model.addAttribute("location", location);
        return "location";
    }

    @PostMapping("addLocation")
    public String addLocation(@Valid Location location, BindingResult result, Model model) {
        Location duplicate = locationDao.getLocationByLocationName(location.getLocationName());
        if(duplicate != null) {
            FieldError error = new FieldError("location", "locationName", "Name must be unique");;
            result.addError(error);
        }
        if (result.hasErrors()) {
            List<Location> locations = locationDao.getAllLocations();
            model.addAttribute("locations", locations);
            model.addAttribute("location", location);
            return "location";
        }
        locationDao.addLocation(location);
        return "redirect:/location";
    }

    @GetMapping("deleteLocationConfirm")
    public String deleteLocationConfirm(String locationName, Model model) {
        model.addAttribute("locationName", locationName);
        return "deleteLocationConfirm";
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
        model.addAttribute("locationName", locationName);
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
    public String performEditLocation(@Valid Location location, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Location> locations = locationDao.getAllLocations();
            model.addAttribute("locations", locations);
            model.addAttribute("location", location);
            return "editLocation";
        }
        locationDao.updateLocation(location);
        String locationName = location.getLocationName();
        List<Hero> sightingHeroes = heroDao.getHeroesForLocation(location);
        model.addAttribute("location", location);
        model.addAttribute("sightingHeroes", sightingHeroes);
        return "locationDetail";
    }
}
