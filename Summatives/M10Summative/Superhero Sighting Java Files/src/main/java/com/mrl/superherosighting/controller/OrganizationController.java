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
import com.mrl.superherosighting.dto.Organization;
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
public class OrganizationController {

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

    @GetMapping("organization")
    public String displayOrganizations(Model model) {
        List<Organization> organizations = organizationDao.getAllOrganizations();
        List<Hero> heroes = heroDao.getAllHeroes();
        Organization organization = new Organization();
        model.addAttribute("organizations", organizations);
        model.addAttribute("heroes", heroes);
        model.addAttribute("organization", organization);
        return "organization";
    }

    @PostMapping("addOrganization")
    public String addOrganization(@Valid Organization organization, BindingResult result, HttpServletRequest request, Model model) {
        String[] heroNames = request.getParameterValues("heroName");
        List<Hero> heroes = new ArrayList<>();
        Organization duplicate = organizationDao.getOrganizationByOrganizationName(organization.getOrganizationName());
        if(duplicate != null) {
            FieldError error = new FieldError("organization", "organizationName", "Name must be unique");;
            result.addError(error);
        }
        if (heroNames != null) {
            for (String heroName : heroNames) {
                heroes.add(heroDao.getHeroByHeroName(heroName));
            }
            organization.setHeroes(heroes);
        } else {
            FieldError error = new FieldError("organization", "heroes", "Must include at least one hero");
            result.addError(error);            
        }
        if (result.hasErrors()) {
            List<Organization> organizations = organizationDao.getAllOrganizations();
            List<Hero> heroes2 = heroDao.getAllHeroes();
            model.addAttribute("organizations", organizations);
            model.addAttribute("heroes", heroes2);
            model.addAttribute("organization", organization);
            return "organization";
        }
        organizationDao.addOrganization(organization);
        return "redirect:/organization";
    }

    @GetMapping("deleteOrganizationConfirm")
    public String deleteOrganizationConfirm(String organizationName, Model model) {
        List<Hero> heroes = heroDao.getAllHeroes();
        model.addAttribute("heroes", heroes);
        model.addAttribute("organizationName", organizationName);
        return "deleteOrganizationConfirm";
    }
    
    @GetMapping("deleteOrganization")
    public String deleteOrganization(String organizationName) {
        organizationDao.deleteOrganizationByOrganzationName(organizationName);
        return "redirect:/organization";
    }

    @GetMapping("organizationDetail")
    public String organizationDetail(String organizationName, Model model) {
        Organization organization = organizationDao.getOrganizationByOrganizationName(organizationName);
        model.addAttribute("organization", organization);
        model.addAttribute("organizationName", organizationName);
        return "organizationDetail";
    }

    @GetMapping("editOrganization")
    public String editOrganization(String organizationName, Model model) {
        Organization organization = organizationDao.getOrganizationByOrganizationName(organizationName);
        List<Hero> heroes = heroDao.getAllHeroes();
        model.addAttribute("organization", organization);
        model.addAttribute("heroes", heroes);
        return "editOrganization";
    }

    @PostMapping("editOrganization")
    public String performEditOrganization(@Valid Organization organization, BindingResult result, HttpServletRequest request, Model model) {
        String[] heroNames = request.getParameterValues("heroName");
        List<Hero> heroes = new ArrayList<>();
        if (heroNames != null) {
            for (String heroName : heroNames) {
                heroes.add(heroDao.getHeroByHeroName(heroName));
            }
            organization.setHeroes(heroes);
        } else {
            FieldError error = new FieldError("organization", "heroes", "Must include at least one hero");
            result.addError(error);            
        }
        if (result.hasErrors()) {
            List<Organization> organizations = organizationDao.getAllOrganizations();
            List<Hero> heroes2 = heroDao.getAllHeroes();
            List<Hero> heroes3 = heroDao.getHeroesForOrganization(organization);
            organization.setHeroes(heroes3);
            model.addAttribute("organizations", organizations);
            model.addAttribute("heroes", heroes2);
            model.addAttribute("organization", organization);
            return "editOrganization";
        }
        organizationDao.updateOrganization(organization);
        String organizationName = organization.getOrganizationName();
        model.addAttribute("organizationName", organizationName);
        return "organizationDetail";
    }
}
