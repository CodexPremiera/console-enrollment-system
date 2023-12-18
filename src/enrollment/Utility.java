package enrollment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
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
                System.out.println("\nPlease enter a valid string.");
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
                System.out.println("\nPlease enter a valid short.");

            }
            scanner.nextLine();
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
                System.out.println("\nPlease enter a valid integer.");
                scanner.nextLine();
            }
        }

        scanner.nextLine();
        return input;
    }

    public boolean inputBool(String prompt) {
        char choice = ' ';

        while (true) {
            try {
                System.out.print("(Y/N) " + prompt);
                choice = Character.toUpperCase( scanner.next().charAt(0) );

                if (choice == 'N')
                    System.out.println();

                if (choice == 'Y' || choice == 'N')
                    return choice == 'Y';
            }
            catch (Exception exception) {}

            System.out.println("\nPlease enter Y/N.");
            scanner.nextLine();
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
                System.out.println("\nPlease enter a valid character.");
            }
        }

        System.out.println();
        return input;
    }

    public LocalDate inputDate(String prompt) throws Exception {
        String dateString = inputDateStr(prompt);
        return parseDate(dateString);
    }

    public String inputDateStr(String prompt) {
        String input;

        while (true) {
            try {
                System.out.print(prompt);
                input = scanner.nextLine();
                parseDate(input);
                break;
            }
            catch (Exception exception) {
                System.out.println("\nPlease enter a valid date.");
            }
        }

        return input;
    }

    public LocalDate parseDate(String date) throws Exception {
        String[] patterns = {
                "MM/dd/yyyy", "M/dd/yyyy", "MM/d/yyyy", "M/d/yyyy",
                "MMMM d, yyyy", "MMM d, yyyy", "MMMM dd, yyyy", "MMM dd, yyyy",
                "MMMM d yyyy", "MMM d yyyy", "MMMM dd yyyy", "MMM dd yyyy",
                "d MMMM yyyy", "d MMM yyyy", "dd MMMM yyyy", "dd MMM yyyy"
        };

        DateTimeFormatter formatter;
        LocalDate parsedDate = null;

        for (String pattern : patterns) {
            formatter = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern(pattern).toFormatter();
            try {
                parsedDate = LocalDate.parse(date, formatter);
                break;
            } catch (DateTimeParseException ignored) {
            }
        }

        if (parsedDate == null)
            throw new Exception("Invalid date format.");

        return parsedDate;
    }


}
