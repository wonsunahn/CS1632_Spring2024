package edu.pitt.cs;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.mockito.*;
import static org.mockito.Mockito.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlayerUnitTest {

	static final String newline = System.lineSeparator(); // Platform independent newline ("\n" or "\r\n")

	Player player;

	@Before
	public void setup() {
		// 1. Create a Player with no items (no coffee, no cream, no sugar) and assign to player.
		// TODO: Fill in 
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test that player has been constructed properly.
	 * 
	 * <pre>
	 * Preconditions: player has been created.
	 * Execution steps: None.
	 * Postconditions: player does not have coffee, cream, nor sugar.
	 *                 player.getInventoryString() returns: "YOU HAVE NO COFFEE!" + newline +
	 *                    "YOU HAVE NO CREAM!" + newline + "YOU HAVE NO SUGAR!" + newline.
	 * </pre>
	 */
	@Test
	public void testConstructor() {
		// TODO: Fill in
	}

	/**
	 * Test adding coffee to player.
	 * 
	 * <pre>
	 * Preconditions: player has been created.
	 * Execution steps: Call player.addItem(Item.COFFEE).
	 * Postconditions: player has coffee but does not have cream and sugar.
	 *                 player.getInventoryString() returns: "You have a cup of delicious coffee." + newline +
	 *                    "YOU HAVE NO CREAM!" + newline + "YOU HAVE NO SUGAR!" + newline.
	 * </pre>
	 */
	@Test
	public void testAddCoffee() {
		// TODO: Fill in
	}

	/**
	 * Test adding coffee and cream to player.
	 * 
	 * <pre>
	 * Preconditions: player has been created.
	 * Execution steps: Call player.addItem(Item.COFFEE).
	 *                  Call player.addItem(Item.CREAM).
	 * Postconditions: player has coffee and cream but does not have sugar.
	 *                 player.getInventoryString() returns: "You have a cup of delicious coffee." + newline +
	 *                    "You have some fresh cream." + newline + "YOU HAVE NO SUGAR!" + newline.
	 * </pre>
	 */
	@Test
	public void testAddCoffeeCream() {
		// TODO: Fill in
	}

	/**
	 * Test adding coffee, cream, and sugar to player.
	 * 
	 * <pre>
	 * Preconditions: player has been created.
	 * Execution steps: Call player.addItem(Item.COFFEE).
	 *                  Call player.addItem(Item.CREAM).
	 *                  Call player.addItem(Item.SUGAR).
	 * Postconditions: player has coffee, cream. and sugar.
	 *                 player.getInventoryString() returns: "You have a cup of delicious coffee." + newline +
	 *                    "You have some fresh cream." + newline + "You have some tasty sugar." + newline.
	 * </pre>
	 */
	@Test
	public void testAddCoffeeCreamSugar() {
		// TODO: Fill in
	}

	// TODO: Put in more unit tests of your own making if you need to improve coverage!
	
}