package BIT504_A2;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFileData {

	private static ReadFileData instance;

	// Private constructor to prevent instantiation
	private ReadFileData() {
	}

	// Method to access instance 
	public static ReadFileData getInstance() {
		if (instance == null) {
			instance = new ReadFileData();
		}
		return instance;
	}

	public static List<Book> readBooks(String filename) {
		List<Book> books = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;

			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				if (data.length == 7) {

					String id = data[0];
					String isbn = data[1];
					String title = data[2];
					String author = data[3];
					String publicationDate = data[4];
					String genre = data[5];
					int ageRating = Integer.parseInt(data[6]);

					Book book = new Book(id, isbn, title, author, publicationDate, genre, ageRating, false);

					books.add(book);
				}
			}
			System.out.println("Successfully loaded Books.");
		} catch (IOException e) {
			System.out.println("Error loading books.txt: " + e.getMessage());
		}
		return books;
	}

	public static List<Member> readMembers(String filename) {
		List<Member> members = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;

			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");

				if (data.length == 4) {

					String id = data[0];
					String firstName = data[1];
					String lastName = data[2];
					int age = Integer.parseInt(data[3]);

					Member member = new Member(id, firstName, lastName, age);
					members.add(member);
				}
			}
			System.out.println("Successfully loaded Members.");
		} catch (IOException e) {
			System.out.println("Error loading members.txt: " + e.getMessage());
		}
		return members;
	}
}
