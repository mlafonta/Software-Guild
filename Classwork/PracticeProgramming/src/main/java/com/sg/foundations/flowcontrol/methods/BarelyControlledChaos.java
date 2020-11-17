
package com.sg.foundations.flowcontrol.methods;
import java.util.Random;
public class BarelyControlledChaos {
    public static void main(String[] args) {
        
        String color = randomColor();
        String animal = randomAnimal();
        String colorAgain = randomColor();
        int weight = randomNumber(5, 200);
        int distance = randomNumber(10, 20);
        int number = randomNumber(10000, 20000);
        int time = randomNumber(2, 6);
        
        System.out.println("Once, when I was very small...");
        System.out.println("I was chased by a " + color + ", " + weight + " lb miniature " + animal + " for over " + distance + " miles!!");
        System.out.println("I had to hide in a field of over " + number + " " + colorAgain + " poppies for nearly " + time + " hours until it left me alone!");
        System.out.println("\nIt was QUITE the experience, let me tell you!");
    }
    public static String randomColor() {
        Random rand = new Random();
        int x = rand.nextInt(5);
        String y = "null";
        if(x == 0) {
            y = "red";
        } else if(x == 1) {
            y = "blue";
        } else if(x == 2) {
            y = "green";
        } else if(x == 3) {
            y = "purple";
        } else if(x == 4) {
            y = "black";
        }                
        return y;    
    }
    
    public static String randomAnimal() {
        Random rand = new Random();
        int x = rand.nextInt(5);
        String y = "null";
        if(x == 0) {
            y = "platypus";
        } else if(x == 1) {
            y = "whale";
        } else if(x == 2) {
            y = "hyena";
        } else if(x == 3) {
            y = "falcon";
        } else if(x == 4) {
            y = "dog";
        }                
        return y;    
    }
    public static int randomNumber(int min, int max) {
        Random rand = new Random();
        int x = rand.nextInt(((max + 1) - min)) + min;
        return x;
    }

    
}
