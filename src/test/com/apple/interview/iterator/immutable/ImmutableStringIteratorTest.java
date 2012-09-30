package com.apple.interview.iterator.immutable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import com.apple.interview.AppleExercise;
import com.apple.interview.AppleExercise.Problem2;

@SuppressWarnings({"rawtypes","unchecked"})
public class ImmutableStringIteratorTest {

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
		assertEquals("Expected the 4th value to be 'four'", "four", itr.next());
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
		list[2] = null;
		assertEquals("Expected the 3rd value to be 'four'", "four", itr.next());
		assertEquals("Expected the 4th value to be 'five'", "five", itr.next());
		list[5] = null;
		assertFalse("Expected the iterator to have no more elements", itr.hasNext());
	}
	
	@Test
	public void _1DArrayBasicLoopTest() {		
		String[] list = {"one", "two", "three", "four", "five", "six"};
		
		Iterator<String> itr = Problem2.iterateStrings(list);
		assertNotNull(itr);
		
		int counter = 0;
		while(itr.hasNext()) {
			counter++;
			String s = itr.next();
		}
		
		assertEquals(6, counter);
	}
	
	
	/* ---------------------------------------------------
	 * Test Cases for Problem 2.2
	 * ---------------------------------------------------
	 */
	/**
	 * Pass in a null {@code Iterator<String>} reference to the method 
	 * and ensure that the hasNext() method returns false.
	 */
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
		
		a.add("A");
		b.add("B");
		c.add("C");
		
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
		
		// Add some values to the underlying data structure
		b.add("E");
		b.add("F");
		
		// Make sure we ignore those changes
		assertEquals("Expected the 3rd value to be 'C'", "C", itr.next());
		assertEquals("Expected the 4th value to be 'D'", "D", itr.next());
		assertEquals("Expected the 5th value to be 'G'", "G", itr.next());
		
		// Add some values to the underlying data structure
		c.add("H");
		c.add("I");
		
		assertFalse("Expected the iterator to return only 5 values", itr.hasNext());
		
	}
	
	@Test
	public void iteratorRemovalTest() {
		
		ArrayList<String> a = new ArrayList<String> ();
		ArrayList<String> b = new ArrayList<String> ();
		ArrayList<String> c = new ArrayList<String> ();
		
		a.add("A"); a.add("B"); a.add("C");
		b.add("D");
		c.add("E");
		
		Iterator[] stringIterators = {null, a.iterator(), null, b.iterator(), null, c.iterator(), null};
		
		Iterator<String> itr = Problem2.iterateStrings(stringIterators);
		assertNotNull(itr);
		
		assertEquals("Expected the 1st value to be 'A'", "A", itr.next());
		assertEquals("Expected the 2nd value to be 'B'", "B", itr.next());
		
		// Remove an item from the underlying data structure
		a.remove("C");
				
		// Make sure we ignore the removal
		assertEquals("Expected the 3rd value to be 'C'", "C", itr.next());
		assertEquals("Expected the 4th value to be 'D'", "D", itr.next());
		assertEquals("Expected the 5th value to be 'E'", "E", itr.next());
		
	}
	
	@Test
	public void iteratorBasicLoopTest() {
		ArrayList<String> a = new ArrayList<String> ();
		ArrayList<String> b = new ArrayList<String> ();
		ArrayList<String> c = new ArrayList<String> ();
		
		a.add("A"); a.add("B"); a.add("C");
		b.add("D");
		c.add("E");
		
		Iterator[] stringIterators = {null, a.iterator(), null, b.iterator(), null, c.iterator(), null};
				
		Iterator<String> itr = Problem2.iterateStrings(stringIterators);
		assertNotNull(itr);
		
		int counter = 0;
		while(itr.hasNext()) {
			counter++;
			String s = itr.next();
		}
		
		assertEquals(5, counter);
	}
	
	
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
}
