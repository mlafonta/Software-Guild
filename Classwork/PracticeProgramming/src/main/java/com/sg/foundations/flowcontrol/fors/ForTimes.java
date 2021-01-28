
package com.sg.foundations.flowcontrol.fors;
import java.util.Scanner;
public class ForTimes {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("What times table shall I recite?");
        int number = Integer.parseInt(input.nextLine());
        
        for(int i = 1; i < 16; i++) {
            System.out.println(i + "*" + number + " is: " + (i * number));
        }
    }
    
}
