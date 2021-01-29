/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.flooringmastery.dao;

import com.mrl.flooringmastery.dto.Product;
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
public class FlooringMasteryProductDaoFileImpl implements FlooringMasteryProductDao {

    private final String PRODUCT_FILE;
    public static final String DELIMITER = ",";

    public FlooringMasteryProductDaoFileImpl() {
        PRODUCT_FILE = "Products.txt";
    }

    public FlooringMasteryProductDaoFileImpl(String productTextFile) {
        PRODUCT_FILE = productTextFile;
    }

    private Map<String, Product> productList = new HashMap<>();

    private Product unmarshallProduct(String productAsText) {
        String[] productTokens = productAsText.split(DELIMITER);
        String productType = productTokens[0];
        Product productFromFile = new Product(productType);
        BigDecimal costPerSquareFoot = new BigDecimal(productTokens[1]);
        productFromFile.setCostPerSquareFoot(costPerSquareFoot);
        BigDecimal laborCostPerSquareFoot = new BigDecimal(productTokens[2]);
        productFromFile.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        return productFromFile;
    }

    private void loadProducts() throws FileNotFoundException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Could not load product data into memory.");
        }
        String currentLine;
        Product currentProduct;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentProduct = unmarshallProduct(currentLine);
            productList.put(currentProduct.getProductType(), currentProduct);
        }
        scanner.close();
    }

    @Override
    public List<Product> getProductList() throws FileNotFoundException{
        loadProducts();
        return new ArrayList<Product>(productList.values());
    }

    @Override
    public Product getProduct(String productType) throws FileNotFoundException {
        loadProducts();
        Product product = productList.get(productType);
        return product;
    }

}
