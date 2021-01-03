/**
 * @author Maxwell R. Lafontant
 * @contact maxwell@maxwellRLafontant.com Date Created 1/3/21 Date Last Modified
 * 1/3/21
 */
package com.mrl.vendingmachine.dao;

import com.mrl.vendingmachine.dto.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author flafo
 */
public class VendingMachineDaoFileImp1 implements VendingMachineDao {

    private Map<String, Product> products = new HashMap<>();

    @Override
    public List<Product> listProducts() {
        return new ArrayList<Product>(products.values());
    }

    @Override
    public Product buyProduct(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product addProduct(String name, Product product) {
        Product newProduct = products.put(name, product);
        return newProduct;
    }

    @Override
    public Product removeProduct(String name) {
        Product removedProduct = products.remove(name);
        return removedProduct;
    }

    @Override
    public Product adjustInventory(String name, Product product) {
        Product editedProduct = products.replace(name, product);
        return editedProduct;
    }

    @Override
    public Product adjustPrice(String name, Product product) {
        Product editedProduct = products.replace(name, product);
        return editedProduct;
    }

    @Override
    public Product getProduct(String name) {
        Product product = products.get(name);
        return product;
    }

}
