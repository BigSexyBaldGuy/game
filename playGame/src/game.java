//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Random;
import java.util.Scanner;

public class game {
    public static final Scanner console;

    public game() {
    }

    public static void main(String[] var0) {
        int var1 = 0;
        Random var4 = new Random();
        int var3 = var4.nextInt(100);
        System.out.print("Enter an integer between 0 and 99, inclusive: ");

        for(String var5 = console.next(); var5.charAt(0) >= '0' && var5.charAt(0) <= '9'; var5 = console.next()) {
            ++var1;
            int var2 = Integer.parseInt(var5);
            if (var2 < var3) {
                System.out.println("" + var2 + " is too low");
            } else {
                if (var2 <= var3) {
                    System.out.println("Yay!  You guessed it!");
                    break;
                }

                System.out.println("" + var2 + " is too high");
            }

            System.out.print("Enter an integer between 0 and 99, inclusive: ");
        }

        System.out.println("You took " + var1 + " guess" + (var1 == 1 ? "" : "es") + ".\nThanks for playing!");
    }

    static {
        console = new Scanner(System.in);
    }
}
