
package com.sg.foundations.scanner;

import java.util.Scanner;

public class HealthyHearts {
    public static void main(String[] args) {
        
        int age;
        int heartRate;
       
        Scanner input = new Scanner(System.in);
        
        System.out.println("What is your age?");
        age = Integer.parseInt(input.nextLine());
        
        System.out.println("Your maximum heart rate should be " + (220 - age) + " beats per minute.");
        heartRate = 220 - age;
        System.out.println("Your target HR zone is " + (.5 * heartRate) + " - " + (.85 * heartRate) + " beats per minute.");
    }
}
