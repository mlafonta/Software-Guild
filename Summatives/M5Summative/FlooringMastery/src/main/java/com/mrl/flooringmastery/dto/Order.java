/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.flooringmastery.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author flafo
 */
public class Order implements Comparable<Order> {
    private int orderNumber;
    private String customerName;
    private String State;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal area;
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal total;

    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    public int getOrderNumber() {
        return orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost() {
        materialCost = costPerSquareFoot.multiply(area).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost() {
        laborCost = laborCostPerSquareFoot.multiply(area).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax() {
        BigDecimal percentor = new BigDecimal("100");
        tax = taxRate.divide(percentor).multiply(materialCost.add(laborCost)).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal() {
        total = tax.add(materialCost.add(laborCost)).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public int compareTo(Order o) {
        int compareOrderNumber = ((Order)o).getOrderNumber();
        return this.orderNumber - compareOrderNumber;
    }


}
