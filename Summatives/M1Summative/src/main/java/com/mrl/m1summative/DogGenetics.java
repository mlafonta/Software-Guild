/** author: Maxwell R. Lafontant
 * email: maxwell@MaxwellRLafontant.com
 * date last modified: 11/14/2020
 */
package com.mrl.m1summative;

import java.util.Scanner;
import java.util.Random;

public class DogGenetics {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        
        System.out.println("What is your dog's name?");
        String name = input.nextLine();
        System.out.println("Well then, I have this highly reliable report on " + name + "'s prestigious background right here.");
        System.out.println();
        System.out.print(name + " is:");
        System.out.println();
        
        //I couldn't quite figure out how to make this a loop with the different dog breeds, but I'm sure there's a way.
        int percent = random.nextInt(96) + 1; //starting at 96 to make sure there's always room for the other dogs
        int used = 0;
        System.out.println(percent + "% Bedlington Terrier");
        used += percent;
        percent = random.nextInt(97 - used) + 1;
        System.out.println(percent + "% Azawakh");
        used += percent;
        percent = random.nextInt(98 - used) + 1;
        System.out.println(percent + "% Catahoula Leopard Dog");
        used += percent;
        percent = random.nextInt(99 - used) + 1;
        System.out.println(percent + "% Bergamasco Shepherd");
        used += percent;
        System.out.println((100 - used) + "% Mudi");
        System.out.println();
        System.out.println("Wow, that's QUITE a dog!");
      
    }
    
}
