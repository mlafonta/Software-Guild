/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.superherosighting.dto;

import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author flafo
 */
public class Location {

    @NotBlank(message = "Name required")
    @Size(max = 50, message = "Name must be less than 50 characters")
    private String locationName;
    @NotBlank(message = "Description required")
    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;
    @NotBlank(message = "Address required")
    @Size(max = 50, message = "Address must be less than 100 characters")
    private String address;
    @NotBlank(message = "City required")
    @Size(max = 50, message = "City must be less than 45 characters")
    private String city;
    @NotBlank(message = "State required")
    private String state;
    @Pattern(regexp = "\\d{5}", message = "Five digit Zip required")
    private String zip;
    @NotBlank(message = "Latitude required")
    @Pattern(regexp = "\\+|-?\\d*\\.?\\d*", message = "Valid latitude required (decimal notation)")
    private String latitude;
    @NotBlank(message = "Longitude required")
    @Pattern(regexp = "\\+|-?\\d*\\.?\\d*", message = "Valid longitude required (decimal notation)")
    private String longitude;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.locationName);
        hash = 19 * hash + Objects.hashCode(this.description);
        hash = 19 * hash + Objects.hashCode(this.address);
        hash = 19 * hash + Objects.hashCode(this.city);
        hash = 19 * hash + Objects.hashCode(this.state);
        hash = 19 * hash + Objects.hashCode(this.zip);
        hash = 19 * hash + Objects.hashCode(this.latitude);
        hash = 19 * hash + Objects.hashCode(this.longitude);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;
        if (!Objects.equals(this.locationName, other.locationName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.zip, other.zip)) {
            return false;
        }
        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }
        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }
        return true;
    }

}
