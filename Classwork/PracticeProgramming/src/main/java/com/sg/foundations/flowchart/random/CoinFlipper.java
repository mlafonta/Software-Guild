
package com.sg.foundations.flowchart.random;

import java.util.Random;

public class CoinFlipper {
    public static void main(String[] args) {
        Random coin = new Random();
        
        System.out.println("Ready, Set, Flip....!!");
        boolean side = coin.nextBoolean();
        
        if(side == true) {
            System.out.println("You got HEADS!");
        }else {
            System.out.println("You got TAILS");
        }
    }
    
}
