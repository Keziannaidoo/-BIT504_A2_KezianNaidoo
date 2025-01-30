package BIT504_A2;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

public class BookManagement {
	private List<Book> books;
	private List<Member> members;
	private Scanner scanner = new Scanner(System.in);

	// Constructor
	public BookManagement(List<Book> books, List<Member> members) {
		this.books = books;
		this.members = members;
	}

	// Display all books
	public void displayAllBooks() {
		showTableHeader("All Books");
		showBookTable(books);
	}

	// Display all the borrowed books
	public void displayAllBorrowedBooks() {
		showTableHeader("Borrowed Books");
		List<Book> borrowedBooks = books.stream().filter(book -> book.isBorrowed() == true).toList();
		showBookTable(borrowedBooks);
	}

	// Display all the unborrowed books
	public void displayAllUnborrowedBooks() {
		showTableHeader("Available Books");
		List<Book> availableBooks = books.stream().filter(book -> book.isBorrowed() == false).toList();

		showBookTable(availableBooks);
	}

	// Add a new book method
	public void addNewBook() {
		System.out.println("\nAdd New Book");
		System.out.println("-".repeat(30));

		String id = createId();
		String isbn = checkIsbn();
		String title = getInput("Enter title: ");
		String author = getInput("Enter author: ");
		String publicationDate = getInput("Enter publication date (yyyy-mm-dd): ");
		String genre = getInput("Enter genre: ");
		int ageRating = getAgeRating();

		Book newBook = new Book(id, isbn, title, author, publicationDate, genre, ageRating, false);
		books.add(newBook);
		System.out.println("New book added successfully!");
	}

	private String createId() {
		if (books.isEmpty()) {
			return "1";
		}

		Book lastBookAdded = books.get(books.size() - 1);
		int lastId = Integer.parseInt(lastBookAdded.getId());

		return String.valueOf(lastId + 1);
	}

	private String checkIsbn() {
		while (true) {

			String isbnNumber = getInput("Enter ISBN (the following must be 10 characters): ");

			if (isbnNumber.length() != 10) {
				System.out.println("ISBN must be 10 characters.");
				continue;
			}

			boolean isIsbnUnique = true;

			for (Book book : books) {
				if (book.getIsbn().equals(isbnNumber)) {
					isIsbnUnique = false;
					break;
				}
			}

			if (isIsbnUnique) {
				return isbnNumber;
			} else {
				System.out.println("This ISBN already exists. Please enter a unique ISBN.");
			}
		}
	}

	private String getInput(String prompt) {
		String input;
		do {
			System.out.print(prompt);
			input = scanner.nextLine().trim();
			if (input.isEmpty()) {
				System.out.println("This field is required. Please try again.");
			}
		} while (input.isEmpty());
		return input;
	}

	private int getAgeRating() {
		while (true) {
			System.out.print("Enter age rating: ");
			try {
				int rating = Integer.parseInt(scanner.nextLine().trim());
				if (rating < 0) {
					System.out.println("The Following Age rating must be a positive.");
				} else {
					return rating;
				}
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid number rating.");
			}
		}
	}

	// Display the table header
	private void showTableHeader(String tableName) {
		System.out.println("\n" + tableName);
		System.out.println("-".repeat(180));
		System.out.printf("%-5s | %-25s | %-30s | %-20s | %-12s | %-25s | %s%n", "ID", "ISBN", "Title", "Author",
				"Published", "Genre", "Age Rating");
		System.out.println("-".repeat(180));
	}

	// Display book table
	private void showBookTable(List<Book> bookList) {
		for (Book book : bookList) {
			System.out.printf("%-5s | %-25s | %-30s | %-20s | %-12s | %-25s | %d%n", book.getId(), book.getIsbn(),
					book.getTitle(), book.getAuthor(), book.getPublicationDate(), book.getGenre(), book.getAgeRating());
		}
		System.out.println("-".repeat(180));
	}

	// Check out a book from system
	public void checkOutBook() {
		System.out.println("\nCheck Out a Book");
		System.out.println("-".repeat(30));

		String bookId = getInput("Enter book ID: ");
		Book book = findBookById(bookId);

		if (book == null) {
			System.out.println("Book not found.");
			return;
		}

		if (book.isBorrowed()) {
			System.out.println("This book is already borrowed.");
			return;
		}

		// get the member
		String memberId = getInput("Enter member ID: ");
		Member member = findMemberById(memberId);

		if (member == null) {
			System.out.println("Member not found.");
			return;
		}

		if (book.getAgeRating() > member.getAge()) {
			System.out.println("Warning: The member is to young to read the book based on rating.");
			System.out.println("Unable to check out the book.");
			return;
		}

		System.out.println("Book: " + book.getTitle());
		System.out.println("Member: " + member.getFirstName() + " " + member.getLastName());
		String confirm = getInput("Confirm check out? (yes/no): ");

		if (confirm.equalsIgnoreCase("yes")) {
			book.setBorrowedMemberId(memberId);
			System.out.println("Book checked out successfully.");
		} else {
			System.out.println("Check out cancelled.");
		}
	}

	// Check in a book
	public void checkInBook() {
		System.out.println("\nCheck In a Book");
		System.out.println("-".repeat(30));

		String bookId = getInput("Enter book ID: ");
		Book book = findBookById(bookId);
		if (book == null) {
			System.out.println("Book not found.");
			return;
		}

		if (!book.isBorrowed()) {
			System.out.println("This book is not currently borrowed.");
			return;
		}

		book.setBorrowedMemberId(null);
		System.out.println("Book '" + book.getTitle() + "' has been successfully returned.");
	}
	
	public void findBook() {
        System.out.println("\nFind a Book");
        System.out.println("-".repeat(30));

        String title = getInput("Enter book title (or part of it): ");
        List<Book> foundBooks = books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());

        if (foundBooks.isEmpty()) {
            System.out.println("No books found with the title containing '" + title + "'.");
        } else {
            System.out.println("Found " + foundBooks.size() + " book(s):");
            for (Book book : foundBooks) {
                displayBookInfo(book);
            }
        }
    }

    private void displayBookInfo(Book book) {
        System.out.println("\nID: " + book.getId());
        System.out.println("ISBN: " + book.getIsbn());
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Publish date: " + book.getPublicationDate());
        System.out.println("Genre: " + book.getGenre());
        System.out.println("Age rating: " + book.getAgeRating());

        if (book.isBorrowed()) {
            Member borrower = findMemberById(book.getBorrowedMemberId());
            if (borrower != null) {
                System.out.println("Borrowed by the following member: " + borrower.getFirstName() + " " + borrower.getLastName());
            } else {
                System.out.println("Borrowed The borrower information is no found");
            }
        } else {
            System.out.println("Status: Available");
        }
        System.out.println("-".repeat(30));
    }

	// Get the book based on id
	private Book findBookById(String id) {
		return books.stream().filter(book -> book.getId().equals(id)).findFirst().orElse(null);
	}

	// Find member
	private Member findMemberById(String id) {
		return members.stream().filter(member -> member.getId().equals(id)).findFirst().orElse(null);
	}
}