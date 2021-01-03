/**
 * @author Maxwell R. Lafontant
 * @contact maxwell@maxwellRLafontant.com Date Created 1/3/21 Date Last Modified
 * 1/3/21
 */
package com.mrl.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Change {

    private BigDecimal quarters;
    private BigDecimal dimes;
    private BigDecimal nickels;
    private BigDecimal pennies;

    BigDecimal quarter = new BigDecimal(".25");
    BigDecimal dime = new BigDecimal(".1");
    BigDecimal nickel = new BigDecimal(".05");
    BigDecimal penny = new BigDecimal(".01");

    public BigDecimal getQuarters() {
        return quarters;
    }

    public BigDecimal getDimes() {
        return dimes;
    }

    public BigDecimal getNickels() {
        return nickels;
    }

    public BigDecimal getPennies() {
        return pennies;
    }

    public Change(BigDecimal balance) {
        if ((balance.divide(quarter, 0, RoundingMode.FLOOR)).compareTo(BigDecimal.ZERO) != 0) {
            quarters = balance.divide(quarter, 0, RoundingMode.FLOOR);
            balance = balance.subtract(quarters.multiply(quarter));
        } else {
            quarters = BigDecimal.ZERO;
        }
        if ((balance.divide(dime, 0, RoundingMode.FLOOR)).compareTo(BigDecimal.ZERO) != 0) {
            dimes = balance.divide(dime, 0, RoundingMode.FLOOR);
            balance = balance.subtract(dimes.multiply(dime));
        } else {
            dimes = BigDecimal.ZERO;
        }
        if ((balance.divide(nickel, 0, RoundingMode.FLOOR)).compareTo(BigDecimal.ZERO) != 0) {
            nickels = balance.divide(nickel, 0, RoundingMode.FLOOR);
            balance = balance.subtract(nickels.multiply(nickel));
        } else {
            nickels = BigDecimal.ZERO;
        }
        if ((balance.divide(penny, 0, RoundingMode.FLOOR)).compareTo(BigDecimal.ZERO) != 0) {
            pennies = balance.divide(penny, 0, RoundingMode.FLOOR);
        } else {
            pennies = BigDecimal.ZERO;
        }
    }
}
