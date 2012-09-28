

/**
 * Copyright (C) 2010 Apple Inc.
 *
 * Add unit tests that will help verify that your implementation
 * for each problem is correct.
 *
 * Use junit annotations to identify your test methods.
 */
public final class SampleCacheTest extends AbstractCacheTest {
		
	@Override
	protected AppleExercise.Problem1.Cache getCache() {		
		return new AppleExercise.Problem1.SampleCache();
	}
}
