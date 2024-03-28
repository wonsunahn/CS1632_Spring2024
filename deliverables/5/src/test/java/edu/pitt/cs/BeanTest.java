package edu.pitt.cs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Random;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BeanTest {

	final int beanCount = 4;
	final int slotCount = 10;

	Random rand;
	Bean[] beans;

	/**
	 * Set up the JUnit test fixture.
	 */
	@Before
	public void setUp() {
		beans = new Bean[beanCount];

		// TODO: Create a mock Random object and assign to rand
		
		
		// TODO: Call Bean.createInstance to create a bean in luck mode for slotCount and assign to beans[0].
		// To make the bean always go left, pass in a rand which always returns 0 on rand.nextInt(2).
		
		
		// TODO: Call Bean.createInstance to create a bean in skilled mode for slotCount and assign to beans[1].
		// To fix bean skill level to 0, pass in a rand which always returns -3.0 on rand.nextGaussian().
		

		// TODO: Call Bean.createInstance to create a bean in skilled mode for slotCount and assign to beans[2].
		// To fix bean skill level to 2, pass in a rand which always returns -1.5 on rand.nextGaussian().
		

		// TODO: Call Bean.createInstance to create a bean in skilled mode for slotCount and assign to beans[3].
		// To fix bean skill level to 9, pass in a rand which always returns 3.0 on rand.nextGaussian().
		
	}

	/**
	 * Test BeanImpl(int slotCount, boolean isLuck, Random rand).
	 * 
	 * <pre>
	 * Preconditions: beans array populated with beans as described in setUp().
	 * Execution steps: None. 
	 * Postconditions: For each bean in beans, getXPos() == 0 and getYPos() == 0.
	 * </pre>
	 */
	@Test
	public void testConstructor() {
		// TODO: Implement
	}

	/**
	 * Test reset().
	 * 
	 * <pre>
	 * Preconditions: beans array populated with beans as described in setUp().
	 * Execution steps: For each bean in beans, call advanceStep().
	 *                  For each bean in beans, call reset().
	 * Postconditions: For each bean in beans, getXPos() == 0 and getYPos() == 0.
	 * </pre>
	 */
	@Test
	public void testReset() throws BeanOutOfBoundsException {
		// TODO: Implement
	}

	/**
	 * Test advanceStep().
	 * 
	 * <pre>
	 * Preconditions: beans array populated with beans as described in setUp().
	 *                beans[0] always goes left by having rand.nextInt(2) return 0.
	 * Execution steps: For beans[0], call advanceStep().
	 * Postconditions: For beans[0], getXPos() == 0 and getYPos() == 1.
	 * </pre>
	 */
	@Test
	public void testAdvanceStepLuckyBeanOnceLeft() throws BeanOutOfBoundsException {
		// TODO: Implement;
	}

	/**
	 * Test advanceStep().
	 * 
	 * <pre>
	 * Preconditions: beans array populated with beans as described in setUp().
	 *                beans[0] always goes left by having rand.nextInt(2) return 0.
	 * Execution steps: For beans[0], call advanceStep() twice.
	 * Postconditions: For beans[0], getXPos() == 0 and getYPos() == 2.
	 * </pre>
	 */
	@Test
	public void testAdvanceStepLuckyBeanTwiceLeft() throws BeanOutOfBoundsException {
		// TODO: Implement
	}

	/**
	 * Test advanceStep().
	 * 
	 * <pre>
	 * Preconditions: beans array populated with beans as described in setUp().
	 *                beans[0] always goes right by having rand.nextInt(2) return 1.
	 * Execution steps: For beans[0], call advanceStep().
	 * Postconditions: For beans[0], getXPos() == 1 and getYPos() == 1.
	 * </pre>
	 */
	@Test
	public void testAdvanceStepLuckyBeanOnceRight() throws BeanOutOfBoundsException {
		// TODO: Implement
	}

	/**
	 * Test advanceStep().
	 * 
	 * <pre>
	 * Preconditions: beans array populated with beans as described in setUp().
	 *                beans[0] always goes right by having rand.nextInt(2) return 1.
	 * Execution steps: For beans[0], call advanceStep() twice.
	 * Postconditions: For beans[0], getXPos() == 2 and getYPos() == 2.
	 * </pre>
	 */
	@Test
	public void testAdvanceStepLuckyBeanTwiceRight() throws BeanOutOfBoundsException {
		// TODO: Implement
	}

	/**
	 * Test advanceStep().
	 * 
	 * <pre>
	 * Preconditions: beans array populated with beans as described in setUp().
	 *                beans[0] always goes left by having rand.nextInt(2) return 0.
	 * Execution steps: For beans[0], call advanceStep() 10 times.
	 * Postconditions: BeanOutOfBoundsException is thrown at 10th call to advanceStep().
	 * </pre>
	 */
	@Test
	public void testAdvanceStepLuckyBean10TimesLeft() throws BeanOutOfBoundsException {
		// TODO: Implement
	}

	/**
	 * Test advanceStep().
	 * 
	 * <pre>
	 * Preconditions: beans array populated with beans as described in setUp().
	 * Execution steps: For beans[1], call advanceStep() 9 times.
	 * Postconditions: For beans[1], getXPos() == 0 and getYPos() == 9.
	 * </pre>
	 */
	@Test
	public void testAdvanceStepSkill0Bean9Times() throws BeanOutOfBoundsException {
		// TODO: Implement
	}

	/**
	 * Test advanceStep().
	 * 
	 * <pre>
	 * Preconditions: beans array populated with beans as described in setUp().
	 * Execution steps: For beans[2], call advanceStep() 9 times.
	 * Postconditions: For beans[2], getXPos() == 2 and getYPos() == 9.
	 * </pre>
	 */
	@Test
	public void testAdvanceStepSkill2Bean9Times() throws BeanOutOfBoundsException {
		// TODO: Implement
	}

	/**
	 * Test advanceStep().
	 * 
	 * <pre>
	 * Preconditions: beans array populated with beans as described in setUp().
	 * Execution steps: For beans[3], call advanceStep() 9 times.
	 * Postconditions: For beans[3], getXPos() == 9 and getYPos() == 9.
	 * </pre>
	 */
	@Test
	public void testAdvanceStepSkill9Bean9Times() throws BeanOutOfBoundsException {
		// TODO: Implement
	}

	/**
	 * Test advanceStep().
	 * 
	 * <pre>
	 * Preconditions: beans array populated with beans as described in setUp().
	 * Execution steps: For beans[2], call advanceStep() once.
	 * Postconditions: For beans[2], getXPos() == 1 and getYPos() == 1.
	 * </pre>
	 */
	@Test
	public void testAdvanceStepSkill2BeanOnce() throws BeanOutOfBoundsException {
		// TODO: Implement
	}

	/**
	 * Test advanceStep().
	 * 
	 * <pre>
	 * Preconditions: beans array populated with beans as described in setUp().
	 * Execution steps: For beans[2], call advanceStep() twice.
	 * Postconditions: For beans[2], getXPos() == 2 and getYPos() == 2.
	 * </pre>
	 */
	@Test
	public void testAdvanceStepSkill2BeanTwice() throws BeanOutOfBoundsException {
		// TODO: Implement
	}

	/**
	 * Test advanceStep().
	 * 
	 * <pre>
	 * Preconditions: beans array populated with beans as described in setUp().
	 * Execution steps: For beans[2], call advanceStep() thrice.
	 * Postconditions: For beans[2], getXPos() == 2 and getYPos() == 3.
	 * </pre>
	 */
	@Test
	public void testAdvanceStepSkill2BeanThrice() throws BeanOutOfBoundsException {
		// TODO: Implement
	}
	
}
