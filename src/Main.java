import enrollment.EnrollmentForm;
import enrollment.EnrollmentSystem;
import enrollment.Utility;
import personel.Person;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        Utility utility = new Utility();
        EnrollmentSystem enrollmentSystem = new EnrollmentSystem();

        boolean isDecisionFinal = false;
        boolean canExitSystem = false;
        int operation;

        do {
            System.out.println("""
                            ---------------------------------------------------
                            == COMANDAO INSTITUTE OF TECHNOLOGY - UNIVERSITY ==
                            
                            THE ENROLLMENT SYSTEM
                            
                            System options:    
                                [1] Enroll student\s
                                [2] View Student\s
                                [3] Edit Student\s
                                [4] Delete Student\s
                                
                                [0] exit \s
                            ----------""");

            operation = utility.inputInt("Please enter operation: ");
            System.out.println();

            switch (operation) {
                case 1 -> enrollmentSystem.performEnrollment();
                case 2 -> enrollmentSystem.performViewing();
                case 0 -> {
                    isDecisionFinal = utility.inputBool("Confirm exit program? ");
                }
                default -> {}
            }

            System.out.println();
        } while (!isDecisionFinal);

    }
}