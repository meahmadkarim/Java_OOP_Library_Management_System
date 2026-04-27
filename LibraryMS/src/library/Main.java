package library;

import java.util.Scanner;

/**
 * Main - Entry point for the Library Management System.
 *
 * Demonstrates a console-based menu system with exception handling
 * and proper error reporting. Keeps everything as simple as possible
 * for a semester project.
 */
public class Main {
    // POLYMORPHISM in action: Referring to LibraryManager via its interface (LibraryService)
    private static final LibraryService service = new LibraryManager();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("          LIBRARY MANAGEMENT SYSTEM");
        System.out.println("==================================================");
        
        boolean running = true;
        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Add Book");
            System.out.println("2. List All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Register Member");
            System.out.println("5. List All Members");
            System.out.println("6. Borrow Book");
            System.out.println("7. Return Book");
            System.out.println("8. List Active Loans");
            System.out.println("9. Generate Report");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                continue; // skips the switch case and re-prompts
            }

            try {
                switch (choice) {
                    case 1: addBook(); break;
                    case 2: service.listAllBooks(); break;
                    case 3: searchBook(); break;
                    case 4: registerMember(); break;
                    case 5: service.listAllMembers(); break;
                    case 6: borrowBook(); break;
                    case 7: returnBook(); break;
                    case 8: service.listActiveLoans(); break;
                    case 9: service.generateReport(); break;
                    case 0:
                        System.out.println("Exiting Library System. Goodbye!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try a number between 0 and 9.");
                }
            } catch (LibraryException e) {
                // Catch custom business exceptions (controlled flow errors)
                System.out.println("Error [" + e.getErrorCode() + "]: " + e.getMessage());
            } catch (Exception e) {
                // Catch unexpected technical exceptions
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();
        System.out.print("Enter Total Copies: ");
        int copies = Integer.parseInt(scanner.nextLine());
        service.addBook(title, author, isbn, category, copies);
    }

    private static void searchBook() {
        System.out.print("Enter keyword (Title or Author): ");
        String kw = scanner.nextLine();
        service.searchBook(kw);
    }

    private static void registerMember() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        service.registerMember(name, email, phone);
    }

    private static void borrowBook() {
        System.out.print("Enter Member ID: ");
        int mId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Book ID: ");
        int bId = Integer.parseInt(scanner.nextLine());
        service.borrowBook(mId, bId);
    }

    private static void returnBook() {
        System.out.print("Enter Loan ID: ");
        int lId = Integer.parseInt(scanner.nextLine());
        service.returnBook(lId);
    }
}
