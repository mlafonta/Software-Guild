/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.flooringmastery.dao;

import com.mrl.flooringmastery.dto.Order;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author flafo
 */
public interface FlooringMasteryOrdersDao {

    Order retrieveOrder(LocalDate date, int orderNumber) throws IOException;

    List<Order> displayOrders(LocalDate date) throws IOException;

    Order createNewOrderNumber(LocalDate date) throws IOException;

    Order addOrder(LocalDate date, Order order) throws IOException;

    Order editOrder(LocalDate date, Order order) throws IOException;

    Order removeOrder(LocalDate date, int orderNumber) throws IOException;

    void exportOrder() throws IOException;

    void cleanUpEmptyDates(LocalDate date) throws IOException;
    
    void exportData() throws IOException;

}
