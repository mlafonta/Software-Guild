/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.superherosighting.dao;

import com.mrl.superherosighting.dto.Hero;
import com.mrl.superherosighting.dto.Superpower;
import java.util.List;

/**
 *
 * @author flafo
 */
public interface SuperpowerDao {

    Superpower getSuperpowerBySuperpowerName(String superpowerName);

    List<Superpower> getAllSuperpowers();

    Superpower addSuperpower(Superpower superpower);

    void updateSuperpower(Superpower superpower);

    void deleteSuperpowerBySuperpowerName(String superpowerName);

    public List<Superpower> getSuperpowersForHero(Hero hero);
}
