package edu.pitt.cs;

import java.util.ArrayList;

import org.mockito.Mockito;

public interface CoffeeMakerQuest {
	public static CoffeeMakerQuest createInstance(InstanceType type, Player player, ArrayList<Room> rooms) {
		switch (type) {
			case IMPL:
				return new CoffeeMakerQuestImpl(player, rooms);
			case BUGGY:
				return new CoffeeMakerQuestBuggy(player, rooms);
			case SOLUTION:
				return new CoffeeMakerQuestSolution(player, rooms);
			case MOCK:
				// TODO: Fill in as needed
				return null;
			default:
				assert (false);
				return null;
		}
	}
	
	// Public interface of CoffeeMakerQuest
	public boolean isGameOver();
	public boolean areDoorsPlacedCorrectly();
	public boolean areRoomsUnique();
	public Room getCurrentRoom();
	public boolean setCurrentRoom(Room room);
	public String getInstructionsString();
	public String processCommand(String cmd);
}