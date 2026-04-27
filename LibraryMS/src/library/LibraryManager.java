package library;

import java.util.ArrayList;
import java.util.List;

/**
 * LibraryManager - Concrete implementation of LibraryService.
 *
 * OOP Concept: POLYMORPHISM
 * - Provides specific implementations for the LibraryService contract.
 *
 * OOP Concept: ENCAPSULATION
 * - Uses private Lists to encapsulate the system's data (books, users, loans).
 */
public class LibraryManager implements LibraryService {

    // ENCAPSULATION: private collections to store in-memory data
    private final List<Book> books = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    private final List<Loan> loans = new ArrayList<>();

    @Override
    public void addBook(String title, String author, String isbn, String category, int totalCopies) {
        Book book = new Book(title, author, isbn, category, totalCopies);
        books.add(book);
        System.out.println("Book added successfully: " + book.getDetails());
    }

    @Override
    public void listAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        for (Book book : books) {
            System.out.println(book.getDetails());
        }
    }

    @Override
    public void searchBook(String keyword) {
        boolean found = false;
        String lower = keyword.toLowerCase();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(lower) || 
                book.getAuthor().toLowerCase().contains(lower)) {
                System.out.println(book.getDetails());
                found = true;
            }
        }
        if (!found) System.out.println("No matching books found.");
    }

    @Override
    public void registerMember(String name, String email, String phone) {
        Member member = new Member(name, email, phone);
        users.add(member);
        System.out.println("Member registered successfully: " + member.getDetails());
    }

    @Override
    public void listAllMembers() {
        boolean hasMembers = false;
        for (User user : users) {
            if (user instanceof Member) {
                System.out.println(user.getDetails());
                hasMembers = true;
            }
        }
        if (!hasMembers) System.out.println("No members registered.");
    }

    @Override
    public void borrowBook(int memberId, int bookId) throws LibraryException {
        Book tBook = null;
        for (Book b : books) {
            if (b.getId() == bookId) {
                tBook = b; break;
            }
        }
        if (tBook == null) throw new LibraryException("NOT_FOUND", "Book not found.");

        Member tMember = null;
        for (User u : users) {
            if (u.getId() == memberId && u instanceof Member) {
                tMember = (Member) u; break;
            }
        }
        if (tMember == null) throw new LibraryException("NOT_FOUND", "Member not found.");

        // Throw exceptions if requirements are not met
        tBook.borrow();
        tMember.incrementBorrow();

        Loan loan = new Loan(tMember.getId(), tMember.getName(), tBook.getId(), tBook.getTitle());
        loans.add(loan);
        System.out.println("Book borrowed successfully. Loan details: " + loan.getDetails());
    }

    @Override
    public void returnBook(int loanId) throws LibraryException {
        Loan targetLoan = null;
        for (Loan l : loans) {
            if (l.getId() == loanId) {
                targetLoan = l; break;
            }
        }
        if (targetLoan == null) throw new LibraryException("NOT_FOUND", "Loan not found.");
        if (targetLoan.isReturned()) throw new LibraryException("ALREADY_RETURNED", "This book was already returned.");

        targetLoan.completeLoan();

        // Increment book copy
        for (Book b : books) {
            if (b.getId() == targetLoan.getBookId()) {
                b.returnCopy();
                break;
            }
        }

        // Decrement member borrow count
        for (User u : users) {
            if (u.getId() == targetLoan.getMemberId() && u instanceof Member) {
                ((Member) u).decrementBorrow();
                break;
            }
        }

        System.out.println("Book returned successfully. Loan details: " + targetLoan.getDetails());
    }

    @Override
    public void listActiveLoans() {
        boolean hasLoans = false;
        for (Loan l : loans) {
            if (!l.isReturned()) {
                System.out.println(l.getDetails());
                hasLoans = true;
            }
        }
        if (!hasLoans) System.out.println("No active loans.");
    }

    @Override
    public void generateReport() {
        System.out.println("--- Library System Report ---");
        System.out.println("Total Books in Catalog: " + books.size());
        int totalCopies = 0, availableCopies = 0;
        for (Book b : books) { 
            totalCopies += b.getTotalCopies(); 
            availableCopies += b.getAvailableCopies(); 
        }
        System.out.println("Total Book Copies: " + totalCopies);
        System.out.println("Available Book Copies: " + availableCopies);
        System.out.println("Total Registered Users: " + users.size());
        
        int activeLoans = 0;
        for (Loan l : loans) {
            if (!l.isReturned()) activeLoans++;
        }
        System.out.println("Total Active Loans: " + activeLoans);
        System.out.println("-----------------------------");
    }
}
