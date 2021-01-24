/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.flooringmastery.dao;

import com.mrl.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author flafo
 */
public class FlooringMasteryOrdersDaoFolderImpl implements FlooringMasteryOrdersDao {

    private Map<Integer, Order> orders = new HashMap<>();
    
    @Override
    public Order retrieveOrder(LocalDate date, int orderNumber) throws NullPointerException {
        orders.clear();
        loadDate(date);
        try {
            Order order = orders.get(orderNumber);
            return order;
        } catch (NullPointerException e) {
            throw new NullPointerException("No such file exists");
        }
    }

    @Override
    public List<Order> displayOrders(LocalDate date) {
        orders.clear();
        loadDate(date);
        return new ArrayList<Order>(orders.values());
    }

    @Override
    public Order addOrder(LocalDate date, Order order) {
        orders.clear();
        loadDate(date);
        orders.put(order.getOrderNumber(), order);
        return order;
    }

    @Override
    public Order editOrder(LocalDate date, int orderNumber) {
        orders.clear();
        loadDate(date);
        Order order = orders.get(orderNumber);
        orders.replace(orderNumber, order);
        return order;
    }

    @Override
    public Order removeOrder(LocalDate date, int orderNumber) {
        orders.clear();
        loadDate(date);
        Order order = orders.get(orderNumber);
        orders.remove(orderNumber, order);
        return order;
    }

    @Override
    public void exportOrder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void loadDate(LocalDate date) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order createNewOrderNumber(LocalDate date) {
        orders.clear();
        loadDate(date);
        int lastOrderNumber = 0;
        for(int key : orders.keySet()){
            if(key > lastOrderNumber) {
                key = lastOrderNumber;
            }
        }
        Order order = new Order(lastOrderNumber + 1);
        return order;
    }
    
}
