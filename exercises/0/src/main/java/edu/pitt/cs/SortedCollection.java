package edu.pitt.cs;

import java.util.Arrays;
//TODO: Import libraries as needed
import java.util.NoSuchElementException;

public class SortedCollection {
	// TODO: Add member variables or methods as needed

	private int[] numbers; // Array to store numbers in the collection
	private int size; // Number of elements in the collection

	public SortedCollection() {
		numbers = new int[10]; // initial capacity is 10 (arbitrary)
		size = 0; // initial size is 0
	}

	/**
	 * Adds the number n to the collection.
	 * 
	 * @param n the number to add to the collection
	 * @return always returns true
	 */
	public boolean add(int n) {
		// TODO: Implement

		ensureCapacity(); // ensure capacity before adding a new element

		// find the index to insert the new number while maintaining sorted order
		int index = 0;
		while (index < size && numbers[index] < n) {
			index++;
		}

		// shift elements to make space for the new number
		for (int i = size; i > index; i--) {
			numbers[i] = numbers[i - 1];
		}

		// insert the new number
		numbers[index] = n;
		size++;

		return true;
	}

	private void ensureCapacity() {
		// double the array size if it's full
		if (size == numbers.length) {
			numbers = Arrays.copyOf(numbers, size * 2);
		}
	}

	/**
	 * Removes the smallest number in the collection and returns it.
	 * If the collection is empty, throws a NoSuchElementException.
	 * 
	 * @return the smallest number in the collection
	 */
	public int remove() throws NoSuchElementException {
		// TODO: Implement

		if (size == 0) {
			throw new NoSuchElementException("Collection is empty");
		}

		// remove the smallest number (at index 0) and shift the remaining elements
		int smallest = numbers[0];
		for (int i = 0; i < size - 1; i++) {
			numbers[i] = numbers[i + 1];
		}
		size--;

		return smallest;
	}

	/**
	 * Prints usage information.
	 */
	public static void showUsage() {
		System.out.println("Usage: java SortedCollection [num1] [num2] [num3] ...");
	}

	/**
	 * Main method. Receives a list of numbers as commandline arguments and prints
	 * out the list in sorted order from smallest to largest.
	 * 
	 * @param args commandline arguments; see showUsage() for detailed information
	 */
	public static void main(String[] args) {
		SortedCollection collection = new SortedCollection();

		if (args.length == 0) {
			showUsage();
			return;
		}

		// TODO: add numbers in commandline arguments to collection using the add(int)
		// method.
		// If any commandline argument is not a number, call showUsage() and return.

		// Add numbers from command line arguments to the collection using the add(int)
		// method.
		// If any command line argument is not a number, call showUsage() and return.
		for (String arg : args) {
			try {
				int num = Integer.parseInt(arg);
				collection.add(num);
			} catch (NumberFormatException e) {
				showUsage();
				return;
			}
		}

		System.out.print("sorted: ");
		for (int i = 0; i < args.length; i++) {
			int num = collection.remove();
			System.out.print(num + " ");
		}
		System.out.println();
	}
}
