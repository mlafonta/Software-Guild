
package com.sg.foundations.flowcontrol.whiles;
import java.util.Scanner;
import java.util.Random;
public class BewareTheKraken {
    public static void main(String[] args) {
        System.out.println("Alrighty, get those flippers and wetsuit on - we're going diving!");
        System.out.println("Here we goooOOooOooo.....! *Splash*");
        
        int depthDivedInFt = 0;
        Scanner input = new Scanner(System.in);
        Random randomizer = new Random();
        int fish;
        
        while(true) {
            System.out.println("So far, we've swum " + depthDivedInFt + " feet.");
            if(depthDivedInFt > 0) {
                fish = randomizer.nextInt(3);
                switch(fish) {
                    case 0:
                        System.out.println("It's a tuna!");
                        break;
                    case 1:
                        System.out.println("Oh look, a swordfish!");
                        break;
                    case 2:
                        System.out.println("Wow! A grouper!");
                        break;
                }
            }
            System.out.println("Do you want to keep going? (y/n)");
            if(input.nextLine().equalsIgnoreCase("n")) {
                System.out.println("Okay, let's bail.");
                break;
            }
            if(depthDivedInFt >= 20000) {
                System.out.println("Uhh, I think I see a Kraken, guys ....");
                System.out.println("TIME TO GO!");
                break;
            }
            
            depthDivedInFt += 1000;
        }
        System.out.println();
        System.out.println("We ended up swimming " + depthDivedInFt + " feet down.");
        System.out.println("I bet we can do better next time!");
    }
    
}
