/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.vendingmachine.service;

import com.mrl.vendingmachine.dao.VendingMachineDao;
import static com.mrl.vendingmachine.dao.VendingMachineDaoFileImp1.DELIMITER;
import com.mrl.vendingmachine.dao.VendingMachinePersistenceException;
import com.mrl.vendingmachine.dto.Coins;
import com.mrl.vendingmachine.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author flafo
 */
public class VendingMachineDaoStubImp1 implements VendingMachineDao {

    public Product product;

    public VendingMachineDaoStubImp1() {
        product = new Product("Coke");
        product.setPrice(BigDecimal.ONE);
        product.setStock(1);
    }

    BigDecimal balance = new BigDecimal(".99").setScale(2);

    @Override
    public List<Product> listProducts() throws VendingMachinePersistenceException {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        return productList;
    }

    @Override
    public Product buyProduct(Product product) throws VendingMachinePersistenceException {
        product.setStock(product.getStock() - 1);
        balance = balance.subtract(product.getPrice());
        return product;
    }

    @Override
    public Product addProduct(String name, Product product) throws VendingMachinePersistenceException {
        if (name.equals(product.getName())) {
            return product;
        } else {
            return null;
        }
    }

    @Override
    public Product removeProduct(String name) throws VendingMachinePersistenceException {
        if (name.equals(product.getName())) {
            return product;
        } else {
            return null;
        }
    }

    @Override
    public Product adjustInventory(String name, Product product) throws VendingMachinePersistenceException {
        product.setStock(2);
        return product;
    }

    @Override
    public Product adjustPrice(String name, Product product) throws VendingMachinePersistenceException {
        product.setPrice(BigDecimal.TEN);
        return product;
    }

    @Override
    public Product getProduct(String name) throws VendingMachinePersistenceException {
        if (name.equals(product.getName())) {
            return product;
        } else {
            return null;
        }
    }

    @Override
    public BigDecimal addToBalance(Coins coin) {
        balance = balance.add(BigDecimal.ONE);
        return balance;
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

}
