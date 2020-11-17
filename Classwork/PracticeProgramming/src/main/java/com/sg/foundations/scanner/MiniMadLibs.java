package com.sg.foundations.scanner;

import java.util.Scanner;

public class MiniMadLibs {
    public static void main(String[] args) {
        
        String noun1, adjective2, noun3, number4, adjective5;
        String pluralNoun6, pluralNoun7, pluralNoun8, verb9, pastVerb10;
        
        Scanner input = new Scanner(System.in);
       
        System.out.println("Let's play MAD LIBS!");
        System.out.println();
        
        System.out.println("I need a noun:");
        noun1 = input.nextLine();
        
        System.out.println("Now an adjective:");
        adjective2 = input.nextLine();
        
        System.out.println("Another noun:");
        noun3 = input.nextLine();
        
        System.out.println("And a number:");
        number4 = input.nextLine();
        
        System.out.println("Another adjective:");
        adjective5 = input.nextLine();
        
        System.out.println("A plural noun:");
        pluralNoun6 = input.nextLine();
        
        System.out.println("Another one:");
        pluralNoun7 = input.nextLine();
        
        System.out.println("One more:");
        pluralNoun8 = input.nextLine();
        
        System.out.println("A verb (infinitive form)");
        verb9 = input.nextLine();
        
        System.out.println("Same verb (past participle):");
        pastVerb10 = input.nextLine();
        
        System.out.println();
        
        System.out.println("*** NOW LET'S GET MAD (libs) ***");
        System.out.println(noun1 + ": the " + adjective2 + " frontier.");
        System.out.println("These are the voyages of the starship " + noun3 + ".");
        System.out.println("Its " + number4 + "-year mission: to explore strange " + adjective5 + " " + pluralNoun6 + ", to seek out " + adjective5 + " " + pluralNoun7 + " and " + adjective5 + " " + pluralNoun8 + ", to boldly " + verb9 + " where no one has " + pastVerb10 + " before.");
    }
}
