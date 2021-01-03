/**
 * @author Maxwell R. Lafontant
 * @contact maxwell@maxwellRLafontant.com Date Created 1/3/21 Date Last Modified
 * 1/3/21
 */
package com.mrl.vendingmachine.controller;

import com.mrl.vendingmachine.dao.VendingMachineDao;
import com.mrl.vendingmachine.dto.Change;
import com.mrl.vendingmachine.dto.Coins;
import static com.mrl.vendingmachine.dto.Coins.DIME;
import static com.mrl.vendingmachine.dto.Coins.NICKEL;
import static com.mrl.vendingmachine.dto.Coins.PENNY;
import static com.mrl.vendingmachine.dto.Coins.QUARTER;
import com.mrl.vendingmachine.dto.Product;
import com.mrl.vendingmachine.service.VendingMachineService;
import com.mrl.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author flafo
 */
public class VendingMachineController {

    private VendingMachineView view;
    private VendingMachineService service;
    private VendingMachineDao dao;

    public VendingMachineController(VendingMachineView view, VendingMachineDao dao, VendingMachineService service) {
        this.dao = dao;
        this.view = view;
        this.service = service;
    }

    public void run() {
        boolean keepGoingMain = true;
        int menuSelection = 0;
        while (keepGoingMain) {
            displayProducts();
            displayBalance();

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    boolean keepGoingCoins = true;
                    int coinsMenuSelection = 0;
                    while (keepGoingCoins) {
                        coinsMenuSelection = getCoinsMenuSelection();

                        switch (coinsMenuSelection) {
                            case 1:
                                insertCoins(QUARTER);
                                break;
                            case 2:
                                insertCoins(DIME);
                                break;
                            case 3:
                                insertCoins(NICKEL);
                                break;
                            case 4:
                                insertCoins(PENNY);
                                break;
                            case 5:
                                keepGoingCoins = false;
                                break;
                            default:
                                unknownCommand();
                        }
                    }
                    break;
                case 2:
                    purchaseItem();
                    break;
                case 3:
                    int maintenanceMenuSelection = 0;
                    boolean keepGoingMaintenance = true;
                    while (keepGoingMaintenance) {
                        maintenanceMenuSelection = getMaintenanceMenuSelection();
                        
                        switch(maintenanceMenuSelection) {
                            case 1:
                                addProduct();
                                break;
                            case 2:
                                removeProduct();
                                break;
                            case 3:
                                adjustInventory();
                                break;
                            case 4:
                                adjustPrice();
                                break;
                            case 5:
                                keepGoingMaintenance = false;
                                break;
                        }
                    }
                    break;
                case 4:
                    getChange();
                    keepGoingMain = false;
                    break;
                default:
                    unknownCommand();
            }
            view.displayExitMessage();
        }
    }

    private void displayProducts() {
        view.displayMainBanner();
        List<Product> productList = dao.listProducts();
        view.displayProducts(productList);
    }

    private void displayBalance() {
        BigDecimal balance = service.getBalance();
        view.displayBalance(balance);
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void insertCoins(Coins coin) {
        service.addToBalance(coin);
        BigDecimal balance = service.getBalance();
        view.displayBalance(balance);
    }

    private void purchaseItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int getMaintenanceMenuSelection() {
        return view.printMaintenanceMenuAndGetSelection();
    }

    private void getChange() {
        BigDecimal balance = service.getBalance();
        view.displayBalance(balance);
        if (balance.compareTo(BigDecimal.ZERO) != 0) {
            Change change = new Change(balance);
            view.displayChange(change);
        } else {
            view.displayNoChange();
        }
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private int getCoinsMenuSelection() {
        return view.printCoinsMenuAndGetSelection();
    }

    private void addProduct() {
        view.displayAddProductBanner();
        Product newProduct = view.getNewProductInfo();
        if(dao.getProduct(newProduct.getName()) == null) {
            dao.addProduct(newProduct.getName(), newProduct);
            view.displayAddSuccess();
        } else {
            view.displayAlreadyExists();
        }
        view.pressEnterToContinue();
    }

    private void removeProduct() {
        view.displayRemoveProductBanner();
        String name = view.getProductName();
        if(dao.getProduct(name) != null) {
            dao.removeProduct(name);
            view.displayRemoveSuccess();
        } else {
            view.displayDoesNotExist();
        }
        view.pressEnterToContinue();
    }

    private void adjustInventory() {
        view.displayAdjustInventoryBanner();
        String name = view.getProductName();
        if(dao.getProduct(name) != null) {
            Product product = dao.getProduct(name);
            Product adjustedProduct = view.getAdjustedInventory(product);
            dao.adjustInventory(name, adjustedProduct);
            view.displayEditSuccess();
        } else {
            view.displayDoesNotExist();
        }
        view.pressEnterToContinue();
    }

    private void adjustPrice() {
        view.displayAdjustPriceBanner();
        String name = view.getProductName();
        if(dao.getProduct(name) != null) {
            Product product = dao.getProduct(name);
            Product adjustedProduct = view.getAdjustedPrice(product);
            dao.adjustInventory(name, adjustedProduct);
            view.displayEditSuccess();
        } else {
            view.displayDoesNotExist();
        }
        view.pressEnterToContinue();
    }

}
