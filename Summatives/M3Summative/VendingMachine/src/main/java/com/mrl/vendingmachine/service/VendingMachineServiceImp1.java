/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.vendingmachine.service;

import com.mrl.vendingmachine.dto.Coins;
import java.math.BigDecimal;

/**
 *
 * @author flafo
 */
public class VendingMachineServiceImp1 implements VendingMachineService {

    BigDecimal balance = new BigDecimal("0").setScale(2);
    BigDecimal quarter = new BigDecimal(".25");
    BigDecimal dime = new BigDecimal(".1");
    BigDecimal nickel = new BigDecimal(".05");
    BigDecimal penny = new BigDecimal(".01");
            

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public BigDecimal addToBalance(Coins coin) {
        switch(coin) {
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



}
