
package com.sg.foundations.flowchart.random;

import java.util.Random;

public class FortuneCookie {
    public static void main(String[] args) {
        Random randomizer = new Random();
        int x = randomizer.nextInt(10);
        
        System.out.println("Your Nietzsche quote of the day:");
        switch(x) {
            case 0:
                System.out.println("To live is to suffer, to survive is to find some meaning in the suffering.");
                break;
            case 1:
                System.out.println("He who has a why to live can bear almost any how.");
                break;
            case 2:
                System.out.println("Without music, life would be a mistake.");
                break;
            case 3:
                System.out.println("It is not a lack of love, but a lack of friendship that makes unhappy marriages.");
                break;
            case 4:
                System.out.println("It is hard enough to remember my opinions, without also remembering my reasons for them!");
                break;
            case 5:
                System.out.println("There is always some madness in love. But there is also always some reason in madness");
                break;
            case 6:
                System.out.println("You must have chaos within you to give birth to a dancing star.");
                break;
            case 7:
                System.out.println("We should consider every day lost on which we have not danced at least once.");
                break;
            case 8:
                System.out.println("I cannot believe in a God who wants to be praised all the time.");
                break;
            case 9:
                System.out.println("Man is the cruelest animal.");
                break;
        }
    }
    
}
