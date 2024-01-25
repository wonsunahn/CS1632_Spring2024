package edu.pitt.cs;

import static org.mockito.Mockito.when;

import org.mockito.*;

public interface Room {
	static final String newline = System.lineSeparator(); // Platform independent newline ("\n" or "\r\n")
	
	public static Room createInstance(InstanceType type, String furnishing, String adjective, Item item,
			String northDoor, String southDoor) {
		switch (type) {
			case IMPL:
				return new RoomImpl(furnishing, adjective, item, northDoor, southDoor);
			case BUGGY:
				return new RoomBuggy(furnishing, adjective, item, northDoor, southDoor);
			case SOLUTION:
				return new RoomSolution(furnishing, adjective, item, northDoor, southDoor);
			case MOCK:
				// TODO: Fill in as needed
				return null;
			default:
				assert (false);
				return null;
		}
	}

	// WARNING: You are not allowed to change any part of the interface.
	// That means you cannot add any method nor modify any of these methods.

	public String getFurnishing();

	public String getAdjective();

	public Item getItem();

	public String getNorthDoor();

	public String getSouthDoor();

	public String getDescription();
}
