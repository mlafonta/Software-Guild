package com.sg.foundations.flowchart.random;

import java.util.Scanner;
import java.util.Random;
public class GuessMeMore {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random randomizer = new Random();
        
        int modifier = 0;
        boolean modDecider = randomizer.nextBoolean();
        boolean end = false;
        
        if(modDecider == true) {
            modifier = 1;
        }else if(modDecider == false) {
            modifier = -1;
        }
        
        int answer = randomizer.nextInt(101) * modifier;
        System.out.println("I've chosen a number between -100 and 100. Betcha can't guess it!");
        while(end == false) {
            System.out.println("Your guess: ");
            int guess = Integer.parseInt(input.nextLine());
            System.out.println();
                if(guess < answer) {
                    System.out.println(guess + "? Ha, nice try - too low! Try again.");
                }else if(guess > answer) {
                    System.out.println(guess + "? Too bad, way too high. Try again.");
                }else if(guess == answer) {
                    System.out.println(guess + "? Wow, nice guess! That was it!");
                    end = true;
            }
        }
    }
}
