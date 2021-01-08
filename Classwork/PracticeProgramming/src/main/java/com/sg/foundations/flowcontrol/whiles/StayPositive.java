
package com.sg.foundations.flowcontrol.whiles;

import java.util.Scanner;

public class StayPositive {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int x =10;
        boolean valid = false;
        
        do {
            try {
                System.out.println("What number should I count down from?");
                x = Integer.parseInt(input.nextLine());
                if(x > 0) {
                    valid = true;
                }
                if(!valid) {
                    System.out.println("Please enter a positive integer.");
                    
                }
            } catch(NumberFormatException ex) {
                System.out.println("Invalid format.");
            }
        } while(!valid);
        
        System.out.println();
        System.out.println("Here goes!");
        System.out.println();
        
        int y = 9; //counter before new line
        while(x > 0) {
            if(y > 0) {
            System.out.print(x + " ");
            x--;
            y--;
            } else {
            System.out.println(x + " ");
            x--;
            y += 9;
            }
            
            
        }
        
        System.out.println(x);
        System.out.println();
        System.out.println("Blast off!");
    }
    
}
