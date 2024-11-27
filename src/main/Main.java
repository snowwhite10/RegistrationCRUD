package main;

import crud.RegistrationCRUD;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Registration CRUD Operations ===");
            System.out.println("1. Create a new registration");
            System.out.println("2. Read registration(s)");
            System.out.println("3. Update a registration");
            System.out.println("4. Delete a registration");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // CREATE
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
                    String dob = scanner.nextLine();
                    System.out.print("Enter Phone Number (optional): ");
                    String phone = scanner.nextLine();
                    RegistrationCRUD.createRegistration(name, email, dob, phone);
                    break;

                case 2: // READ
                    System.out.print("Enter ID to read a specific record (or 0 for all): ");
                    int id = scanner.nextInt();
                    if (id == 0) {
                        RegistrationCRUD.readRegistration(null);
                    } else {
                        RegistrationCRUD.readRegistration(id);
                    }
                    break;

                case 3: // UPDATE
                    System.out.print("Enter ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new Name (or press Enter to skip): ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new Email (or press Enter to skip): ");
                    String newEmail = scanner.nextLine();
                    System.out.print("Enter new Date of Birth (YYYY-MM-DD) (or press Enter to skip): ");
                    String newDob = scanner.nextLine();
                    System.out.print("Enter new Phone Number (or press Enter to skip): ");
                    String newPhone = scanner.nextLine();
                    RegistrationCRUD.updateRegistration(updateId, 
                        newName.isEmpty() ? null : newName,
                        newEmail.isEmpty() ? null : newEmail,
                        newDob.isEmpty() ? null : newDob,
                        newPhone.isEmpty() ? null : newPhone);
                    break;

                case 4: // DELETE
                    System.out.print("Enter ID to delete: ");
                    int deleteId = scanner.nextInt();
                    RegistrationCRUD.deleteRegistration(deleteId);
                    break;

                case 5: // EXIT
                    System.out.println("Exiting the program...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
