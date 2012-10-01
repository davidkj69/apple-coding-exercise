package com.apple.interview;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;

import org.junit.Before;
import org.junit.Test;

import com.apple.interview.AppleExercise;
import com.apple.interview.AppleExercise.Problem1.Cache;
import com.apple.interview.AppleExercise.Problem1.SingletonCache;

/**
 * Copyright (C) 2010 Apple Inc.
 *
 * Add unit tests that will help verify that your implementation
 * for each problem is correct.
 *
 * Use junit annotations to identify your test methods.
 */
public class Problem11Test {

	protected Cache cache;
	
	
	private static final String getNextRandomString() {
		return Long.toHexString(Double.doubleToLongBits(Math.random()));
	}
	
	
	/* ---------------------------------------------------
	 * Initialization
	 * ---------------------------------------------------
	 */
	@Before
	public void setUp() {
		cache = AppleExercise.Problem1.SingletonCache.INSTANCE;
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
	
	/**
	 * Show that two different calls to the SingletonCache.INSTANCE return
	 * the same object, including the underlying dataStore
	 */
	@Test
	public void equalityTest() {
		
		// Get two singletonCaches the 'normal' way
		SingletonCache s1 = SingletonCache.INSTANCE;
		SingletonCache s2 = SingletonCache.INSTANCE;
		
		// Verify that they are references to the same object.
		assertEquals(s1, s2);
		
		String key = getNextRandomString();
		String value = getNextRandomString();
		
		// Verify they are accessing the same underlying dataStore object
		s1.put(key, value);
		assertEquals(value, s2.get(key));
	}
	
	/**
	 * Verify that values stored thru one reference to the singleton
	 * are accessible by a different reference to the singleton
	 */
	@Test
	public void sharedDataStoreTest() {
		
		SingletonCache s1 = SingletonCache.INSTANCE;
		SingletonCache s2 = SingletonCache.INSTANCE;
		
		for (int idx = 0; idx < 100; idx++) {
			String k = getNextRandomString();
			String v = getNextRandomString();
			s1.put(k, v);
			assertEquals(v, s2.get(k));
		}
	}
	
	
	/**
	 * Test the singletonCache using a Reflection attack to attempt to create multiple
	 * instances of the SingletonCache class.
	 * 
	 */
	@Test 
	public void reflectionAttackTest() {
		
				
		// Show that the singleton is immune to reflection attacks, as it has no constructors to exploit
		try {
			
			// Grab the "private" constructor and changes its accessibility to public
			Constructor<SingletonCache> c = SingletonCache.class.getDeclaredConstructor();
			c.setAccessible(true);
			fail("Expected a NoSuchMethodException to have been thrown");
			
		} catch (NoSuchMethodException e) {
			assertEquals("com.apple.interview.AppleExercise$Problem1$SingletonCache.<init>()", e.getMessage());
		} catch (Exception e) {
			fail("Expected a NoSuchMethodException to have been thrown");
		} 
	
		
	}
	
}
