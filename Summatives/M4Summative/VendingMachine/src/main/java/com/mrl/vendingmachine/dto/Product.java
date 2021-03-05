/**
 * @author Maxwell R. Lafontant
 * @contact maxwell@maxwellRLafontant.com
 * Date Created 1/3/21
 * Date Last Modified 1/3/21
 */
package com.mrl.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private String name;
    private int stock;
    private BigDecimal price;
    
    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + this.stock;
        hash = 29 * hash + Objects.hashCode(this.price);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.stock != other.stock) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product{" + "name=" + name + ", stock=" + stock + ", price=" + price + '}';
    }
    
}
