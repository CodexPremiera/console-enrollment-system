package course;

import enrollment.Utility;
import personel.Student;

public class Course {
    private final Utility utility = new Utility();

    private static int COURSE_ID = 0;
    private String courseName = "";
    private short units;
    private final StudentList studentList;



    /* ============================== CONSTRUCTORS ============================== */
    public Course (String courseName) {
        COURSE_ID++;
        this.courseName = courseName;
        this.units = 3;
        this.studentList = new StudentList();
    }

    public Course (String courseName, short units) {
        COURSE_ID++;
        this.courseName = courseName;
        this.units = units;
        this.studentList = new StudentList();
    }


    /* ============================== SETTERS AND GETTERS ============================== */
    public static int getCourseId() {
        return COURSE_ID;
    }

    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public short getUnits() {
        return units;
    }
    public void setUnits(short units) {
        this.units = units;
    }

    public StudentList getStudentList() {
        return studentList;
    }


    /* ============================== METHODS ============================== */
    public void enrollStudent(Student student) {
        studentList.add(student);
        student.setCourse(this);
        student.setActive(true);
    }

    public void removeStudent(Student student) {
        studentList.remove(student);
        student.setCourse(null);
        student.setActive(false);
    }

    public void displayInfo() {
        System.out.println(
                "--------------------------------------------------\n" +
                        this.toString() +
                        "--------------------------------------------------\n");
    }

    @Override
    public String toString() {
        return "COURSE #" + COURSE_ID + "\n" +
                "Course Name: " + this.courseName + "\n" +
                "Population:  " + this.studentList.getSize() + "\n";
    }

}
