
package com.sg.foundations.flowcontrol.fors;

public class ForAndTwentyBlackbirds {
    public static void main(String[] args) {
        int birdsInPie = 0;
        
        for(int i = 1; i < 25; i++) {//i added one to the initialization and end condition
            System.out.println("Blackbird #" + i + " goes into the pie!");
            birdsInPie++;
        }
        
        System.out.println("There are " + birdsInPie + " birds in there!");
        System.out.println("Quite the pie full!");
    }
    
}
