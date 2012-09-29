package com.apple.interview;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import com.apple.interview.AppleExercise.Problem2;

public class IntegerIteratorTests {

	@Test
	public void threeDimArrayWithoutNullsTest() {
		Integer [][][] a = { { {1,2,3,4,5,6,7}, {8,9,10,11,12,13,14} },
				         { {15,16,17}, {18,19,20,21,22,23,24} }, 
				         { {25,26,27}, {28,29,30,31,32,33,34} }};
		
		Iterator<Integer> itr = Problem2.iterateInts(a);
		assertNotNull(itr);
		assertTrue(itr.hasNext());
		
		for (int idx = 1; idx < 34; idx++) {
			assertEquals(idx, itr.next().intValue());
		}
	}
	
	@Test
	public void threeDimArrayWithNullsTest() {
		Integer [][][] a = { null, { {1,2,3,4,5,6,7}, null, {8,9,10,11,12,13,14} },
		         { {15,16,17}, {18,19,20,21,22,23,24}, null }, 
		         { null,{25,26,27}, {28,29,30,31,32,33,34} },
		          null, null };

		Iterator<Integer> itr = Problem2.iterateInts(a);
		assertNotNull(itr);
		assertTrue(itr.hasNext());

		for (int idx = 1; idx < 34; idx++) {
			assertEquals(idx, itr.next().intValue());
		}
	}
}
