
import java.util.Random;

public class Customer {
    private String lastName; //Format: A-Z or a-z
    private String firstName; //Format: A-Z or a-z
    private String dateOfBirth; // Format: YYYY-MM-DD
    private String socialSecurityNumber; // Format: NNN-NN-NNNN
    private String accountNumber; //Format: NNNNNNNNNNNNNNNN (16 digits)

    //Constructors
    public Customer(String lastName, String firstName, String dateOfBirth, String socialSecurityNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.socialSecurityNumber = socialSecurityNumber;
        this.accountNumber = generateAccountNumber();
    }

    // Getters and setters
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    private static String generateAccountNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        //Ensure the first digit is not zero
        sb.append(random.nextInt(9) + 1);
        for (int i = 1; i < 16; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

}
