/**
 * @author Maxwell R. Lafontant
 * @contact maxwell@maxwellRLafontant.com
 * Date Created 1/3/21
 * Date Last Modified 1/3/21
 */
package com.mrl.vendingmachine.dao;

import com.mrl.vendingmachine.dto.Product;
import java.util.List;

public interface VendingMachineDao {
    
    List<Product> listProducts();
    
    Product buyProduct(String name);
    
    Product getProduct(String name);
    
    Product addProduct(String name, Product product);
    
    Product removeProduct(String name);
    
    Product adjustInventory(String name, Product product);
    
    Product adjustPrice(String name, Product product);
}
