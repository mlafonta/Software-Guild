
package com.sg.foundations.flowcontrol.ifs;

import java.util.Scanner;

public class MiniZork {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("You are standing in an open field west of a white house,");
        System.out.println("With a boarded front door.");
        System.out.println("There is a small mailbox here.");
        System.out.println("Go to the house, or open the mailbox?");
        String action = input.nextLine();
        
        if(action.equalsIgnoreCase("open the mailbox")) {
            System.out.println("You open the mailbox.");
            System.out.println("Look inside or stick your hand in?");
            action = input.nextLine();
            
            if(action.equalsIgnoreCase("look inside")) {
                System.out.println("You peer inside the mailbox.");
                System.out.println("It's really very dark. So ... so very dark.");
                System.out.println("Run away or keep looking?");
                action = input.nextLine();
                
                if(action.equalsIgnoreCase("run away")) {
                    System.out.println("You run away screaming across the fields - looking very foolish.");
                    System.out.println("But you're alive. Possibly a wise choice."); //coward ending
                }else if(action.equalsIgnoreCase("keep looking")) {
                    System.out.println("Turns out, hanging out around dark places isn't a very good idea.");
                    System.out.println("You've been eaten by a grue."); //bad ending
                }
                
            }else if(action.equalsIgnoreCase("stick your hand in")) {
                System.out.println("You stick your hand in the mailbox.");
                System.out.println("You feel something ... furry ... inside.");
                System.out.println("Do you grab it or pull your arm out?");
                action = input.nextLine();
                if(action.equalsIgnoreCase("grab it")) {
                    System.out.println("You grab the furry thing.");
                    System.out.println("It grabs you back ... with razor sharp fangs!");
                    System.out.println("As it devours your arm, you think about how stealing mail is a felony.");
                    System.out.println("You are eaten by a grue."); //bad ending
                }else if(action.equalsIgnoreCase("pull your arm out")) {
                    System.out.println("You quickly pull your hand out of the mailbox");
                    System.out.println("The mailbox starts to shake and growl ... and glowing yellow eyes appear inside.");
                    System.out.println("Do you stay and fight or run away?");
                    action = input.nextLine();
                }
                if(action.equalsIgnoreCase("run away")) {
                    System.out.println("You run away screaming across the fields - looking very foolish.");
                    System.out.println("But you're alive. Possibly a wise choice."); //coward ending
                }else if(action.equalsIgnoreCase("stay and fight;")) {
                    System.out.println("You put up your dukes and get ready to fight.");
                    System.out.println("Out of the mailbox emerges a monster much bigger than the mailbox, and in fact much bigger than you.");
                    System.out.println("You never stood a chance. You are eaten by a grue.");    
                }
            }    
        }else if(action.equalsIgnoreCase("Go to the house")) {
            System.out.println("You head east to the white house. There's a large red door.");
            System.out.println("You also notice an open window leading into the cellar, that you could easily slip through if you wanted.");
            System.out.println("Do you want to approach the door or go to the cellar?");
            action = input.nextLine();
            
            if(action.equalsIgnoreCase("approach the door")) {
                System.out.println("You approach the door. You notice the address is 666, and you can hear all sorts of strange sounds from inside");
                System.out.println("Do you knock or run away?");
                action = input.nextLine();
                
                if(action.equalsIgnoreCase("knock")) {
                    System.out.println("You rap your hand hard against the door.");
                    System.out.println("The door swings open, revealing a large one-man eyed man in a bloody apron, holding a bloody meat cleaver.");
                    System.out.println("Do you run away, attack, or say hello?");
                    
                    if(action.equalsIgnoreCase("run away")) {
                        System.out.println("You run away screaming across the fields - looking very foolish.");
                        System.out.println("But you're alive. Possibly a wise choice."); //coward ending
                    }else if(action.equalsIgnoreCase("attack")) {
                        System.out.println("You charge at the man!");
                        System.out.println("He is much larger than you and easily subdues you.");
                        System.out.println("He calls the cops and you go to jail for aggravated assault."); //bad ending
                    }else if(action.equalsIgnoreCase("say hello")) {
                        System.out.println("You smile and say hello");
                        System.out.println("He returns your smile and in a heavy accent says 'It's your lucky day, my friend!'");
                        System.out.println("He hands you the bag of steaks he just finished butchering. You now have a six month supply of steak. Go home and enjoy!"); //steak ending 
                    }
                }else if(action.equalsIgnoreCase("run away")) {
                   System.out.println("You run away screaming across the fields - looking very foolish.");
                   System.out.println("But you're alive. Possibly a wise choice."); //coward ending 
                }
            }else if(action.equalsIgnoreCase("go to the cellar")) {
                System.out.println("You go through the window and slide down the cellar wall.");
                System.out.println("It's pitch black in here. As you read the ground, you feel a switch on the wall.");
                System.out.println("Do you flip the switch or stay in the dark?");
                action = input.nextLine();
                
                if(action.equalsIgnoreCase("stay in the dark")) {
                    System.out.println("Turns out, hanging out around dark places isn't a very good idea.");
                    System.out.println("You've been eaten by a grue."); //bad ending
                }else if(action.equalsIgnoreCase("flip the switch")) {
                    System.out.println("You flip the switch, and gentle redd and blue lights come on.");
                    System.out.println("Looking around the room, you see a bed, and harness, a variety of ropes and paddles, and various fuzzy handcuffs.");
                    System.out.println("You hear the door open at the top of the stairs.");
                    System.out.println("Do you climb out the window, or wait here?");
                    action = input.nextLine();
                    
                    if(action.equalsIgnoreCase("climb out the window")) {
                        System.out.println("You climb out the window as fast as you can.");
                        System.out.println("You run away screaming across the fields - looking very foolish.");
                        System.out.println("But you're alive. And unviolated. Possibly a wise choice."); //coward ending 
                    }else if(action.equalsIgnoreCase("wait here")) {
                        System.out.println("You wait in the dungeon as you hear footsteps coming down the stairs.");
                        System.out.println("A large man with one eye appears.");
                        System.out.println("As it turns out, he's a very giving and generous lover.");
                        System.out.println("You're going back tomorrow ;)."); //sex ending
                    }
                }
            }
            
        }
    }
}            

