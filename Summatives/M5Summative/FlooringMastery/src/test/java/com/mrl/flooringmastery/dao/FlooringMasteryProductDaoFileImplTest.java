/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.flooringmastery.dao;

import com.mrl.flooringmastery.dto.Product;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author flafo
 */
public class FlooringMasteryProductDaoFileImplTest {

    FlooringMasteryProductDao testDao;

    public FlooringMasteryProductDaoFileImplTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        testDao = ctx.getBean("flooringMasteryProductDao", FlooringMasteryProductDao.class);
    }

    @Test
    public void getProduct() throws Exception {
        Product product = testDao.getProduct("Wood");
        assertNotNull(product);
        assertEquals("Wood", product.getProductType());
    }

    @Test
    public void getProductList() throws Exception {
        List<Product> productList = testDao.getProductList();
        assertNotNull(productList);
    }

}
