import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class playGame {

    public static void main(String[] var0) {
        Process     game;
        PrintWriter	sortInput;
        Scanner		sortError;
        Scanner		sortOutput;
        int highestRange = 100;
        int lowestRange = 0;
        int guessedNumber = 50;
        String printOutput = "";

        try {
//            Process game = (new ProcessBuilder(new String[]{"java",
//                    "-cp", "C:\\Users\\baldd\\OneDrive\\Documents" +
//                    "\\Personal\\David\\Java\\game\\playGame\\out" +
//                    "\\production\\playGame", "game"})).start();

            // This one works when they are in the same folder
            game = (new ProcessBuilder(new String[]{"java",
                    "-cp", System.getProperty("user.dir"), "game"})).start();

            sortInput = new PrintWriter(game.getOutputStream(), true);
            sortError = new Scanner(game.getErrorStream());
            sortOutput = new Scanner(game.getInputStream());

            sortInput.println(guessedNumber);

            while(sortOutput.hasNextLine()) {
                printOutput = sortOutput.nextLine();

                String newline = System.getProperty("line.separator");
                boolean hasNewline = printOutput.contains(newline);
                if (hasNewline) {
                    System.out.println("newline");
                }

                System.out.println(printOutput);
                if (printOutput.contains("low")) {
                    lowestRange = guessedNumber + 1;
                } else if (printOutput.contains("high")) {
                    highestRange = guessedNumber - 1;
                }

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
        } catch (IOException var10) {
            var10.printStackTrace();
        } catch (InterruptedException var11) {
            var11.printStackTrace();
        }

    }
}
