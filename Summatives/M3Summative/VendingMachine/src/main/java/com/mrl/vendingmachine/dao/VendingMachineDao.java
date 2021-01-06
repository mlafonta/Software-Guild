/**
 * @author Maxwell R. Lafontant
 * @contact maxwell@maxwellRLafontant.com
 * Date Created 1/3/21
 * Date Last Modified 1/3/21
 */
package com.mrl.vendingmachine.dao;

import com.mrl.vendingmachine.dto.Coins;
import com.mrl.vendingmachine.dto.Product;
import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineDao {
    BigDecimal addToBalance(Coins coin);
    
    BigDecimal getBalance();
    
    List<Product> listProducts() throws VendingMachinePersistenceException;
    
    Product buyProduct(Product product) throws VendingMachinePersistenceException;
    
    Product getProduct(String name) throws VendingMachinePersistenceException;
    
    Product addProduct(String name, Product product) throws VendingMachinePersistenceException;
    
    Product removeProduct(String name) throws VendingMachinePersistenceException;
    
    Product adjustInventory(String name, Product product) throws VendingMachinePersistenceException;
    
    Product adjustPrice(String name, Product product) throws VendingMachinePersistenceException;
}
