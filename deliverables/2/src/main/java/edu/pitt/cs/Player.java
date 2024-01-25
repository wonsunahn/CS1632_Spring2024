package edu.pitt.cs;

import static org.mockito.Mockito.when;

import org.mockito.*;

public interface Player {
	static final String newline = System.lineSeparator(); // Platform independent newline ("\n" or "\r\n")
	
	public static Player createInstance(InstanceType type) {
		switch (type) {
			case IMPL:
				return new PlayerImpl();
			case BUGGY:
				return new PlayerBuggy();
			case SOLUTION:
				return new PlayerSolution();
			case MOCK:
				// TODO: Fill in as needed
				return null;
			default:
				assert(false);
				return null;
		}
	}

	// WARNING: You are not allowed to change any part of the interface.
	// That means you cannot add any method nor modify any of these methods.
	
	public void addItem(Item item);

	public boolean hasItem(Item item);

	public String getInventoryString();
}
