import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class playGame {

    public static void main(String[] args) {
        Process     game;
        PrintWriter	sortInput;
        Scanner		sortError;
        Scanner		sortOutput;
        int highestRange = 100;
        int lowestRange = 0;
        int guessedNumber = 50;
        String printOutput = "";
        String[] printOutputSplit;

        try {
            game = (new ProcessBuilder(new String[]{"java",
                    "-cp", System.getProperty("user.dir"), "game"})).start();

            sortInput = new PrintWriter(game.getOutputStream(), true);
            sortError = new Scanner(game.getErrorStream());
            sortOutput = new Scanner(game.getInputStream());

            sortInput.println(guessedNumber);

            while(sortOutput.hasNextLine()) {
                printOutput = sortOutput.nextLine();

                if (printOutput.contains("low")) {
                    lowestRange = guessedNumber + 1;
                } else if (printOutput.contains("high")) {
                    highestRange = guessedNumber - 1;
                }else if (printOutput.contains("Yay")) {
                    printOutputSplit = printOutput.split(": ");
                    printOutput = printOutputSplit[0] + ": " + guessedNumber +
                                  "\n" + printOutputSplit[1];
                }

                System.out.println(printOutput);
                // Calculate the number to guess by adding the high and low range together and then
                // dividing by 2. This should help limit the number of guesses by splitting the
                // difference before each guess.
                guessedNumber = (int)Math.ceil((double)((float)(highestRange + lowestRange) / 2.0F));
                sortInput.println(guessedNumber);
            }

            if (sortError.hasNextLine()) {
                System.out.println("<--- Game Error --->");

                while(sortError.hasNextLine()) {
                    System.out.println(sortError.nextLine());
                }
            }

            sortOutput.close();
            sortInput.close();
            sortError.close();
            int exitCode = game.waitFor();
            System.out.println("\nExited with error code : " + exitCode);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (InterruptedException interuptException) {
            interuptException.printStackTrace();
        }

    }
}
