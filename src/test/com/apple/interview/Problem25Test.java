package com.apple.interview;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import org.junit.Test;

import com.apple.interview.AppleExercise.Problem2;

public class Problem25Test {

	/* ---------------------------------------------------
	 * Test Cases for Problem 2.5
	 * ---------------------------------------------------
	 */
	@Test
	public void nullListTest() {
		List<LinkedHashSet<Integer>> integerSetList = null;
		Iterator<Integer> itr = Problem2.iterateInts2(integerSetList);
		assertNotNull(itr);
		assertFalse(itr.hasNext());
	}
	
	@Test
	public void listWithoutNullsTest() {
		LinkedHashSet<Integer> a = new LinkedHashSet<Integer> ();
		LinkedHashSet<Integer> b = new LinkedHashSet<Integer> ();
		LinkedHashSet<Integer> c = new LinkedHashSet<Integer> ();
		
		for (int idx = 0; idx < 10; idx++) {
			a.add(idx); 
			b.add(idx + 10); 
			c.add(idx + 20); c.add(null);
		}		
		
		a.add(null);
		b.add(null);
		
		List<LinkedHashSet<Integer>> integerSetList = new ArrayList<LinkedHashSet<Integer>> ();
		integerSetList.add(a);
		integerSetList.add(b);
		integerSetList.add(c);
		
		Iterator<Integer> itr = Problem2.iterateInts2(integerSetList);
		assertNotNull(itr);
		assertTrue(itr.hasNext());
		
		// All the numbers from 0 to 29 should be returned in increasing order
		for (int idx = 0; idx < 30; idx++) {			
			assertEquals(idx, itr.next().intValue());
		}
		
	}
	
	@Test
	public void listWithNullsTest() {
		LinkedHashSet<Integer> a = new LinkedHashSet<Integer> ();
		LinkedHashSet<Integer> b = new LinkedHashSet<Integer> ();
		LinkedHashSet<Integer> c = new LinkedHashSet<Integer> ();
		
		for (int idx = 0; idx < 10; idx++) {
			a.add(idx); 
			b.add(idx + 10); 
			c.add(idx + 20); c.add(null);
		}		
		
		a.add(null);
		b.add(null);
		
		List<LinkedHashSet<Integer>> integerSetList = new ArrayList<LinkedHashSet<Integer>> ();
		integerSetList.add(null);
		integerSetList.add(a);
		integerSetList.add(b);
		integerSetList.add(null);
		integerSetList.add(c);
		integerSetList.add(null);
		
		Iterator<Integer> itr = Problem2.iterateInts2(integerSetList);		
		assertNotNull(itr);
		assertTrue(itr.hasNext());
		
		// All the numbers from 0 to 29 should be returned in increasing order
		for (int idx = 0; idx < 30; idx++) {			
			assertEquals(idx, itr.next().intValue());
		}
	}
	
	@Test
	public void listAdditionTest() {
		LinkedHashSet<Integer> a = new LinkedHashSet<Integer> ();
		LinkedHashSet<Integer> b = new LinkedHashSet<Integer> ();
		LinkedHashSet<Integer> c = new LinkedHashSet<Integer> ();
		
		for (int idx = 0; idx < 10; idx++) {
			a.add(idx); 
			b.add(idx + 10); 
			c.add(idx + 20); c.add(null);
		}
		
		List<LinkedHashSet<Integer>> integerSetList = new ArrayList<LinkedHashSet<Integer>> ();
		integerSetList.add(a);
		integerSetList.add(b);
		integerSetList.add(c);
					
		Iterator<Integer> itr = Problem2.iterateInts2(integerSetList);
		assertNotNull(itr);
		
		for (int idx = 0; idx < 5; idx++) {			
			assertEquals(idx, itr.next().intValue());
		}
		
		a.add(56);
		try {
			assertEquals(5, itr.next().intValue());
			fail("Expected a ConcurrentModificationException to be thrown");
		} catch (final ConcurrentModificationException cmEx) {
			assertEquals(null, cmEx.getMessage());
		}
				
	}
	
	@Test
	public void listRemovalTest() {
		
		LinkedHashSet<Integer> a = new LinkedHashSet<Integer> ();
		LinkedHashSet<Integer> b = new LinkedHashSet<Integer> ();
		LinkedHashSet<Integer> c = new LinkedHashSet<Integer> ();
		
		for (int idx = 0; idx < 10; idx++) {
			a.add(idx); 
			b.add(idx + 10); 
			c.add(idx + 20); c.add(null);
		}
				
		List<LinkedHashSet<Integer>> integerSetList = new ArrayList<LinkedHashSet<Integer>> ();
		integerSetList.add(a);
		integerSetList.add(b);
		integerSetList.add(c);
					
		Iterator<Integer> itr = Problem2.iterateInts2(integerSetList);
		assertNotNull(itr);
		
		for (int idx = 0; idx < 5; idx++) {			
			assertEquals(idx, itr.next().intValue());
		}
		
		a.remove(7);
		try {
			assertEquals(5, itr.next().intValue());
			fail("Expected a ConcurrentModificationException to be thrown");
		} catch (final ConcurrentModificationException cmEx) {
			assertEquals(null, cmEx.getMessage());
		}
		
		b.remove(14);
		
	}
	
	@Test
	public void listBasicLoopTest() {
		LinkedHashSet<Integer> a = new LinkedHashSet<Integer> ();
		LinkedHashSet<Integer> b = new LinkedHashSet<Integer> ();
		LinkedHashSet<Integer> c = new LinkedHashSet<Integer> ();
		
		for (int idx = 0; idx < 10; idx++) {
			a.add(idx); 
			b.add(idx + 10); 
			c.add(idx + 20); c.add(null);
		}
		
		List<LinkedHashSet<Integer>> integerSetList = new ArrayList<LinkedHashSet<Integer>> ();
		integerSetList.add(a);
		integerSetList.add(b);
		integerSetList.add(c);
					
		Iterator<Integer> itr = Problem2.iterateInts2(integerSetList);
		assertNotNull(itr);
		
		int counter = 0;
		while(itr.hasNext()) {
			counter++;
			itr.next();
		}
		
		assertEquals(30, counter);
	}
	
	/**
	 * Verify that any call the the return Iterators remove() method
	 * results in a ConcurrentModificationException being thrown.
	 */
	@Test
	public void directMutationTest() {
		
		LinkedHashSet<Integer> a = new LinkedHashSet<Integer> ();
		LinkedHashSet<Integer> b = new LinkedHashSet<Integer> ();
		LinkedHashSet<Integer> c = new LinkedHashSet<Integer> ();
		
		for (int idx = 0; idx < 10; idx++) {
			a.add(idx); 
			b.add(idx + 10); 
			c.add(idx + 20); c.add(null);
		}
		
		List<LinkedHashSet<Integer>> integerSetList = new ArrayList<LinkedHashSet<Integer>> ();
		integerSetList.add(a);
		integerSetList.add(b);
		integerSetList.add(c);
					
		Iterator<Integer> itr = Problem2.iterateInts2(integerSetList);
		assertNotNull(itr);
		
		try {
			itr.remove();
			fail("Expected a ConcurrentModificationException to be thrown");
		} catch (final ConcurrentModificationException cmEx) {
			assertEquals(null, cmEx.getMessage());
		}	
		
	}
}
