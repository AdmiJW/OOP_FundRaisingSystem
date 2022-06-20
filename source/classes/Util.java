package source.classes;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Util {
    
    // Prompts user to enter a number in range [from, to].
    // Performs exception handling if invalid input is entered.
    public static int getInputOfRange(int from, int to) {
        Integer input = null;
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        while (input == null) {
            System.out.printf("Enter your choice [%d - %d]:", from, to);
        
            try {
                int value = scan.nextInt();
                if (value < from || value > to) throw new InputMismatchException();
                else input = value;
            } catch (InputMismatchException e) {
                System.out.printf("Invalid input. Only value from %d to %d is allowed!\n", from, to);
            }
            scan.nextLine();        // Skip the \n character
        }
        return input;
    }



    public static void clearScreen() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }




    public static int printChoiceMenu(String title,  String description, String[] choices) {
        clearScreen();
        System.out.println("*".repeat( title.length() + 4 ));
        System.out.printf("* %s *\n", title);
        System.out.println("*".repeat( title.length() + 4 ));

        System.out.println();

        if (description.length() != 0) System.out.printf("%s\n\n", description);

        for (int i = 0; i < choices.length; ++i)
            System.out.printf("%d. %s\n", i+1, choices[i]);
        System.out.println();

        return getInputOfRange(1, choices.length);
    }
    


    public static void pressEnterToContinue() {
        System.out.println("Press Enter key to continue...");
        try { System.in.read(); }  
        catch(Exception e) {}  
    }




    
    public static void assertTrue(boolean condition, String message, int testcase) {
        if (!condition) System.out.printf("(X) Test case #%d FAILED: %s\n", testcase, message);
        else System.out.printf("(OK) Test case #%d PASSED.\n", testcase);
    }


    public static void loadingAnimation() {
        loadingAnimation("Loading, Please wait...", 1000, 3, '>');
    }

    public static void loadingAnimation(String loadingMessage, long millisecondDelay, int length, char loadingChar) {
        System.out.println(loadingMessage);
        System.out.print("Progress: ");
        
        try {
            for (int i = 0; i < length; ++i) {
                Thread.sleep(millisecondDelay);
                System.out.print(loadingChar);
            }
        } catch (InterruptedException e) {}
        System.out.println();
    }
}
