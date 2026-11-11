package library;

import java.lang.reflect.Field;
import java.util.List;

/** Run with java -cp build library.BorrowRegressionTest. */
public class BorrowRegressionTest {
    public static void main(String[] args) throws Exception {
        LibraryManager manager = new LibraryManager();
        manager.addBook("Test book", "Test author", "test-isbn", "Test", 5);
        manager.registerMember("Test member", "example@example.com", "000");
        Field booksField = LibraryManager.class.getDeclaredField("books");
        Field usersField = LibraryManager.class.getDeclaredField("users");
        booksField.setAccessible(true);
        usersField.setAccessible(true);
        Book book = (Book) ((List<?>) booksField.get(manager)).get(0);
        Member member = (Member) ((List<?>) usersField.get(manager)).get(0);
        for (int i = 0; i < 3; i++) manager.borrowBook(member.getId(), book.getId());
        int before = book.getAvailableCopies();
        try {
            manager.borrowBook(member.getId(), book.getId());
            throw new AssertionError("Fourth loan must be rejected");
        } catch (LibraryException expected) {
            if (book.getAvailableCopies() != before || member.getCurrentBorrowCount() != 3)
                throw new AssertionError("Rejected loan changed inventory or member state");
        }
        System.out.println("PASS: rejected loan preserves inventory and member state");
    }
}
