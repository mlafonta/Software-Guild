/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.superherosighting.dao;

import com.mrl.superherosighting.dto.Hero;
import com.mrl.superherosighting.dto.Location;
import com.mrl.superherosighting.dto.Sighting;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author flafo
 */
public interface SightingDao {

    Sighting getSightingById(int sightingId);

    List<Sighting> getAllSightings();

    Sighting addSighting(Sighting sighting);

    void updateSighting(Sighting sighting);

    void deleteSightingById(int sightingId);

    List<Sighting> getSightingsForHero(Hero hero);

    List<Sighting> getSightingsForLocation(Location location);
    
    List<Sighting> getSightingsForDate(LocalDate date);
}
