/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.flooringmastery.dao;

import com.mrl.flooringmastery.dto.Tax;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author flafo
 */
public interface FlooringMasteryTaxDao {
    Tax getTaxInfo(String state) throws FileNotFoundException;

    public List<Tax> getStateList() throws FileNotFoundException;

    public Tax getTax(String state) throws FileNotFoundException;
}
