package library;

/**
 * Member - Concrete user class demonstrating INHERITANCE and POLYMORPHISM.
 *
 * OOP Concept: INHERITANCE
 * - Extends User (which extends BaseEntity) — multi-level inheritance.
 * - Inherits: id, createdAt (from BaseEntity), name, email, phone (from User).
 * - Adds member-specific fields: borrowLimit, currentBorrowCount.
 *
 * OOP Concept: POLYMORPHISM
 * - Overrides getRole() and getDetails() from User with Member-specific output.
 * - A Member object can be treated as a User or BaseEntity reference.
 *
 * OOP Concept: ENCAPSULATION
 * - borrowLimit and currentBorrowCount are private with business method control.
 */
public class Member extends User {

    // ENCAPSULATION: Member-specific private fields
    private int borrowLimit;
    private int currentBorrowCount;

    /**
     * Constructor: creates a new library member.
     * Default borrow limit is 3 books at a time.
     */
    public Member(String name, String email, String phone) {
        super(name, email, phone); // INHERITANCE: calls User constructor
        this.borrowLimit = 3;
        this.currentBorrowCount = 0;
    }

    // ─── Getters ─────────────────────────────────────────────────────────────

    public int getBorrowLimit()        { return borrowLimit; }
    public int getCurrentBorrowCount() { return currentBorrowCount; }

    // ─── Business Methods ─────────────────────────────────────────────────────

    /**
     * Checks if this member can still borrow more books.
     */
    public boolean canBorrow() {
        return isActive() && currentBorrowCount < borrowLimit;
    }

    /**
     * Increments borrow count when a book is taken out.
     * Throws a LibraryException if the limit would be exceeded.
     */
    public void incrementBorrow() {
        if (!canBorrow()) {
            throw new LibraryException("BORROW_LIMIT",
                "Member '" + getName() + "' has reached the borrow limit of " + borrowLimit);
        }
        currentBorrowCount++;
    }

    /**
     * Decrements borrow count when a book is returned.
     */
    public void decrementBorrow() {
        if (currentBorrowCount > 0) {
            currentBorrowCount--;
        }
    }

    // ─── Overridden Abstract Methods ──────────────────────────────────────────

    /**
     * POLYMORPHISM: Method overriding — provides Member-specific role label.
     */
    @Override
    public String getRole() {
        return "Member";
    }

    /**
     * POLYMORPHISM: Method overriding — describes this member in detail.
     */
    @Override
    public String getDetails() {
        return String.format(
            "Member[ID=%d | Name='%s' | Email=%s | Phone=%s | Books=%d/%d | Active=%s]",
            getId(), getName(), getEmail(), getPhone(),
            currentBorrowCount, borrowLimit, isActive() ? "Yes" : "No"
        );
    }
}
