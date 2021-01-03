/**
 * @author Maxwell R. Lafontant
 * @contact maxwell@maxwellRLafontant.com
 * Date Created 1/3/21
 * Date Last Modified 1/3/21
 */
package com.mrl.vendingmachine.service;

import com.mrl.vendingmachine.dto.Coins;
import java.math.BigDecimal;

public interface VendingMachineService {
    /**
     * convert user input of coin to functional BigDecimal
     * 
     * @param coin 
     * @return BigDecimal value of coin enum
     */
    
    BigDecimal getBalance();
    
    BigDecimal addToBalance(Coins coin);
}
