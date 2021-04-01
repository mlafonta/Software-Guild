
package com.sg.foundations.flowcontrol.whiles;

public class LovesMe {
    public static void main(String[] args) {
        int daisy = 34;
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
