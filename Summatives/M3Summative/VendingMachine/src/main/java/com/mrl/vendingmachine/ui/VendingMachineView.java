/**
 * @author Maxwell R. Lafontant
 * @contact maxwell@maxwellRLafontant.com Date Created 1/3/21 Date Last Modified
 * 1/3/21
 */
package com.mrl.vendingmachine.ui;

import com.mrl.vendingmachine.dto.Change;
import com.mrl.vendingmachine.dto.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author flafo
 */
public class VendingMachineView {

    private UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public void displayMainBanner() {
        io.print("===MRL VENDING===");
    }

    public void displayProducts(List<Product> productList) {
        //get rid of zero inventory
        List<Product> nonZeroInventory = productList.stream()
                .filter((p) -> p.getStock() > 0)
                .collect(Collectors.toList());
        for (Product currentProduct : nonZeroInventory) {
            io.print(currentProduct.getName() + " | $" + currentProduct.getPrice());
        }
    }

    public void displayBalance(BigDecimal balance) {
        io.print("Current Balance: $" + balance);
    }

    public int printMenuAndGetSelection() {
        io.print("===MENU===");
        io.print("1. Insert Coins");
        io.print("2. Purchase Item");
        io.print("3. System Maintenance");
        io.print("4. Get Change and Exit");

        return io.readInt("Please select from the above choices.", 1, 4);
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public int printCoinsMenuAndGetSelection() {
        io.print("===INSERT COINS===");
        io.print("1. Insert Quarter");
        io.print("2. Insert Dime");
        io.print("3. Insert Nickel");
        io.print("4. Insert Penny");
        io.print("5. Return to main menu");

        return io.readInt("Please select from the above choices.", 1, 5);
    }

    public void displayChange(Change change) {
        io.print("Your change is:");
        if (change.getQuarters().compareTo(BigDecimal.ZERO) == 0) {
            //display nothing
        } else if (change.getQuarters().compareTo(BigDecimal.ONE) == 0) {
            io.print("1 Quarter");
        } else {
            io.print(change.getQuarters() + " Quarters");
        }
        if (change.getDimes().compareTo(BigDecimal.ZERO) == 0) {
            //display nothing
        } else if (change.getDimes().compareTo(BigDecimal.ONE) == 0) {
            io.print("1 Dime");
        } else {
            io.print(change.getDimes() + " Dimes");
        }
        if (change.getNickels().compareTo(BigDecimal.ZERO) == 0) {
            //display nothing
        } else if (change.getNickels().compareTo(BigDecimal.ONE) == 0) {
            io.print("1 Nickel");
        } else {
            io.print(change.getNickels() + " Nickels");
        }
        if (change.getPennies().compareTo(BigDecimal.ZERO) == 0) {
            //display nothing
        } else if (change.getPennies().compareTo(BigDecimal.ONE) == 0) {
            io.print("1 Penny");
        } else {
            io.print(change.getPennies() + " Pennies");
        }
    }

    public void displayNoChange() {
        io.print("As your current balance is $0.00, you will recieve no change.");
    }

    public int printMaintenanceMenuAndGetSelection() {
        io.print("===Maintenance Menu===");
        io.print("1. Add Product");
        io.print("2. Remove Product");
        io.print("3. Adjust Inventory");
        io.print("4. Adjust Price");
        io.print("5. Return to Main Menu");
        return io.readInt("Please select from the above choices.", 1, 5);
    }

    public void displayAddProductBanner() {
        io.print("===Add Product===");
    }

    public Product getNewProductInfo() {
        String name = io.readString("Please enter the name of the product");
        Product newProduct = new Product(name);
        String priceString = io.readString("Please enter the price of the product");
        BigDecimal price = new BigDecimal(priceString).setScale(2);
        int stock = io.readInt("Please enter the current stock of the product");
        newProduct.setPrice(price);
        newProduct.setStock(stock);
        return newProduct;

    }

    public void pressEnterToContinue() {
        io.readString("Please press enter to continue");
    }

    public void displayAddSuccess() {
        io.print("Product successfully added");
    }

    public void displayAlreadyExists() {
        io.print("Product already exists in system");
    }

    public void displayRemoveProductBanner() {
        io.print("===Remove Product===");
    }

    public String getProductName() {
        String name = io.readString("What is the product's name?");
        return name;
    }

    public void displayRemoveSuccess() {
        io.print("Product successfully removed");
    }

    public void displayDoesNotExist() {
        io.print("No product with given name found in system");
    }

    public void displayAdjustInventoryBanner() {
        io.print("===Adjust Inventory===");
    }

    public Product getAdjustedInventory(Product product) {
        int stock = io.readInt("Current stock: " + product.getStock() + "; Please enter new total stock amount.");
        product.setStock(stock);
        return product;
    }

    public void displayEditSuccess() {
        io.print("Data successfully edited");
    }

    public void displayAdjustPriceBanner() {
        io.print("===Adjust Price===");
    }

    public Product getAdjustedPrice(Product product) {
        String priceString = io.readString("Current price: " + product.getPrice() + "; Please enter new price.");
        BigDecimal price = new BigDecimal(priceString);
        product.setPrice(price);
        return product;
    }

    public void displayExitMessage() {
        io.print("Thank you and have a nice day!");
    }
}
