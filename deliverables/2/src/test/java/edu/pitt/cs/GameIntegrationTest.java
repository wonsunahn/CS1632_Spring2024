package edu.pitt.cs;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.mockito.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GameIntegrationTest {

	static final String newline = System.lineSeparator(); // Platform independent newline ("\n" or "\r\n")

	ByteArrayOutputStream out; // Output stream for testing system output
	PrintStream stdout; // Print stream to hold the original stdout stream
	InputStream stdin = System.in; // Input stream to hold the original stdin stream
	
	static final String loseNoItemsOutput = "Coffee Maker Quest 1.0" + newline + //
			newline + //
			"You see a Small room." + newline + //
			"It has a Quaint sofa." + newline + //
			"A Magenta door leads North." + newline + //
			newline + //
			" INSTRUCTIONS (N,S,L,I,D,H) > " + newline + //
			"YOU HAVE NO COFFEE!" + newline + //
			"YOU HAVE NO CREAM!" + newline + //
			"YOU HAVE NO SUGAR!" + newline + //
			newline + //
			"You drink thin air and can only dream of coffee. You cannot study." + newline + //
			"You lose!" + newline + //
			newline;

	static final String loseWithCream = "Coffee Maker Quest 1.0" + newline + //
			newline + //
			"You see a Small room." + newline + //
			"It has a Quaint sofa." + newline + //
			"A Magenta door leads North." + newline + //
			newline + //
			" INSTRUCTIONS (N,S,L,I,D,H) > " + newline + //
			"There might be something here..." + newline + //
			"You found some creamy cream!" + newline + //
			newline + //
			"You see a Small room." + newline + //
			"It has a Quaint sofa." + newline + //
			"A Magenta door leads North." + newline + //
			newline + //
			" INSTRUCTIONS (N,S,L,I,D,H) > " + newline + //
			"YOU HAVE NO COFFEE!" + newline + //
			"You have some fresh cream." + newline + //
			"YOU HAVE NO SUGAR!" + newline + //
			newline + //
			"You refuse to drink this half-made sludge. You cannot study." + newline + //
			"You lose!" + newline + //
			newline;

	static final String win = "Coffee Maker Quest 1.0" + newline + //
			newline + //
			"You see a Small room." + newline + //
			"It has a Quaint sofa." + newline + //
			"A Magenta door leads North." + newline + //
			newline + //
			" INSTRUCTIONS (N,S,L,I,D,H) > " + newline + //
			"There might be something here..." + newline + //
			"You found some creamy cream!" + newline + //
			newline + //
			"You see a Small room." + newline + //
			"It has a Quaint sofa." + newline + //
			"A Magenta door leads North." + newline + //
			newline + //
			" INSTRUCTIONS (N,S,L,I,D,H) > " + newline + //
			newline + //
			"You see a Funny room." + newline + //
			"It has a Sad record player." + newline + //
			"A Beige door leads North." + newline + //
			"A Massive door leads South." + newline + //
			newline + //
			" INSTRUCTIONS (N,S,L,I,D,H) > " + newline + //
			newline + //
			"You see a Refinanced room." + newline + //
			"It has a Tight pizza." + newline + //
			"A Dead door leads North." + newline + //
			"A Smart door leads South." + newline + //
			newline + //
			" INSTRUCTIONS (N,S,L,I,D,H) > " + newline + //
			"There might be something here..." + newline + //
			"You found some caffeinated coffee!" + newline + //
			newline + //
			"You see a Refinanced room." + newline + //
			"It has a Tight pizza." + newline + //
			"A Dead door leads North." + newline + //
			"A Smart door leads South." + newline + //
			newline + //
			" INSTRUCTIONS (N,S,L,I,D,H) > " + newline + //
			newline + //
			"You see a Dumb room." + newline + //
			"It has a Flat energy drink." + newline + //
			"A Vivacious door leads North." + newline + //
			"A Slim door leads South." + newline + //
			newline + //
			" INSTRUCTIONS (N,S,L,I,D,H) > " + newline + //
			newline + //
			"You see a Bloodthirsty room." + newline + //
			"It has a Beautiful bag of money." + newline + //
			"A Purple door leads North." + newline + //
			"A Sandy door leads South." + newline + //
			newline + //
			" INSTRUCTIONS (N,S,L,I,D,H) > " + newline + //
			newline + //
			"You see a Rough room." + newline + //
			"It has a Perfect air hockey table." + newline + //
			"A Minimalist door leads South." + newline + //
			newline + //
			" INSTRUCTIONS (N,S,L,I,D,H) > " + newline + //
			"There might be something here..." + newline + //
			"You found some sweet sugar!" + newline + //
			newline + //
			"You see a Rough room." + newline + //
			"It has a Perfect air hockey table." + newline + //
			"A Minimalist door leads South." + newline + //
			newline + //
			" INSTRUCTIONS (N,S,L,I,D,H) > " + newline + //
			"You have a cup of delicious coffee." + newline + //
			"You have some fresh cream." + newline + //
			"You have some tasty sugar." + newline + //
			newline + //
			"You drink the beverage and are ready to study!" + newline + //
			"You win!" + newline + //
			newline;

	@Before
	public void setup() {
		// 1. Redirect system output from stdout to the "out" stream
		stdout = System.out; // Make a back up of the original stdout (DON'T REMOVE!)
		// TODO: Fill in
		out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		// 2. Make a back up of stdin
		stdin = System.in;
	}

	@After
	public void tearDown() {
		// 1. Redirect system output to the original stdout
		System.setOut(stdout);

		// 2. Redirect system input to the original stdin
		System.setIn(stdin);
	}

	/**
	 * Test case for immediately drinking the coffee and losing.
	 * 
	 * <pre>
	 * Preconditions: System.in is fed with input string: "D" + newline.
	 * Execution steps: Call Game.main(new String[0]).
	 * Postconditions: System output is equal to the loseNoItemsOutput string.
	 * </pre>
	 */
	@Test
	public void testLoseNoItems() {
		// TODO: Fill in
	}

	/**
	 * Test case for picking up cream and then losing.
	 * 
	 * <pre>
	 * Preconditions: System.in is fed with input string: "L" + newline + "D" + newline.
	 * Execution steps: Call Game.main(new String[0]).
	 * Postconditions: System output is equal to the loseWithCream string.
	 * </pre>
	 */
	@Test
	public void testLoseWithCream() {
		// TODO: Fill in
	}

	/**
	 * Test case for picking up all items and winning.
	 * 
	 * <pre>
	 * Preconditions: System.in is fed with input string:
	 *                "L" + newline + "N" + newline + "N" + newline + "L" + newline + "N" + newline + "N" + newline + "N" + newline + "L" + newline + "D" + newline.
	 * Execution steps: Call Game.main(new String[0]).
	 * Postconditions: System output is equal to the win string.
	 * </pre>
	 */
	@Test
	public void testWin() {
		// TODO: Fill in
	}
}