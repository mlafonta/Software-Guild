/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.superherosighting.dao;

import com.mrl.superherosighting.dto.Hero;
import com.mrl.superherosighting.dto.Orginization;
import java.util.List;

/**
 *
 * @author flafo
 */
public interface OrginizationDao {

    Orginization getOrginizationByOrginizationName(String orginizationName);

    List<Orginization> getAllOrginizations();

    Orginization addOrginization(Orginization orginization);

    void updateOrginization(Orginization orginization);

    void deleteOrginizationByOrginzationName(String orginizationName);

    List<Orginization> getOrginizationsForHero(Hero hero);
}
