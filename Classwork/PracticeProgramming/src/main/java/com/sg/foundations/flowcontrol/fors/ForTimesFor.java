
package com.sg.foundations.flowcontrol.fors;

import java.util.Scanner;

public class ForTimesFor {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("What times table shall I recite?");
        int number = Integer.parseInt(input.nextLine());
        int score = 0;
        
        for(int i = 1; i < 16; i++) {
            System.out.println(i + "*" + number + " is: ");
            if(Integer.parseInt(input.nextLine()) == (i * number)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Sorry no, the answer is: " + (i * number));
            }
        }
        
        System.out.println("You got " + score + " correct.");
        if(score > (.9 * 15)){
           System.out.println("Great job!");
        } else if(score < (.5 * 15)){
            System.out.println("Do better.");
        }
    }
    
}
