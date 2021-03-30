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
import com.mrl.superherosighting.dto.Superpower;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author flafo
 */
@Controller
public class SuperpowerController {

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

    @GetMapping("superpower")
    public String displaySuperpowers(Model model) {
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        Superpower superpower = new Superpower();
        model.addAttribute("superpower", superpower);
        model.addAttribute("superpowers", superpowers);
        return "superpower";
    }

    @PostMapping("addSuperpower")
    public String addSuperpower(@Valid Superpower superpower, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
            model.addAttribute("superpower", superpower);
            model.addAttribute("superpowers", superpowers);
            return "superpower";
        }
        superpowerDao.addSuperpower(superpower);
        return "redirect:/superpower";
    }
    
    @GetMapping("deleteSuperpowerConfirm")
    public String deleteSuperpowerConfirm(String superpowerName, Model model) {
        model.addAttribute("superpowerName", superpowerName);
        return "deleteSuperpowerConfirm";
    }
    
    @GetMapping("deleteSuperpower")
    public String deleteSuperpower(String superpowerName) {
        superpowerDao.deleteSuperpowerBySuperpowerName(superpowerName);
        return "redirect:/superpower";
    }

    @GetMapping("editSuperpower")
    public String editSuperpower(String superpowerName, Model model) {
        Superpower superpower = superpowerDao.getSuperpowerBySuperpowerName(superpowerName);
        model.addAttribute("superpower", superpower);
        return "editSuperpower";
    }

    @PostMapping("editSuperpower")
    public String performEditSuperpower(@Valid Superpower superpower, BindingResult result) {
        if(result.hasErrors()) {
            return "editSuperpower";
        }
        
        superpowerDao.updateSuperpower(superpower);
        return "redirect:/superpower";
    }
}
