/**
 * @author Maxwell R. Lafontant
 * @contact maxwell@maxwellRLafontant.com Date Created 1/3/21 Date Last Modified
 * 1/3/21
 */
package com.mrl.vendingmachine.dao;

import com.mrl.vendingmachine.dto.Coins;
import com.mrl.vendingmachine.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineDaoFileImp1 implements VendingMachineDao {

    private final String INVENTORY_FILE;
    public static final String DELIMITER = "::";
    public VendingMachineDaoFileImp1() {
        INVENTORY_FILE = "inventory.txt";
    }
    public VendingMachineDaoFileImp1(String inventoryTextFile) {
        INVENTORY_FILE = inventoryTextFile;
    }
    
    private Map<String, Product> products = new HashMap<>();
    BigDecimal balance = new BigDecimal("0").setScale(2);
    BigDecimal quarter = new BigDecimal(".25");
    BigDecimal dime = new BigDecimal(".1");
    BigDecimal nickel = new BigDecimal(".05");
    BigDecimal penny = new BigDecimal(".01");

    @Override
    public List<Product> listProducts() throws VendingMachinePersistenceException {
        loadInventory();
        return new ArrayList<Product>(products.values());
    }

    @Override
    public Product buyProduct(Product product) throws VendingMachinePersistenceException {
        loadInventory();
        product.setStock(product.getStock() - 1);
        products.replace(product.getName(), product);
        balance = balance.subtract(product.getPrice());
        writeInventory();
        return product;
    }

    @Override
    public Product addProduct(String name, Product product) throws VendingMachinePersistenceException {
        loadInventory();
        Product newProduct = products.put(name, product);
        writeInventory();
        return newProduct;
    }

    @Override
    public Product removeProduct(String name) throws VendingMachinePersistenceException {
        loadInventory();
        Product removedProduct = products.remove(name);
        writeInventory();
        return removedProduct;
    }

    @Override
    public Product adjustInventory(String name, Product product) throws VendingMachinePersistenceException {
        loadInventory();
        Product editedProduct = products.replace(name, product);
        writeInventory();
        return editedProduct;
    }

    @Override
    public Product adjustPrice(String name, Product product) throws VendingMachinePersistenceException {
        loadInventory();
        Product editedProduct = products.replace(name, product);
        writeInventory();
        return editedProduct;
    }

    @Override
    public Product getProduct(String name) throws VendingMachinePersistenceException {
        loadInventory();
        Product product = products.get(name);
        return product;
    }

    private Product unmarshallProduct(String productAsText) {
        String[] productTokens = productAsText.split(DELIMITER);
        String name = productTokens[0];
        Product productFromFile = new Product(name);
        BigDecimal price = new BigDecimal(productTokens[1]);
        productFromFile.setPrice(price);
        productFromFile.setStock(Integer.parseInt(productTokens[2]));
        return productFromFile;
    }

    private void loadInventory() throws VendingMachinePersistenceException {
        Scanner scanner;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException("Could not load inventory data into memory.", e);
        }

        String currentLine;
        Product currentProduct;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentProduct = unmarshallProduct(currentLine);
            products.put(currentProduct.getName(), currentProduct);
        }
        scanner.close();
    }

    private String marshallProduct(Product product) {
        String productAsText = product.getName() + DELIMITER;
        String price = product.getPrice().toString();
        productAsText += price + DELIMITER;
        String stock = String.valueOf(product.getStock());
        productAsText += stock;
        return productAsText;
    }

    private void writeInventory() throws VendingMachinePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not save inventory data.", e);
        }
        String productAsText;
        List<Product> productList = this.listProducts();
        for (Product currentProduct : productList) {
            productAsText = marshallProduct(currentProduct);
            out.println(productAsText);
            out.flush();
        }
        out.close();
    }

    @Override
    public BigDecimal addToBalance(Coins coin) {
        switch (coin) {
            case QUARTER:
                balance = balance.add(quarter);
                break;
            case DIME:
                balance = balance.add(dime);
                break;
            case NICKEL:
                balance = balance.add(nickel);
                break;
            case PENNY:
                balance = balance.add(penny);
                break;
        }
        return balance;
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

}
