
package com.sg.foundations.flowcontrol.fors;
import java.util.Scanner;
public class TraditionalFizzBuzz {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("How many units of fizzing and buzzing do you need in your life?");
        int tradition = input.nextInt();
        int fizzBuzz = 0;
        
        for(int i = 0; fizzBuzz < tradition; i++) {
            if(i % 3 == 0 && i % 5 == 0 && i != 0){
                System.out.println("fizz buzz");
                fizzBuzz += 2;
            } else if(i % 5 == 0 && i != 0) {
                System.out.println("buzz");
                fizzBuzz++;
            } else if(i % 3 == 0 && i != 0) {
                System.out.println("fizz");
                fizzBuzz++;
            } else {
                System.out.println(i);
            }
        }
        System.out.println("TRADITION!!!!!");
    }
    
}
