import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class InputAsker {
    private final Scanner scanner;
    private final PrintStream out;

    public InputAsker(InputStream in, PrintStream out) {
        scanner = new Scanner(in);
        this.out = out;
    }

    public String promptWord(String message) {
        out.print(message);
        return scanner.next();
    }
    public char promptChar(String message) {
        out.print(message);
        return scanner.next().charAt(0);
    }

    public int promptInt(String message, int defaultInt) {
        out.print(message);
        /* if (!scanner.hasNextInt() ) {
            return defaultInt;
        } */
        return scanner.nextInt();
    }

    public int getStringsChoiceIndex(String message, String @NotNull [] entries) {
        out.println(message);
        for (int i = 0; i < entries.length; i++) {
            out.print(i + ". ");
            out.println(entries[i]);
        }
        int choice;
        do {
            choice = promptInt("Your choice: ", -1);
        } while (choice < 0 || choice > entries.length);
        out.println("\n You chose: " + entries[choice]);
        return choice;
    }

    public boolean getBooleanChoice(String message) {
        char choice = promptChar(message + " [Y/n]");
            return !(choice == 'N' || choice == 'n');
    }

    public String chooseStringWithGUI(String message, String @NotNull [] entries) {
        String ANSI_WHITE_BACKGROUND = "\u001B[47m";
        String ANSI_RESET = "\u001B[0m";
        int selectedIndex = 0;
        boolean choosing = true;
        while (choosing) {
            for (int i = 0; i < entries.length; i++) {
                out.print(selectedIndex == i ? "  " : "> ");
                String styledString;
                if (selectedIndex == i) {
                    out.println(ANSI_WHITE_BACKGROUND + entries[i] + ANSI_RESET);
                } else {
                    out.println(entries[i]);
                }
            }
        // It seems that you can't listen for key inputs in java terminal. The solution would be to add a GUI
        }
        return entries[selectedIndex];

    }

}