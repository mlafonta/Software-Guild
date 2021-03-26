package com.sg.foundations.scanner;

import java.util.Scanner;

public class BiggerBetterAdder {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
                
        int firstNumber;
        int secondNumber;
        int thirdNumber;
        int sum;
       
        System.out.println("What's your first number?");
        firstNumber = Integer.parseInt(input.nextLine());
        System.out.println("What's your second number?");
        secondNumber = Integer.parseInt(input.nextLine());
        System.out.println("What's your final number?");
        thirdNumber = Integer.parseInt(input.nextLine());
        
        sum = firstNumber + secondNumber + thirdNumber;
        
        System.out.print(firstNumber + " + " + secondNumber + " + " + thirdNumber + " = " + sum);
         
    }  
}
