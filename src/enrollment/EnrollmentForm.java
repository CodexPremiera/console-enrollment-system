package enrollment;

import course.Course;
import personel.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EnrollmentForm {
    private static int ENROLLMENT_FORM_ID;
    private final HashMap<String, String> enrollmentForm = new HashMap<>();
    private ArrayList<String> coursesList;
    private HashMap<String, Course> coursesMap;

    private final Utility utility = new Utility();


    /* ============================== PRIVATE HELPERS ============================== */
    private void printCourse() {
        int count = 1;
        for (String course : coursesList)
            System.out.println("[" + (count++) + "] " + course);
    }

    private void createForm() {
        this.enrollmentForm.put("Full Name", "");
        this.enrollmentForm.put("Birthdate", "");
        this.enrollmentForm.put("Gender", "");
        this.enrollmentForm.put("Nationality", "");
        this.enrollmentForm.put("Address", "");
        this.enrollmentForm.put("Year Level", "");
        this.enrollmentForm.put("Course", "");
    }


    /* ============================== CONSTRUCTORS ============================== */
    public EnrollmentForm(HashMap<String, Course> coursesMap) {
        ENROLLMENT_FORM_ID++;
        this.coursesMap = coursesMap;
        this.coursesList = new ArrayList<>(coursesMap.keySet());
        this.createForm();
    }

    /* ============================== METHODS ============================== */
    public String getName() {
        return this.enrollmentForm.get("Full Name");
    }

    public void fillOut() {
        System.out.println("STUDENT INFORMATION");
        this.enrollmentForm.put( "Full Name", utility.inputStr("Full Name: ") );
        this.enrollmentForm.put( "Birthdate", utility.inputStr("Birthdate: ") );
        this.enrollmentForm.put( "Gender", utility.inputStr("Gender: ") );
        this.enrollmentForm.put( "Nationality", utility.inputStr("Nationality: ") );
        this.enrollmentForm.put( "Address", utility.inputStr("Address: ") );
        this.enrollmentForm.put( "Year Level", utility.inputStr("Year Level: ") );

        System.out.println("\nSELECT COURSE: ");
        printCourse();
        int courseNumber = utility.inputInt("Enter preferred course: ");
        enrollmentForm.put("Course", coursesList.get(courseNumber));
    }
    
    public void print() {
        System.out.println("""
                            
                            ----------\s
                            Student Details: \s""");
        for (Map.Entry<String, String> entry : enrollmentForm.entrySet())
            System.out.println("    " + entry.getKey() + ": " + entry.getValue());

        System.out.println("----------\n");
    }
    
    public void edit() {
        short operation;

        do {
            operation = utility.inputShort("""
                            Choose item to edit:
                            [1] Full Name \s
                            [2] Birthdate \s
                            [3] Gender \s
                            [4] Nationality \s
                            [5] Address \s
                            [6] Year Level \s
                            [7] Course \s
                            
                            [0] finish edit\s
                            -----\s
                            Enter operation:\s""");

            switch (operation) {
                case 1 -> this.enrollmentForm.put( "Full Name", utility.inputStr("Full Name: ") );
                case 2 -> this.enrollmentForm.put( "Birthdate", utility.inputStr("Birthdate: ") );
                case 3 -> this.enrollmentForm.put( "Gender", utility.inputStr("Gender: ") );
                case 4 -> this.enrollmentForm.put( "Nationality", utility.inputStr("Nationality: ") );
                case 5 -> this.enrollmentForm.put( "Address", utility.inputStr("Address: ") );
                case 6 -> this.enrollmentForm.put( "Year Level", utility.inputStr("Year Level: ") );
                case 7 -> {
                    System.out.println("\nSELECT COURSE: ");
                    printCourse();
                    int courseNumber = utility.inputInt("Enter preferred course: ");
                    this.enrollmentForm.put("Course", coursesList.get(courseNumber));
                }
                default -> {}                
            }
        } while (operation != 0);
    }

    public void review() {
        boolean isDecisionFinal = false;
        short operation;
        do {
            operation = utility.inputShort("""
                            
                            [1] view form \s
                            [2] modify form \s
                            [3] confirm changes
                            -----\s
                            Enter operation:\s""");

            // switch to modify form
            switch (operation) {
                case 1 -> this.print();
                case 2 -> this.edit();
                case 3 -> isDecisionFinal  = utility.inputBool("Confirm details? ");
                default -> {}
            }
        } while (!isDecisionFinal);
    }

    public Student export() throws Exception {
        Student student = new Student(
                this.enrollmentForm.get("Full Name"),
                this.enrollmentForm.get("Birthdate"),
                this.enrollmentForm.get("Gender").charAt(0),
                this.enrollmentForm.get("Nationality"),
                this.enrollmentForm.get("Address"),
                this.coursesMap.get(this.enrollmentForm.get("Course")),
                Short.parseShort(this.enrollmentForm.get("Year Level"))
        );

        return student;
    }
}
