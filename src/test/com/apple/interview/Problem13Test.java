package com.apple.interview;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.apple.interview.AppleExercise.Problem1.GenericCache;

/**
 * Copyright (C) 2010 Apple Inc.
 *
 * Add unit tests that will help verify that your implementation
 * for each problem is correct.
 *
 * Use junit annotations to identify your test methods.
 */
public class Problem13Test  {

	private GenericCache<String, Integer> cache;
	
	@Before
	public void setUp() {
		cache = new GenericCache<String, Integer> ();
	}
	
	@Test
	public void nullKeyTest() {
		cache.put(null, 1);
		assertEquals(null, cache.get(null));
	}
	
	@Test
	public void nullValueTest() {	
		cache.put("nullKey", null);
		assertNull(cache.get("nullKey"));
	}
	
	@Test
	public void getNonExistantKeyTest() {
		assertNull(cache.get("blah"));
	}
	
	@Test
	public void putTest() {
		cache.put("foo", 2);
		assertEquals(2, cache.get("foo").intValue());
	}
	
	@Test
	public void putOverwriteTest() {
		cache.put("duplicate", 3);
		cache.put("duplicate", 4);		
		assertEquals(4, cache.get("duplicate").intValue());
	}
	
	
	@Test
	public void getTest() {
		assertNull(cache.get("key"));
		cache.put("key", 5);
		assertEquals(5, cache.get("key").intValue());
	}
	

}
