package com.apple.interview;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;

import org.junit.Test;

import com.apple.interview.AppleExercise.Problem2;

public class Problem24Test {

	/* ---------------------------------------------------
	 * Test Cases for Problem 2.4
	 * ---------------------------------------------------
	 */
	/**
	 * Pass in a null 3-dimensional Integer array reference to the 
	 * method and ensure that the hasNext() method returns false.
	 */
	@Test
	public void null3DArrayTest() {
		Integer [][][] array = null;
		Iterator<Integer> itr = Problem2.iterateInts(array);
		assertNotNull(itr);
		assertFalse(itr.hasNext());
	}
	
	/**
	 * Pass in a 3-dimensional Integer array reference to the method and ensure that the 
	 * iterator iterates through all Integers within each two-dimensional Integer array in
     * the order that they are provided, but skips {@code null} values..
	 */
	@Test
	public void _3DArrayWithoutNullsTest() {
		Integer [][][] a = { { {1,2,3,4,5,6, null,7}, {8,9,10,null, 11,12,13,14} },
				             { {null, 15,16,17}, {18,19,20,21,22, null,23,24} }, 
				             { {25,26, null, 27}, {28,29,30,31,32,33,34, null} }};
		
		Iterator<Integer> itr = Problem2.iterateInts(a);
		assertNotNull(itr);
		assertTrue(itr.hasNext());
		
		for (int idx = 1; idx < 34; idx++) {
			assertEquals(idx, itr.next().intValue());
		}
	}
	
	/**
	 * Pass in a 3-dimensional Integer array reference to the method and ensure that the 
	 * iterator iterates through all Integers within each two-dimensional Integer array in
     * the order that they are provided, but skips {@code null} values..
	 */
	@Test
	public void _3DArrayWithNullsTest() {
		Integer [][][] a = { null, 
				             { {1,2,3,4,5,6,7}, null, {8,9,10,11,12,13,14} },
		                     { {15,16,17}, {18,19,20,21,22,23,24}, null }, 
		                     { null,{25,26,27}, {28,29,30,31,32,33,34} },
		                     null, 
		                     null 
		                    };

		Iterator<Integer> itr = Problem2.iterateInts(a);
		assertNotNull(itr);
		assertTrue(itr.hasNext());

		for (int idx = 1; idx < 34; idx++) {
			assertEquals(idx, itr.next().intValue());
		}
	}
	
	@Test
	public void _3DArrayAdditionTest() {
		Integer[] a = {1,2,3,4,5,6,7, null, null};
		Integer[] b = {10,11,12,13,14, null, null};
		
		Integer[][][] array = { {a,b}, null };
				
		Iterator<Integer> itr = Problem2.iterateInts(array);
		assertNotNull(itr);
		assertTrue(itr.hasNext());
		
		for (int idx = 1; idx < 6; idx++) {
			assertEquals(idx, itr.next().intValue());
		}
		
		// fill in some blanks 
		a[7] = 8; a[8] = 9;
		
		for (int idx = 6; idx < 8; idx++) {
			assertEquals(idx, itr.next().intValue());
		}
		
		// Fill in some more blanks
		b[5] = 15; b[6] = 16;
		
		for (int idx = 10; idx < 15; idx++) {
			assertEquals(idx, itr.next().intValue());
		}
		
		// Add a new array to the end
		Integer[] c = {100, 101, 102};
		Integer[][] d = {c};
		array[1] = d;
		
		// Make sure we don't want to iterate over the newly added items
		assertFalse(itr.hasNext());
		
		
	}
	
	@Test
	public void _3DArrayRemovalTest() {
		Integer[] a = {1,2,3,4,5,6,7};
		Integer[] b = {8,9,10,11,12};
		Integer[] c = {15,16,17,18,19};
		Integer[] d = {20,21,22,23,24};
		Integer[] e = {25,26,27,28,29};
		
		Integer[][][] array = { {a,b}, {c,d}, {e} };
				
		Iterator<Integer> itr = Problem2.iterateInts(array);
		assertNotNull(itr);
		assertTrue(itr.hasNext());
		
		// Iterate through the first five elements.
		for (int idx = 1; idx < 6; idx++) {
			assertEquals(idx, itr.next().intValue());
		}
		
		// Remove some items from the array
		a[5] = null; a[6] = null;
		
		// Make sure we can still iterate over the elements that have been "deleted"
		for (int idx = 6; idx < 13; idx++) {
			assertEquals(idx, itr.next().intValue());
		}
		
		// remove a 2D array from the 3D array
		array[1] = null;
		
		// Make sure we can still iterate over elements in the deleted array
		for (int idx = 15; idx < 30; idx++) {
			assertEquals(idx, itr.next().intValue());
		}
				
	}

	@Test
	public void _3DArrayBasicLoopTest() {
		Integer [][][] a = { { {1,2,3,4,5,6, null,7}, {8,9,10,null, 11,12,13,14} },
	             { {null, 15,16,17}, {18,19,20,21,22, null,23,24} }, 
	             { {25,26, null, 27}, {28,29,30,31,32,33,34, null} }};

		Iterator<Integer> itr = Problem2.iterateInts(a);
		assertNotNull(itr);
		assertTrue(itr.hasNext());

		int counter = 0;
		while(itr.hasNext()) {
			counter++;
			itr.next();
		}
		
		assertEquals(34, counter);
	}
	
	@Test
	public void mutuataionTest() {
		
		Integer [][][] a = { { {1,2,3,4,5,6, null,7}, {8,9,10,null, 11,12,13,14} },
	             { {null, 15,16,17}, {18,19,20,21,22, null,23,24} }, 
	             { {25,26, null, 27}, {28,29,30,31,32,33,34, null} }};

		Iterator<Integer> itr = Problem2.iterateInts(a);
		assertNotNull(itr);
		
		try {
			itr.remove();
			fail("Excpected an UnsupportedOperationException to be thrown");
		} catch (final UnsupportedOperationException ex) {
			assertEquals("Fail-safe implementation does not allow for mutation of the underlying data strucutre.", ex.getMessage());
		}
	}
}
