
package com.sg.foundations.flowchart.random;

import java.util.Scanner;
import java.util.Random;

public class HighRoller {
    public static void main(String[] args) {
        Random diceRoller = new Random();
        Scanner input = new Scanner(System.in);
        
        System.out.println("TIME TO ROOOOOOOLL THE DICE!");
        System.out.println("How many sides does the dice have?");
        int diceSides = Integer.parseInt(input.nextLine());
        int rollResult = diceRoller.nextInt(diceSides) + 1;
        
        
        System.out.println("You rolled a " + rollResult + " on a " + diceSides + "-sided die.");
        
        if(rollResult == 1) {
            System.out.println("You rolled a critical failure!");
        }else if(rollResult == diceSides) {
            System.out.println("You rolled a critical! Nice job!");
        }
    }
    
}
