import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class playGame {

    public static void main(String[] var0) {
        PrintWriter	sortInput;
        Scanner		sortError;
        Scanner		sortOutput;
        int highestRange = 100;
        int lowestRange = 0;
        int guessedNumber = 50;
        String output = "";

        try {
            Process game = (new ProcessBuilder(new String[]{"java",
                    "-cp", "C:\\Users\\baldd\\OneDrive\\Documents" +
                    "\\Personal\\David\\Java\\game\\playGame\\out" +
                    "\\production\\playGame", "game"})).start();

            sortInput = new PrintWriter(game.getOutputStream(), true);
            sortError = new Scanner(game.getErrorStream());
            sortOutput = new Scanner(game.getInputStream());

            sortInput.println(guessedNumber);

            while(sortOutput.hasNextLine()) {
                output = sortOutput.nextLine();
                System.out.println(output);
                if (output.contains("low")) {
                    lowestRange = guessedNumber + 1;
                } else if (output.contains("high")) {
                    highestRange = guessedNumber - 1;
                }

                guessedNumber = (int)Math.ceil((double)((float)(highestRange + lowestRange) / 2.0F));
                sortInput.println(guessedNumber);
            }

            if (sortError.hasNextLine()) {
                System.out.println("-=-=- sort stderr -=-=-");

                while(sortError.hasNextLine()) {
                    System.out.println(sortError.nextLine());
                }
            }

            sortOutput.close();
            sortInput.close();
            sortError.close();
            int var9 = game.waitFor();
            System.out.println("\nExited with error code : " + var9);
        } catch (IOException var10) {
            var10.printStackTrace();
        } catch (InterruptedException var11) {
            var11.printStackTrace();
        }

    }
}
