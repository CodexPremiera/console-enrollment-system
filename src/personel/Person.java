package personel;

import enrollment.Utility;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class Person {
    private static int PERSON_ID = 0;
    private String firstname = "";
    private String lastname = "";
    private String middleName = "";
    private String address = "";
    private String nationality;
    private LocalDate birthdate = LocalDate.of(2000, 1, 1);
    private char gender = ' ';

    private final Utility utility = new Utility();

    /* ============================== CONSTRUCTORS ============================== */
    public Person(String fullName) {
        PERSON_ID++;
        this.setFullName(fullName);
    }

    public Person(String fullName, String birthdate, char gender, String nationality, String address)
            throws Exception {
        PERSON_ID++;
        this.setFullName(fullName);
        this.setBirthdate(birthdate);
        this.gender = gender;
        this.nationality = nationality;
        this.address = address;
    }

    public Person() throws Exception {
        PERSON_ID++;
        System.out.println("Enter details for a new person:");

        this.setFullName();
        this.setBirthdate();
        this.setGender();
        this.setNationality();
        this.setAddress();
    }

    /* ============================== SETTERS AND GETTERS ============================== */
    public static int getPersonId() {
        return PERSON_ID;
    }

    public String getFullName() {
        String middleString = (middleName.length() != 0) ? middleName.charAt(0) + ". " : "";
        return this.firstname + " " + middleString + this.lastname;
    }
    public void setFullName(String fullName) {
        // split the string
        String[] wordsArray = fullName.split("\\s+");

        ArrayList<String> wordsList = new ArrayList<>();
        for (int i = 0; i < wordsArray.length; i++) {
            wordsArray[i] = wordsArray[i].trim();
            wordsArray[i] = wordsArray[i].substring(0, 1).toUpperCase()
                    + wordsArray[i].substring(1).toLowerCase();
            wordsList.add(wordsArray[i]);
        }

        // build the name
        int size = wordsList.size();

        String firstWord = wordsList.get(0);
        int firstWordLength = firstWord.length();
        char firstWordLastLetter = firstWord.charAt(firstWordLength - 1);

        this.lastname =  (firstWordLastLetter == ',') ?
                wordsList.remove(0).substring(0, firstWordLength - 1) :
                wordsList.remove(size - 1);
        size--;

        String lastWord = wordsList.get(size - 1);
        if (lastWord.charAt(lastWord.length() - 1) == '.')
            this.middleName = wordsList.remove(--size);

        StringBuilder firstnameBuilder = new StringBuilder();
        for (String word : wordsList) {
            firstnameBuilder.append(word).append(" ");
        }
        this.firstname = firstnameBuilder.toString().trim();
    }
    public void setFullName() {
        this.setFullName(utility.inputStr("Enter full name: "));
    }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = utility.capitalize(firstname.trim());
    }
    public void setFirstname() {
        this.setFirstname(utility.inputStr("Enter firstname: "));
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = utility.capitalize(lastname.trim());
    }
    public void setLastname() {
        this.setLastname(utility.inputStr("Enter lastname: "));
    }

    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = utility.capitalize(middleName.trim());
    }
    public void setMiddleName() {
        this.setMiddleName(utility.inputStr("Enter middle name: "));
    }


    public int getAge() {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthdate, currentDate).getYears();
    }
    public String getBirthdateText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return birthdate.format(formatter);
    }
    public void setBirthdate(String birthdateText) throws Exception {
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
                parsedDate = LocalDate.parse(birthdateText, formatter);
                break;
            } catch (DateTimeParseException ignored) {}
        }

        if (parsedDate != null)
            this.birthdate = parsedDate;
        else
            throw new Exception("Invalid date format.");
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
    public void setBirthdate() throws Exception {
        this.setBirthdate( utility.inputStr("Enter birthdate: ") );
    }

    public char getGender() {
        return gender;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }
    public void setGender() {
        this.gender = utility.inputChar("Enter gender: ");
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address.trim();
    }
    public void setAddress() {
        this.address = utility.inputStr("Enter address: ");
    }

    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality.trim();
    }
    public void setNationality() {
        this.nationality = utility.inputStr("Enter nationality: ");
    }



    /* ============================== METHODS ============================== */
    public void displayInfo() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Person ID: " + PERSON_ID + "\n" +
                "Name: " + getFullName() + "\n" +
                "Birthdate: " + getBirthdateText() + "\n" +
                "Age: " + getAge() + "\n" +
                "Gender: " + gender + "\n" +
                "Address: " + address + "\n";
    }
}