/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.vendingmachine.service;

/**
 *
 * @author flafo
 */
public class VendingMachineDuplicateProductException extends Exception {

    public VendingMachineDuplicateProductException(String message) {
        super(message);
    }

    public VendingMachineDuplicateProductException(String message, Throwable cause) {
        super(message, cause);
    }

}
