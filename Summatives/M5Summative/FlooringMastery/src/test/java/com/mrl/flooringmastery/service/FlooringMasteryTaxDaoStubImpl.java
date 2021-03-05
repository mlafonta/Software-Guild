/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.flooringmastery.service;

import com.mrl.flooringmastery.dao.FlooringMasteryTaxDao;
import com.mrl.flooringmastery.dto.Tax;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author flafo
 */
public class FlooringMasteryTaxDaoStubImpl implements FlooringMasteryTaxDao {
    public Tax tax;
    
    @Override
    public List<Tax> getStateList() throws FileNotFoundException {
        List<Tax> taxList = new ArrayList<>();
        taxList.add(tax);
        return taxList;
    }

    @Override
    public Tax getTax(String state) throws FileNotFoundException {
        tax = new Tax("Texas");
        tax.setStateAbbreviation("TX");
        tax.setTaxRate(BigDecimal.TEN);
        return tax;
    }
    
}
