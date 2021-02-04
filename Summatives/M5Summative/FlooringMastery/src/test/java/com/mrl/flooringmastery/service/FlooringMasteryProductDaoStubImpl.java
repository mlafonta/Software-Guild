/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.flooringmastery.service;

import com.mrl.flooringmastery.dao.FlooringMasteryProductDao;
import com.mrl.flooringmastery.dto.Product;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author flafo
 */
public class FlooringMasteryProductDaoStubImpl implements FlooringMasteryProductDao{
    public Product product;
    
    @Override
    public List<Product> getProductList() throws FileNotFoundException {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        return productList;
    }

    @Override
    public Product getProduct(String productType) throws FileNotFoundException {
        product = new Product("Wood");
        product.setCostPerSquareFoot(BigDecimal.TEN);
        product.setLaborCostPerSquareFoot(BigDecimal.TEN);
        return product;
    }
    
}
