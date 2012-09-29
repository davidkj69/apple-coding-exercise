package com.apple.interview;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

import com.apple.interview.AppleExercise.Problem1.Cache;


public class ThreadSafeCacheTest extends AbstractCacheTest {

	@Override
	protected AppleExercise.Problem1.Cache getCache() {
		return AppleExercise.Problem1.ThreadSafeCache.getInstance();
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
