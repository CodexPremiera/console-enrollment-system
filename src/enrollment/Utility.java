package enrollment;

import java.util.Scanner;

public class Utility {
    private Scanner scanner;

    /* ============================== CONSTRUCTOR ============================== */
    public Utility () {
        scanner = new Scanner(System.in);
    }


    /* ============================== METHODS ============================== */
    public String capitalize(String string) {
        String[] words = string.split("\\s+");

        for (int i = 0; i < words.length; i++) {
            if (!words[i].isEmpty())
                words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
        }

        return String.join(" ", words);
    }

    public String inputStr(String prompt) {
        String input;
        while (true) {
            try {
                System.out.print(prompt);
                input = scanner.nextLine();
                break;
            }
            catch (Exception exception) {
                System.out.println("Please enter a valid string.");
                scanner.nextLine();
            }
        }

        return input.trim();
    }

    public short inputShort(String prompt) {
        short input;
        while (true) {
            try {
                System.out.print(prompt);
                input = scanner.nextShort();
                break;
            }
            catch (Exception exception) {
                System.out.println("Please enter a valid short.");
                scanner.nextLine();
            }
        }

        return input;
    }

    public int inputInt(String prompt) {
        int input;
        while (true) {
            try {
                System.out.print(prompt);
                input = scanner.nextInt();
                break;
            }
            catch (Exception exception) {
                System.out.println("Please enter a valid integer.");
                scanner.nextLine();
            }
        }

        return input;
    }

    public boolean inputBool(String prompt) {
        char choice = ' ';

        while (true) {
            try {
                System.out.print("(Y/N) " + prompt);
                choice = Character.toUpperCase( scanner.next().charAt(0) );
            }
            catch (Exception exception) {
                System.out.println("Please enter Y/N.");
                scanner.nextLine();
            }

            if (choice == 'Y' || choice == 'N')
                return choice == 'Y';
            else
                System.out.println("Please enter Y/N.");
        }
    }

    public char inputChar(String prompt) {
        char input;
        while (true) {
            try {
                System.out.print(prompt);
                input = scanner.next().charAt(0);
                break;
            }
            catch (Exception exception) {
                System.out.println("Please enter a valid character.");
            }
        }

        return input;
    }
}
