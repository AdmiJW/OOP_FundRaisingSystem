
import source.classes.Util;


public class Test2 {
    private static int testcase = 1;


    private static void test_ClearScreen() {
        System.out.println("If you see this, clearscreen() is failed");
        Util.clearScreen();
        System.out.println("If you see this, clearscreen() is failed");
        Util.clearScreen();
        Util.assertTrue(true, "", testcase++);
    }


    private static void test_InputRange() {
        int from = 10;
        int to = 11;
        int value = Util.getInputOfRange(from, to);
        Util.assertTrue( value >= from && value <= to, "Error in getInputOfRange(). ", testcase++);
    }


    private static void test_ChoiceMenu() {
        String[] options = {"Choice 1", "Choice 2", "Choice 3"};
        int value = Util.printChoiceMenu("Test", "Test", options);
        Util.assertTrue( value >= 1 && value <= 3, "Error in printChoiceMenu(). ", testcase++);
    }


    private static void test_LoadingAnimation() {
        String loadingMessage = "Test loading...";

        Util.loadingAnimation(loadingMessage);
        Util.assertTrue( true, "Error in test_LoadingAnimation()", testcase++);
    }



    public static void main(String[] args) {
        test_ClearScreen();
        Util.pressEnterToContinue();
        test_InputRange();
        Util.pressEnterToContinue();
        test_ChoiceMenu();
        Util.pressEnterToContinue();
        test_LoadingAnimation();
    }
}
