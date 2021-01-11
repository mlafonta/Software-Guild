/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.vendingmachine.dao;

import static com.mrl.vendingmachine.dto.Coins.QUARTER;
import com.mrl.vendingmachine.dto.Product;
import com.mrl.vendingmachine.service.VendingMachineService;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author flafo
 */
public class VendingMachineDaoFileImp1Test {

    VendingMachineDao testDao;

    public VendingMachineDaoFileImp1Test() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        testDao = ctx.getBean("vendingMachineDao", VendingMachineDao.class);
    }

    @BeforeEach
    public void setUp() throws Exception {

        String testFile = "testinventory.txt";
        new FileWriter(testFile);

    }

    @Test
    public void testAddGetBalance() {

        testDao.addToBalance(QUARTER);
        BigDecimal balance = testDao.getBalance();
        BigDecimal control = new BigDecimal(".25");
        assertTrue(balance.compareTo(control) == 0);
    }

    @Test
    public void testAddGetProduct() throws Exception {
        String name = "Coke";
        Product product = new Product(name);
        product.setPrice(BigDecimal.ONE);
        product.setStock(1);

        testDao.addProduct(name, product);
        Product gotProduct = testDao.getProduct(name);

        assertEquals(product.getName(), gotProduct.getName(), "Compare names");
        assertEquals(product.getPrice(), gotProduct.getPrice(), "Compare prices");
        assertEquals(product.getStock(), gotProduct.getStock(), "Compare stock");
    }

    @Test
    public void testAddGetAllProducts() throws Exception {
        String first = "Coke";
        Product firstProduct = new Product(first);
        firstProduct.setPrice(BigDecimal.ONE);
        firstProduct.setStock(1);

        String second = "Pepsi";
        Product secondProduct = new Product(second);
        secondProduct.setPrice(BigDecimal.TEN);
        secondProduct.setStock(3);

        testDao.addProduct(firstProduct.getName(), firstProduct);
        testDao.addProduct(secondProduct.getName(), secondProduct);

        List<Product> productList = testDao.listProducts();

        assertNotNull(productList);
        assertEquals(2, productList.size());
        assertTrue(testDao.listProducts().contains(firstProduct));
        assertTrue(testDao.listProducts().contains(secondProduct));

    }

    @Test
    public void testRemoveProduct() throws Exception {
        String first = "Coke";
        Product firstProduct = new Product(first);
        firstProduct.setPrice(BigDecimal.ONE);
        firstProduct.setStock(1);

        String second = "Pepsi";
        Product secondProduct = new Product(second);
        secondProduct.setPrice(BigDecimal.TEN);
        secondProduct.setStock(3);

        testDao.addProduct(firstProduct.getName(), firstProduct);
        testDao.addProduct(secondProduct.getName(), secondProduct);

        Product removedProduct = testDao.removeProduct(firstProduct.getName());

        assertEquals(removedProduct, firstProduct);

        List<Product> productList = testDao.listProducts();

        assertFalse(productList.contains(firstProduct));
    }

    @Test
    public void testAddBuyProduct() throws Exception {
        String first = "Coke";
        Product firstProduct = new Product(first);
        BigDecimal price = new BigDecimal(".15");
        firstProduct.setPrice(price);
        firstProduct.setStock(3);
        testDao.addToBalance(QUARTER);

        testDao.addProduct(first, firstProduct);
        testDao.buyProduct(firstProduct);
        BigDecimal control = new BigDecimal(".10");

        assertEquals(2, firstProduct.getStock());
        assertTrue(testDao.getBalance().compareTo(control) == 0);

    }

    @Test
    public void testAdjustInventory() throws Exception {
        String first = "Coke";
        Product firstProduct = new Product(first);
        BigDecimal price = new BigDecimal(".15");
        firstProduct.setPrice(price);
        firstProduct.setStock(3);

        testDao.addProduct(first, firstProduct);

        Product secondProduct = new Product(first);
        secondProduct.setPrice(price);
        secondProduct.setStock(2);

        testDao.adjustInventory(first, secondProduct);

        assertEquals(2, testDao.getProduct(first).getStock());
    }

    @Test
    public void testAdjustPrice() throws Exception {
        String first = "Coke";
        Product firstProduct = new Product(first);
        BigDecimal price = new BigDecimal(".15");
        firstProduct.setPrice(price);
        firstProduct.setStock(3);

        testDao.addProduct(first, firstProduct);

        Product secondProduct = new Product(first);
        BigDecimal price2 = new BigDecimal(".25");
        secondProduct.setPrice(price2);
        secondProduct.setStock(3);

        testDao.adjustPrice(first, secondProduct);

        assertEquals(price2, testDao.getProduct(first).getPrice());
    }
}
