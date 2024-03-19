package edu.pitt.cs;

/**
 * Code by @author Wonsun Ahn
 * 
 * <p>CarnivalShooter: Using arbitrary trial and shooter
 * numbers, find how many targets are hit in those trials.
 */

public interface DrunkCarnivalShooter {
	/**
	 * Returns the either DrunkCarnivalShooterImpl or DrunkCarnivalShooterBuggy instance
	 * depending on the type parameter.
	 * 
	 * @param type The type of object to be created.
	 * @return DrunkCarnivalShooter object.
	 */
	public static DrunkCarnivalShooter createInstance(InstanceType type) {
		switch (type) {
			case IMPL:
				return new DrunkCarnivalShooterImpl();
			case BUGGY:
				return new DrunkCarnivalShooterBuggy();
			default:
				assert (false);
				return null;
		}
	}

	public String getRoundString();

	public boolean shoot(int t, StringBuilder builder);

	public boolean takeDownTarget(int t);

	public boolean isTargetStanding(int t);

	public int getRemainingTargetNum();
}
