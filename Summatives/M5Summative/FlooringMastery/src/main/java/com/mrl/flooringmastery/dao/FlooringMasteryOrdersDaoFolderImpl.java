/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.flooringmastery.dao;

import com.mrl.flooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author flafo
 */
public class FlooringMasteryOrdersDaoFolderImpl implements FlooringMasteryOrdersDao {

    private final String ORDER_FOLDER;
    public static final String DELIMITER = "::";
    private Map<Integer, Order> orders = new HashMap<>();

    public FlooringMasteryOrdersDaoFolderImpl() {
        ORDER_FOLDER = "Orders/";
    }

    public FlooringMasteryOrdersDaoFolderImpl(String orderFolderTextFile) {
        ORDER_FOLDER = orderFolderTextFile;
    }

    @Override
    public Order retrieveOrder(LocalDate date, int orderNumber) throws NullPointerException, IOException {
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
    public List<Order> displayOrders(LocalDate date) throws IOException {
        orders.clear();
        loadDate(date);
        return new ArrayList<Order>(orders.values());
    }

    @Override
    public Order addOrder(LocalDate date, Order order) throws IOException {
        orders.clear();
        loadDate(date);
        orders.put(order.getOrderNumber(), order);
        writeToFile(date);
        return order;
    }

    @Override
    public Order editOrder(LocalDate date, Order order) throws IOException {
        orders.clear();
        loadDate(date);
        int orderNumber = order.getOrderNumber();
        orders.replace(orderNumber, order);
        writeToFile(date);
        return order;
    }

    @Override
    public Order removeOrder(LocalDate date, int orderNumber) throws IOException {
        orders.clear();
        loadDate(date);
        Order order = orders.get(orderNumber);
        orders.remove(orderNumber, order);
        writeToFile(date);
        return order;
    }


    private void loadDate(LocalDate date) throws IOException {
        String dateString = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        String fileName = ("Orders_" + dateString + ".txt");
        File dateFile = new File(ORDER_FOLDER + fileName);
        dateFile.createNewFile();
        if (dateFile.length() == 0) {
            PrintWriter out;
            try {
                out = new PrintWriter(new FileWriter(dateFile, true));
            } catch (IOException e) {
                throw new IOException();
            }
            out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
            out.flush();
            out.close();
        }
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(dateFile)));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }
        String currentLine;
        Order currentOrder;
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentOrder = unmarshallOrder(currentLine);
            orders.put(currentOrder.getOrderNumber(), currentOrder);
        }
        scanner.close();
    }

    private void writeToFile(LocalDate date) throws IOException {
        String dateString = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        String fileName = ("Orders_" + dateString + ".txt");
        File dateFile = new File(ORDER_FOLDER + fileName);
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(dateFile));
        } catch (IOException e) {
            throw new IOException();
        }
        String orderAsText;
        List<Order> orderList = new ArrayList<Order>(orders.values());
        out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
        if (!orderList.isEmpty()) {
            for (Order currentOrder : orderList) {
                orderAsText = marshallOrder(currentOrder);
                out.println(orderAsText);
                out.flush();
            }
            out.close();

        } else {
            dateFile.delete();
        }

    }

    private Order unmarshallOrder(String orderAsText) {
        String[] orderTokens = orderAsText.split(DELIMITER);
        int orderNumber = Integer.parseInt(orderTokens[0]);
        Order orderFromFile = new Order(orderNumber);
        orderFromFile.setCustomerName(orderTokens[1]);
        orderFromFile.setState(orderTokens[2]);
        BigDecimal taxRate = new BigDecimal(orderTokens[3]);
        orderFromFile.setTaxRate(taxRate);
        orderFromFile.setProductType(orderTokens[4]);
        BigDecimal area = new BigDecimal(orderTokens[5]);
        orderFromFile.setArea(area);
        BigDecimal costPerSquareFoot = new BigDecimal(orderTokens[6]);
        orderFromFile.setCostPerSquareFoot(costPerSquareFoot);
        BigDecimal laborCostPerSquareFoot = new BigDecimal(orderTokens[7]);
        orderFromFile.setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        orderFromFile.setMaterialCost();
        orderFromFile.setLaborCost();
        orderFromFile.setTax();
        orderFromFile.setTotal();
        return orderFromFile;
    }

    private String marshallOrder(Order order) {
        String orderAsText = order.getOrderNumber() + DELIMITER;
        String customerName = order.getCustomerName();
        orderAsText += customerName + DELIMITER;
        String state = order.getState();
        orderAsText += state + DELIMITER;
        String taxRate = order.getTaxRate().toString();
        orderAsText += taxRate + DELIMITER;
        String productType = order.getProductType();
        orderAsText += productType + DELIMITER;
        String area = order.getArea().toString();
        orderAsText += area + DELIMITER;
        String costPerSquareFoot = order.getCostPerSquareFoot().toString();
        orderAsText += costPerSquareFoot + DELIMITER;
        String laborCostPerSquareFoot = order.getLaborCostPerSquareFoot().toString();
        orderAsText += laborCostPerSquareFoot + DELIMITER;
        String materialCost = order.getMaterialCost().toString();
        orderAsText += materialCost + DELIMITER;
        String laborCost = order.getLaborCost().toString();
        orderAsText += laborCost + DELIMITER;
        String tax = order.getTax().toString();
        orderAsText += tax + DELIMITER;
        String total = order.getTotal().toString();
        orderAsText += total;
        return orderAsText;
    }

    @Override
    public void cleanUpEmptyDates(LocalDate date) throws IOException {
        orders.clear();
        loadDate(date);
        String dateString = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        String fileName = ("Orders_" + dateString + ".txt");
        File dateFile = new File(ORDER_FOLDER + fileName);
        List<Order> orderList = new ArrayList<Order>(orders.values());
        if (orderList.isEmpty()) {
            dateFile.delete();
        }
    }

    @Override
    public void exportData() throws IOException {
        File dataExport = new File("Backup/DataExport.txt");
        dataExport.createNewFile();
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter("Backup/DataExport.txt"));
        } catch (IOException e) {
            throw new IOException();
        }
        out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total,Date");
        File orderFile = new File("Orders");
        File[] orderFileContents = orderFile.listFiles();
        Scanner scanner;
        for (File currentFile : orderFileContents) {
            String name = currentFile.getName().toString();
            LocalDate date = LocalDate.parse(name.substring(7, 15), DateTimeFormatter.ofPattern("MMddyyyy"));
            String dateFormatted = date.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            try {
                scanner = new Scanner(new BufferedReader(new FileReader(currentFile)));
            } catch (FileNotFoundException e) {
                throw new FileNotFoundException();
            }
            String currentLine;
            Order order;
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                order = unmarshallOrder(currentLine);
                String orderAsText = marshallOrder(order);
                orderAsText += DELIMITER + dateFormatted;
                out.println(orderAsText);
                out.flush();
            }
            scanner.close();
        }
        out.close();
    }

    @Override
    public List<Order> retrieveAll() throws IOException {
        File orderFile = new File(ORDER_FOLDER);
        File[] orderFileContents = orderFile.listFiles();
        Scanner scanner;
        List<Order> allOrders = new ArrayList<>();
        for (File currentFile : orderFileContents) {
            try {
                scanner = new Scanner(new BufferedReader(new FileReader(currentFile)));
            } catch (FileNotFoundException e) {
                throw new FileNotFoundException();
            }
            String currentLine;
            Order order;
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                order = unmarshallOrder(currentLine);
                allOrders.add(order);
            }
        }
        return allOrders;
    }
}