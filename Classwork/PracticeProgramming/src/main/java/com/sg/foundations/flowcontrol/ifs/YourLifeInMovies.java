
package com.sg.foundations.flowcontrol.ifs;

import java.util.Scanner;

public class YourLifeInMovies {
    public static void main(String[] args) {
        int birthYear;
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("What year were you born?");
        birthYear = Integer.parseInt(input.nextLine());
        
        if(birthYear >= 2005) {
            System.out.println("You're a baby. This program is not for you.");
        }
        if(birthYear < 2005) {
            System.out.println("Did you know that Pixar's 'Up' came out over a decade ago?");
        }
        if(birthYear < 1995) {
            System.out.println("And that the first Harry Potter came out over 15 years ago!");
        }
        if(birthYear < 1985) {
            System.out.println("Also, Space Jam came out not last decade, but the one before THAT!");
        }
        if(birthYear < 1975) {
            System.out.println("Not to mention, Jurassic Park came out closer to the moon landing than to today!");
        }
        if(birthYear < 1965) {
            System.out.println("And M*A*S*H* has been around for half a century!");
        }
        
    }
}
