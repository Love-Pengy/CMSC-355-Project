import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Collections;
import java.util.Comparator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Banker {
    private List<Customer> customers;

    public Banker() {
        this.customers = new ArrayList<>();
    }

    // Method to add a new customer
    public void addCustomer(String lastName, String firstName, String dateOfBirth, String ssn) {
        Customer newCustomer = new Customer(lastName, firstName, dateOfBirth, ssn);
        customers.add(newCustomer);
        System.out.println("New customer added: " + newCustomer.getFirstName() + " " + newCustomer.getLastName() + " with account number: " + newCustomer.getAccountNumber());
    }

    // Method to display customers
    private void displayCustomers() {
        // Sort customers by last name alphabetically
        Collections.sort(customers, new Comparator<Customer>() {
            @Override
            public int compare(Customer c1, Customer c2) {
                return c1.getLastName().compareToIgnoreCase(c2.getLastName());
            }
        });
        System.out.println("List of Customers:");
        for (Customer customer : customers) {
            System.out.println(customer.getLastName() + ", " + customer.getFirstName() + ", Account Number: " + customer.getAccountNumber());
        }
    }


    // Method to search for a customer by last name
    public Customer searchByLastName(String lastName) {
        for (Customer customer : customers) {
            if (customer.getLastName().equalsIgnoreCase(lastName)) {
                return customer;
            }
        }
        return null; // No customer found
    }

    // Method to search for a customer by account number
    public Customer searchByAccountNumber(String accountNumber) {
        for (Customer customer : customers) {
            if (customer.getAccountNumber().equals(accountNumber)) {
                return customer;
            }
        }
        return null; // No customer found
    }

    // Input validators
    private static boolean isValidName(String name) {
        return Pattern.matches("[a-zA-Z]+", name);
    }
    private static boolean isValidDOB(String dob) {
        try {
            LocalDate.parse(dob, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    private static boolean isValidSSN(String ssn) {
        return Pattern.matches("\\d{3}-\\d{2}-\\d{4}", ssn);
    }

    // Method to update customers name
    public void updateCustomerDetails(String accountNumber, String newLastName, String newFirstName){
        Customer customer = searchByAccountNumber(accountNumber);
        if(customer != null) {
            customer.setLastName(newLastName);
            customer.setFirstName(newFirstName);
            System.out.println("Customer name update for account number: " + accountNumber);
        } else {
            System.out.println("Customer with account number: " + accountNumber + " was not found.");
        }
    }




    // Main method to run the program
    public static void main(String[] args) {
        Banker banker = new Banker();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Do you want to (1) search for an existing customer, (2) add a new customer, (3) display the list of customers, or (4) edit a customers name? (Enter 1, 2, 3, or 4, or any other key to exit): ");
            String choice = scanner.nextLine();

            if ("1".equals(choice)) {
                System.out.println("Search by (1) Last Name or (2) Account Number? (Enter 1 or 2): ");
                String searchChoice = scanner.nextLine();
                if ("1".equals(searchChoice)) {
                        System.out.println("Enter the last name: ");
                        String lastName = scanner.nextLine();
                        Customer foundCustomer = banker.searchByLastName(lastName);
                        if (foundCustomer != null) {
                            System.out.println("Customer found: " + foundCustomer.getFirstName() + " " + foundCustomer.getLastName() + ", Account Number: " + foundCustomer.getAccountNumber());
                        }
                        else {
                            System.out.println("No customer found with last name: " + lastName);
                        }
            } else if ("2".equals(searchChoice)) {
                    System.out.println("Enter the account number: ");
                    String accountNumber = scanner.nextLine();
                    Customer foundCustomer = banker.searchByAccountNumber(accountNumber);
                    if (foundCustomer != null) {
                        System.out.println("Customer found: " + foundCustomer.getFirstName() + " " + foundCustomer.getLastName());
                    } else {
                        System.out.println("No customer found with account number: " + accountNumber);
                    }
            }
            } 
            // Adding a new customer
            else if ("2".equals(choice)) { 
                String lastName, firstName, dob, ssn;
                do {
                    System.out.println("Enter last name: ");
                    lastName = scanner.nextLine();
                    if (!isValidName(lastName)) {
                        System.out.println("Invalid input. Last name should only contain letters A-Z or a-z. Please try again.");
                    }
                } while (!isValidName(lastName));
    
                do {
                    System.out.println("Enter first name: ");
                    firstName = scanner.nextLine();
                    if (!isValidName(firstName)) {
                        System.out.println("Invalid input. First name should only contain letters A-Z or a-z. Please try again.");
                    }
                } while (!isValidName(firstName));
                do {
                    System.out.println("Enter date of birth (YYYY-MM-DD): ");
                    dob = scanner.nextLine();
                    if (!isValidDOB(dob)) {
                        System.out.println("Invalid input. Date of birth must be in the format YYYY-MM-DD and contain only numbers. Please try again.");
                    }
                } while (!isValidDOB(dob));
                do {
                    System.out.println("Enter Social Security Number (NNN-NN-NNNN): ");
                    ssn = scanner.nextLine();
                    if (!isValidSSN(ssn)) {
                        System.out.println("Invalid input. Social Security Number must be in the format NNN-NN-NNNN and contain only numbers. Please try again.");
                    }
                } while (!isValidSSN(ssn));
                banker.addCustomer(lastName, firstName, dob, ssn);
            } 
            // Display the list of customers
            else if("3".equals(choice)){
                banker.displayCustomers();
            } 

            // Update a customers name
            else if ("4".equals(choice)){
                System.out.println("Enter a customer's account number: ");
                String accountNumber =  scanner.nextLine();

                System.out.println("Enter new last name: ");
                String newLastName = scanner.nextLine();

                System.out.println("Enter new first name: ");
                String newFirstName = scanner.nextLine();

                banker.updateCustomerDetails(accountNumber, newLastName, newFirstName);
            }
            
            // Exiting The program
            else {
                System.out.println("Exiting program.");
                break;
            }
        }

        scanner.close();
    }
}