package edu.pitt.cs;

import java.util.ArrayList;
import java.util.Scanner;

public class RentACatImpl implements RentACat {

	private ArrayList<Cat> cats = new ArrayList<Cat>();

	/**
	 * Return a cat. This should call the .returnCat() method on the cat for the
	 * passed-in cat id. If the cat with the id exists in the list of cats and has
	 * been rented out, then this method should return true after calling
	 * .returnCat() on that cat. Otherwise, the method should return false.
	 * 
	 * @param id the ID of the cat to rent
	 * @return true if cat exists and was rented out, false otherwise
	 */

	public boolean returnCat(int id) {
		// TODO: Fill in
		return false;
	}

	/**
	 * Rent a cat. This should call the .rentCat() method on the cat for the
	 * passed-in cat id. If the cat with the id exists in the list of cats and has
	 * *not* been rented out, then this method should return true after calling
	 * .rentCat() on that cat. Otherwise, the method should return false.
	 * 
	 * @param id the ID of the cat to rent
	 * @return true if cat exists and was not rented out, false otherwise
	 */

	public boolean rentCat(int id) {
		// TODO: Fill in
		return false;
	}

	/**
	 * Rename a cat. This calls the .renameCat(String) method on the cat for the
	 * passed-in cat id, if the cat exists, and then returns true. If the cat does
	 * not exist, the method returns false.
	 * 
	 * @param id the ID of the cat to rename
	 * @return true if cat exists, false otherwise
	 */

	public boolean renameCat(int id, String name) {
		// TODO: Fill in
		return false;
	}

	/**
	 * Create a String list from the list of cats using the .toString() method of
	 * each NON-RENTED Cat object in the list. That is, it should only add cats who
	 * are available to be rented. These cats should be separated by "\n" characters
	 * (line feeds). Example: ID 1. Jennyanydots ID 2. Old Deuteronomy ID 3.
	 * Mistoffelees
	 * 
	 * @return "\n"-delimited list of rentable cats
	 */

	public String listCats() {
		// TODO: Fill in
		return "WRITE CODE FOR THIS";
	}

	/**
	 * Given an id, return a reference to the specified cat if a cat with that ID
	 * exists. Return null if no cat of that ID exists in the list.
	 * 
	 * @param int id ID of cat to search for
	 * @return Cat searched for if exists, null otherwise
	 */

	private Cat getCat(int id) {

		// null check
		if (cats == null) {
			return null;
		}

		// Loop through every cat in the cat list
		for (Cat c : cats) {
			// If we found a cat whose id matches the id
			// of the argument, then we have a match and
			// can thus return a reference to that cat
			if (c.getId() == id) {
				return c;
			}
		}
		// If we get all the way through the list and did
		// not find a cat whose ID matches the passed-in
		// ID, then the cat is not in the list
		System.out.println("Invalid cat ID.");
		return null;

	}

	/**
	 * Add a cat to the list of cats.
	 * 
	 * @param c the Cat to add
	 */

	public void addCat(Cat c) {
		cats.add(c);
	}

	/**
	 * Main method
	 * 
	 * @param args - IGNORED, kept for compatibility
	 */
	public static void main(String[] args) {
		RentACat rc = new RentACatImpl();

		rc.addCat(new CatImpl(1, "Jennyanydots"));
		rc.addCat(new CatImpl(2, "Old Deuteronomy"));
		rc.addCat(new CatImpl(3, "Mistoffelees"));

		Scanner sc = new Scanner(System.in);

		int option;
		boolean keepGoing = true;

		while (keepGoing) {
			System.out.print("Option [1,2,3,4,5] > ");
			try {
				option = sc.nextInt();
				switch (option) {
					case 1:
						System.out.println("Cats for Rent");
						System.out.print(rc.listCats());
						break;
					case 2:
						System.out.print("Rent which cat? > ");
						try {
							int catIdToRent = sc.nextInt();
							rc.rentCat(catIdToRent);
						} catch (Exception ex) {
							System.out.println("Invalid cat ID.");
							sc.next();
							break;
						}
						break;
					case 3:
						System.out.print("Return which cat? > ");
						try {
							int catIdToReturn = sc.nextInt();
							rc.returnCat(catIdToReturn);
						} catch (Exception ex) {
							System.out.println("Invalid cat ID.");
							sc.next();
							break;
						}
						break;
					case 4:
						System.out.print("Rename which cat? > ");
						try {
							int catIdToRename = sc.nextInt();
							sc.nextLine(); // to flush the previous line
							System.out.print("What is the new name? > ");
							String newName = sc.nextLine();
							rc.renameCat(catIdToRename, newName);
						} catch (Exception ex) {
							System.out.println("Invalid cat ID.");
							sc.next();
							break;
						}
						break;
					case 5:
						keepGoing = false;
						break;
					default:
						throw new NumberFormatException();
				}
			} catch (Exception nfex) {
				System.err.println("Please enter 1, 2, 3, 4 or 5");
				System.err.println("1. See list of cats for rent");
				System.err.println("2. Rent a cat to a customer");
				System.err.println("3. Return a cat from a customer");
				System.err.println("4. Rename a cat");
				System.err.println("5. Quit");
				// Clear out the non-int in the scanner
				sc.next();
			}
		}

		System.out.println("Closing up shop for the day!");

		sc.close();
	}
}
