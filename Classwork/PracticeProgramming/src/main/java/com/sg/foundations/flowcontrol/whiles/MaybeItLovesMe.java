
package com.sg.foundations.flowcontrol.whiles;

import java.util.Random;

public class MaybeItLovesMe {
    public static void main(String[] args) {
        Random randomizer = new Random();
        int daisy = randomizer.nextInt(76) + 13;
        boolean love;
        
        do {
            System.out.println("It loves me NOT!");
            daisy--;
            love = false;
            if(daisy > 0) {
                System.out.println("It LOVES me!");
                daisy--;
                love = true;
            }
        } while(daisy > 0);
        
        if(love) {
            System.out.println("I knew it! It LOVES ME!");
        } else {
            System.out.println("Awwww, bummer.");
        }
    }
}
