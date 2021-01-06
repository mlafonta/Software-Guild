/**
 * @author Maxwell R. Lafontant
 * @contact maxwell@maxwellRLafontant.com Date Created 1/3/21 Date Last Modified
 * 1/3/21
 */
package com.mrl.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.quarters);
        hash = 97 * hash + Objects.hashCode(this.dimes);
        hash = 97 * hash + Objects.hashCode(this.nickels);
        hash = 97 * hash + Objects.hashCode(this.pennies);
        hash = 97 * hash + Objects.hashCode(this.quarter);
        hash = 97 * hash + Objects.hashCode(this.dime);
        hash = 97 * hash + Objects.hashCode(this.nickel);
        hash = 97 * hash + Objects.hashCode(this.penny);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Change other = (Change) obj;
        if (!Objects.equals(this.quarters, other.quarters)) {
            return false;
        }
        if (!Objects.equals(this.dimes, other.dimes)) {
            return false;
        }
        if (!Objects.equals(this.nickels, other.nickels)) {
            return false;
        }
        if (!Objects.equals(this.pennies, other.pennies)) {
            return false;
        }
        if (!Objects.equals(this.quarter, other.quarter)) {
            return false;
        }
        if (!Objects.equals(this.dime, other.dime)) {
            return false;
        }
        if (!Objects.equals(this.nickel, other.nickel)) {
            return false;
        }
        if (!Objects.equals(this.penny, other.penny)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Change{" + "quarters=" + quarters + ", dimes=" + dimes + ", nickels=" + nickels + ", pennies=" + pennies + ", quarter=" + quarter + ", dime=" + dime + ", nickel=" + nickel + ", penny=" + penny + '}';
    }
    
    
}
