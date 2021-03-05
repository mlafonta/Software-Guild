/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.flooringmastery.dao;

import com.mrl.flooringmastery.dto.Product;
import com.mrl.flooringmastery.dto.Tax;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author flafo
 */
public class FlooringMasteryTaxDaoFileImplTest {
    FlooringMasteryTaxDao testDao;

    public FlooringMasteryTaxDaoFileImplTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        testDao = ctx.getBean("flooringMasteryTaxDao", FlooringMasteryTaxDao.class);
    }
    
    @Test
    public void getProduct() throws Exception {
        Tax tax = testDao.getTax("Texas");
        assertNotNull(tax);
        assertEquals("Texas", tax.getState());
    }

    @Test
    public void getProductList() throws Exception {
        List<Tax> taxList = testDao.getStateList();
        assertNotNull(taxList);
    }
    
}
