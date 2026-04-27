package library;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Loan - Transaction class demonstrating ENCAPSULATION and INHERITANCE.
 *
 * OOP Concept: INHERITANCE
 * - Extends BaseEntity, inheriting the auto-generated ID and timestamp.
 *
 * OOP Concept: ENCAPSULATION
 * - All fields are private; status changes happen through business methods
 *   (completeLoan()) rather than direct field access.
 *
 * OOP Concept: POLYMORPHISM
 * - Overrides getDetails() from BaseEntity with loan-specific output.
 *
 * This class represents the transaction of a member borrowing a book.
 * It tracks borrow date, due date, return date, and calculates fines.
 */
public class Loan extends BaseEntity {

    // ENCAPSULATION: private fields
    private final int memberId;
    private final String memberName;
    private final int bookId;
    private final String bookTitle;
    private final LocalDate borrowDate;
    private final LocalDate dueDate;
    private LocalDate returnDate;
    private boolean returned;
    private double fineAmount;

    // Fine rate: 0.50 per day overdue
    private static final double FINE_PER_DAY = 0.50;
    // Default loan period: 14 days
    private static final int LOAN_DAYS = 14;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructor: creates a new loan for a member borrowing a book.
     */
    public Loan(int memberId, String memberName, int bookId, String bookTitle) {
        super(); // INHERITANCE: auto-generates loan ID and createdAt
        this.memberId = memberId;
        this.memberName = memberName;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(LOAN_DAYS);
        this.returned = false;
        this.fineAmount = 0.0;
    }

    // ─── Getters ─────────────────────────────────────────────────────────────

    public int getMemberId()     { return memberId; }
    public String getMemberName(){ return memberName; }
    public int getBookId()       { return bookId; }
    public String getBookTitle() { return bookTitle; }
    public LocalDate getDueDate(){ return dueDate; }
    public boolean isReturned()  { return returned; }
    public double getFineAmount(){ return fineAmount; }

    // ─── Business Methods ─────────────────────────────────────────────────────

    /**
     * ENCAPSULATION: Marks the loan as returned and calculates any fine.
     * Internal state change controlled through this single method.
     */
    public void completeLoan() {
        this.returnDate = LocalDate.now();
        this.returned = true;

        // Calculate fine if returned after due date
        long daysOverdue = ChronoUnit.DAYS.between(dueDate, returnDate);
        if (daysOverdue > 0) {
            this.fineAmount = daysOverdue * FINE_PER_DAY;
        }
    }

    /**
     * Checks if this loan is currently overdue.
     */
    public boolean isOverdue() {
        return !returned && LocalDate.now().isAfter(dueDate);
    }

    /**
     * Calculates how many days overdue this loan currently is.
     */
    public long getDaysOverdue() {
        if (!isOverdue()) return 0;
        return ChronoUnit.DAYS.between(dueDate, LocalDate.now());
    }

    /**
     * POLYMORPHISM: Overrides abstract getDetails() from BaseEntity.
     */
    @Override
    public String getDetails() {
        String status = returned
            ? "Returned on " + returnDate.format(FMT) + (fineAmount > 0 ? " | Fine=$" + fineAmount : "")
            : (isOverdue() ? "OVERDUE by " + getDaysOverdue() + " days" : "Active");

        return String.format(
            "Loan[ID=%d | Member='%s'(#%d) | Book='%s'(#%d) | Borrowed=%s | Due=%s | %s]",
            getId(), memberName, memberId, bookTitle, bookId,
            borrowDate.format(FMT), dueDate.format(FMT), status
        );
    }
}
