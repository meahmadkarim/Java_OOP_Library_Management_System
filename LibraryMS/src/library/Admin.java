package library;

/**
 * Admin - Concrete user class demonstrating INHERITANCE and POLYMORPHISM.
 *
 * OOP Concept: INHERITANCE
 * - Extends User (which extends BaseEntity) — multi-level inheritance.
 * - Inherits all User fields (name, email, phone) and BaseEntity fields (id).
 * - Adds admin-specific field: adminCode (a unique admin identifier).
 *
 * OOP Concept: POLYMORPHISM
 * - Overrides getRole() and getDetails() differently from Member.
 * - An Admin object can be referenced as a User, demonstrating subtype polymorphism.
 *
 * OOP Concept: ENCAPSULATION
 * - adminCode is private, accessible only through the getter.
 */
public class Admin extends User {

    // ENCAPSULATION: Admin-specific private field
    private String adminCode;

    /**
     * Constructor: creates a new admin user with a unique admin code.
     *
     * @param name      Admin's full name
     * @param email     Admin's email address
     * @param phone     Admin's phone number
     * @param adminCode A unique code identifying this admin (e.g., "ADM-001")
     */
    public Admin(String name, String email, String phone, String adminCode) {
        super(name, email, phone); // INHERITANCE: calls User constructor
        this.adminCode = adminCode;
    }

    // ─── Getter ───────────────────────────────────────────────────────────────

    public String getAdminCode() { return adminCode; }

    // ─── Overridden Abstract Methods ──────────────────────────────────────────

    /**
     * POLYMORPHISM: Method overriding — provides Admin-specific role label.
     * Compare to Member.getRole() which returns "Member".
     */
    @Override
    public String getRole() {
        return "Admin";
    }

    /**
     * POLYMORPHISM: Method overriding — describes this admin in detail.
     * Different output compared to Member.getDetails(), same method name.
     */
    @Override
    public String getDetails() {
        return String.format(
            "Admin[ID=%d | Name='%s' | Email=%s | Code=%s | Active=%s]",
            getId(), getName(), getEmail(), adminCode, isActive() ? "Yes" : "No"
        );
    }
}
