/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.flooringmastery.dao;

import com.mrl.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author flafo
 */
public interface FlooringMasteryOrdersDao {
    Order retrieveOrder(LocalDate date, int orderNumber);
    
    List<Order> displayOrders(LocalDate date);
    
    Order createNewOrderNumber(LocalDate date);
    
    Order addOrder(LocalDate date, Order order);
    
    Order editOrder(LocalDate date, int orderNumber);
    
    Order removeOrder(LocalDate date, int orderNumber);
    
    void exportOrder();
        
}
