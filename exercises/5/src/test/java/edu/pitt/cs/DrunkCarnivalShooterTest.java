package edu.pitt.cs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import gov.nasa.jpf.util.test.TestJPF;

/**
 * Code by @author Wonsun Ahn
 * 
 * <p>
 * Uses the Java Path Finder model checking tool to check DrunkCarnivalShooter
 * shoot method for all scenarios. It enumerates all possible states of the
 * targets as well as all possible target choices by the user.
 */

public class DrunkCarnivalShooterTest extends TestJPF {
	private DrunkCarnivalShooter shooter; // The game object
	private StringBuilder builder; // The string builder object
	private boolean[] targets;
	private int targetChoice; // The user inputed target choice to test (can be between 0 - 3)

	/**
	 * Sets up the test fixture.
	 */
	public void setUp() {
		targets = new boolean[4];
		/*
		 * TODO: Use the Java Path Finder Verify.getInt(int min, int max) API to
		 * generate choices for targetChoice. It should take values 0-3. Also, generate
		 * choices for targets such that each of the four elements are enumerated with a
		 * true or false value using the Verify.getBoolean() API. To see how to use the
		 * Verify API, look at:
		 * https://github.com/javapathfinder/jpf-core/wiki/Verify-API-of-JPF
		 */
		
		// Create the game
		shooter = DrunkCarnivalShooter.createInstance(InstanceType.IMPL);
		// Set up the targets in the game to reflect the targets array
		for (int i = 0; i < 4; i++) {
			if (targets[i] == false) {
				shooter.takeDownTarget(i);
			}
		}

		// Create the string builder
		builder = new StringBuilder();
	}

	/**
	 * Test case for boolean shoot(int t, StringBuilder builder).
	 * 
	 * <pre>
	 * Preconditions: targetChoice has been initialized with a target number.
	 *                shooter is instantiated with DrunkCarnivalShooter.createInstance() with preconfigured targets.
	 *                builder is instantiated with new StringBuilder().
	 * Execution steps: Call shooter.shoot(targetChoice, builder);
	 * Invariant: The number of targets which returns true on shooter.isTargetStanding(i)
	 *            where i = 0 ... 3 is equal to shooter.getRemainingTargetNum().
	 * </pre>
	 */
	@Test
	public void testShoot() {
		/*
		 * When host JVM encounters the verifyNoPropertyViolation(), it invokes the JPF
		 * VM on this test method. So there are effectively two virtual machines
		 * executing this method. The verifyNoPropertyViolation() method returns false
		 * if the executing VM is the host JVM and returns true if it is the JPF VM.
		 */
		if (verifyNoPropertyViolation() == false) {
			// This is the host JVM so return immediately.
			return;
		}
		// This is the JPF VM, so run the test case on top of it, starting from the setUp().
		setUp();

		/*
		 * Currently, it just prints out the failString to demonstrate to you all the
		 * cases considered by Java Path Finder. If you called the Verify API correctly
		 * in setUp(), you should see all combinations of targets and targetChoices:
		 * 
		 * Failure in Round #0:                         (targetChoice=0):
		 * Failure in Round #0:                    ||   (targetChoice=0):
		 * Failure in Round #0:              ||         (targetChoice=0):
		 * Failure in Round #0:              ||    ||   (targetChoice=0):
		 * Failure in Round #0:        ||               (targetChoice=0):
		 * Failure in Round #0:        ||          ||   (targetChoice=0):
		 * Failure in Round #0:        ||    ||         (targetChoice=0):
		 * Failure in Round #0:        ||    ||    ||   (targetChoice=0):
		 * Failure in Round #0:  ||                     (targetChoice=0):
		 * ...
		 * Failure in Round #0:  ||    ||          ||   (targetChoice=3):
		 * Failure in Round #0:  ||    ||    ||         (targetChoice=3):
		 * Failure in Round #0:  ||    ||    ||    ||   (targetChoice=3):
		 * 
		 * Comment out the println when you are done implementing.
		 */

		// A failstring useful to pass to assertions to get a more descriptive error.
		String failString = "Failure in " + shooter.getRoundString() + " (targetChoice=" + targetChoice + "):";
		System.out.println(failString);
		
		// TODO: Implement
	}
}
