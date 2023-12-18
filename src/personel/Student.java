package personel;

import course.Course;
import enrollment.Utility;

import java.util.Scanner;

public class Student extends Person {
    private static int STUDENT_ID = 0;
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
        STUDENT_ID++;
        this.course = course;
        this.yearLevel = yearLevel;
        this.isRegular = true;
        this.isActive = true; 
    }

    public Student(Scanner scanner) throws Exception {
        super();
        STUDENT_ID++;
        // other attributes for scan
        this.setYearLevel();
        this.setRegular();
        this.setActive();
    }


    /* ============================== SETTERS AND GETTERS ============================== */
    public int getStudentId() {
        return STUDENT_ID;
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

    @Override
    public void displayInfo() {
        System.out.println(this.toString());
    }

    public void displayInfoCard() {
        System.out.println(
                "--------------------------------------------------\n" +
                this.toString() +
                "--------------------------------------------------");
    }

    @Override
    public String toString() {
        return "Student #" + STUDENT_ID + "\n" +
                "Name:    " + getFullName() + "\n" +
                "Course:  " + this.course.getCourseName() + "\n" +
                "Year:    " + this.yearLevel + "\n"+
                "\n" +
                "Birth:   " + getBirthdateText() + "\n" +
                "Age:     " + getAge() + "years old \n" +
                "Gender:  " + getGender() + "\n" +
                "Address: " + getAddress() + "\n";
    }
}
