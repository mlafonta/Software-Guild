/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrl.flooringmastery.ui;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author flafo
 */
public class UserIOConsoleImpl implements UserIO {

    final private Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        boolean illegalCharacters = true;
        String input = null;
        while (illegalCharacters) {
            System.out.println(prompt);
            Pattern pattern = Pattern.compile("[^a-zA-z0-9 \\-.,-]");
            input = sc.nextLine();
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                System.out.println("Illegal character. Please only use a-z, 0-9, and \",\" \".\" \"-\"");
            } else {
                illegalCharacters = false;
            }
        }
        return input;
    }

    @Override
    public int readInt(String prompt) {
        while (true) {
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
        while (true) {
            try {
                System.out.println(prompt);
                answer = Integer.parseInt(sc.nextLine());
                if (answer > max || answer < min) {
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
        while (true) {
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
        while (true) {
            try {
                System.out.println(prompt);
                answer = Double.parseDouble(sc.nextLine());
                if (answer > max || answer < min) {
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
        while (true) {
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
        while (true) {
            try {
                System.out.println(prompt);
                answer = Float.parseFloat(sc.nextLine());
                if (answer > max || answer < min) {
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
        while (true) {
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
        while (true) {
            try {
                System.out.println(prompt);
                answer = Long.parseLong(sc.nextLine());
                if (answer > max || answer < min) {
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
    public BigDecimal readBigDecimal(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                BigDecimal bd = new BigDecimal(sc.nextLine());
                return bd;
            } catch (NumberFormatException e) {
                System.out.println("Input error. Please try again.");
            }
        }
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        while (true) {
            try {
                System.out.println(prompt);
                BigDecimal bd = new BigDecimal(sc.nextLine());
                if (bd.compareTo(min) < 0 || bd.compareTo(max) > 0) {
                    System.out.println("Input error. Please try again");
                } else {
                    return bd;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input error. Please try again.");
            }
            }
        }

        @Override
        public LocalDate readLocalDate
        (String prompt
        
            ) {
        while (true) {
                try {
                    System.out.println(prompt);
                    LocalDate date = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                    return date;
                } catch (DateTimeException e) {
                    System.out.println("Input error. Please try again.");
                }
            }
        }
    }
