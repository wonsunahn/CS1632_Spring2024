package edu.pitt.cs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Random;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;

/**
 * Code by @author Wonsun Ahn. Copyright Spring 2024.
 * 
 * <p>
 * Does integration testing on BeanCounterLogic. Makes tests deterministic by
 * seeding the random number generator for lucky beans and mocking it for
 * skilled beans.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BeanCounterLogicTest {

	private ByteArrayOutputStream out = new ByteArrayOutputStream();
	private PrintStream stdout;

	private final int slotCount = 5;
	private final int beanCount = 3;

	private BeanCounterLogic logic;
	private Bean[] luckyBeans;
	private Bean[] skilledBeans;

	/**
	 * Returns the number of in-flight beans that are streaming down the machine.
	 * 
	 * @return number of beans in-flight in the machine
	 */
	private int getInFlightBeanCount() {
		int inFlight = 0;
		for (int yPos = 0; yPos < slotCount; yPos++) {
			int xPos = logic.getInFlightBeanXPos(yPos);
			if (xPos != BeanCounterLogic.NO_BEAN_IN_YPOS) {
				inFlight++;
			}
		}
		return inFlight;
	}

	/**
	 * Returns the number of beans that are collected in the slots at the bottom.
	 * 
	 * @return number of beans in slots in the machine
	 */
	private int getInSlotsBeanCount() {
		int inSlots = 0;
		for (int i = 0; i < slotCount; i++) {
			inSlots += logic.getSlotBeanCount(i);
		}
		return inSlots;
	}

	/**
	 * Sets up the JUnit test fixture.
	 */
	@Before
	public void setUp() {
		logic = BeanCounterLogic.createInstance(InstanceType.IMPL, slotCount);
		luckyBeans = new Bean[beanCount];
		skilledBeans = new Bean[beanCount];

		// TODO: Use Bean.createInstance to populate the luckyBeans array with beans in luck mode for slotCount.
		// For each bean, pass in a NEW rand seeded with the number "42 + i" where i is the bean index.
		// This is necessary to make each bean act deterministically but also differently from each other.
		
		
		// TODO: Use Bean.createInstance to populate the skilledBeans array with beans in skilled mode for slotCount.
		// For skilledBeans[0], enforce a skill level of 1 by having rand.nextGaussian() return -1.0.
		// For the rest, enforce a skill level of 4 by having rand.nextGaussian() return 2.0.
		
	}

	/**
	 * Test reset(Bean[]).
	 * 
	 * <pre>
	 * Preconditions: logic, luckyBeans, skilledBeans initialized in setUp() as described.
	 * Execution steps: Pour lucky beans into machine by calling logic.reset(luckyBeans).
	 * Postconditions: logic.getRemainingBeanCount() returns 2.
	 *                 getInSlotsBeanCount() returnd 0.
	 *                 logic.getInFlightBeanXPos(0) returns 0.
	 *                 For all other i, logic.getInFlightBeanXPos(i) returns BeanCounterLogic.NO_BEAN_IN_YPOS.
	 * </pre>
	 */
	@Test
	public void testReset() {
		// TODO: Implement
	}

	/**
	 * Test calling advanceStep() in luck mode once.
	 * 
	 * <pre>
	 * Preconditions: logic, luckyBeans, skilledBeans initialized in setUp() as described.
	 * Execution steps: Pour lucky beans into machine by calling logic.reset(luckyBeans).
	 *                  Call logic.advanceStep() once.
	 * Postconditions: logic.getRemainingBeanCount() returns 1.
	 *                 getInSlotsBeanCount() returnd 0.
	 *                 logic.getInFlightBeanXPos(0) returns 0.
	 *                 logic.getInFlightBeanXPos(1) returns 0.
	 *                 For all other i, logic.getInFlightBeanXPos(i) returns BeanCounterLogic.NO_BEAN_IN_YPOS.
	 * </pre>
	 */
	@Test
	public void testAdvanceStepOnceLuckMode() throws BeanOutOfBoundsException {
		// TODO: Implement
	}

	/**
	 * Test calling advanceStep() in luck mode twice.
	 * 
	 * <pre>
	 * Preconditions: logic, luckyBeans, skilledBeans initialized in setUp() as described.
	 * Execution steps: Pour lucky beans into machine by calling logic.reset(luckyBeans).
	 *                  Call logic.advanceStep() twice.
	 * Postconditions: logic.getRemainingBeanCount() returns 0.
	 *                 getInSlotsBeanCount() returnd 0.
	 *                 logic.getInFlightBeanXPos(0) returns 0.
	 *                 logic.getInFlightBeanXPos(1) returns 1.
	 *                 logic.getInFlightBeanXPos(2) returns 1.
	 *                 For all other i, logic.getInFlightBeanXPos(i) returns BeanCounterLogic.NO_BEAN_IN_YPOS.
	 * </pre>
	 */
	@Test
	public void testAdvanceStepTwiceLuckMode() throws BeanOutOfBoundsException {
		// TODO: Implement
	}

	/**
	 * Test calling advanceStep() in luck mode thrice.
	 * 
	 * <pre>
	 * Preconditions: logic, luckyBeans, skilledBeans initialized in setUp() as described.
	 * Execution steps: Pour lucky beans into machine by calling logic.reset(luckyBeans).
	 *                  Call logic.advanceStep() thrice.
	 * Postconditions: logic.getRemainingBeanCount() returns 0.
	 *                 getInSlotsBeanCount() returnd 0.
	 *                 logic.getInFlightBeanXPos(1) returns 1.
	 *                 logic.getInFlightBeanXPos(2) returns 2.
	 *                 logic.getInFlightBeanXPos(3) returns 1.
	 *                 For all other i, logic.getInFlightBeanXPos(i) returns BeanCounterLogic.NO_BEAN_IN_YPOS.
	 * </pre>
	 */
	@Test
	public void testAdvanceStepThriceLuckMode() throws BeanOutOfBoundsException {
		// TODO: Implement
	}

	/**
	 * Test calling advanceStep() in luck mode 4 times.
	 * 
	 * <pre>
	 * Preconditions: logic, luckyBeans, skilledBeans initialized in setUp() as described.
	 * Execution steps: Pour lucky beans into machine by calling logic.reset(luckyBeans).
	 *                  Call logic.advanceStep() 4 times.
	 * Postconditions: logic.getRemainingBeanCount() returns 0.
	 *                 getInSlotsBeanCount() returnd 0.
	 *                 logic.getInFlightBeanXPos(2) returns 1.
	 *                 logic.getInFlightBeanXPos(3) returns 2.
	 *                 logic.getInFlightBeanXPos(4) returns 2.
	 *                 For all other i, logic.getInFlightBeanXPos(i) returns BeanCounterLogic.NO_BEAN_IN_YPOS.
	 * </pre>
	 */
	@Test
	public void testAdvanceStep4TimesLuckMode() throws BeanOutOfBoundsException {
		// TODO: Implement
	}

	/**
	 * Test calling advanceStep() in luck mode 5 times.
	 * 
	 * <pre>
	 * Preconditions: logic, luckyBeans, skilledBeans initialized in setUp() as described.
	 * Execution steps: Pour lucky beans into machine by calling logic.reset(luckyBeans).
	 *                  Call logic.advanceStep() 5 times.
	 * Postconditions: logic.getRemainingBeanCount() returns 0.
	 *                 logic.getInFlightBeanXPos(3) returns 2.
	 *                 logic.getInFlightBeanXPos(4) returns 2.
	 *                 For all other i, logic.getInFlightBeanXPos(i) returns BeanCounterLogic.NO_BEAN_IN_YPOS.
	 *                 logic.getSlotBeanCount(2) returns 1.
	 *                 For all other i, logic.getSlotBeanCount(i) returns 0.
	 * </pre>
	 */
	@Test
	public void testAdvanceStep5TimesLuckMode() throws BeanOutOfBoundsException {
		// TODO: Implement
	}

	/**
	 * Test calling advanceStep() in luck mode 6 times.
	 * 
	 * <pre>
	 * Preconditions: logic, luckyBeans, skilledBeans initialized in setUp() as described.
	 * Execution steps: Pour lucky beans into machine by calling logic.reset(luckyBeans).
	 *                  Call logic.advanceStep() 6 times.
	 * Postconditions: logic.getRemainingBeanCount() returns 0.
	 *                 logic.getInFlightBeanXPos(4) returns 3.
	 *                 For all other i, logic.getInFlightBeanXPos(i) returns BeanCounterLogic.NO_BEAN_IN_YPOS.
	 *                 logic.getSlotBeanCount(2) returns 2.
	 *                 For all other i, logic.getSlotBeanCount(i) returns 0.
	 * </pre>
	 */
	@Test
	public void testAdvanceStep6TimesLuckMode() throws BeanOutOfBoundsException {
		// TODO: Implement
	}

	/**
	 * Test calling advanceStep() in luck mode 7 times.
	 * 
	 * <pre>
	 * Preconditions: logic, luckyBeans, skilledBeans initialized in setUp() as described.
	 * Execution steps: Pour lucky beans into machine by calling logic.reset(luckyBeans).
	 *                  Call logic.advanceStep() 7 times.
	 * Postconditions: logic.getRemainingBeanCount() returns 0.
	 *                 getInFlightBeanCount() returns 0.
	 *                 logic.getSlotBeanCount(2) returns 2.
	 *                 logic.getSlotBeanCount(3) returns 1.
	 *                 For all other i, logic.getSlotBeanCount(i) returns 0.
	 * </pre>
	 */
	@Test
	public void testAdvanceStep7TimesLuckMode() throws BeanOutOfBoundsException {
		// TODO: Implement
	}

	/**
	 * Test calling advanceStep() in skill mode.
	 * 
	 * <pre>
	 * Preconditions: logic, luckyBeans, skilledBeans initialized in setUp() as described.
	 * Execution steps: Pour skilled beans into machine by calling logic.reset(skilledBeans).
	 *                  Call logic.advanceStep() 7 times.
	 * Postconditions: logic.getRemainingBeanCount() returns 0.
	 *                 getInFlightBeanCount() returns 0.
	 *                 logic.getSlotBeanCount(1) returns 1.
	 *                 logic.getSlotBeanCount(4) returns 2.
	 *                 For all other i, logic.getSlotBeanCount(i) returns 0.
	 * </pre>
	 */
	@Test
	public void testAdvanceStepSkillMode() throws BeanOutOfBoundsException {
		// TODO: Implement
	}

	/**
	 * Test calling lowerHalf() in skill mode.
	 * 
	 * <pre>
	 * Preconditions: logic, luckyBeans, skilledBeans initialized in setUp() as described.
	 * Execution steps: Pour skilled beans into machine by calling logic.reset(skilledBeans).
	 *                  Call logic.advanceStep() until it returns false.
	 *                  Call logic.lowerHalf().
	 * Postconditions: logic.getSlotBeanCount(1) returns 1.
	 *                 logic.getSlotBeanCount(4) returns 1.
	 *                 For all other i, logic.getSlotBeanCount(i) returns 0.
	 * </pre>
	 */
	@Test
	public void testLowerHalf() throws BeanOutOfBoundsException {
		// TODO: Implement
	}

	/**
	 * Test calling upperHalf() in skill mode.
	 * 
	 * <pre>
	 * Preconditions: logic, luckyBeans, skilledBeans initialized in setUp() as described.
	 * Execution steps: Pour skilled beans into machine by calling logic.reset(skilledBeans).
	 *                  Call logic.advanceStep() until it returns false.
	 *                  Call logic.upperHalf().
	 * Postconditions: logic.getSlotBeanCount(4) returns 2.
	 *                 For all other i, logic.getSlotBeanCount(i) returns 0.
	 * </pre>
	 */
	@Test
	public void testUpperHalf() throws BeanOutOfBoundsException {
		// TODO: Implement
	}

	/**
	 * Test calling repeat() in skill mode.
	 * 
	 * <pre>
	 * Preconditions: logic, luckyBeans, skilledBeans initialized in setUp() as described.
	 * Execution steps: Pour skilled beans into machine by calling logic.reset(skilledBeans).
	 *                  Call logic.advanceStep() until it returns false.
	 *                  Call logic.repeat().
	 *                  Call logic.advanceStep() until it returns false.
	 * Postconditions: logic.getRemainingBeanCount() returns 0.
	 *                 getInFlightBeanCount() returns 0.
	 *                 logic.getSlotBeanCount(1) returns 1.
	 *                 logic.getSlotBeanCount(4) returns 2.
	 *                 For all other i, logic.getSlotBeanCount(i) returns 0.
	 * </pre>
	 */
	@Test
	public void testRepeat() throws BeanOutOfBoundsException {
		// TODO: Implement
	}

	/**
	 * Test calling getAverageSlotBeanCount() in luck mode.
	 * 
	 * <pre>
	 * Preconditions: logic, luckyBeans, skilledBeans initialized in setUp() as described.
	 * Execution steps: Pour lucky beans into machine by calling logic.reset(luckyBeans).
	 *                  Call logic.advanceStep() until it returns false.
	 *                  Call logic.getAverageSlotBeanCount().
	 * Postconditions: return value is 2.3333333333333333 within a difference delta of 0.001
	 * </pre>
	 */
	@Test
	public void testGetAverageSlotBeanCount() throws BeanOutOfBoundsException {
		// TODO: Implement
	}

	/**
	 * Test main(String[] args).
	 * 
	 * <pre>
	 * Preconditions: None.
	 * Execution steps: Call BeanCounterLogicImpl.main("10", "500", "luck").
	 * Postconditions: There are two lines of output.
	 *             There are 10 slot counts on the second line of output.
	 *             The sum of the 10 slot counts is equal to 500.
	 * </pre>
	 */
	@Test
	public void testMain() {
		// TODO: Implement
	}

}
