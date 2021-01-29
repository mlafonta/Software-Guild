/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.flooringmastery.dao;

import com.mrl.flooringmastery.dto.Product;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author flafo
 */
public interface FlooringMasteryProductDao {
    
    public List<Product> getProductList() throws FileNotFoundException;

    public Product getProduct(String productType) throws FileNotFoundException;
}
