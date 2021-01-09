/**
 * @author Maxwell R. Lafontant
 * @contact maxwell@maxwellRLafontant.com Date Created 1/3/21 Date Last Modified
 * 1/3/21
 */
package com.mrl.vendingmachine.controller;

import com.mrl.vendingmachine.dao.VendingMachineDao;
import com.mrl.vendingmachine.dao.VendingMachinePersistenceException;
import com.mrl.vendingmachine.dto.Change;
import com.mrl.vendingmachine.dto.Coins;
import static com.mrl.vendingmachine.dto.Coins.DIME;
import static com.mrl.vendingmachine.dto.Coins.NICKEL;
import static com.mrl.vendingmachine.dto.Coins.PENNY;
import static com.mrl.vendingmachine.dto.Coins.QUARTER;
import com.mrl.vendingmachine.dto.Product;
import com.mrl.vendingmachine.service.InsufficientFundsException;
import com.mrl.vendingmachine.service.NoItemInventoryException;
import com.mrl.vendingmachine.service.NoSuchProductException;
import com.mrl.vendingmachine.service.VendingMachineDataValidationException;
import com.mrl.vendingmachine.service.VendingMachineDuplicateProductException;
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

    public VendingMachineController(VendingMachineView view, VendingMachineService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        boolean keepGoingMain = true;
        int menuSelection = 0;
        try {
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

                            switch (maintenanceMenuSelection) {
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

            }
        } catch (VendingMachinePersistenceException | InsufficientFundsException | NoItemInventoryException | VendingMachineDataValidationException | VendingMachineDuplicateProductException | NoSuchProductException e) {
            view.displayErrorMessage(e.getMessage());
        }
        view.displayExitMessage();
    }

    private void displayProducts() throws VendingMachinePersistenceException {
        view.displayMainBanner();
        List<Product> productList = service.listProducts();
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

    private void purchaseItem() throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException, NoSuchProductException {
        view.displayItemSelection();

        displayProducts();
        displayBalance();
        String name = view.getProductName();
        try {
            Product product = service.getProduct(name);

            service.buyProduct(product);
            view.displayPurchaseSuccess(product);

        } catch (InsufficientFundsException | NoItemInventoryException | NoSuchProductException e) {
            view.displayErrorMessage(e.getMessage());
        }

    }

    private int getMaintenanceMenuSelection() throws VendingMachinePersistenceException {
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

    private void addProduct() throws VendingMachinePersistenceException, VendingMachineDataValidationException, VendingMachineDuplicateProductException {
        view.displayAddProductBanner();
        boolean hasErrors = false;
        do {
            Product newProduct = view.getNewProductInfo();
            try {
                service.addProduct(newProduct);
                view.displayAddSuccess();
                view.pressEnterToContinue();
                hasErrors = false;
            } catch (VendingMachineDataValidationException | VendingMachineDuplicateProductException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

    private void removeProduct() throws VendingMachinePersistenceException {
        view.displayRemoveProductBanner();
        String name = view.getProductName();
        service.removeProduct(name);
        view.displayRemoveSuccess();
        view.pressEnterToContinue();
    }

    private void adjustInventory() throws VendingMachinePersistenceException, VendingMachineDataValidationException, NoSuchProductException {
        view.displayAdjustInventoryBanner();
        boolean hasErrors = false;
        do {
            String name = view.getProductName();
            try {
                Product product = service.getProduct(name);
                Product adjustedProduct = view.getAdjustedInventory(product);
                service.adjustInventory(name, adjustedProduct);
                view.displayEditSuccess();
                view.pressEnterToContinue();
                hasErrors = false;
            } catch (VendingMachineDataValidationException | NoSuchProductException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

    private void adjustPrice() throws VendingMachinePersistenceException, VendingMachineDataValidationException, NoSuchProductException {
        view.displayAdjustPriceBanner();
        boolean hasErrors = false;
        do {
            String name = view.getProductName();
            try {
                Product product = service.getProduct(name);
                Product adjustedProduct = view.getAdjustedPrice(product);
                service.adjustInventory(name, adjustedProduct);
                view.displayEditSuccess();
                view.pressEnterToContinue();
                hasErrors = false;
            } catch (VendingMachineDataValidationException | NoSuchProductException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

}
