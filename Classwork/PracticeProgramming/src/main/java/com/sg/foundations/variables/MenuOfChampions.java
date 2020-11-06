
package com.sg.foundations.variables;

public class MenuOfChampions {
    public static void main(String[] args) {
        
        String dish1;
        String dish2;
        String dish3;
        double dish1Price;
        double dish2Price;
        double dish3Price;
        
        dish1 = "Burger";
        dish2 = "Filet mignon";
        dish3 = "Unagi sushi";
        dish1Price = 14.99;
        dish2Price = 49.69;
        dish3Price = 27.99;
        
        System.out.println("__  __  __  __  __  __  __  __  __  __  __  __  __\n" +
"\\//\\\\//\\\\//\\\\//\\\\//\\\\//\\\\//\\\\//\\\\//\\\\//\\\\//\\\\//\\\\//\\\n" +
"  \"\"  \"\"  \"\"  \"\"  \"\"  \"\"  \"\"  \"\"  \"\"  \"\"  \"\"  \"\"  \"\"\n" +
"\n");
/*"------------------------------------------------\n" +
"Thank you for visiting https://asciiart.website/\n" +
"This ASCII pic can be found at\n" +
"https://asciiart.website//index.php?art=art%20and%20design/borders");
*/
        System.out.println("Welcome to San Giorgio's Ristorante");
        System.out.println("Today's Menu Is...");
        
        System.out.println("__  __  __  __  __  __  __  __  __  __  __  __  __\n" +
"\\//\\\\//\\\\//\\\\//\\\\//\\\\//\\\\//\\\\//\\\\//\\\\//\\\\//\\\\//\\\\//\\\n" +
"  \"\"  \"\"  \"\"  \"\"  \"\"  \"\"  \"\"  \"\"  \"\"  \"\"  \"\"  \"\"  \"\"\n" +
"\n");
        System.out.println("$" + dish1Price + "..." + dish1);
        System.out.println("$" + dish2Price + "..." + dish2);
        System.out.println("$" + dish3Price + "..." + dish3);
    }
}
