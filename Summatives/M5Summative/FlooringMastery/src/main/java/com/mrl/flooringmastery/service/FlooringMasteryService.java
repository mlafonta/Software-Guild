/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.flooringmastery.service;

import com.mrl.flooringmastery.dto.Order;
import com.mrl.flooringmastery.dto.Product;
import com.mrl.flooringmastery.dto.Tax;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author flafo
 */
public interface FlooringMasteryService {

    public List<Order> getOrdersForDate(LocalDate date) throws IOException;

    public Order createNewOrderNumberForDate(LocalDate date) throws IOException;

    public List<Tax> getStateList() throws FileNotFoundException;

    public List<Product> getProductList() throws FileNotFoundException;

    public void calculatePrice(Order order) throws FileNotFoundException;

    public void addOrder(LocalDate date, Order order) throws IOException;

    public void cleanUpEmptyDates(LocalDate date) throws IOException;

    public Order retrieveOrder(LocalDate date, int orderNumber) throws IOException;

    public void removeOrder(Order order, LocalDate date) throws IOException;

    public void editOrder(LocalDate date, Order order) throws IOException;

    public void exportData() throws IOException;

}
