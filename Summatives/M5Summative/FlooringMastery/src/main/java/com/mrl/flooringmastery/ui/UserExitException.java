/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.flooringmastery.ui;

/**
 *
 * @author flafo
 */
public class UserExitException extends Exception {

    public UserExitException(String message) {
        super(message);
    }

    public UserExitException(String message, Throwable cause) {
        super(message, cause);
    }

}
