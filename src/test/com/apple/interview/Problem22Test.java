package com.apple.interview;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.apple.interview.AppleExercise.Problem2;

@SuppressWarnings({"rawtypes", "unchecked"})
public class Problem22Test {
	
	/* ---------------------------------------------------
	 * Test Cases for Problem 2.2
	 * ---------------------------------------------------
	 */
	/**
	 * Pass in a null {@code Iterator<String>} reference to the method 
	 * and ensure that the hasNext() method returns false.
	 */
	@Test
	public void nullParameterTest() {
		Iterator<String> itr = Problem2.iterateStrings((Iterator<String>[])null);
		assertNotNull(itr);
		assertFalse(itr.hasNext());
	}
	
	@Test
	public void nullIteratorTest() {
		Iterator<String>[] stringIterators = null;
		Iterator<String> itr = Problem2.iterateStrings(stringIterators);
		assertNotNull(itr);
		assertFalse(itr.hasNext());
	}
	
	/**
	 * Pass in an array of {@code Iterator<String>} and ensure that the iterator iterates through of 
	 * each String in the order that they are provided, but skips {@code null} values.
	 */
	@Test
	public void iteratorsWithoutNullsTest() {
		ArrayList<String> a = new ArrayList<String> ();
		ArrayList<String> b = new ArrayList<String> ();
		ArrayList<String> c = new ArrayList<String> ();
		
		a.add("A"); a.add(null); a.add(null);
		b.add("B"); b.add(null);
		c.add(null); c.add("C");
		
		Iterator[] stringIterators = {a.iterator(), b.iterator(), c.iterator()};;
		
		Iterator<String> itr = Problem2.iterateStrings(stringIterators);
		assertNotNull(itr);
		
		assertEquals("Expected the 1st value to be 'A'", "A", itr.next());
		assertEquals("Expected the 2nd value to be 'B'", "B", itr.next());
		assertEquals("Expected the 3rd value to be 'C'", "C", itr.next());
		assertFalse("Expected the iterator to return only 3 values", itr.hasNext());
		
	}
	
	/**
	 * Pass in an array of {@code Iterator<String>} and ensure that the iterator iterates through of 
	 * each String in the order that they are provided, but skips {@code null} values.
	 */
	@Test
	public void iteratorsWithNullsTest() {
		ArrayList<String> a = new ArrayList<String> ();
		ArrayList<String> b = new ArrayList<String> ();
		ArrayList<String> c = new ArrayList<String> ();
		
		a.add("A");
		b.add("B");
		c.add("C");
		
		Iterator[] stringIterators = {null, a.iterator(), null, b.iterator(), null, c.iterator(), null};
		
		Iterator<String> itr = Problem2.iterateStrings(stringIterators);
		assertNotNull(itr);
		
		assertEquals("Expected the 1st value to be 'A'", "A", itr.next());
		assertEquals("Expected the 2nd value to be 'B'", "B", itr.next());
		assertEquals("Expected the 3rd value to be 'C'", "C", itr.next());
		assertFalse("Expected the iterator to return only 3 values", itr.hasNext());
		
	}
	
	/**
	 * Add some values to an underlying data structure that the Iterator is traversing
	 * and verify that a ConcurrentModificationException is thrown when we encounter
	 * the mutated collection.
	 */
	@Test
	public void iteratorAdditionTest() {
		
		ArrayList<String> a = new ArrayList<String> ();
		ArrayList<String> b = new ArrayList<String> ();
		ArrayList<String> c = new ArrayList<String> ();
		
		a.add("A"); a.add("B"); a.add("C");
		b.add("D");
		c.add("G");
		
		Iterator[] stringIterators = {null, a.iterator(), null, b.iterator(), null, c.iterator(), null};
				
		Iterator<String> itr = Problem2.iterateStrings(stringIterators);
		assertNotNull(itr);
		
		assertEquals("Expected the 1st value to be 'A'", "A", itr.next());
		assertEquals("Expected the 2nd value to be 'B'", "B", itr.next());
		assertEquals("Expected the 3rd value to be 'C'", "C", itr.next());
		
		// Add some values to the underlying data structure
		b.add("E");
		b.add("F");
				
		// Make sure we get a ConcurrentModificationException when we iterator over the mutated collection.
		try {
			assertEquals("Expected the 3rd value to be 'D'", "D", itr.next());
			fail("Expected a ConcurrentModificationException to be thrown");
		} catch (final ConcurrentModificationException cmEx) {
			assertEquals(null, cmEx.getMessage());
		}
		
		
	}
	
	/**
	 * Remove some values from an underlying data structure that the Iterator is traversing
	 * and verify that a ConcurrentModificationException is thrown when we encounter
	 * the mutated collection.
	 */
	@Test
	public void iteratorRemovalTest() {
		
		ArrayList<String> a = new ArrayList<String> ();
		ArrayList<String> b = new ArrayList<String> ();
		ArrayList<String> c = new ArrayList<String> ();
		
		a.add("A"); a.add("B"); a.add("C");a.add("D");
		b.add("E");
		c.add("F");
				
		Iterator[] stringIterators = {null, a.iterator(), null, b.iterator(), null, c.iterator(), null};
		
		Iterator<String> itr = Problem2.iterateStrings(stringIterators);
		assertNotNull(itr);
		
		assertEquals("Expected the 1st value to be 'A'", "A", itr.next());
		assertEquals("Expected the 2nd value to be 'B'", "B", itr.next());
		
		// Remove an item from the underlying data structure
		a.remove("C");
				
		try {
			itr.next();
			fail("Expected a ConcurrentModificationException to be thrown");
		} catch (final ConcurrentModificationException cmEx) {
			assertEquals(null, cmEx.getMessage());
		}
		
	}
	
	/**
	 * Basic loop iteration test to make sure we traverse ALL the elements and 
	 * in the proper order, skipping nulls.
	 */
	@Test
	public void iteratorBasicLoopTest() {
		ArrayList<String> a = new ArrayList<String> ();
		ArrayList<String> b = new ArrayList<String> ();
		ArrayList<String> c = new ArrayList<String> ();
		
		a.add("A"); a.add("B"); a.add("C");
		b.add("D"); b.add(null); b.add(null);
		c.add("E"); c.add(null);
		
		Iterator[] stringIterators = {null, a.iterator(), null, b.iterator(), null, c.iterator(), null};
				
		Iterator<String> itr = Problem2.iterateStrings(stringIterators);
		assertNotNull(itr);
		
		int counter = 0;
		while(itr.hasNext()) {
			counter++;
			itr.next();
		}
		
		assertEquals(5, counter);
		
		// Make sure we are at the end of the list
		assertFalse(itr.hasNext());
		
		// Now try to get next() and make sure we get null
		assertNull(itr.next());
	}
	
	/**
	 * Verify that if all the remaining elements in available to the Iterator
	 * are nulls, that we will return false for hasNext()
	 */
	@Test
	public void hasNextTest() {
		// Simplest case, one iterator with one null value
		ArrayList<String> a = new ArrayList<String> ();		
		a.add(null);
		Iterator[] stringIterators = {a.iterator()};
		
		Iterator<String> itr = Problem2.iterateStrings(stringIterators);
		assertNotNull(itr);
		assertFalse(itr.hasNext());
		
		// More complex case, one iterator, full of nulls.
		for (int idx = 0; idx < 10; idx++) {
			a.add(null);
		}
		
		Iterator[] moreComplex = {a.iterator()};
		itr = Problem2.iterateStrings(moreComplex);
		assertNotNull(itr);
		assertFalse(itr.hasNext());
		
		// Even more complex case, multiple iterators full of nulls
		ArrayList<String> b = new ArrayList<String> ();	
		ArrayList<String> c = new ArrayList<String> ();	
		ArrayList<String> d = new ArrayList<String> ();	
		
		for (int idx = 0; idx < 50; idx++) {
			b.add(null); c.add(null); d.add(null);
		}
		
		Iterator[] nothingButNulls = {a.iterator(), b.iterator(), c.iterator(), d.iterator()};
		itr = Problem2.iterateStrings(nothingButNulls);
		assertNotNull(itr);
		assertFalse(itr.hasNext());
	}
		
	/**
	 * Verify that any call the the return Iterators remove() method
	 * results in a ConcurrentModificationException being thrown.
	 */
	@Test
	public void directMutationTest() {
		
		ArrayList<String> a = new ArrayList<String> ();
		ArrayList<String> b = new ArrayList<String> ();
		ArrayList<String> c = new ArrayList<String> ();
		
		a.add("A"); a.add("B"); a.add("C");
		b.add("D");
		c.add("E");
		
		Iterator[] stringIterators = {null, a.iterator(), null, b.iterator(), null, c.iterator(), null};
				
		Iterator<String> itr = Problem2.iterateStrings(stringIterators);
		assertNotNull(itr);
		
		try {
			itr.remove();
			fail("Expected a ConcurrentModificationException to be thrown");
		} catch (final ConcurrentModificationException cmEx) {
			assertEquals(null, cmEx.getMessage());
		}	
	}
	
	
	/**
	 * Test case to demonstrate the behavior of the default java Iterator implementation.
	 * My implementation does not mimic this behavior as it has already cycled on to the
	 * next Iterator in the list.
	 */
	@Test
	public void concurrentModEx() {
		
		List<String> a = new ArrayList<String> ();
		a.add("A");
		a.add("B");
		a.add("C");
		
		Iterator<String> itr = a.iterator();
		
		assertTrue(itr.hasNext());
		assertEquals("A", itr.next());
		assertTrue(itr.hasNext());
		assertEquals("B", itr.next());
		
		a.remove("C");
		assertFalse(itr.hasNext());  
		
		try {
			itr.next();
		} catch (final ConcurrentModificationException cmEx) {
			assertEquals(null, cmEx.getMessage());
		}
	}
	
	/**
	 * The behavior of my implementation under the same scenario.
	 */
	@Test
	public void noConcurrentModEx() {
		
		ArrayList<String> a = new ArrayList<String> ();
		
		
		a.add("A"); a.add("B"); a.add("C");
		
		Iterator[] stringIterators = {a.iterator()};
		
		Iterator<String> itr = Problem2.iterateStrings(stringIterators);
		assertNotNull(itr);
		
		assertEquals("Expected the 1st value to be 'A'", "A", itr.next());
		assertEquals("Expected the 2nd value to be 'B'", "B", itr.next());
		
		// Remove an item from the underlying data structure
		a.remove("C");
				
		try {
			itr.next();
		} catch (final ConcurrentModificationException cmEx) {
			fail("Did NOT expect a ConcurrentModificationException to be thrown");
		}
	}
}
