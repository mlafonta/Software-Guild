/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.flooringmastery.ui;

import com.mrl.flooringmastery.dto.Order;
import com.mrl.flooringmastery.dto.Product;
import com.mrl.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author flafo
 */
public class FlooringMasteryView {

    private UserIO io;

    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print("*<<Flooring Program>>");
        io.print("*1. Display Orders");
        io.print("*2. Add an Order");
        io.print("*3. Edit an Order");
        io.print("*4. Remove an Order");
        io.print("*5. Export All Data");
        io.print("*6. Quit");
        io.print("*");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public void displayExitMessage() {
        io.print("Program closed. Have a nice day!");
    }

    public LocalDate getDate() {
        return io.readLocalDate("What date is this order for? (mm/dd/yyyy)");
    }

    public void displayOrderListForDate(LocalDate date, List<Order> orderList) {
        String formattedDate = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        io.print("All orders for " + formattedDate + ":");
        Collections.sort(orderList);
        for (Order order : orderList) {
            io.print("Order Number: " + String.valueOf(order.getOrderNumber()));
            io.print("Customer Name: " + order.getCustomerName());
            io.print("Order Total: $" + order.getTotal().toString());
        }
    }

    public void displayNoOrdersForDateBanner() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void getCustomerName(Order order) {
        boolean Blank = true;
        while (Blank) {
            String name = io.readString("What is the customer's name?");
            if (name.isEmpty() || name.isBlank()) {
                io.print("Please enter a value.");
                Blank = true;
            } else {
                order.setCustomerName(name);
                Blank = false;
            }
        }
    }

    public void getState(List<Tax> states, Order order) throws UserExitException {
        boolean validState = false;
        List<String> stateList = states.stream()
                .map((p) -> p.getState())
                .collect(Collectors.toList());
        while (!validState) {
            String state = io.readString("What state is this order in?");

            if (stateList.contains(state)) {
                order.setState(state);
                validState = true;
            } else if (state.equalsIgnoreCase("exit")) {
                throw new UserExitException("Function Left");
            } else {
                io.print("Please enter a state in our service area, or type \"Exit\" to return to the main menu");
            }

        }
    }

    public void displayErrorMessage(String errorMsg) {
        io.print(errorMsg);
    }

    public void displayActionCancelled() {
        io.print("Action Cancelled");
    }

    public void getProduct(List<Product> productList, Order order) throws UserExitException {
        boolean validProduct = false;
        List<String> productNames = productList.stream()
                .map((p) -> p.getProductType())
                .collect(Collectors.toList());
        while (!validProduct) {
            io.print("===Product List===");
            for (Product product : productList) {
                io.print("Product Name: " + product.getProductType());
                io.print("Material cost per square foot: " + product.getCostPerSquareFoot());
                io.print("Labor cost per square foot: " + product.getLaborCostPerSquareFoot());
                io.print("");
            }
            String productString = io.readString("Which product is this order for?");
            if (productNames.contains(productString)) {
                order.setProductType(productString);
                validProduct = true;
            } else if (productString.equalsIgnoreCase("exit")) {
                throw new UserExitException("Function Left");
            } else {
                io.print("Please enter one of the listed products or type \"Exit\" to return to the main menu");
            }
        }
    }

    public void getArea(Order order) {
        BigDecimal min = new BigDecimal("100");
        BigDecimal max = new BigDecimal("10000000000");
        BigDecimal area = io.readBigDecimal("What is the square footage of the order? Minimum 100 square feet.", min, max);
        order.setArea(area);
    }

    public int confirm(Order order, LocalDate date) {
        io.print("===CONFIRMATION SCREEN===");
        io.print("Order date: " + date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
        io.print("Order number: " + order.getOrderNumber());
        io.print("Customer name: " + order.getCustomerName());
        io.print("State: " + order.getState());
        io.print("Tax rate: " + order.getTaxRate() + "%");
        io.print("Product type: " + order.getProductType());
        io.print("Area: " + order.getArea() + " square feet");
        io.print("Cost per square foot: $" + order.getCostPerSquareFoot());
        io.print("Labor cost per square foot: $" + order.getLaborCostPerSquareFoot());
        io.print("Material Cost: $" + order.getMaterialCost());
        io.print("Labor Cost: $" + order.getLaborCost());
        io.print("Tax: $" + order.getTax());
        io.print("--------------------");
        io.print("Total: $" + order.getTotal());
        io.print("--------------------");
        io.print("CONFIRM?");
        io.print("1. Yes");
        return io.readInt("2. No", 1, 2);
        
    }
}
