/**
 * @author Maxwell R. Lafontant
 * @contact maxwell@maxwellRLafontant.com Date Created 1/3/21 Date Last Modified
 * 1/3/21
 */
package com.mrl.vendingmachine.service;

import com.mrl.vendingmachine.dao.VendingMachinePersistenceException;
import com.mrl.vendingmachine.dto.Coins;
import com.mrl.vendingmachine.dto.Product;
import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineService {

    /**
     * convert user input of coin to functional BigDecimal
     *
     * @param coin
     * @return BigDecimal value of coin enum
     */

    BigDecimal getBalance();

    BigDecimal addToBalance(Coins coin);

    void buyProduct(Product product) throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException, NoSuchProductException;

    List<Product> listProducts() throws VendingMachinePersistenceException;


    Product getProduct(String name) throws VendingMachinePersistenceException, NoSuchProductException;

    void addProduct(Product product) throws VendingMachinePersistenceException, VendingMachineDataValidationException, VendingMachineDuplicateProductException;

    Product removeProduct(String name) throws VendingMachinePersistenceException;

    Product adjustInventory(String name, Product product) throws VendingMachinePersistenceException, VendingMachineDataValidationException;

    Product adjustPrice(String name, Product product) throws VendingMachinePersistenceException, VendingMachineDataValidationException;
}
