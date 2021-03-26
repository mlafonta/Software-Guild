/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.superherosighting.dao;

import com.mrl.superherosighting.dto.Hero;
import com.mrl.superherosighting.dto.Location;
import java.util.List;

/**
 *
 * @author flafo
 */
public interface LocationDao {

    Location getLocationByLocationName(String locationName);

    List<Location> getAllLocations();

    Location addLocation(Location location);

    void updateLocation(Location location);

    void deleteLocationByLocationName(String locationName);
    
    List<Location> getLocationsForHero (Hero hero);
}
