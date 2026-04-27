package library;

/**
 * User - Abstract class demonstrating ABSTRACTION and INHERITANCE.
 *
 * OOP Concept: ABSTRACTION
 * - User is abstract — you cannot create a raw "User" object.
 * - Forces all subclasses (Member, Admin) to implement getRole()
 *   and getDetails(), since users have different roles/behaviors.
 *
 * OOP Concept: INHERITANCE
 * - Extends BaseEntity to inherit id and createdAt.
 * - Member and Admin will extend this class (multi-level inheritance).
 *
 * OOP Concept: ENCAPSULATION
 * - Private fields with getters/setters for controlled access.
 */
public abstract class User extends BaseEntity {

    // ENCAPSULATION: private fields common to all users
    private String name;
    private String email;
    private String phone;
    private boolean active;

    /**
     * Constructor: initializes common user fields.
     * Called by subclass constructors via super().
     */
    public User(String name, String email, String phone) {
        super(); // INHERITANCE: calls BaseEntity constructor
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.active = true; // All new users start as active
    }

    // ─── Getters ─────────────────────────────────────────────────────────────

    public String getName()  { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public boolean isActive(){ return active; }

    // ─── Setters ─────────────────────────────────────────────────────────────

    public void setName(String name)   { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setActive(boolean a)   { this.active = a; }

    // ─── Abstract Methods ─────────────────────────────────────────────────────

    /**
     * ABSTRACTION: Subclasses must define their own role label.
     * Member returns "Member", Admin returns "Admin".
     */
    public abstract String getRole();

    /**
     * ABSTRACTION: Each subclass describes itself differently.
     * Implements the abstract method from BaseEntity.
     */
    @Override
    public abstract String getDetails();
}
