/**
 * @author Maxwell R. Lafontant
 * @contact maxwell@maxwellRLafontant.com Date Created 1/3/21 Date Last Modified
 * 1/3/21
 */
package com.mrl.vendingmachine.dao;

public class VendingMachinePersistenceException extends Exception {

    public VendingMachinePersistenceException(String message) {
        super(message);
    }

    public VendingMachinePersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
