package com.apple.interview;


public class SingletonCacheTest extends AbstractCacheTest {

	@Override
	protected AppleExercise.Problem1.Cache getCache() {		
		return AppleExercise.Problem1.SingletonCache.getInstance();
	}
	
}
