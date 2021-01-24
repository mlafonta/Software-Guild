/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.flooringmastery.dao;

import com.mrl.flooringmastery.dto.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author flafo
 */
public class FlooringMasteryTaxDaoFileImpl implements FlooringMasteryTaxDao{

    private final String TAX_FILE;
    public static final String DELIMITER = ",";

    public FlooringMasteryTaxDaoFileImpl() {
        TAX_FILE = "Taxes.txt";
    }

    public FlooringMasteryTaxDaoFileImpl(String taxTextFile) {
        TAX_FILE = taxTextFile;
    }

    private Map<String, Tax> taxList = new HashMap<>();

    @Override
    public Tax getTaxInfo(String state) throws FileNotFoundException{
        loadTaxes();
        Tax tax = taxList.get(state);
        return tax;
    }

    private Tax unmarshallTax(String taxAsText) {
        String[] taxTokens = taxAsText.split(DELIMITER);
        String state = taxTokens[1];
        Tax taxFromFile = new Tax(state);
        BigDecimal taxRate = new BigDecimal(taxTokens[2]);
        taxFromFile.setTaxRate(taxRate);
        taxFromFile.setStateAbbreviation(taxTokens[0]);
        return taxFromFile;
    }

    private void loadTaxes() throws FileNotFoundException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(TAX_FILE)));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Could not load tax data into memory.");
        }
        String currentLine;
        Tax currentTax;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTax = unmarshallTax(currentLine);
            taxList.put(currentTax.getState(), currentTax);
        }
        scanner.close();
    }

    @Override
    public List<Tax> getStateList() throws FileNotFoundException {
        loadTaxes();
        return new ArrayList<Tax>(taxList.values());
    }

    @Override
    public Tax getTax(String state) throws FileNotFoundException {
        loadTaxes();
        Tax tax = taxList.get(state);
        return tax;
    }

}
