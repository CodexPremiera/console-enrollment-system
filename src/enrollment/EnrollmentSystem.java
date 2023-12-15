package enrollment;

import course.Course;
import course.StudentList;
import personel.EnrollmentHandler;
import personel.Student;

import java.util.*;

public class EnrollmentSystem {
    private static int SYSTEM_ID;
    private final StudentList studentList;

    private final ArrayList<EnrollmentHandler> handlerList;
    private final ArrayList<String> coursesList;
    private final HashMap<String, Course> coursesMap;

    private final Utility utility = new Utility();


    /* ============================== PRIVATE HELPERS ============================== */
    private void listCourse() {
        coursesList.add("BS Computer Science");
        coursesList.add("BS Information Technology");
        coursesList.add("BS Computer Engineering");
        coursesList.add("BS Nursing");
        coursesList.add("BS Medical Technology");
    }
    private void mapCourse() {
        for (String course : coursesList) {
            coursesMap.put(course, new Course(course));
        }
    }
    private void printCourse() {
        int count = 1;
        for (String course : coursesList)
            System.out.println("[" + (count++) + "] " + course);
    }


    /* ============================== CONSTRUCTORS ============================== */
    public EnrollmentSystem() {
        SYSTEM_ID++;
        studentList = new StudentList();
        handlerList = new ArrayList<>();
        coursesList = new ArrayList<>();
        coursesMap = new HashMap<>();
        this.listCourse();
        this.mapCourse();
    }


    /* ============================== GETTERS ============================== */
    public static int getSystemId() {
        return SYSTEM_ID;
    }

    public ArrayList<EnrollmentHandler> getHandlerList() {
        return handlerList;
    }

    public ArrayList<String> getCoursesList() {
        return coursesList;
    }

    public HashMap<String, Course> getCoursesMap() {
        return coursesMap;
    }

    /* ============================== METHODS ============================== */
    public void enrollStudent() throws Exception {
        EnrollmentForm enrollmentForm = registerStudent();

        if (enrollmentForm != null) {
            Student student = enrollmentForm.export();
            Course course = this.coursesMap.get(student.getCourse().getCourseName());
            course.enrollStudent(student);
        }
    }
    public void enrollBatch() throws Exception {
        ArrayList<EnrollmentForm> applicantList = registerBatch();

        if (applicantList == null)
            return;

        for (EnrollmentForm applicant : applicantList) {
            Student student = applicant.export();
            Course course = this.coursesMap.get(student.getCourse().getCourseName());
            course.enrollStudent(student);
        }
    }

    public EnrollmentForm registerStudent() {
        EnrollmentForm enrollmentForm = new EnrollmentForm(coursesMap);
        enrollmentForm.fillOut();
        enrollmentForm.print();

        boolean isDecisionFinal = false;
        boolean isEnrollmentConfirmed = false;

        short operation;
        do {
            operation = utility.inputShort("""
                            [1] review form
                            [2] confirm enrollment\s
                            [0] cancel enrollment \s
                            -----\s
                            Enter operation:\s""");

            switch (operation) {
                case 1 -> enrollmentForm.review();
                case 2 -> {
                    isDecisionFinal = utility.inputBool("Confirm enroll? ");
                    if (isDecisionFinal)
                        isEnrollmentConfirmed = true;
                }
                case 0 -> {
                    isDecisionFinal = utility.inputBool("Confirm cancel? ");
                    if (isDecisionFinal)
                        isEnrollmentConfirmed = false;
                }
                default -> {}
            }
        } while (!isDecisionFinal);

        return (isEnrollmentConfirmed) ? enrollmentForm : null;
    }

    public ArrayList<EnrollmentForm> registerBatch() {
        ArrayList<EnrollmentForm> applicantList = new ArrayList<>();
        int studentCount = utility.inputInt("Enter number of students: ");

        for (int count = 0; count < studentCount; count++) {
            EnrollmentForm enrollmentForm = registerStudent();
            if (enrollmentForm != null)
                applicantList.add(enrollmentForm);
        }

        boolean isDecisionFinal = false;
        boolean isEnrollmentConfirmed = false;

        short operation;
        do {
            operation = utility.inputShort("""
                            [1] review form
                            [2] confirm enrollment\s
                            [0] cancel enrollment \s
                            -----\s
                            Enter operation:\s""");

            switch (operation) {
                case 1 -> {
                    for (int count = 0; count < applicantList.size(); count++)
                        System.out.println("[" + count + "] " + applicantList.get(count).getName());

                    short choice = utility.inputShort("""
                            -----
                            Enter the number of applicant: \s""");
                    applicantList.get(choice - 1).review();
                }

                case 2 -> {
                    isDecisionFinal = utility.inputBool("Confirm enroll all? ");
                    if (isDecisionFinal)
                        isEnrollmentConfirmed = true;
                }
                case 0 -> {
                    isDecisionFinal = utility.inputBool("Confirm cancel all? ");
                    if (isDecisionFinal)
                        isEnrollmentConfirmed = false;
                }
                default -> {}
            }
        } while (!isDecisionFinal);

        return (isEnrollmentConfirmed) ? applicantList : null;
    }



}
