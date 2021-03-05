package com.sg.foundations.scanner;

import java.util.Scanner;

public class DontForgetToStoreIt {
    public static void main(String[] args) {
        
        int meaningOfLifeAndEverything = 42;
        double pi = 3.14159;
        String cheese, color;
        
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("Give me pi to at least 5 decimals: ");
        Double.parseDouble(inputReader.nextLine());
        
        System.out.println("What is the meaning of life, the universe & everything? ");
        Integer.parseInt(inputReader.nextLine());
        
        System.out.println("What is your favoite kind of cheese? ");
        cheese = inputReader.nextLine();
        
        System.out.println("Do you like the color red or blue more? ");
        color = inputReader.nextLine();
        
        System.out.println("Ooh, " + color + " " + cheese + " sounds delicious!");
        System.out.println("The circumference of life is " +(2 * pi * meaningOfLifeAndEverything));
        
    }
    
    
}
