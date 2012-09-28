import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class ThreadSafeCacheTest extends AbstractCacheTest {

	@Override
	protected AppleExercise.Problem1.Cache getCache() {
		return AppleExercise.Problem1.ThreadSafeCache.getInstance();
	}

	@Override
	@Test
	public void nullKeyTest() {
		cache.put(null, "value");
		assertEquals(null, cache.get(null));
	}
}
