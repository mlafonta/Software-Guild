/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.flooringmastery.dao;

import com.mrl.flooringmastery.dto.Order;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author flafo
 */
public class FlooringMasteryOrdersDaoFolderImplTest {

    FlooringMasteryOrdersDao testDao;

    public FlooringMasteryOrdersDaoFolderImplTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        testDao = ctx.getBean("flooringMasteryOrdersDao", FlooringMasteryOrdersDao.class);
    }


    @BeforeEach
    public void setUp() throws Exception {
        String orderNumberTextFile = "./TestOrderNumber.txt";
        new FileWriter(orderNumberTextFile);

        
        
    }


    @Test
    public void createNewOrderNumber() throws Exception{
        LocalDate date = LocalDate.parse("10/10/2022", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Order order = testDao.createNewOrderNumber(date);
        
    }

}
