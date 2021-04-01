package com.sg.foundations.flowcontrol.ifs;

import java.util.Scanner;

public class PickyEater {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        
        System.out.println("How many times has it been fried? (#) ");
        int timesFried = Integer.parseInt(userInput.nextLine());
        
        System.out.println("Does it have any spinach in it? (y/n) ");
        String hasSpinach = userInput.nextLine();
        
        System.out.println("Is it covered in cheese? (y/n) ");
        String cheeseCovered = userInput.nextLine();
        
        System.out.println("How many pats of butter are on top? (#) ");
        int butterPats = Integer.parseInt(userInput.nextLine());
        
        System.out.println("Is it covered in chocolate? (y/n) ");
        String chocolateCovered = userInput.nextLine();
        
        System.out.println("Does it have a funny name? (y/n) ");
        String funnyName = userInput.nextLine();
        
        System.out.println("Is it broccoli? (y/n)");
        String isBroccoli = userInput.nextLine();
        
        if(hasSpinach.equalsIgnoreCase("y") || funnyName.equalsIgnoreCase("y")) {
            System.out.println("There's no way he'll eat that!");
        }else if(timesFried >2 && timesFried <4 && chocolateCovered.equalsIgnoreCase("y")) {
            System.out.println("Oh, it's like a deep fried Snickers. That'll be a hit!");
        }else if(timesFried == 2 && cheeseCovered.equalsIgnoreCase("y")) {
            System.out.println("Mmm. Yeah, he'll eat fried cheesy doodles.");
        }else if(isBroccoli.equalsIgnoreCase("y") && butterPats > 6 && cheeseCovered.equalsIgnoreCase("y")) {
                System.out.println("As long as the green is hidden by cheddar, it'll happen!");
        }else if(isBroccoli.equalsIgnoreCase("y")) {
            System.out.println("Oh, green stuff like that might as well go in the bin.");
        }else {
            System.out.println("Yeah, probably, but there's no vegetable in there, so what's the point?");
        }
         
    }
    
}
