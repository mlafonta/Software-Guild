/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author flafo
 */
public class Tax {
    private String stateAbbreviation;
    private String state;
    private BigDecimal taxRate;

    public Tax(String state) {
        this.state = state;
    }
    
    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    public String getState() {
        return state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
    
    
}
