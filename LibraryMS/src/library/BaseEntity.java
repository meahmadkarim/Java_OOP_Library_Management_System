package library;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * BaseEntity - Abstract base class demonstrating ABSTRACTION.
 *
 * OOP Concept: ABSTRACTION
 * - Defines common fields (id, createdAt) shared by all entities.
 * - Forces subclasses to implement getDetails() through abstraction.
 *
 * OOP Concept: ENCAPSULATION
 * - All fields are private with protected getters for controlled access.
 */
public abstract class BaseEntity {

    // ENCAPSULATION: private fields, not directly accessible from outside
    private final int id;
    private final String createdAt;

    // Static counter to auto-generate unique IDs
    private static int idCounter = 1;

    /**
     * Constructor: assigns a unique ID and records creation timestamp.
     */
    public BaseEntity() {
        this.id = idCounter++;
        this.createdAt = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // ENCAPSULATION: Controlled access through getters
    public int getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * ABSTRACTION: Abstract method — every entity must describe itself.
     * Each subclass provides its own specific implementation.
     */
    public abstract String getDetails();

    /**
     * POLYMORPHISM: Overrides Object.toString() to use getDetails().
     * Allows printing any entity without knowing its exact type.
     */
    @Override
    public String toString() {
        return getDetails();
    }
}
