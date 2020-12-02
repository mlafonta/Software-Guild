
package com.sg.foundations.flowcontrol.ifs;

import java.util.Scanner;

public class TriviaNight {
    public static void main(String[] args) {
        int answer1;
        int answer2;
        int answer3;
        int score = 0;
        String ready;
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("It's TRIVIA NIGHT! Are you ready?!");
        
        System.out.println("FIRST QUESTION!");
        System.out.println("What is the largest human organ?");
        System.out.println("1) Lung");
        System.out.println("2) Skin");
        System.out.println("3) Liver");
        System.out.println("4) Heart");
        System.out.println();
        System.out.println("What is your answer?");
        answer1 = Integer.parseInt(input.nextLine());
        
        if(answer1 == 2) {
            score++;
            System.out.println("That's right!");
        } else {
            System.out.println("I'm sorry, it was 2) Skin.");
        }
        System.out.println("Your current score is " + score + ".");
        System.out.println("There are 2 questions remaining. Press enter to continue.");
        input.nextLine();
        
        System.out.println("SECOND QUESTION!");
        System.out.println("Who was the 2nd US president?");
        System.out.println("1) John Adams");
        System.out.println("2) George Washington");
        System.out.println("3) Thomas Jefferson");
        System.out.println("4) Alexander Hamilton");
        System.out.println();
        System.out.println("What is your answer?");
        answer2 = Integer.parseInt(input.nextLine());
        
        if(answer2 == 1) {
            score++;
            System.out.println("That's right!");
        } else {
            System.out.println("I'm sorry, it was 1) John Adams.");
        }
        System.out.println("Your current score is " + score + ".");
        System.out.println("There is 1 question remaining. Press enter to continue.");
        input.nextLine();
        
        System.out.println("FINAL QUESTION!");
        System.out.println("What instrument was Frederic Chopin famous for?");
        System.out.println("1) Violin");
        System.out.println("2) Tuba");
        System.out.println("3) Piano");
        System.out.println("4) Cello");
        System.out.println();
        System.out.println("What is your answer?");
        answer3 = Integer.parseInt(input.nextLine());
        
        if(answer3 == 3) {
            score++;
            System.out.println("That's right!");
        } else {
            System.out.println("I'm sorry, it was 3) Piano.");
        }
        System.out.println("Your current score is " + score + ".");
        System.out.println();
        System.out.println("There are no more questions remaining. Press enter to end the quiz.");
        input.nextLine();
        if(score == 3) {
            System.out.println("3/3! Great job!");
        } else if(score == 2) {
            System.out.println("2/3. Not bad.");
        } else if(score == 1) {
            System.out.println("1/3. Try again maybe.");
        } else {
            System.out.println("0/3. You did bad and you should feel bad.");
        }
    }
    
}
