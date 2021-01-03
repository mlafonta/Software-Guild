/**
 * @author Maxwell R. Lafontant
 * @contact maxwell@maxwellRLafontant.com
 * Date Created 1/3/21
 * Date Last Modified 1/3/21
 */
package com.mrl.vendingmachine.ui;

import java.util.Scanner;

public class UserIOConsoleImp1 implements UserIO {

    final private Scanner sc = new Scanner(System.in);
    
    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return sc.nextLine();        
    }

    @Override
    public int readInt(String prompt) {
        while(true) {
            try {
                System.out.println(prompt);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input error. Please try again.");
            }
        }
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int answer;
        while(true) {
            try {
                System.out.println(prompt);
                answer = Integer.parseInt(sc.nextLine());
                if(answer > max || answer < min) {
                    System.out.println("Input error. Please try again");
                } else {
                    return answer;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input error. Please try again.");
            }                
        }
    }

    @Override
    public double readDouble(String prompt) {
        while(true) {
            try {
                System.out.println(prompt);
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input error. Please try again.");
            }
        }    
    }
        
    @Override
    public double readDouble(String prompt, double min, double max) {
        double answer;
        while(true) {
            try {
                System.out.println(prompt);
                answer = Double.parseDouble(sc.nextLine());
                if(answer > max || answer < min) {
                    System.out.println("Input error. Please try again");
                } else {
                    return answer;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input error. Please try again.");
            }    
        }    
    }

    @Override
    public float readFloat(String prompt) {
        while(true) {
            try {
                System.out.println(prompt);
                return Float.parseFloat(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input error. Please try again.");
            }
        }
    }    

    @Override
    public float readFloat(String prompt, float min, float max) {
        float answer;
        while(true) {
            try {
                System.out.println(prompt);
                answer = Float.parseFloat(sc.nextLine());
                if(answer > max || answer < min) {
                    System.out.println("Input error. Please try again");
                } else {
                    return answer;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input error. Please try again.");
            }                
        }    
    }
    

    @Override
    public long readLong(String prompt) {
        while(true) {
            try {
                System.out.println(prompt);
                return Long.parseLong(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input error. Please try again.");
            }
        }    
    }
    
    @Override
    public long readLong(String prompt, long min, long max) {
        long answer;
        while(true) {
            try {
                System.out.println(prompt);
                answer = Long.parseLong(sc.nextLine());
                if(answer > max || answer < min) {
                    System.out.println("Input error. Please try again");
                } else {
                    return answer;
                }   
            } catch (NumberFormatException e) {
                System.out.println("Input error. Please try again.");
            }
        }    
    }   
}