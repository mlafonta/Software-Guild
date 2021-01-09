/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.vendingmachine.service;

import com.mrl.vendingmachine.dao.VendingMachinePersistenceException;
import com.mrl.vendingmachine.dto.Product;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author flafo
 */
public class VendingMachineServiceImp1Test {

    private VendingMachineService service;

    public VendingMachineServiceImp1Test() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        service = ctx.getBean("service", VendingMachineService.class);
    }

    @Test
    public void testBuyProductValid() {
        Product product = new Product("Pepsi");
        BigDecimal price = new BigDecimal(".50");
        product.setPrice(price);
        product.setStock(1);

        try {
            service.buyProduct(product);
        } catch (VendingMachinePersistenceException
                | InsufficientFundsException
                | NoItemInventoryException
                | NoSuchProductException e) {
            fail("Purchase was valid. No exception should be thrown.");
        }
    }

    @Test
    public void testBuyProductFunds() {
        Product product = new Product("Pepsi");
        BigDecimal price = new BigDecimal("5.50");
        product.setPrice(price);
        product.setStock(1);

        try {
            service.buyProduct(product);
            fail("Should throw Insufficient Funds");
        } catch (VendingMachinePersistenceException
                | NoItemInventoryException
                | NoSuchProductException e) {
            fail("Wrong Exception");
        } catch (InsufficientFundsException e) {
            return;
        }
    }

    @Test
    public void testBuyProductInventory() {
        Product product = new Product("Pepsi");
        BigDecimal price = new BigDecimal(".50");
        product.setPrice(price);
        product.setStock(0);

        try {
            service.buyProduct(product);
            fail("Should throw No Item");
        } catch (VendingMachinePersistenceException
                | InsufficientFundsException
                | NoSuchProductException e) {
            fail("Wrong Exception");
        } catch (NoItemInventoryException e) {
            return;
        }
    }

    @Test
    public void testGetProduct() {
        try{
            service.getProduct("Coke");
        } catch (NoSuchProductException
                | VendingMachinePersistenceException e){
            fail("No exceptions expected");
        }
    }

    @Test
    public void testGetNonExistantProduct() {
        try{
            service.getProduct("Pepsi");
            fail("Should throw no such pruduct");
        } catch (VendingMachinePersistenceException e){
            fail("wrong exception");
        } catch (NoSuchProductException e) {
            return;
        } 
    }

    @Test
    public void testAddDuplicateProduct() {
        Product product = new Product("Coke");
        product.setPrice(BigDecimal.ONE);
        product.setStock(2);
        
        try {
            service.addProduct(product);
            fail("Should throw Duplicate");
        } catch (VendingMachinePersistenceException
                | VendingMachineDataValidationException e) {
            fail("Wrong Exception");
        } catch (VendingMachineDuplicateProductException e) {
            return;
        }
    }
}
