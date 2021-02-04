/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.flooringmastery.service;

import com.mrl.flooringmastery.dao.FlooringMasteryOrdersDao;
import com.mrl.flooringmastery.dto.Order;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author flafo
 */
public class FlooringMasteryOrdersDaoStubImpl implements FlooringMasteryOrdersDao {
    public Order order1;
    public Order order2;
    @Override
    public Order retrieveOrder(LocalDate date, int orderNumber) throws IOException {
        return order1;
    }

    @Override
    public List<Order> displayOrders(LocalDate date) throws IOException {
        List<Order> orderList = new ArrayList<>();
        orderList.add(order1);
        return orderList;
    }



    @Override
    public Order addOrder(LocalDate date, Order order) throws IOException {
        return order1;
    }

    @Override
    public Order editOrder(LocalDate date, Order order) throws IOException {
        return order1;
    }

    @Override
    public Order removeOrder(LocalDate date, int orderNumber) throws IOException {
        return order1;
    }


    @Override
    public void cleanUpEmptyDates(LocalDate date) throws IOException {
    }

    @Override
    public void exportData() throws IOException {
    }

    @Override
    public List<Order> retrieveAll() throws IOException {
        order1 = new Order(1);
        order2 = new Order(2);
        List<Order> orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);
        return orderList;
    }
    
}
