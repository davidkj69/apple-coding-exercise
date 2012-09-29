package com.apple.interview.iterator;

import java.util.Iterator;

public class IteratorBasedIteratorProvider<T> implements IteratorProvider<T> {

	private Iterator<T>[] arrays;
	
	/**
	 * Keep track of the current array we are using
	 */
	private int currentArrayPointer = 0;
	
	private Iterator<T> currentIterator;
	
	public IteratorBasedIteratorProvider(Iterator<T>... stringIterators) {
		this.arrays = stringIterators;
	}
	
	@Override
	public Iterator<T> getCurrentIterator() {
		if ( (currentIterator == null) || !currentIterator.hasNext()) {
			increment();
		}
		return currentIterator;
	}

	private void increment() {
		if ( (arrays == null) || (currentArrayPointer >= arrays.length) ) {
			currentIterator = new ArrayBasedIteratorProvider<T>((T[])null).getCurrentIterator();
		} else {
			
			Iterator<T> next = null;
			
			do {
				next  = arrays[currentArrayPointer++];
			} while ( (next == null) && (currentArrayPointer < arrays.length) );
			
			if (next != null)
				currentIterator = next;
		}
	}
}
