package com.apple.interview.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.apple.interview.AppleExercise;
import com.apple.interview.AppleExercise.Problem1.*;

/**
 * Copyright (C) 2010 Apple Inc.
 *
 * Add unit tests that will help verify that your implementation
 * for each problem is correct.
 *
 * Use junit annotations to identify your test methods.
 */
public class ThreadSafeCacheTest {

	protected Cache cache;
	
	
	/* ---------------------------------------------------
	 * Initialization
	 * ---------------------------------------------------
	 */
	@Before
	public void setUp() {
		cache = AppleExercise.Problem1.ThreadSafeCache.getInstance();
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

	@Test
	public void concurrentAccessTest() {
		
		Map<String,String> t1Data = new HashMap<String,String> ();
		Map<String,String> t2Data = new HashMap<String,String> ();
		String[] KEYS = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
		
		// Generate random values for each cache writer for the same keys
		for (int idx = 0; idx < KEYS.length; idx++) {
			t1Data.put(KEYS[idx], Long.toHexString(Double.doubleToLongBits(Math.random())));
			t2Data.put(KEYS[idx], Long.toHexString(Double.doubleToLongBits(Math.random())));
		}
		
		CacheWriterThread t1 = new CacheWriterThread(cache, t1Data);
		CacheWriterThread t2 = new CacheWriterThread(cache, t2Data);
		
		t1.start();
		t2.start();
		
		try {
			t1.join(10000);
			t2.join(10000);
		} catch (final Exception ex) {
			
		}
		
		for (int idx = 0; idx < KEYS.length; idx++) {
			Object data = cache.get(KEYS[idx]);
			System.out.println(data);
			if (data.equals(t1Data.get(KEYS[idx]))) {
				System.out.println(" from t1");
			} else if (data.equals(t2Data.get(KEYS[idx]))) {
				System.out.println(" from t2");
			}
		}
		
	}
	
	private static class CacheWriterThread extends Thread {
		
		private Random rnd = new Random();
		private Cache theCache;
		private Map<String,String> data;
		
		public CacheWriterThread(Cache c, Map<String,String> map) {
			this.theCache = c;
			this.data = map;
		}
		
		public void run() {
			
			for (String key : data.keySet()) {
				try {
					Thread.sleep(rnd.nextInt(10) * 100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				theCache.put(key, data.get(key));
			}
			
		}
		
		
	}
	
}
