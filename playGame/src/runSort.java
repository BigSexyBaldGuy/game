import java.io.*;
import java.util.*;

public class runSort
{
	public static void main(String arg[])
	{
		int			i;
		Process		sort;
		PrintWriter	sortInput;
		Scanner		sortError;
		Scanner		sortOutput;
		Random		myRandom;

		sort = null;
		try {
			sort = new ProcessBuilder("sort").start();
		} catch (Exception exception) {
			System.err.println("sort failed");
			exception.printStackTrace(System.err);
			System.exit(1);
		}
		System.out.println("Running sort");

		sortInput = new PrintWriter(sort.getOutputStream(), true);
		sortError = new Scanner(sort.getErrorStream());
		sortOutput = new Scanner(sort.getInputStream());

		myRandom = new Random();
		System.out.println("-=-=- sort stdin -=-=-");
		for (i = myRandom.nextInt(10) + 5; (i >= 0); --i) {
			int		number;

			number = myRandom.nextInt(100);
			System.out.println(number);
			sortInput.println(number);
		}
		sortInput.close();

		if (sortOutput.hasNextLine() == true) {
			System.out.println("-=-=- sort stdout -=-=-");
			while (sortOutput.hasNextLine() == true) {
				System.out.println(sortOutput.nextLine());
			}
		}
		sortOutput.close();

		if (sortError.hasNextLine() == true) {
			System.out.println("-=-=- sort stderr -=-=-");
			while (sortError.hasNextLine() == true) {
				System.out.println(sortError.nextLine());
			}
		}
		sortError.close();

		System.out.println("Waiting on sort");
		do {
			try {
				System.out.println("sort exited with: " +
								   sort.waitFor());
				break;
			} catch (Exception exception) {
				if (exception instanceof InterruptedException) {
					continue;
				}

				System.err.println("waiting for sort failed");
				exception.printStackTrace(System.err);
				System.exit(1);
			}
		} while (true);
	}
}