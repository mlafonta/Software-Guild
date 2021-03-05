
package com.sg.foundations.flowcontrol.ifs;

import java.util.Scanner;

public class FieldDay {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String name;
        
        System.out.println("What is your last name?");
        name = input.nextLine();
        
        if(name.compareToIgnoreCase("Baggins") <= 0) {
            System.out.println("Aha! You're on the team 'Red Dragons'!");
        }else if(name.compareToIgnoreCase("Baggins") > 0 && name.compareToIgnoreCase("Dresden") <= 0) {
            System.out.println("Aha! You're on the team 'Dark Wizards'!");
        }else if(name.compareToIgnoreCase("Dresden") > 0 && name.compareToIgnoreCase("Howl") <= 0) {
            System.out.println("Aha! You're on the team 'Moving Castles'!");
        }else if(name.compareToIgnoreCase("Howl") > 0 && name.compareToIgnoreCase("Potter") <= 0) {
            System.out.println("Aha! You're on the team 'Golden Snitches'!");
        }else if(name.compareToIgnoreCase("Potter") > 0 && name.compareToIgnoreCase("Vimes") <= 0) {
            System.out.println("Aha! You're on the team 'Night Guards'!");
        }else if(name.compareToIgnoreCase("Vimes") > 0) {
            System.out.println("Aha! You're on the team 'Black Holes'");
        }
        System.out.println("Good luck in the games!");
    }
    
}
