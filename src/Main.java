import enrollment.EnrollmentSystem;
import enrollment.Utility;
import personel.Person;

public class Main {
    public static void main(String[] args) throws Exception {
        EnrollmentSystem enrollmentSystem = new EnrollmentSystem();
        Utility utility = new Utility();

        //enrollmentSystem.login();
        //enrollmentSystem.enrollStudent();

        System.out.println( utility.inputInt("Enter int value: ") );
        System.out.println( utility.inputShort("Enter short value: ") );
        System.out.println( utility.inputBool("Enter bool value: ") );
    }
}