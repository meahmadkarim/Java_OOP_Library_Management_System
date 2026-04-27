package library;

/**
 * Book - Concrete entity class demonstrating INHERITANCE and ENCAPSULATION.
 *
 * OOP Concept: INHERITANCE
 * - Extends BaseEntity, inheriting id, createdAt, and toString().
 * - Adds book-specific fields: title, author, isbn, category, copies.
 *
 * OOP Concept: ENCAPSULATION
 * - All fields are private; public getters/setters control access.
 * - availableCopies is managed internally (borrow/return logic).
 *
 * OOP Concept: POLYMORPHISM
 * - Overrides getDetails() from BaseEntity to give book-specific output.
 */
public class Book extends BaseEntity {

    // ENCAPSULATION: private fields
    private String title;
    private String author;
    private String isbn;
    private String category;
    private int totalCopies;
    private int availableCopies;

    /**
     * Constructor: creates a new book with all required details.
     */
    public Book(String title, String author, String isbn, String category, int totalCopies) {
        super(); // INHERITANCE: calls BaseEntity constructor to set id & timestamp
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies; // Initially all copies available
    }

    // ─── Getters ─────────────────────────────────────────────────────────────

    public String getTitle()           { return title; }
    public String getAuthor()          { return author; }
    public String getIsbn()            { return isbn; }
    public String getCategory()        { return category; }
    public int getTotalCopies()        { return totalCopies; }
    public int getAvailableCopies()    { return availableCopies; }

    // ─── Setters ─────────────────────────────────────────────────────────────

    public void setTitle(String title)       { this.title = title; }
    public void setAuthor(String author)     { this.author = author; }
    public void setCategory(String category) { this.category = category; }

    // ─── Business Methods ─────────────────────────────────────────────────────

    /**
     * Checks if at least one copy is available to borrow.
     */
    public boolean isAvailable() {
        return availableCopies > 0;
    }

    /**
     * Decrements available copies when a book is borrowed.
     * Called internally by LibraryManager during a borrow operation.
     */
    public void borrow() {
        if (!isAvailable()) {
            throw new LibraryException("NO_COPIES", "No available copies of: " + title);
        }
        availableCopies--;
    }

    /**
     * Increments available copies when a book is returned.
     */
    public void returnCopy() {
        if (availableCopies < totalCopies) {
            availableCopies++;
        }
    }

    /**
     * POLYMORPHISM: Overrides abstract getDetails() from BaseEntity.
     * Each entity type provides its own version of this method.
     */
    @Override
    public String getDetails() {
        return String.format(
            "Book[ID=%d | Title='%s' | Author='%s' | ISBN=%s | Category=%s | Available=%d/%d]",
            getId(), title, author, isbn, category, availableCopies, totalCopies
        );
    }
}
