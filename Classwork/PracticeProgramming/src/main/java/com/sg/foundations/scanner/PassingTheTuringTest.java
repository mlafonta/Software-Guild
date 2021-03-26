
package com.sg.foundations.scanner;

import java.util.Scanner;

public class PassingTheTuringTest {
    
    public static void main(String[] args) {
        
        String name;
        String color;
        String fruit;
        int number;
    
        Scanner input = new Scanner(System.in);
    
        System.out.println("Hello there!");
        System.out.println("What's your name?");
        name = input.nextLine();
        System.out.println("Hi, " + name + "! I'm HAL.");
        
        System.out.println("What's your favorite color?");
        color = input.nextLine();
        System.out.println("Huh, " + color + "? Mine's Electric Lime.");
        
        System.out.println("I really like limes. They're my favorite fruit, too.");
        System.out.println("What's your favorite fruit, " + name + "?");
        fruit = input.nextLine();
        System.out.println("Really? " + fruit + "? That's wild!");
        
        System.out.println("Speaking of favorites, what's your favorite number?");
        number = Integer.parseInt(input.nextLine());
        System.out.println(number + " is a cool number. Mine's -7.");
       
        System.out.println("Did you know " + number + " * -7 is " + (number * -7) + "? That's a cool number too!");
        
        System.out.println("Well, thanks for talking with me, " + name + "!");
    }
}
