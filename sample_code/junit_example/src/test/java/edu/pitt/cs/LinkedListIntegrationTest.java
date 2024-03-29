package edu.pitt.cs;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LinkedListIntegrationTest {

	LinkedList<Integer> ll;
	Node<Integer>[] nodes;
	
	// Automatically called before every @Test method
	@Before
	public void setUp() throws Exception {
		// Create LinkedList which we will be testing
		ll = new LinkedList<Integer>();
		// Create a bunch Nodes that will be used to test LinkedList
		nodes = new Node[10];
		for (int j = 0; j < 10; j++) {
			nodes[j] = new Node<Integer>(Integer.valueOf(j));
		}
	}

	// Automatically called after every @Test method
	@After
	public void tearDown() throws Exception {
		// any necessary teardown - none needed here
	}	
		
	
	// --------------------------------------------------------------
	// ZERO-LENGTH TESTS
	// --------------------------------------------------------------
	
	// Test that a zero-length list will return null if you try to get 
	// the first element
	@Test
	public void testZeroList() {
		LinkedList<Integer> ll0 = new LinkedList<Integer>();		
		assertNull(ll0.getFront());
	}

	// Test that the .clear() methods works, by first adding an item, and then
	// clearing the list.  It should be empty (tested by ensuring that
	// the first element is null).
	@Test
	public void testClearedList() {
		ll.addToFront(nodes[7]);
		ll.clear();
		assertNull(ll.getFront());
	}
	
	// This tests whether a multiple item linked lit will
	// clear down to zero items when clear method is called.
	
	@Test
	public void testMultiList() {
		for (int j=0; j < 10; j++) {
			ll.addToFront(nodes[j]);
		}
		ll.clear();
		assertNull(ll.getFront());
	}
	
	// --------------------------------------------------------------
	// ADD TO FRONT TESTS
	// --------------------------------------------------------------
	
	// Add ten nodes, then add one more (testNode).  Check that setNext() has been
	// called to the last of the first ten nodes, and that the added
	// node testNode is the same as the one that is at the front of the list.

	@Test
	public void testAddToTenItemLL() {
		for (int j = 0; j < 10; j++) {
			ll.addToFront(nodes[j]);
		}
		
		Node<Integer> testNode = new Node<Integer>(Integer.valueOf(1));
		ll.addToFront(testNode);
		assertSame(ll.getFront(), testNode);
		
	}
	
	// Add only one node, then add one more (testNode).  Check that setNext() has been
	// called to the original node and that the added
	// node testNode is the same as the one that is at the front of the list.

	@Test
	public void testAddToOneItemLL() {
		Node<Integer> existingNode = nodes[1];
		Node<Integer> testNode = nodes[2];
		ll.addToFront(existingNode);
		ll.addToFront(testNode);
		assertSame(ll.getFront(), testNode);
	}
	
	//  Check that passing null to addToFront() results in an IllegalArgumentException	
	@Test
	public void testAddNullToNoItemLL() {
		try {
			ll.addToFront(null);
			fail("Adding a null node should result in an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		}
	}
	
	// --------------------------------------------------------------
	// DELETE FROM FRONT TESTS
	// --------------------------------------------------------------
	
	// Check that attempting to delete a node from a Linked List with 
	// no elements will not throw an error.
	
	@Test
	public void testDeleteFrontNoItem() {
		ll.deleteFront();
		assertEquals(ll.getFront(), null);
		
	}
	
	// Check that deleting a node from a Linked List with 
	// one elements will not throw an error, and will result in an
	// empty LinkedList (front is null).
	
	@Test
	public void testDeleteFrontOneItem() {
		ll.addToFront(nodes[1]);
		ll.deleteFront();
		assertEquals(ll.getFront(), null);
	}
	
	// Check that deleting a node from a Linked List with 
	// multiple elements will properly delete the first node
	// and leave the old second node as the new first node..	
	
	@Test
	public void testDeleteFrontMultipleItems() {
		for (int j = 0; j < 10; j++) {
			ll.addToFront(nodes[j]);
		}
		
		ll.deleteFront();
		
		assertSame(ll.getFront(), nodes[8]);
	}
	
	// --------------------------------------------------------------
	// EQUALITY TESTS
	// --------------------------------------------------------------
	
	//  Check that a new linked list equals itself.	
	@Test 
	public void testEqualsSelf() {
		assertEquals(ll, ll);
	}
	
	//	Check that two new linked lists with no elements equal each other.
	@Test
	public void testEquals0Elems() {
		LinkedList<Integer> ll01 = new LinkedList<Integer>();
		LinkedList<Integer> ll02 = new LinkedList<Integer>();
		assertEquals(ll01, ll02);
	}
	
	//	An instantiated linked list should not equal null.
	@Test
	public void testNotEqualsNull() {
		LinkedList<Integer> ll01 = new LinkedList<Integer>();
		assertFalse(ll01.equals(null));
	}
	
	//  Check that a LL object does equal a non-LinkedList, e.g. Object
	@Test
	public void testNotEqualsRegularObject() {
		LinkedList<Integer> ll01 = new LinkedList<Integer>();
		Object obj = new Object();
		assertFalse(ll01.equals(obj));
	}
    
    
	//  Check that two LLs with the same Node value with a single node are equal	
	@Test
	public void testEqualsOneNodeSameVals() {
		LinkedList<Integer> ll11 = new LinkedList<Integer>();
		LinkedList<Integer> ll12 = new LinkedList<Integer>();
		ll11.addToFront(new Node<Integer>(Integer.valueOf(1)));
		ll12.addToFront(new Node<Integer>(Integer.valueOf(1)));
		assertEquals(ll11, ll12);
	}
	
	//  Check that two LL with different Node values with a single node are NOT equal	
	@Test
	public void testEqualsOneNodeDiffVals() {
		LinkedList<Integer> ll11 = new LinkedList<Integer>();
		LinkedList<Integer> ll2 = new LinkedList<Integer>();
		ll11.addToFront(new Node<Integer>(Integer.valueOf(1)));
		ll2.addToFront(new Node<Integer>(Integer.valueOf(2)));
		assertFalse(ll11.equals(ll2));
	}
	
	//  Check that two LLs with different sizes, but the same front node value,
	// are not considered equal.
	@Test
	public void testNotEqualsDiffSizes() {
		LinkedList<Integer> ll11 = new LinkedList<Integer>();
		LinkedList<Integer> ll_3elems = new LinkedList<Integer>();
		
		ll11.addToFront(new Node<Integer>(Integer.valueOf(1)));
		ll_3elems.addToFront(new Node<Integer>(Integer.valueOf(3)));
		ll_3elems.addToFront(new Node<Integer>(Integer.valueOf(2)));
		ll_3elems.addToFront(new Node<Integer>(Integer.valueOf(1)));
		
		assertFalse(ll_3elems.equals(ll11));
	}

	// Check that a LL which is just a reference to another instance of itself
	// equals itself
	@Test
	public void testEqualsRef() {
		LinkedList<Integer> ll11 = new LinkedList<Integer>();
		ll11.addToFront(new Node<Integer>(Integer.valueOf(1)));
		LinkedList<Integer> ll11_new = ll11;
		assertSame(ll11, ll11_new);
	}
	
	// Check that LLs with the same size, but different data in the nodes,
	// do not equal each other.	
	@Test
	public void testNotEqualsDiffData() {
		LinkedList<Integer> ll_3elems = new LinkedList<Integer>();
		LinkedList<Integer> ll_321 = new LinkedList<Integer>();
		ll_3elems.addToFront(new Node<Integer>(Integer.valueOf(3)));
		ll_3elems.addToFront(new Node<Integer>(Integer.valueOf(2)));
		ll_3elems.addToFront(new Node<Integer>(Integer.valueOf(1)));
		
		ll_321.addToFront(new Node<Integer>(Integer.valueOf(1)));
		ll_321.addToFront(new Node<Integer>(Integer.valueOf(2)));
		ll_321.addToFront(new Node<Integer>(Integer.valueOf(3)));
		assertFalse(ll_321.equals(ll_3elems));
	}
	
	//  Check that two multiple-node LLs with the same data  equal each other
	@Test
	public void testEqualsSameData() {
		LinkedList<Integer> ll_321 = new LinkedList<Integer>();
		LinkedList<Integer> ll_321_2 = new LinkedList<Integer>();
		
		ll_321.addToFront(new Node<Integer>(Integer.valueOf(1)));
		ll_321.addToFront(new Node<Integer>(Integer.valueOf(2)));
		ll_321.addToFront(new Node<Integer>(Integer.valueOf(3)));
		
		ll_321_2.addToFront(new Node<Integer>(Integer.valueOf(1)));
		ll_321_2.addToFront(new Node<Integer>(Integer.valueOf(2)));
		ll_321_2.addToFront(new Node<Integer>(Integer.valueOf(3)));
		
		assertTrue(ll_321.equals(ll_321_2));
	
	}
	
}
