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
    public Person(String firstname, String middleName, String lastname,
                  String birthdate, char gender, String nationality, String address)
            throws Exception {
        PERSON_ID++;
        this.setFirstname(firstname);
        this.setMiddleName(middleName);
        this.setLastname(lastname);

        this.setBirthdate(birthdate);
        this.gender = gender;
        this.nationality = nationality;
        this.address = address;
    }

    public Person() throws Exception {
        PERSON_ID++;
        System.out.println("Enter details for a new person:");

        this.setFirstname();
        this.setMiddleName();
        this.setLastname();

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

    public LocalDate getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
    public void setBirthdate() throws Exception {
        this.setBirthdate( utility.inputDate("Enter birthdate: ") );
    }
    public void setBirthdate(String birthdate) throws Exception {
        this.birthdate = utility.parseDate(birthdate);
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