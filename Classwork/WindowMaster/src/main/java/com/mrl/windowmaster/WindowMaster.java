                                                                                                                                                                /**
 * @author Maxwell R. Lafontant
 * email: maxwell@MaxwellRLafontant.com
 * date: 11/3/2020
 * purpose: Using operators
 */
package com.mrl.windowmaster;

import java.util.Scanner;

public class WindowMaster {
    public static void main(String [] args) {
        //declare variables
        float height = 0;
        float width = 0;
        String stringHeight;
        String stringWidth;
        String stringCostOfGlass;
        String stringCostOfTrim;
        float areaOfWindow;
        float costOfGlass = 0;
        float costOfTrim = 0;
        float cost;
        float perimeterOfWindow;
        boolean isValid = false;
        
        //get input
        Scanner myScanner = new Scanner(System.in);
        do {
            try {
                System.out.println("Please enter window height:");
                stringHeight = myScanner.nextLine();
                height = Float.parseFloat(stringHeight);
                if (height > 0) {
                    isValid = true;
                }
            } catch(NumberFormatException ex) {
                System.out.println("Invalid");
            }
        } while(!isValid);
        isValid = false;
        do {
            try {
                  System.out.println("Please enter window width:");
                  stringWidth = myScanner.nextLine();
                  width = Float.parseFloat(stringWidth);
                  if (width > 0) {
                      isValid = true;
                  }
            } catch(NumberFormatException ex) {
                System.out.println("Invalid");
            }
        } while (!isValid);
        isValid = false;
        do {
            try {
                System.out.println("Please enter the cost of glass:");
                stringCostOfGlass = myScanner.nextLine();
                costOfGlass = Float.parseFloat(stringCostOfGlass);
                if (costOfGlass > 0) {
                    isValid = true;
                }
            } catch(NumberFormatException ex) {
                System.out.println("Invalid");
            }
        } while (!isValid);
        isValid = false;
        do {
            try {
                System.out.println("Please enter cost of trim");
                stringCostOfTrim = myScanner.nextLine();
                costOfTrim = Float.parseFloat(stringCostOfTrim);
                if (costOfTrim > 0) {
                    isValid = true;
                }
            } catch(NumberFormatException ex) {
                System.out.println("Invalid");
            }
        } while (!isValid);
        //use input for output
        areaOfWindow = height * width;
        perimeterOfWindow = 2 * (height + width);
        cost = (costOfGlass * areaOfWindow) + (costOfTrim * perimeterOfWindow);
        
        //display output
        System.out.println("Window height = " + height);
        System.out.println("Window width = " + width);
        System.out.println("Cost of glass = " + costOfGlass);
        System.out.println("Cost of trim = " + costOfTrim);
        System.out.println("Window area = " + areaOfWindow);
        System.out.println("Window Perimeter =  " + perimeterOfWindow);
        System.out.println("Total Cost = " + cost);
        
    }
}
