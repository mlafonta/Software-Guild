
package com.sg.foundations.flowcontrol.whiles;

import java.util.Scanner;

public class DoOrDoNot {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Should I do it? (y/n)");
        
        boolean doIt;
        
        if(input.next().equalsIgnoreCase("y")) {
            doIt = true;
        } else {
            doIt = false;
        }
        
        boolean iDidIt = false;
        
        /*do {
            iDidIt = true;
            break;
        } while (doIt); */
        
        while(doIt) {
            iDidIt = true;
            break;
        }
        
        if(doIt && iDidIt) {
            System.out.println("I did it!");
        } else if(!doIt && iDidIt) {
            System.out.println("I know you said not to ... but I totally did anyways.");
        } else {
            System.out.println("Don't look at me, I didn't do anything!");
        }
    }
    
}
