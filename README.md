# Library Management System

A Java console semester project demonstrating object-oriented programming through books, members and loans. Records live in memory and reset when the process exits.

## Implemented workflows

- Add, list and search books by title or author.
- Register and list members.
- Borrow and return books, enforce available copies and member borrowing limits.
- List active loans and print inventory totals.

## Run from source

Install a JDK with `javac` and `java` on PATH. From the repository root:

```sh
javac -encoding UTF-8 -d build LibraryMS/src/library/*.java
java -cp build library.Main
```

Use the numbered menu; choose 0 to exit. Use fictional member contact information when demonstrating the application.

## Design

`LibraryService` defines the service interface; `LibraryManager` manages collections. `BaseEntity` and `User` provide shared abstractions; `Book`, `Member`, `Admin` and `Loan` model the domain. `LibraryException` reports business-rule failures.

## Limitations

No database, authentication, network API or GUI is implemented. Input validation is basic. Existing compiled files under `LibraryMS/out` are legacy build artefacts; compile source into `build` rather than running those binaries. `.gitignore` prevents future artefacts but does not untrack files already committed.

## Regression check

```sh
javac -encoding UTF-8 -cp build -d build LibraryMS/tests/library/BorrowRegressionTest.java
java -cp build library.BorrowRegressionTest
```

Verified with JDK 25: a fourth loan is rejected without reducing available stock. This regression fix and documentation were prepared with AI assistance.

## Technology and implementation details

This small coursework application uses **Java's standard library**, `ArrayList`/`List` collections and console input/output. `LibraryService` defines an interface; `LibraryManager` implements operations, while domain classes model books, members and loans. There is no external framework, database or ML model. The regression check uses Java reflection to inspect inventory and member state after a rejected loan. Java is a supporting coursework skill in this portfolio.
