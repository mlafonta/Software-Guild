/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.flooringmastery.service;

import com.mrl.flooringmastery.dto.Order;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author flafo
 */
public class FlooringMasteryServiceImplTest {
    
    private FlooringMasteryService service;
    public FlooringMasteryServiceImplTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("service", FlooringMasteryService.class);
    }
    
    @Test
    public void testCreateOrderNumber() throws Exception {
        Order order = service.createNewOrderNumber();
        assertEquals(3, order.getOrderNumber());
    }
    
    @Test
    public void testCalculate() throws Exception {
        Order order = new Order(1);
        order.setCustomerName("Maxwell");
        order.setState("Texas");
        order.setProductType("Wood");
        order.setArea(BigDecimal.TEN);
        service.calculatePrice(order);
        BigDecimal bd = new BigDecimal("220.00");
        
        assertEquals(bd, order.getTotal());
    }
}
