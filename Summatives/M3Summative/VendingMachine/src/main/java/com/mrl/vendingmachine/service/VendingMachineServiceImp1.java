/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.vendingmachine.service;

import com.mrl.vendingmachine.dao.VendingMachineAuditDao;
import com.mrl.vendingmachine.dao.VendingMachineDao;
import com.mrl.vendingmachine.dao.VendingMachinePersistenceException;
import com.mrl.vendingmachine.dto.Coins;
import com.mrl.vendingmachine.dto.Product;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author flafo
 */
public class VendingMachineServiceImp1 implements VendingMachineService {

    VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;

    public VendingMachineServiceImp1(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public BigDecimal getBalance() {
        return dao.getBalance();
    }

    @Override
    public BigDecimal addToBalance(Coins coin) {
        return dao.addToBalance(coin);
    }

    @Override
    public void buyProduct(Product product) throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException, NoSuchProductException {
        if (product.getStock() == 0) {
            throw new NoItemInventoryException("Item not in stock");
        }
        BigDecimal balance = dao.getBalance();
        BigDecimal price = product.getPrice();
        if (balance.compareTo(price) < 0) {
            throw new InsufficientFundsException("ERROR: Insufficient funds. " + product.getName() + " costs $" + price + ", but your current balance is only $" + balance + ".");
        }
        dao.buyProduct(product);
        auditDao.writeAuditEntry(product.getName() + " purchased.");

    }

    @Override
    public List<Product> listProducts() throws VendingMachinePersistenceException {
        return dao.listProducts();
    }

    @Override
    public Product getProduct(String name) throws VendingMachinePersistenceException, NoSuchProductException {
        if (dao.getProduct(name) == null) {
            throw new NoSuchProductException("ERROR: No such product exists.");
        }
        return dao.getProduct(name);
    }

    @Override
    public void addProduct(Product product) throws VendingMachinePersistenceException, VendingMachineDataValidationException, VendingMachineDuplicateProductException {
        if (dao.getProduct(product.getName()) != null) {
            throw new VendingMachineDuplicateProductException("ERROR: Could not add product, " + product.getName() + " already exists in the system");
        }
        validateProductData(product);
        dao.addProduct(product.getName(), product);
        auditDao.writeAuditEntry(product.getStock() + " " + product.getName() + " added.");
    }

    @Override
    public Product removeProduct(String name) throws VendingMachinePersistenceException {
        auditDao.writeAuditEntry(name + " removed.");
        return dao.removeProduct(name);

    }

    @Override
    public Product adjustInventory(String name, Product product) throws VendingMachinePersistenceException, VendingMachineDataValidationException {
        dao.adjustInventory(name, product);
        validateProductData(product);
        auditDao.writeAuditEntry(product.getName() + " stock changed to " + product.getStock() + ".");
        return product;
    }

    @Override
    public Product adjustPrice(String name, Product product) throws VendingMachinePersistenceException, VendingMachineDataValidationException {
        dao.adjustPrice(name, product);
        validateProductData(product);
        auditDao.writeAuditEntry(product.getName() + " price changed to " + product.getPrice() + ".");
        return product;
    }

    private void validateProductData(Product product) throws
            VendingMachineDataValidationException {
        String price = product.getPrice().toString();
        String stock = String.valueOf(product.getStock());
        if (product.getName() == null
                || product.getName().trim().length() == 0
                || price == null
                || price.trim().length() == 0
                || stock == null
                || stock.trim().length() == 0) {
            throw new VendingMachineDataValidationException(
                    "ERROR: All fields are required.");
        }

    }

}
