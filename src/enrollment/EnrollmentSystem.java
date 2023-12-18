package enrollment;

import course.Course;
import course.StudentList;
import personel.EnrollmentHandler;
import personel.Student;

import java.util.*;

public class EnrollmentSystem {
    private static int SYSTEM_ID;
    private final StudentList studentList;
    private final ArrayList<EnrollmentForm> applicantList;

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

        for (String course : coursesList) {
            coursesMap.put(course, new Course(course));
        }
    }
    private void printCourse() {
        int count = 1;
        for (String course : coursesList)
            System.out.println("[" + (count++) + "] " + course);
    }

    private void getApplicants(int studentCount) {
        System.out.println();
        for (int count = 1; count <= studentCount; count++) {
            System.out.println(
                    "--------------------\n" +
                    "Applicant #" + count + "\n");
            EnrollmentForm enrollmentForm = registerStudent();
            if (enrollmentForm != null)
                this.applicantList.add(enrollmentForm);
        }
    }

    private void printApplicants() {
        System.out.println("""
                    --------------------
                    Applicant List:""");
        for (int count = 0; count < this.applicantList.size(); count++)
            System.out.println("    #" + (count + 1) + " " + this.applicantList.get(count).getName());
    }

    private void modifyApplicants() {
        short choice;

        while (true) {
            try {
                choice = utility.inputShort("\nPlease enter the number of applicant: ");
                this.applicantList.get(choice - 1).review();
                break;
            } catch (IndexOutOfBoundsException exception) {
                System.out.println("\nApplicant number out of range.");
            }
        }
    }


    /* ============================== CONSTRUCTORS ============================== */
    public EnrollmentSystem() {
        SYSTEM_ID++;
        studentList = new StudentList();
        applicantList = new ArrayList<>();
        handlerList = new ArrayList<>();
        coursesList = new ArrayList<>();
        coursesMap = new HashMap<>();
        this.listCourse();
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
        boolean isDecisionFinal = false;
        boolean isEnrollmentConfirmed = false;

        EnrollmentForm enrollmentForm = new EnrollmentForm(coursesMap);
        enrollmentForm.fillOut();
        enrollmentForm.print();

        short operation;
        do {
            operation = utility.inputShort("""
                            Registration options:    
                                [1] modify form
                                [2] finish registration\s
                                
                                [0] cancel registration \s
                            ----------\s
                            Enter operation:\s""");

            switch (operation) {
                case 1 -> {
                    enrollmentForm.edit();
                    enrollmentForm.print();
                }
                case 2 -> {
                    isDecisionFinal = utility.inputBool("Confirm finish registration? ");
                    isEnrollmentConfirmed = true;
                }
                case 0 -> {
                    isDecisionFinal = utility.inputBool("Confirm cancel registration? ");
                    isEnrollmentConfirmed = false;
                }
                default -> {}
            }

        } while (!isDecisionFinal);

        System.out.println();
        return (isEnrollmentConfirmed) ? enrollmentForm : null;
    }

    public ArrayList<EnrollmentForm> registerBatch() {
        boolean isDecisionFinal = false;
        boolean isEnrollmentConfirmed = false;
        int studentCount = utility.inputInt("Enter number of students: ");
        this.getApplicants(studentCount);
        this.printApplicants();

        short operation;
        do {
            operation = utility.inputShort("""
                            
                            Select options:
                                [1] modify enrollment form
                                [2] confirm enrollment\s
                                
                                [0] cancel enrollment \s
                            ----------\s
                            Enter operation:\s""");

            switch (operation) {
                case 1 -> {
                    this.modifyApplicants();
                    this.printApplicants();
                }

                case 2 -> {
                    isDecisionFinal = utility.inputBool("Confirm enroll all? ");
                    isEnrollmentConfirmed = true;
                }

                case 0 -> {
                    isDecisionFinal = utility.inputBool("Confirm cancel all? ");
                    isEnrollmentConfirmed = false;
                }
                default -> {}
            }
        } while (!isDecisionFinal);

        applicantList.clear();
        return (isEnrollmentConfirmed) ? applicantList : null;
    }

}
