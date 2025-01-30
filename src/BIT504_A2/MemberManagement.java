package BIT504_A2;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Scanner;

public class MemberManagement {
	private List<Member> members;
	private List<Book> books;
	private Scanner scanner = new Scanner(System.in);

	// Constructor
	public MemberManagement(List<Member> members, List<Book> books) {
		this.members = members;
		this.books = books;
	}

	// Display all system members
	public void displayAllSystemMembers() {
		showTableHeader("All Members");
		showMemberTable(members);
	}

	// Add a new member
	public void addNewMember() {
		System.out.println("\nAdd New Member");
		System.out.println("-".repeat(30));

		String id = createId();
		String firstName = getInput("Enter first name: ");
		String lastName = getInput("Enter last name: ");
		int age = getAge();

		Member newMember = new Member(id, firstName, lastName, age);
		members.add(newMember);
		System.out.println("New member added successfully!");
	}

	// Get the last record id and add 1 for new id
	private String createId() {
		if (members.isEmpty()) {
			return "1";
		}

		Member lastMemberAdded = members.get(members.size() - 1);
		int lastId = Integer.parseInt(lastMemberAdded.getId());

		return String.valueOf(lastId + 1);
	}

	// get user input
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

	//
	private int getAge() {
		while (true) {
			System.out.print("Enter age (0-125): ");
			try {
				int age = Integer.parseInt(scanner.nextLine().trim());
				if (age < 0 || age > 125) {
					System.out.println("Age must be between 0 and 125.");
				} else {
					return age;
				}
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid number for age.");
			}
		}
	}

	public void findMember() {
		System.out.println("\nFind a Member");
		System.out.println("-".repeat(30));

		String lastName = getInput("Enter member's last name (or part of it): ");
		List<Member> foundMembers = members.stream()
				.filter(member -> member.getLastName().toLowerCase().contains(lastName.toLowerCase()))
				.collect(Collectors.toList());

		if (foundMembers.isEmpty()) {
			System.out.println("There are no members found with the following last name containing '" + lastName + "'.");
		} else {
			System.out.println("Found " + foundMembers.size() + " member(s):");
			for (Member member : foundMembers) {
				displayMemberInfo(member);
			}
		}
	}

	private void displayMemberInfo(Member member) {
		System.out.println("\nMember Information:");
		System.out.println("ID: " + member.getId());
		System.out.println("Name: " + member.getFirstName() + " " + member.getLastName());
		System.out.println("Age: " + member.getAge());

		List<Book> borrowedBooks = books.stream().filter(book -> member.getId().equals(book.getBorrowedMemberId()))
				.collect(Collectors.toList());

		if (borrowedBooks.isEmpty()) {
			System.out.println("Currently there are no borrowed books:");
		} else {
			System.out.println("Currently the following books are borrowed:");
			for (Book book : borrowedBooks) {
				System.out.println("- " + book.getTitle() + " (ID: " + book.getId() + ")");
			}
		}
		System.out.println("-".repeat(30));
	}

	// Display the table header
	private void showTableHeader(String tableName) {
		System.out.println("\n" + tableName);
		System.out.println("-".repeat(80));
		System.out.printf("%-5s | %-20s | %-20s | %s%n", "ID", "First Name", "Last Name", "Age");
		System.out.println("-".repeat(80));
	}

	// Display member table
	private void showMemberTable(List<Member> memberList) {
		for (Member member : memberList) {
			System.out.printf("%-5s | %-20s | %-20s | %d%n", member.getId(), member.getFirstName(),
					member.getLastName(), member.getAge());
		}
		System.out.println("-".repeat(80));
	}
}