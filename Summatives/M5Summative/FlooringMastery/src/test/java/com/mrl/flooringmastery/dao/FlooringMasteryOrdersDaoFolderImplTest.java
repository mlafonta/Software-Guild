/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.flooringmastery.dao;

import com.mrl.flooringmastery.dto.Order;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        String orderFolderTextFile = "TestOrders/";
        new FileWriter(orderFolderTextFile);

    }

    @Test
    public void addRetrieveOrder() throws Exception {
        LocalDate date = LocalDate.parse("10/10/3030", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Order order = new Order(1);
        order.setCustomerName("Testy");
        order.setState("Texas");
        order.setTaxRate(BigDecimal.ONE);
        order.setProductType("Wood");
        order.setArea(BigDecimal.ONE);
        order.setCostPerSquareFoot(BigDecimal.TEN);
        order.setLaborCostPerSquareFoot(BigDecimal.TEN);
        order.setMaterialCost();
        order.setLaborCost();
        order.setTax();
        order.setTotal();

        testDao.addOrder(date, order);
        Order gotOrder = testDao.retrieveOrder(date, 1);

        assertEquals(order.getState(), gotOrder.getState());
        assertEquals(order.getTotal(), gotOrder.getTotal());
    }

    @Test
    public void removeRetrieveAll() throws Exception {
        LocalDate date = LocalDate.parse("10/11/3030", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Order order = new Order(1);
        order.setCustomerName("Testy");
        order.setState("Texas");
        order.setTaxRate(BigDecimal.ONE);
        order.setProductType("Wood");
        order.setArea(BigDecimal.ONE);
        order.setCostPerSquareFoot(BigDecimal.TEN);
        order.setLaborCostPerSquareFoot(BigDecimal.TEN);
        order.setMaterialCost();
        order.setLaborCost();
        order.setTax();
        order.setTotal();

        Order order2 = new Order(2);
        order2.setCustomerName("Testy2");
        order2.setState("Texas");
        order2.setTaxRate(BigDecimal.ONE);
        order2.setProductType("Wood");
        order2.setArea(BigDecimal.ONE);
        order2.setCostPerSquareFoot(BigDecimal.TEN);
        order2.setLaborCostPerSquareFoot(BigDecimal.TEN);
        order2.setMaterialCost();
        order2.setLaborCost();
        order2.setTax();
        order2.setTotal();
        testDao.addOrder(date, order);
        testDao.addOrder(date, order2);
        Order removedOrder = testDao.removeOrder(date, order.getOrderNumber());
        List<Order> orderList = testDao.retrieveAll();

        assertEquals(removedOrder.getOrderNumber(), order.getOrderNumber());
        assertFalse(orderList.contains(order));
    }

    @Test
    public void editDisplay() throws Exception {
        LocalDate date = LocalDate.parse("10/12/3030", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Order order = new Order(1);
        order.setCustomerName("Testy");
        order.setState("Texas");
        order.setTaxRate(BigDecimal.ONE);
        order.setProductType("Wood");
        order.setArea(BigDecimal.ONE);
        order.setCostPerSquareFoot(BigDecimal.TEN);
        order.setLaborCostPerSquareFoot(BigDecimal.TEN);
        order.setMaterialCost();
        order.setLaborCost();
        order.setTax();
        order.setTotal();

        testDao.addOrder(date, order);

        Order editedOrder = new Order(1);
        editedOrder.setCustomerName("Testy");
        editedOrder.setState("Texas");
        editedOrder.setTaxRate(BigDecimal.ONE);
        editedOrder.setProductType("Wood");
        editedOrder.setArea(BigDecimal.TEN);
        editedOrder.setCostPerSquareFoot(BigDecimal.TEN);
        editedOrder.setLaborCostPerSquareFoot(BigDecimal.TEN);
        editedOrder.setMaterialCost();
        editedOrder.setLaborCost();
        editedOrder.setTax();
        editedOrder.setTotal();

        testDao.editOrder(date, editedOrder);
        BigDecimal bd = new BigDecimal("202.00");
        List<Order> orderList = testDao.displayOrders(date);
        
        assertEquals(bd, testDao.retrieveOrder(date, 1).getTotal());
        assertEquals(1, orderList.size());
    }
}
