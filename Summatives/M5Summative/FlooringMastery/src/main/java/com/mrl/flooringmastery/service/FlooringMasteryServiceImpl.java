/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.flooringmastery.service;

import com.mrl.flooringmastery.dao.FlooringMasteryOrdersDao;
import com.mrl.flooringmastery.dao.FlooringMasteryProductDao;
import com.mrl.flooringmastery.dao.FlooringMasteryTaxDao;
import com.mrl.flooringmastery.dto.Order;
import com.mrl.flooringmastery.dto.Product;
import com.mrl.flooringmastery.dto.Tax;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author flafo
 */
public class FlooringMasteryServiceImpl implements FlooringMasteryService {

    FlooringMasteryProductDao productDao;
    FlooringMasteryTaxDao taxDao;
    FlooringMasteryOrdersDao orderDao;

    public FlooringMasteryServiceImpl(FlooringMasteryProductDao productDao, FlooringMasteryTaxDao taxDao, FlooringMasteryOrdersDao orderDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.taxDao = taxDao;
    }

    @Override
    public List<Order> getOrdersForDate(LocalDate date) {
        return orderDao.displayOrders(date);
    }

    @Override
    public Order createNewOrderNumberForDate(LocalDate date) {
        return orderDao.createNewOrderNumber(date);
    }

    @Override
    public List<Tax> getStateList() throws FileNotFoundException{
        return taxDao.getStateList();
    }

    @Override
    public List<Product> getProductList() throws FileNotFoundException {
        return productDao.getProductList();
    }

    @Override
    public void calculatePrice(Order order) throws FileNotFoundException{
        Tax tax = taxDao.getTax(order.getState());
        order.setTaxRate(tax.getTaxRate());
        Product product = productDao.getProduct(order.getProductType());
        order.setCostPerSquareFoot(product.getCostPerSquareFoot());
        order.setLaborCostPerSquareFoot(product.getLaborCostPerSquareFoot());
        order.setMaterialCost();
        order.setLaborCost();
        order.setTax();
        order.setTotal();
    }
}
