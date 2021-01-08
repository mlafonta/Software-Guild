
package com.sg.foundations.flowcontrol.ifs;

import java.util.Scanner;

public class BirthStones {
    public static void main(String[] args) {
        int month;
        Scanner input = new Scanner(System.in);
        
        System.out.println("What number month's birthstone do you want to know?");
        month = Integer.parseInt(input.nextLine());
        
        if(month == 1) {
            System.out.println("January's birthstone is Garnet.");
        } else if(month == 2) {
            System.out.println("February's birthstone is Amethyst.");
        } else if(month == 3) {
            System.out.println("March's birthstone is Aquamarine.");
        } else if(month == 4) {
            System.out.println("April's birthstone is Diamond.");
        } else if(month == 5) {
            System.out.println("May's birthstone is Emerald.");
        } else if(month == 6) {
            System.out.println("June's birthstone is Pearl.");
        } else if(month == 7) {
            System.out.println("July's birthstone is Ruby.");
        } else if(month == 8) {
            System.out.println("August's birthstone is Peridot.");
        } else if(month == 9) {
            System.out.println("September's birthstone is Saphhire.");
        } else if(month == 10) {
            System.out.println("October's birthstone is Opal.");
        } else if(month == 11) {
            System.out.println("November's birthstone is Topaz.");
        } else if(month == 12) {
            System.out.println("December's birthstone is Turquoise.");
        } else {
            System.out.println("There are only twelve months, my dude.");
        }
    }
}
