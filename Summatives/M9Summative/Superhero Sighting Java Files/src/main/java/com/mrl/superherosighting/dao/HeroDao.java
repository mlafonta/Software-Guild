/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.superherosighting.dao;

import com.mrl.superherosighting.dto.Hero;
import com.mrl.superherosighting.dto.Location;
import com.mrl.superherosighting.dto.Orginization;
import java.util.List;

/**
 *
 * @author flafo
 */
public interface HeroDao {

    Hero getHeroByHeroName(String heroName);

    List<Hero> getAllHeroes();

    Hero addHero(Hero hero);

    void updateHero(Hero hero);

    void deleteHeroByHeroName(String heroName);

    List<Hero> getHeroesForOrginization(Orginization orginization);
    
    List<Hero> getHeroesForLocation(Location location);
}
