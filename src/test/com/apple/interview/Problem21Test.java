package com.apple.interview;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.Iterator;

import org.junit.Test;

import com.apple.interview.AppleExercise.Problem2;

public class Problem21Test {

	/* ---------------------------------------------------
	 * Test Cases for Problem 2.1
	 * ---------------------------------------------------
	 */
	/**
	 * Pass in a single null value to the method and ensure
	 * that the hasNext() method returns false.
	 */
	@Test
	public void nullParameterTest() {
		String s = null;
		Iterator<String> itr = Problem2.iterateStrings(s);
		assertNotNull(itr);
		assertNull(itr.next());
		assertFalse(itr.hasNext());
	}
	
	/**
	 * Pass in a null array reference to the method and ensure
	 * that the hasNext() method returns false.
	 */
	@Test
	public void nullArrayTest() {
		String[] array = null;
		Iterator<String> itr = Problem2.iterateStrings(array);
		assertNotNull(itr);
		assertFalse(itr.hasNext());
	}
	
	/**
	 * Pass in an array of {@code String}s and ensure that the iterator iterates through of 
	 * each String in the order that they are provided, but skips {@code null} values.
	 */
	@Test
	public void _1DimArrayWithoutNullsTest() {
		String[] list = {"one", "two", "three", "four", "five"};
		
		Iterator<String> itr = Problem2.iterateStrings(list);
		assertNotNull(itr);
		
		assertEquals("Expected the 1st value to be 'one'", "one", itr.next());
		assertEquals("Expected the 2nd value to be 'two'", "two", itr.next());
		assertEquals("Expected the 3rd value to be 'three'", "three", itr.next());
		assertEquals("Expected the 4th value to be 'four'", "four", itr.next());
		assertEquals("Expected the 5th value to be 'five'", "five", itr.next());
		assertFalse("Expected the iterator to return only 5 values", itr.hasNext());
	}
	
	/**
	 * Pass in an array of {@code String}s and ensure that the iterator iterates through of 
	 * each String in the order that they are provided, but skips {@code null} values.
	 */
	@Test
	public void _1DimArrayWithNullsTest() {
		String[] list = {null, "foo", null, "bar", null, null, null, "baz"};
				
		Iterator<String> itr = Problem2.iterateStrings(list);
		assertNotNull(itr);
		assertEquals("Expected the first value to be 'foo'", "foo", itr.next());
		assertEquals("Expected the second value to be 'bar'", "bar", itr.next());
		assertEquals("Expected the third value to be 'baz'", "baz", itr.next());
		assertFalse("Expected the iterator to return only 3 values", itr.hasNext());
	}
	
	/**
	 * Pass in an array of {@code String}s that contains only nulls and
	 * ensure that the the hasNext() method returns false.
	 */
	@Test
	public void _1DimArrayAllNullsTest() {
		String[] list = {null, null, null, null};
		Iterator<String> itr = Problem2.iterateStrings(list);
		assertNotNull(itr);
		assertFalse("Expected the iterator to have no values", itr.hasNext());
	}
	
	@Test
	public void _1DArrayAdditionTest() {
		String[] list = new String[5];
		list[0] = "one"; list[1] = "two"; list[2] = "three";
		
		Iterator<String> itr = Problem2.iterateStrings(list);
		assertNotNull(itr);
		assertEquals("Expected the 1st value to be 'one'", "one", itr.next());
		assertEquals("Expected the 2nd value to be 'two'", "two", itr.next());
		
		list[3] = "four";
		
		assertEquals("Expected the 3rd value to be 'three'", "three", itr.next());
		assertFalse("We should ignore the addition of the last element to the array", itr.hasNext());
	}
	
	/**
	 * Pass in an array of {@code String}s and ensure that the iterator iterates through of 
	 * each String in the order that they are provided, but skips {@code null} values.
	 * 
	 * We are going to invoke iterator.remove() and ensure that the expected element is
	 * removed from the iterator as expected.
	 */
	@Test
	public void _1DArrayRemovalsTest() {
		String[] list = {"one", "two", "three", "four", "five", "six"};
		
		Iterator<String> itr = Problem2.iterateStrings(list);
		assertNotNull(itr);
		
		assertEquals("Expected the 1st value to be 'one'", "one", itr.next());
		assertEquals("Expected the 2nd value to be 'two'", "two", itr.next());
		
		// Remove an item from the array
		list[2] = null;
		assertEquals("Expected the 3rd value to be 'three'", "three", itr.next());
		assertEquals("Expected the 4th value to be 'four'", "four", itr.next());
		assertEquals("Expected the 5th value to be 'five'", "five", itr.next());
		
		// Remove another item from the array
		list[5] = null;
		assertEquals("Expected the 5th value to be 'six'", "six", itr.next());
	}
	
	@Test
	public void _1DArrayBasicLoopTest() {		
		String[] list = {"one", "two", "three", "four", "five", "six"};
		
		Iterator<String> itr = Problem2.iterateStrings(list);
		assertNotNull(itr);
		
		int counter = 0;
		while(itr.hasNext()) {
			counter++;
			itr.next();
		}
		
		assertEquals(6, counter);
	}
	
	@Test
	public void mutuataionTest() {
		
		String[] list = {"one", "two", "three", "four", "five", "six"};
		
		Iterator<String> itr = Problem2.iterateStrings(list);
		assertNotNull(itr);
		
		try {
			itr.remove();
			fail("Excpected an UnsupportedOperationException to be thrown");
		} catch (final UnsupportedOperationException ex) {
			assertEquals("Fail-safe implementation does not allow for mutation of the underlying data strucutre.", ex.getMessage());
		}
	}
	
}
