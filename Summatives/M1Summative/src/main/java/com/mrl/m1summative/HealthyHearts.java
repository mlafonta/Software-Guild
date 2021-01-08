/** author: Maxwell R. Lafontant
 * email: maxwell@MaxwellRLafontant.com
 * date last modified: 11/14/2020
 */
package com.mrl.m1summative;
import java.util.Scanner;
public class HealthyHearts {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("What is your age?");
        int age = input.nextInt();
        System.out.println("Your maximum heart rate should be " + (220 - age) + " beats per minute.");
        int target = (220 - age);
        System.out.println("Your target HR Zone is " + String.format("%.0f", (.5 * target)) + " - " + String.format("%.0f", (.85 * target)) + " beats per minute.");
    }
    
}
