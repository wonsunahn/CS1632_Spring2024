package edu.pitt.cs;

import java.util.ArrayList;

public class CoffeeMakerQuestImpl implements CoffeeMakerQuest {

	static final String newline = System.lineSeparator(); // Platform independent newline ("\n" or "\r\n")

	// TODO: Add more member variables and methods as needed.

	/**
	 * Constructor. Rooms are laid out from south to north, such that the
	 * first room in rooms becomes the southernmost room and the last room becomes
	 * the northernmost room. Initially, the player is at the southernmost room.
	 * 
	 * @param player Player for this game
	 * @param rooms  List of rooms in this game
	 */
	CoffeeMakerQuestImpl(Player player, ArrayList<Room> rooms) {
		// TODO: Fill in
	}

	/**
	 * Whether the game is over. The game ends when the player drinks the coffee.
	 * 
	 * @return true if the game is over, false otherwise
	 */
	public boolean isGameOver() {
		// TODO: Fill in
		return false;
	}

	/**
	 * The method returns success if and only if: 1) Th southernmost room has a
	 * north door only, 2) The northernmost room has a south door only, and 3) The
	 * rooms in the middle have both north and south doors. If there is only one
	 * room, there should be no doors.
	 * 
	 * @return true if check successful, false otherwise
	 */
	public boolean areDoorsPlacedCorrectly() {
		// TODO: Fill in
		return false;
	}

	/**
	 * Checks whether each room has a unique adjective and furnishing.
	 * 
	 * @return true if check successful, false otherwise
	 */

	public boolean areRoomsUnique() {
		// TODO: Fill in
		return false;
	}

	/**
	 * Returns the room the player is currently in. If location of player has not
	 * yet been initialized with setCurrentRoom, returns null.
	 * 
	 * @return room player is in, or null if not yet initialized
	 */
	public Room getCurrentRoom() {
		// TODO: Fill in
		return null;
	}

	/**
	 * Set the current location of the player. If room does not exist in the game,
	 * then the location of the player does not change and false is returned.
	 * 
	 * @param room the room to set as the player location
	 * @return true if successful, false otherwise
	 */
	public boolean setCurrentRoom(Room room) {
		// TODO: Fill in
		return false;
	}

	/**
	 * Get the instructions string command prompt. It returns the following prompt:
	 * " INSTRUCTIONS (N,S,L,I,D,H) > ".
	 * 
	 * @return comamnd prompt string
	 */
	public String getInstructionsString() {
		// TODO: Fill in
		return "";
	}

	/**
	 * A helper method for the "H" command. It returns the following help string:
	 * "N - Go north" + newline + "S - Go south" + newline + "L - Look and collect
	 * any items in the room" + newline +
	 * "I - Show inventory of items collected" + newline + "D - Drink coffee made
	 * from items in inventory" + newline.
	 * 
	 * @return help string
	 */
	private String getHelpString() {
		// TODO: Fill in
		return "";
	}

	/**
	 * Processes the user command given in String cmd and returns the response
	 * string. For the list of commands, please try giving the "H" command in the
	 * solution jar. Make sure you use Player.getInventoryString() whenever you need
	 * to display the inventory.
	 * 
	 * @param cmd the user command
	 * @return response string for the command
	 */
	public String processCommand(String cmd) {
		// TODO: Fill in
		return "";
	}

}
