package com.apple.interview;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import com.apple.interview.AppleExercise.Problem2;

public class StringIteratorTests {

	@Test
	public void nullListOfStringTest() {
		Iterator<String> itr = Problem2.iterateStrings((String)null);
		assertNotNull(itr);
		assertFalse(itr.hasNext());
	}
	
	@Test
	public void listOfStringsWithNullsTest() {
		String[] list = {null, "foo", null, "bar", null, null, null, "baz"};
				
		Iterator<String> itr = Problem2.iterateStrings(list);
		assertNotNull(itr);
		assertEquals("Expected the first value to be 'foo'", "foo", itr.next());
		assertEquals("Expected the second value to be 'bar'", "bar", itr.next());
		assertEquals("Expected the third value to be 'baz'", "baz", itr.next());
		assertFalse("Expected the iterator to return only 3 values", itr.hasNext());
	}
	
	@Test
	public void listOfStringsWithoutNullsTest() {
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
	
	@Test
	public void nullArrayTest() {
		Iterator<String> itr = Problem2.iterateStrings((String[][])null);
		assertNotNull(itr);
		assertFalse(itr.hasNext());
	}
	
	@Test
	public void arraysOfArraysWithoutNullsTest() {
		String[][] arrays = {
				{"A", "B", "C"},
				{"D", "E"},
				{"F", null, null, "G"},
				{"H", "I", "J"},				
				{"K", "L", "M"}
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
	public void arrayOfArraysWithNullsTest() {
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
	public void arrayOfIteratorsWithoutNullsTest() {
		ArrayList<String> a = new ArrayList<String> ();
		ArrayList<String> b = new ArrayList<String> ();
		ArrayList<String> c = new ArrayList<String> ();
		
		a.add("A");
		b.add("B");
		c.add("C");
		
		Iterator<String> itr = Problem2.iterateStrings(a.iterator(), b.iterator(), c.iterator());
		assertNotNull(itr);
		
		assertEquals("Expected the 1st value to be 'A'", "A", itr.next());
		assertEquals("Expected the 2nd value to be 'B'", "B", itr.next());
		assertEquals("Expected the 3rd value to be 'C'", "C", itr.next());
		assertFalse("Expected the iterator to return only 3 values", itr.hasNext());
		
	}
	
	@Test
	public void arrayOfIteratorsWithNullsTest() {
		ArrayList<String> a = new ArrayList<String> ();
		ArrayList<String> b = new ArrayList<String> ();
		ArrayList<String> c = new ArrayList<String> ();
		
		a.add("A");
		b.add("B");
		c.add("C");
		
		Iterator<String> itr = Problem2.iterateStrings(a.iterator(), null, b.iterator(), null, c.iterator(), null);
		assertNotNull(itr);
		
		assertEquals("Expected the 1st value to be 'A'", "A", itr.next());
		assertEquals("Expected the 2nd value to be 'B'", "B", itr.next());
		assertEquals("Expected the 3rd value to be 'C'", "C", itr.next());
		assertFalse("Expected the iterator to return only 3 values", itr.hasNext());
		
	}
}
