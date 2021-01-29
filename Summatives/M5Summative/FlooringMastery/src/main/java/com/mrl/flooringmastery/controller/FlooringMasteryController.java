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
import java.io.IOException;
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
        } catch (IOException | UserExitException e) {
            view.displayErrorMessage(e.getMessage());
        }
        view.displayExitMessage();
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void displayOrders() throws IOException {
        LocalDate date = view.getDate();
        List<Order> orderList = service.getOrdersForDate(date);
        if (orderList.isEmpty()) {
            view.displayEmptyDate();
            service.cleanUpEmptyDates(date);
        } else {
            view.displayOrderListForDate(date, orderList);
        }
    }

    private void addOrder() throws FileNotFoundException, UserExitException, IOException {
        boolean future = false;
        LocalDate date = LocalDate.now();
        while (!future) {
            date = view.getDate();
            if (date.isAfter(LocalDate.now())) {
                future = true;
            } else {
                view.displayFutureError();
            }
        }
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

    private void editOrder() throws IOException, UserExitException{
        LocalDate date = view.getDate();
        List<Order> orderList = service.getOrdersForDate(date);
        if (!orderList.isEmpty()) {
            view.displayOrderListForDate(date, orderList);
            int orderNumber = view.getOrderNumber(date, orderList);
            Order order = service.retrieveOrder(date, orderNumber);
            int nameQuery = view.editNameQuery(order);
            if(nameQuery == 1) {
                view.getCustomerName(order);
            }
            int stateQuery = view.editStateQuery(order);
            if(stateQuery == 1) {
                getState(order);
            }
            int productQuery = view.editProductQuery(order);
            if(productQuery == 1) {
                getProduct(order);
            }
            int areaQuery = view.editAreaQuery(order);
            if(areaQuery == 1) {
                view.getArea(order);
            }
            service.calculatePrice(order);
            int confirmation = view.confirm(order, date);
            if (confirmation == 1) {
                service.editOrder(date, order);
            } else {
                view.displayActionCancelled();
            }
        } else {
            view.displayEmptyDate();
            service.cleanUpEmptyDates(date);
        }
    }

    private void removeOrder() throws IOException{
        LocalDate date = view.getDate();
        List<Order> orderList = service.getOrdersForDate(date);
        if (!orderList.isEmpty()) {
            view.displayOrderListForDate(date, orderList);
            int orderNumber = view.getOrderNumber(date, orderList);
            Order order = service.retrieveOrder(date, orderNumber);
            int confirmation = view.confirm(order, date);
            if (confirmation == 1) {
                service.removeOrder(order, date);
                service.cleanUpEmptyDates(date);
            } else {
                view.displayActionCancelled();
            }
        } else {
            view.displayEmptyDate();
            service.cleanUpEmptyDates(date);
        }
    }

    private void exportData() throws IOException{
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
