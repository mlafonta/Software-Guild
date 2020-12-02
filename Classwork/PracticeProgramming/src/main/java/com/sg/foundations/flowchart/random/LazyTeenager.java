
package com.sg.foundations.flowchart.random;

import java.util.Random;

public class LazyTeenager {
    public static void main(String[] args) {
        Random randomizer = new Random();
        int timesAsked = 0;
        boolean dirty = true;
        int clean = 0;
        
        do {
            System.out.println("Clean your room!!");
            timesAsked++;
            
            if(randomizer.nextInt(10) <= clean) {
                dirty = false;
                break;
            }
            clean++;
            
            if(timesAsked == 7) {
                break;
            }
        } while(dirty);
        
        if(dirty) {
            System.out.println("You're grounded! No Xbox for a month!");
        } else {
            System.out.println("FINE! I'LL CLEAN MY ROOM. BUT I REFUSE TO EAT MY PEAS.");
        }
    }
}
