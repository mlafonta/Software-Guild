
package com.sg.foundations.scanner;

import java.util.Scanner;

public class DoItBetter {
    public static void main(String[] args) {
        
        int milesRun;
        int hotDogsEaten;
        int languagesKnown;
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("How many miles can you run?");
        milesRun = Integer.parseInt(input.nextLine());
        System.out.println("Oh yeah? I can run " + (milesRun * 2 + 1) + " miles!");
        
        System.out.println("How many hot dogs can you eat?");
        hotDogsEaten = Integer.parseInt(input.nextLine());
        System.out.println("Ha! I can eat " + (hotDogsEaten * 2 + 1) + " hot dogs!");
        
        System.out.println("How many languages do you know?");
        languagesKnown = Integer.parseInt(input.nextLine());
        System.out.println("Lame. I can speak " + (languagesKnown * 2 + 1) + " languages!");
        
    }
}
