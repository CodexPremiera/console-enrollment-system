package course;

import enrollment.Utility;
import personel.Student;

import java.util.*;

public class StudentList {
    private static int STUDENT_LIST_ID = 0;
    private final ArrayList<Student> allStudents;
    private final String name;

    private final Utility utility = new Utility();

    /* ============================== CONSTRUCTORS ============================== */
    public StudentList (String name) {
        STUDENT_LIST_ID++;
        this.name = name;
        this.allStudents = new ArrayList<>();
    }


    /* ============================== SETTERS AND GETTERS ============================== */
    public static int getStudentListId() {
        return STUDENT_LIST_ID;
    }

    public ArrayList<Student> getAllStudents() {
        return allStudents;
    }

    public String getName() {
        return name;
    }

    /* ============================== METHODS ============================== */
    public ArrayList<Student> filterYear(short targetYearLevel) {
        ArrayList<Student> filterList = new ArrayList<>();
        for (Student student : this.allStudents) {
            if (student.getYearLevel() == targetYearLevel)
                filterList.add(student);
        }
        return filterList;
    }

    public ArrayList<Student> filterCourse(String targetCourse) {
        ArrayList<Student> filterList = new ArrayList<>();
        targetCourse.toLowerCase();

        for (Student student : this.allStudents) {
            String studentCourse = student.getCourse().getCourseName().toLowerCase();
            if (studentCourse.equals(targetCourse))
                filterList.add(student);
        }

        return filterList;
    }

    public ArrayList<Student> filterActive(boolean isActive) {
        ArrayList<Student> filterList = new ArrayList<>();
        for (Student student : this.allStudents) {
            if (student.isActive() == isActive)
                filterList.add(student);
        }
        return filterList;
    }


    public ArrayList<Student> filterName(String name) {
        if (name == null)
            return null;

        Set<Student> filterList = new HashSet<>();

        for (Student student : this.allStudents) {
            String lastname = student.getLastname();
            String firstname = student.getFirstname();
            String fullName = student.getFullName();

            boolean studentIsMatched =
                    lastname.startsWith(name) || firstname.startsWith(name) || fullName.startsWith(name) ||
                    name.startsWith(lastname) || name.startsWith(firstname) || name.startsWith(fullName);

            if (studentIsMatched)
                filterList.add(student);
        }

        ArrayList<Student> resultList = new ArrayList<>(filterList);
        resultList.sort(Comparator.comparing(Student::getLastname));
        return resultList;
    }

    public ArrayList<Student> filterLastname(String name) {
        if (name == null)
            return null;

        ArrayList<Student> filterList = new ArrayList<>();

        for (Student student : this.allStudents) {
            if (student.getLastname().startsWith(name) && !filterList.contains(student))
                filterList.add(student);
        }

        return filterList;
    }
    public ArrayList<Student> filterFirstname(String name) {
        if (name == null)
            return null;

        ArrayList<Student> filterList = new ArrayList<>();

        for (Student student : this.allStudents) {
            if (student.getFirstname().startsWith(name) && !filterList.contains(student))
                filterList.add(student);
        }

        return filterList;
    }

    public Student get(int studentId) {
        for (Student student : this.allStudents) {
            if (student.getStudentId() == studentId)
                return student;
        }
        return null;
    }

    public int getSize() {
        return allStudents.size();
    }

    public void add(Student student) {
        allStudents.add(student);
    }

    public void remove(Student student) {
        //allStudents.remove(student);
    }

    public void display() {
        System.out.println("----------\n" + this.name);
        System.out.printf("%-5s %-32s %-32s %-4s\n", "#ID", "NAME", "COURSE", "IS ACTIVE");

        for (Student student : allStudents) {
            System.out.printf("#%-4d %-32s %-32s %-5s\n",
                    student.getStudentId(),
                    student.getFullName(),
                    student.getCourse().getCourseName(),
                    student.isActive());
        }
        System.out.println();
    }
}
