package personel;

import course.Course;
import enrollment.Utility;

import java.util.Scanner;

public class Student extends Person {
    private static int STUDENT_COUNT = 0;
    private final int studentId;
    private Course course;
    private short yearLevel;
    private boolean isRegular;
    private boolean isActive;

    private final Utility utility = new Utility();

    /* ============================== CONSTRUCTORS ============================== */
    public Student(String firstname, String middleName, String lastname,
                   String birthdate, char gender, String nationality, String address,
                   Course course, short yearLevel) throws Exception {
        super(firstname, middleName, lastname, birthdate, gender, nationality, address);
        this.studentId = ++STUDENT_COUNT;
        this.course = course;
        this.yearLevel = yearLevel;
        this.isRegular = true;
        this.isActive = true;
    }

    /* ============================== SETTERS AND GETTERS ============================== */
    public int getStudentId() {
        return this.studentId;
    }

    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }

    public short getYearLevel() {
        return yearLevel;
    }
    public void setYearLevel(short yearLevel) {
        this.yearLevel = yearLevel;
    }
    public void setYearLevel() {
        this.setYearLevel(utility.inputShort("Enter full year level: "));
    }

    public boolean isRegular() {
        return isRegular;
    }
    public void setRegular(boolean regular) {
        isRegular = regular;
    }
    public void setRegular() {
        this.isRegular = utility.inputBool("Is the student regular? ");
    }

    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
    public void setActive() {
        this.isActive = utility.inputBool("Is the student enrolled? ");
    }


    /* ============================== METHODS ============================== */
    public void enroll(Course course) {
        course.enrollStudent(this);
    }

    public void dropOut() {
        this.course.removeStudent(this);
    }

    public void shiftCourse(Course newCourse) {
        this.course.removeStudent(this);
        newCourse.enrollStudent(this);
    }

    public void editInfo() throws Exception {
        int operation;

        do {
            operation = utility.inputInt("""
                            
                            Choose item to edit:
                                [1] Firstname \s
                                [2] Middle Name \s
                                [3] Lastname \s
                                [4] Birthdate \s
                                [5] Gender \s
                                [6] Nationality \s
                                [7] Address \s
                                [8] Year Level \s
                                [9] Course \s
                                
                                [0] finish edit\s
                            ----------\s
                            Enter operation:\s""");

            switch (operation) {
                case 1 -> this.setFirstname( utility.inputStr("Firstname: ") );
                case 2 -> this.setMiddleName( utility.inputStr("Middle Name: ") );
                case 3 -> this.setLastname( utility.inputStr("Lastname: ") );
                case 4 -> this.setBirthdate( utility.inputDateStr("Birthdate: ") );
                case 5 -> this.setGender( utility.inputChar("Gender") );
                case 6 -> this.setNationality( utility.inputStr("Nationality: ") );
                case 7 -> this.setAddress( utility.inputStr("Address: ") );
                case 8 -> this.setYearLevel( utility.inputShort("Year Level") );
                //case 9 -> enrollmentSystem.shiftStudent();

                case 0 -> System.out.println();
                default -> {}
            }
        } while (operation != 0);
    }

    @Override
    public void displayInfo() {
        System.out.println("-----\n" + this.toString());
    }

    @Override
    public String toString() {
        return "Student #" + this.studentId + "\n" +
                "    Name:    " + getFullName() + "\n" +
                "    Course:  " + this.course.getCourseName() + "\n" +
                "    Year:    " + this.yearLevel + "\n"+
                "    Birth:   " + getBirthdateText() + "\n" +
                "    Age:     " + getAge() + " years old \n" +
                "    Gender:  " + getGender() + "\n" +
                "    Address: " + getAddress() + "\n";
    }
}
