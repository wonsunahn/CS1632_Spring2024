package edu.pitt.cs;

public class RoomImpl implements Room {

	// TODO: Add more member variables and methods as needed.
	
	static final String newline = System.lineSeparator(); // Platform independent newline ("\n" or "\r\n")

	/**
	 * Constructor. The northDoor or the southDoor can be null if there no doors leading north or south.
	 * 
	 * @param furnishing Furnishing in the room
	 * @param adjective Adjective describing the room
	 * @param item Item present in the room
	 * @param northDoor Description of north door (null if there is no north door)
	 * @param southDoor Description of south door (null if there is no south door)
	 */
	public RoomImpl(String furnishing, String adjective, Item item, String northDoor, String southDoor) {
		// TODO: Fill in
	}
	
	public String getFurnishing() {
		// TODO: Fill in
		return "";
	}

	public String getAdjective() {
		// TODO: Fill in
		return "";
	}

	public Item getItem() {
		// TODO: Fill in
		return Item.NONE;
	}

	public String getNorthDoor() {
		// TODO: Fill in
		return "";
	}

	public String getSouthDoor() {
		// TODO: Fill in
		return "";
	}
	
	public String getDescription() {
		// TODO: Fill in
		return "";
	}
}
