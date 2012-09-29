package com.apple.interview.iterator;

import java.util.Iterator;

public interface IteratorProvider<T> {

	public Iterator<T> getCurrentIterator();
	
}
