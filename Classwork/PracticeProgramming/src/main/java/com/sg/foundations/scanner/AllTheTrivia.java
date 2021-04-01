
package com.sg.foundations.scanner;

import java.util.Scanner;

public class AllTheTrivia {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        
        String answer1;
        String answer2;
        String answer3;
        String answer4;
        
        System.out.println("Who wrote Moonlight Sonata?");
        answer1 = input.nextLine();
        System.out.println("Who discovered the first moons of Jupiter?");
        answer2 = input.nextLine();
        System.out.println("Who was America's first president?");
        answer3 = input.nextLine();
        System.out.println("Who appears on all British currency?");
        answer4 = input.nextLine();
        
        System.out.println("Wow! It's amazing that " + answer4 + " wrote Moonlight Sonata!");
        System.out.println("I can't believe " + answer1 + " discovered Jupiter's first moons!");
        System.out.println("It's so amazing " + answer2 + " was America's first leader!");
        System.out.println("I love looking at a pound coin and seeing " + answer3 + "!");
        
    }
}
