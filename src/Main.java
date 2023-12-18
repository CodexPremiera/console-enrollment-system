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

        //enrollmentSystem.login();

        //enrollmentSystem.enrollStudent();
        enrollmentSystem.enrollBatch();

    }
}