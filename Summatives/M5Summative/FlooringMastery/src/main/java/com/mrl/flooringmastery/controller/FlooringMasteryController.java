/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.flooringmastery.controller;

import com.mrl.flooringmastery.dto.Order;
import com.mrl.flooringmastery.dto.Product;
import com.mrl.flooringmastery.dto.Tax;
import com.mrl.flooringmastery.service.FlooringMasteryService;
import com.mrl.flooringmastery.ui.FlooringMasteryView;
import com.mrl.flooringmastery.ui.UserExitException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author flafo
 */
public class FlooringMasteryController {

    private FlooringMasteryView view;
    private FlooringMasteryService service;

    public FlooringMasteryController(FlooringMasteryView view, FlooringMasteryService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        displayOrders();
                        break;
                    case 2:
                        addOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        exportData();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
        } catch (FileNotFoundException e) {
            view.displayErrorMessage(e.getMessage());
        }
        view.displayExitMessage();
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void displayOrders() {
        LocalDate date = view.getDate();
        List<Order> orderList = service.getOrdersForDate(date);
        if (orderList.isEmpty()) {
            view.displayNoOrdersForDateBanner();
        } else {
            view.displayOrderListForDate(date, orderList);
        }
    }

    private void addOrder() throws FileNotFoundException, UserExitException {
        LocalDate date = view.getDate();
        Order order = service.createNewOrderNumberForDate(date);
        view.getCustomerName(order);
        getState(order);
        if (order.getState() != null) {
            getProduct(order);
            if (order.getProductType() != null) {
                view.getArea(order);
                if (order.getArea() != null) {
                    service.calculatePrice(order);
                    int confirmation = view.confirm(order, date);
                    if (confirmation == 1) {
                        service.addOrder(date, order);
                    } else {
                        view.displayActionCancelled();
                    }
                } else {
                    view.displayActionCancelled();
                }
            }
        }
    }
    

    

    

    private void editOrder() {
        LocalDate date = view.getDate();
        List<Order> orderList = service.getOrdersForDate(date);
        view.displayOrderListForDate(orderList);
        int orderNumber = view.getOrderNumber();
        Order order = service.retrieveOrder(date, orderNumber);
        view.getEditedInfo(order);
        service.calculatePrice(order);
        int confirmation = view.confirm(order);
        if (confirmation == 1) {
            service.editOrder();
        } else {
            view.displayActionCancelled();
        }
    }

    private void removeOrder() {
        LocalDate date = view.getDate();
        List<Order> orderList = service.getOrdersForDate(date);
        view.displayOrderListForDate(orderList);
        int orderNumber = view.getOrderNumber();
        Order order = service.retrieveOrder(date, orderNumber);
        int confirmation = view.confirm(order);
        if (confirmation == 1) {
            service.removeOrder();
        } else {
            view.displayActionCancelled();
        }
    }

    private void exportData() {
        service.exportData();
        view.displayExportSuccess();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void getState(Order order) throws FileNotFoundException, UserExitException {
        List<Tax> states = service.getStateList();
        try {
            view.getState(states, order);
        } catch (UserExitException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void getProduct(Order order) throws FileNotFoundException, UserExitException {
        List<Product> productList = service.getProductList();
        try {
            view.getProduct(productList, order);
        } catch (UserExitException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
}
