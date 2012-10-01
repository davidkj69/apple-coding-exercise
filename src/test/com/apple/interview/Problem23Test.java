package com.apple.interview;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;

import org.junit.Test;

import com.apple.interview.AppleExercise.Problem2;

public class Problem23Test {

	/* ---------------------------------------------------
	 * Test Cases for Problem 2.3
	 * ---------------------------------------------------
	 */
	/**
	 * Pass in a null 2-dimensional array reference to the method 
	 * and ensure that the hasNext() method returns false.
	 */
	@Test
	public void null2DimArrayTest() {
		String[][] array = null;
		Iterator<String> itr = Problem2.iterateStrings(array);
		assertNotNull(itr);
		assertFalse(itr.hasNext());
	}
	
	/**
	 * Pass in a 2-dimensional array of {@code String}s and ensure that the iterator iterates through of 
	 * each String in the order that they are provided, but skips {@code null} values.
	 */
	@Test
	public void _2DimArrayWithoutNullsTest() {
		String[][] arrays = {
				{"A", null, "B", "C"},
				{"D", "E"},
				{"F", null, null, "G"},
				{"H", "I", "J", null},				
				{null, "K", "L", "M"}
		};
		
		Iterator<String> itr = Problem2.iterateStrings(arrays);
		assertNotNull(itr);
		assertTrue(itr.hasNext());
		assertEquals("Expected the 1st value to be 'A'", "A", itr.next());
		assertEquals("Expected the 2nd value to be 'B'", "B", itr.next());
		assertEquals("Expected the 3rd value to be 'C'", "C", itr.next());
		assertEquals("Expected the 4th value to be 'D'", "D", itr.next());
		assertEquals("Expected the 5th value to be 'E'", "E", itr.next());
		assertEquals("Expected the 6h value to be 'F'", "F", itr.next());
		assertEquals("Expected the 7th value to be 'G'", "G", itr.next());
		assertEquals("Expected the 8th value to be 'H'", "H", itr.next());
		assertEquals("Expected the 9th value to be 'I'", "I", itr.next());
		assertEquals("Expected the 10th value to be 'J'", "J", itr.next());
		assertEquals("Expected the 11th value to be 'K'", "K", itr.next());
		assertEquals("Expected the 12th value to be 'L'", "L", itr.next());
		assertEquals("Expected the 13th value to be 'M'", "M", itr.next());
		assertFalse("Expected the iterator to return only 14 values", itr.hasNext());
	}
	
	/**
	 * Pass in a 2-dimensional array of {@code String}s and ensure that the iterator iterates through of 
	 * each String in the order that they are provided, but skips {@code null} values.
	 */
	@Test
	public void _2DimArrayWithNullsTest() {
		String[][] arrays = {
				{"A", "B", "C"},
				null,
				{"D", "E"},
				null,
				{"F", null, null, "G"},
				{"H", "I", "J"},
				null, null, null,
				{"K", "L", "M"},
				null
		};
		
		Iterator<String> itr = Problem2.iterateStrings(arrays);
		assertNotNull(itr);
		assertTrue(itr.hasNext());
		assertEquals("Expected the 1st value to be 'A'", "A", itr.next());
		assertEquals("Expected the 2nd value to be 'B'", "B", itr.next());
		assertEquals("Expected the 3rd value to be 'C'", "C", itr.next());
		assertEquals("Expected the 4th value to be 'D'", "D", itr.next());
		assertEquals("Expected the 5th value to be 'E'", "E", itr.next());
		assertEquals("Expected the 6h value to be 'F'", "F", itr.next());
		assertEquals("Expected the 7th value to be 'G'", "G", itr.next());
		assertEquals("Expected the 8th value to be 'H'", "H", itr.next());
		assertEquals("Expected the 9th value to be 'I'", "I", itr.next());
		assertEquals("Expected the 10th value to be 'J'", "J", itr.next());
		assertEquals("Expected the 11th value to be 'K'", "K", itr.next());
		assertEquals("Expected the 12th value to be 'L'", "L", itr.next());
		assertEquals("Expected the 13th value to be 'M'", "M", itr.next());
		assertFalse("Expected the iterator to return only 14 values", itr.hasNext());
	}

	@Test
	public void _2DimArrayAddtionTest() {
		String[] a = {"A", "B", null, null};
		String[] b = {"E", "F", null, "G"};
		String[] c = {null, "H", null, "I"};
		
		String[][] arrays = {a,b,c, null, null};
		
		Iterator<String> itr = Problem2.iterateStrings(arrays);
		assertNotNull(itr);
		assertTrue(itr.hasNext());
		assertEquals("Expected the 1st value to be 'A'", "A", itr.next());
		assertEquals("Expected the 2nd value to be 'B'", "B", itr.next());
		
		// Change the values 
		a[2] = "C"; a[3] = "D";
		
		// Make sure we ignore the changed values.
		assertEquals("Expected the 3rd value to be 'E'", "E", itr.next());
		assertEquals("Expected the 4th value to be 'F'", "F", itr.next());
		
		// Change some more values 
		b[0] ="X"; b[1] = "Y"; b[2] = "Z";
		
		// Make sure we ignore the changed values.		
		assertEquals("Expected the 5th value to be 'G'", "G", itr.next());
		
		// Add a whole new array
		String[] d = {"J", "K"};
		arrays[3] = d;
		
		assertEquals("Expected the 9th value to be 'H'", "H", itr.next());
		assertEquals("Expected the 10th value to be 'I'", "I", itr.next());						
		
		
	}
	
	@Test
	public void _2DimArrayRemovalTest() {
		String[] a = {"A", "B", "C", "D"};
		String[] b = {"E", "F", null, "G"};
		String[] c = {null, "H", "I", "J"};
		String[] d = {"K", "L", "M"};
		
		String[][] arrays = {a,b,c,d};
		
		Iterator<String> itr = Problem2.iterateStrings(arrays);
		assertNotNull(itr);
		assertTrue(itr.hasNext());
		
		assertEquals("Expected the 1st value to be 'A'", "A", itr.next());
		assertEquals("Expected the 2nd value to be 'B'", "B", itr.next());
		
		// Remove some items from the underlying array
		a[2] = null; a[3] = null;
		
		// Make sure we ignore these changes
		assertEquals("Expected the 3rd value to be 'C'", "C", itr.next());
		assertEquals("Expected the 4th value to be 'D'", "D", itr.next());
		assertEquals("Expected the 5th value to be 'E'", "E", itr.next());
		
		// Remove one of the arrays
		arrays[2] = null;
		
		// Make sure we ignore the change
		assertEquals("Expected the 6th value to be 'F'", "F", itr.next());
		assertEquals("Expected the 7th value to be 'G'", "G", itr.next());
		assertEquals("Expected the 8th value to be 'H'", "H", itr.next());
		assertEquals("Expected the 9th value to be 'I'", "I", itr.next());
		assertEquals("Expected the 10th value to be 'J'", "J", itr.next());
		assertEquals("Expected the 10th value to be 'K'", "K", itr.next());
		assertEquals("Expected the 10th value to be 'L'", "L", itr.next());
		assertEquals("Expected the 10th value to be 'M'", "M", itr.next());
		
	}
	
	@Test
	public void _2DimArrayBasicLoopTest() {
		String[] a = {"A", "B", "C", "D"};
		String[] b = {"E", "F", null, "G"};
		String[] c = {null, "H", "I", "J"};
		String[] d = {"K", "L", "M"};
		
		String[][] arrays = {a,b,c,d};
		
		Iterator<String> itr = Problem2.iterateStrings(arrays);
		assertNotNull(itr);
		
		int counter = 0;
		while(itr.hasNext()) {
			counter++;
			itr.next();
		}
		
		assertEquals(13, counter);
	}
	
	@Test
	public void mutuataionTest() {
		
		String[] a = {"A", "B", "C", "D"};
		String[] b = {"E", "F", null, "G"};
		String[] c = {null, "H", "I", "J"};
		String[] d = {"K", "L", "M"};
		
		String[][] arrays = {a,b,c,d};
		
		Iterator<String> itr = Problem2.iterateStrings(arrays);
		assertNotNull(itr);
		
		try {
			itr.remove();
			fail("Excpected an UnsupportedOperationException to be thrown");
		} catch (final UnsupportedOperationException ex) {
			assertEquals("Fail-safe implementation does not allow for mutation of the underlying data strucutre.", ex.getMessage());
		}
	}
}
