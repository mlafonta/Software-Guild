/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.superherosighting.dao;

import com.mrl.superherosighting.dto.Hero;
import com.mrl.superherosighting.dto.Organization;
import java.util.List;

/**
 *
 * @author flafo
 */
public interface OrganizationDao {

    Organization getOrganizationByOrganizationName(String organizationName);

    List<Organization> getAllOrganizations();

    Organization addOrganization(Organization organization);

    void updateOrganization(Organization organization);

    void deleteOrganizationByOrganzationName(String organizationName);

    List<Organization> getOrganizationsForHero(Hero hero);
}
