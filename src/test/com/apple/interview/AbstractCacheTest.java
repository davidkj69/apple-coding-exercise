package com.apple.interview;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.apple.interview.AppleExercise.Problem1.Cache;

/**
 * A collection of tests that ALL Cache implements must pass
 * in order to be considered a fully functional Cache.
 * 
 * @author David Kjerrumgaard
 *
 */
public abstract class AbstractCacheTest {

	protected Cache cache;
	
	/* ---------------------------------------------------
	 * Abstract method definitions
	 * ---------------------------------------------------
	 */
	protected abstract AppleExercise.Problem1.Cache getCache();
	
	
	@Before
	public void setUp() {
		cache = getCache();
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
