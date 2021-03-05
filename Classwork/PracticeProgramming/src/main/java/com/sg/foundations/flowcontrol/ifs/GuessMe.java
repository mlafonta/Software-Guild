package com.sg.foundations.flowcontrol.ifs;

import java.util.Scanner;

public class GuessMe {
    public static void main(String[] args) {
        int answer = 69;
        int guess;
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("I've chosen a number. Betcha can't guess it!");
        System.out.println("Your guess: ");
        guess = Integer.parseInt(input.nextLine());
        System.out.println();
        
        if(guess < answer) {
            System.out.println(guess + "? Ha, nice try - too low! I chose " + answer + ".");
        }
        if(guess > answer) {
            System.out.println(guess + "? Too bad, way too high. I chose " + answer + ".");
        }
        if(guess == answer) {
            System.out.println(guess + "? Wow, nice guess! That was it!");
        }
    }
}
