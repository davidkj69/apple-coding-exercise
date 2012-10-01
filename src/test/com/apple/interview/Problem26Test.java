package com.apple.interview;



import static org.junit.Assert.*;

import org.junit.Test;

import com.apple.interview.AppleExercise.Problem2;

public class Problem26Test {

	@Test
	public void typeSafteyTest() {
		
		Object[] unknownType = {1, Boolean.TRUE, "32", new Float(32.6)};
		
		try {
			Problem2.iterateStrings((String[])unknownType);
			fail("Excpected a class cast exception to be thrown");
		} catch (ClassCastException ccEx) {
			assertEquals("[Ljava.lang.Object; cannot be cast to [Ljava.lang.String;", ccEx.getMessage());
		}
	}
}
