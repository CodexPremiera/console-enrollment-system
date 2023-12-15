package personel;

import enrollment.Utility;

public class EnrollmentHandler extends Person {
    private static int ENROLL_HANDLER_ID = 0;
    private boolean hasApprovePower;
    private String password = "";

    private final Utility utility = new Utility();

    /* ============================== CONSTRUCTORS ============================== */
    public EnrollmentHandler() throws Exception {
        super();
        ENROLL_HANDLER_ID++;
        this.hasApprovePower = false;
    }

    public EnrollmentHandler(boolean canApprove) throws Exception {
        super();
        ENROLL_HANDLER_ID++;
        this.hasApprovePower = canApprove;
    }

    public EnrollmentHandler(String fullName, String birthdate, char gender, String nationality, String address)
            throws Exception {
        super(fullName, birthdate, gender, nationality, address);
        ENROLL_HANDLER_ID++;
        this.hasApprovePower = false;
    }
    public EnrollmentHandler(String fullName, String birthdate, char gender, String nationality, String address,
                             boolean canApprove) throws Exception {
        super(fullName, birthdate, gender, nationality, address);
        ENROLL_HANDLER_ID++;
        this.hasApprovePower = canApprove;
    }

    /* ============================== SETTERS AND GETTERS ============================== */

    public boolean getHasApprovePower() { //
        return hasApprovePower;
    }
    public void setHasApprovePower(boolean hasApprovePower) { //
        this.hasApprovePower = hasApprovePower;
    }

    public void setPassword(String password) { //
        this.password = password;
    }

    /* ============================== METHODS ============================== */
    @Override
    public void displayInfo() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Enrollment Handler ID: " + ENROLL_HANDLER_ID + "\n" +
                "Name:    " + getFullName() + "\n" +
                "\n" +
                "Birth:   " + getBirthdateText() + "\n" +
                "Age:     " + getAge() + "\n" +
                "Gender:  " + getGender() + "\n" +
                "Address: " + getAddress() + "\n";
    }
}
