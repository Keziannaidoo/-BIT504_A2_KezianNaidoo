package BIT504_A2;

import java.util.List;
import java.util.Scanner;

public class Menu {
	// Create single instance
	ReadFileData readFileData = ReadFileData.getInstance();

	// Read data from files
	List<Book> books = readFileData.readBooks("books.txt");
	List<Member> members = readFileData.readMembers("members.txt");

	private Scanner scanner = new Scanner(System.in);
	private BookManagement bookManagement = new BookManagement(books, members);
	private MemberManagement memberManagement = new MemberManagement(members, books);

	public Menu() {
	}

	// Methods
	public void displayMainMenu() {
		while (true) {
			System.out.println("\n--- Main Menu ---");
			System.out.println("1. Book Management");
			System.out.println("2. Member Management");
			System.out.println("3. Loan Management");
			System.out.println("4. Search");
			System.out.println("5. Exit");
			System.out.print("Enter your choice: ");

			int choice = getIntInput();

			switch (choice) {
			case 1:
				bookManagementSystemMenu();
				break;
			case 2:
				memberManagementSystemMenu();
				break;
			case 3:
				loanMenu();
				break;
			case 4:
				searchMenu();
				break;
			case 5:
				System.out.println("Exiting the program. Goodbye!");
				return;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
	}

	private void bookManagementSystemMenu() {
		while (true) {
			System.out.println("\n--- Book Management ---");
			System.out.println("a. Display all books");
			System.out.println("b. Display borrowed books");
			System.out.println("c. Display unborrowed books");
			System.out.println("d. Add a new book");
			System.out.println("e. Return to main menu");
			System.out.print("Enter your choice: ");

			char choice = getCharInput();

			switch (choice) {
			case 'a':
				bookManagement.displayAllBooks();
				break;
			case 'b':
				bookManagement.displayAllBorrowedBooks();
				break;
			case 'c':
				bookManagement.displayAllUnborrowedBooks();
				break;
			case 'd':
				bookManagement.addNewBook();
				break;
			case 'e':
				return;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
	}

	private void memberManagementSystemMenu() {
		while (true) {
			System.out.println("\n--- Member Management ---");
			System.out.println("a. Display all members");
			System.out.println("b. Add a new member");
			System.out.println("c. Return to main menu");
			System.out.print("Enter your choice: ");

			char choice = getCharInput();

			switch (choice) {
			case 'a':
				memberManagement.displayAllSystemMembers();
				break;
			case 'b':
				memberManagement.addNewMember();
				break;
			case 'c':
				return;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
	}

	private void loanMenu() {
		while (true) {
			System.out.println("\n--- Loan Management ---");
			System.out.println("a. Check out a book");
			System.out.println("b. Check in a book");
			System.out.println("c. Return to main menu");
			System.out.print("Enter your choice: ");

			char choice = getCharInput();

			switch (choice) {
			case 'a':
				bookManagement.checkOutBook();
				break;
			case 'b':
				bookManagement.checkInBook();
				;
			case 'c':
				return;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
	}

	private void searchMenu() {
		while (true) {
			System.out.println("\n--- Search ---");
			System.out.println("a. Find a member");
			System.out.println("b. Find a book");
			System.out.println("c. Return to main menu");
			System.out.print("Enter your choice: ");

			char choice = getCharInput();

			switch (choice) {
			case 'a':
				memberManagement.findMember();
				break;
			case 'b':
				bookManagement.findBook();
				break;
			case 'c':
				return;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
	}

	private int getIntInput() {
		while (!scanner.hasNextInt()) {
			System.out.println("Invalid input. Please enter a number.");
			scanner.next();
		}
		return scanner.nextInt();
	}

	private char getCharInput() {
		return scanner.next().toLowerCase().charAt(0);
	}
}
