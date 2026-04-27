package library;

/**
 * LibraryService - Interface demonstrating ABSTRACTION.
 *
 * OOP Concept: ABSTRACTION (Interface)
 * - Defines a contract: any class implementing this MUST provide
 *   these operations. The caller doesn't need to know the internals.
 *
 * OOP Concept: POLYMORPHISM
 * - LibraryManager implements this interface, meaning it can be
 *   referenced as a LibraryService type (runtime polymorphism).
 */
public interface LibraryService {

    // Book management operations
    void addBook(String title, String author, String isbn, String category, int totalCopies);
    void listAllBooks();
    void searchBook(String keyword);

    // Member management operations
    void registerMember(String name, String email, String phone);
    void listAllMembers();

    // Loan operations
    void borrowBook(int memberId, int bookId) throws LibraryException;
    void returnBook(int loanId) throws LibraryException;
    void listActiveLoans();

    // Report
    void generateReport();
}
