package enrollment;

import course.Course;
import personel.Student;

import java.util.ArrayList;
import java.util.HashMap;

public class EnrollmentForm {
    private static int ENROLLMENT_FORM_ID;
    private final ArrayList<String> fieldList = new ArrayList<>();
    private final HashMap<String, String> enrollmentForm = new HashMap<>();
    private ArrayList<String> coursesList;
    private HashMap<String, Course> coursesMap;

    private final Utility utility = new Utility();


    /* ============================== PRIVATE HELPERS ============================== */
    private void printCourse() {
        int count = 1;
        for (String course : coursesList)
            System.out.println("    [" + (count++) + "] " + course);
    }

    private void createForm() {
        fieldList.add("Firstname");
        fieldList.add("Middle Name");
        fieldList.add("Lastname");
        fieldList.add("Birthdate");
        fieldList.add("Gender");
        fieldList.add("Nationality");
        fieldList.add("Address");
        fieldList.add("Year Level");
        fieldList.add("Course");

        for (String field : fieldList) {
            enrollmentForm.put(field, "");
        }
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
        return this.enrollmentForm.get("Lastname") + ", " +
                this.enrollmentForm.get("Firstname") + " " +
                this.enrollmentForm.get("Middle Name").charAt(0) + ".";
    }

    public void fillOut() {
        System.out.println("Please enter student information.");
        this.enrollmentForm.put( "Firstname", utility.inputStr("Firstname: ") );
        this.enrollmentForm.put( "Middle Name", utility.inputStr("Middle Name: ") );
        this.enrollmentForm.put( "Lastname", utility.inputStr("Lastname: ") );
        this.enrollmentForm.put( "Birthdate", utility.inputDateStr("Birthdate: ") );
        this.enrollmentForm.put( "Gender", utility.inputStr("Gender: ") );
        this.enrollmentForm.put( "Nationality", utility.inputStr("Nationality: ") );
        this.enrollmentForm.put( "Address", utility.inputStr("Address: ") );
        this.enrollmentForm.put( "Year Level", utility.inputStr("Year Level: ") );
        this.editCourse();
        System.out.println();
    }
    
    public void print() {
        System.out.println("""
                            -----\s
                            Information Summary: \s""");
        for (String field : fieldList)
            System.out.println("    " + field + ": " + enrollmentForm.get(field));
        System.out.println();
    }
    
    public void edit() {
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
                case 1 -> editField("Firstname");
                case 2 -> editField("Middle Name");
                case 3 -> editField("Lastname");
                case 4 -> editBirthdate();
                case 5 -> editField("Gender");
                case 6 -> editField("Nationality");
                case 7 -> editField("Address");
                case 8 -> editField("Year Level");
                case 9 -> editCourse();

                case 0 -> System.out.println();
                default -> {}                
            }
        } while (operation != 0);
    }

    public void editField(String field) {
        boolean isDecisionFinal;
        String value;

        do {
            System.out.println();
            value = utility.inputStr(field + ": ");

            System.out.println("You entered: " + value);
            isDecisionFinal = utility.inputBool("Confirm change? ");
        } while (!isDecisionFinal);

        this.enrollmentForm.put(field, value);
    }

    public void editBirthdate() {
        boolean isDecisionFinal;
        String value, field = "Birthdate";

        do {
            System.out.println();
            value = utility.inputDateStr(field + ": ");

            System.out.println("You entered: " + value);
            isDecisionFinal = utility.inputBool("Confirm change? ");
        } while (!isDecisionFinal);

        this.enrollmentForm.put(field, value);
    }

    public void editCourse() {
        boolean isDecisionFinal;
        int courseNumber;
        String value, field = "Course";

        System.out.println("\nSelect course: ");
        printCourse();
        System.out.println("----------");

        while (true) {
            courseNumber = utility.inputInt("Enter preferred course: ");
            value = coursesList.get(courseNumber - 1);

            System.out.println("You entered: " + value);
            isDecisionFinal = utility.inputBool("Confirm change? ");

            if (isDecisionFinal)
                break;
        }

        this.enrollmentForm.put(field, value);
    }


    public void review() {
        boolean isDecisionFinal = false;
        short operation;

         while (true) {
             System.out.println("""
                    --------------------\s
                    REVIEWING FORM: 
                    """);
             this.print();
             operation = utility.inputShort("""
                                [1] modify form \s
                                [2] confirm changes
                            ----------\s
                            Enter operation:\s""");

             // switch to modify form
             switch (operation) {
                 case 1 -> this.edit();
                 case 2 -> isDecisionFinal  = utility.inputBool("Confirm details? ");
                 default -> {}
             }

             if (isDecisionFinal) break;
         }
    }

    public Student export() throws Exception {
        Student student = new Student(
                this.enrollmentForm.get("Firstname"),
                this.enrollmentForm.get("Middle Name"),
                this.enrollmentForm.get("Lastname"),
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
