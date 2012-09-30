package com.apple.interview.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.apple.interview.AppleExercise;
import com.apple.interview.AppleExercise.Problem1.Cache;

/**
 * Copyright (C) 2010 Apple Inc.
 *
 * Add unit tests that will help verify that your implementation
 * for each problem is correct.
 *
 * Use junit annotations to identify your test methods.
 */
public class SingletonCacheTest {

	protected Cache cache;
	
	
	/* ---------------------------------------------------
	 * Initialization
	 * ---------------------------------------------------
	 */
	@Before
	public void setUp() {
		cache = AppleExercise.Problem1.SingletonCache.getInstance();
	}
	
	/* ---------------------------------------------------
	 * Test Cases
	 * ---------------------------------------------------
	 */
	@Test
	public void nullKeyTest() {
		cache.put(null, "value");
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
		cache.put("foo", "bar");
		assertEquals("bar", cache.get("foo"));
	}
	
	@Test
	public void putOverwriteTest() {
		cache.put("duplicate", "foo");
		cache.put("duplicate", "bar");		
		assertEquals("bar", cache.get("duplicate"));
	}
	
	@Test
	public void getTest() {
		assertNull(cache.get("key"));
		cache.put("key", "value");
		assertEquals("value", cache.get("key"));
	}
	
}
