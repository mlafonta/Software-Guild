
package com.sg.foundations.flowcontrol.fors;

import java.util.Scanner;

public class TheCount {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("*** I LOVE TO COUNT! LET ME SHARE MY COUNTING WITH YOU! ***");
        System.out.println("Start at : ");
        int start = Integer.parseInt(input.nextLine());
        System.out.println("Stop at : ");
        int stop = Integer.parseInt(input.nextLine());
        System.out.println("Count by : ");
        int count = Integer.parseInt(input.nextLine());
        System.out.println();
        int AhAhAh = 2;
        
        for(int i = 0; (i * count) < stop; i++) {
            if(AhAhAh > 0) {
                System.out.print((start + (count * i)) + " ");
                AhAhAh--;
            } else {
                System.out.println((start + (count * i)) + " - Ah ah ah!");
                AhAhAh = 2;
            }
            
        }
    }
    
}
