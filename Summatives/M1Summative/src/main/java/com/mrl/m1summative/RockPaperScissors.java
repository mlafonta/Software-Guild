/** author: Maxwell R. Lafontant
 * email: maxwell@MaxwellRLafontant.com
 * date last modified: 11/14/2020
 */
package com.mrl.m1summative;
import java.util.Scanner;
import java.util.Random;
public class RockPaperScissors {
    public static void main(String[] args) {
        //indefinite loop build
        while(true) {
            if(rockPaperScissors()) {
            } else {
                break;
            }
        }
        System.out.println("Thanks for playing!");
    }
    public static boolean rockPaperScissors() {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        int wins = 0;
        int ties = 0;
        int losses = 0;
        int rounds = 0;
        String player;
        String computer = null;
        
        System.out.println("How many rounds would you like to play? (1-10)");
        rounds = Integer.parseInt(input.nextLine()); //I did this because apparently using nextInt was messing up line 40
        if(rounds > 0 && rounds < 11) {
            System.out.println("Let's play " + rounds + " rounds then.");//that's fine then
        } else {
            System.out.println("You need to learn to follow instructions.");
            System.exit(0);
        }
        for(int i = 0; i < rounds; i++) {
            System.out.println("Round " + (i + 1));
            System.out.println("Rock, Paper, Scissors?");
            player = input.nextLine();
            switch(random.nextInt(3)) {
                case 0:
                    computer = "Rock";
                    break;
                case 1:
                    computer = "Paper";
                    break;
                case 2:
                    computer = "Scissors";
                    break;
            }
            System.out.println("I threw " + computer + ". It's " + player + " vs " + computer + "!");
            if(player.equalsIgnoreCase("Rock")) {
                if(computer.equals("Rock")) {
                    System.out.println("It's a tie.");
                    ties++;
                } else if(computer.equals("Paper")) {
                    System.out.println("You lose.");
                    losses++;
                } else if(computer.equals("Scissors")) {
                    System.out.println("You win!");
                    wins++;
                }
            } else if(player.equalsIgnoreCase("Paper")) {
                if(computer.equals("Rock")) {
                    System.out.println("You win!");
                    wins++;
                } else if(computer.equals("Paper")) {
                    System.out.println("It's a tie.");
                    ties++;
                } else if(computer.equals("Scissors")) {
                    System.out.println("You lose.");
                    losses++;
                }
            } else if(player.equalsIgnoreCase("Scissors")) {
                if(computer.equals("Rock")) {
                    System.out.println("You lose.");
                    losses++;
                } else if(computer.equals("Paper")) {
                    System.out.println("You win!");
                    wins++;
                } else if(computer.equals("Scissors")) {
                    System.out.println("It's a tie.");
                    wins++;
                }
            } else {
                    System.out.println("That's not one of the choices. It counts as a loss.");
                    losses++;
                }
        }
        System.out.println("Final score: ");
        System.out.println("Wins: " + wins);
        System.out.println("Ties: " + ties);
        System.out.println("Losses: " + losses);
        if(wins > losses) {
            System.out.println("You won! Great job!");
        } else if(ties > wins && ties > losses) {
            System.out.println("We tied. Huh.");
        } else if(losses > wins) {
            System.out.println("You did bad and you should feel bad.");
        } else if(wins == losses) {
            System.out.println("We tied. Huh.");
        }
        System.out.println("Would you like to play again? (y/n)");
        boolean playAgain2 = true;
        if(input.nextLine().equalsIgnoreCase("y")) {
            playAgain2 = true;
        } else {
            playAgain2 = false;
        }
        return playAgain2;
    }
}
