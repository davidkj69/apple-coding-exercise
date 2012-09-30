package com.apple.interview.iterator.mutable;

import java.util.Iterator;

public class IteratorBackedIterator<E> implements Iterator<E> {

	private Iterator<E>[] data;
	
	/**
	 * Keep track of the current array we are using
	 */
	private int currentArrayPointer = 0;
	
	private Iterator<E> currentIterator;
	
	public IteratorBackedIterator(Iterator<E>...arrays) {
		this.data = arrays;
	}
	
	
	/* ------------------------------------------------------------------------
	 * Default implementations of methods defined in the Iterator<E> interface
	 * ------------------------------------------------------------------------
	 */
	@Override
	public boolean hasNext() {
		if (data == null)
			return false;
		
		if (currentArrayPointer >= data.length)
			return false;
		
		return getCurrentIterator().hasNext();
	}

	@Override
	public E next() {
		return getCurrentIterator().next();
	}

	@Override
	public void remove() {
		getCurrentIterator().remove();
	}
	
	/* -----------------------------------------------------------------------
	 * Utility methods
	 * -----------------------------------------------------------------------
	 */
	protected Iterator<E> getCurrentIterator() {
		return getCurrentIterator(true);
	}
	
	protected Iterator<E> getCurrentIterator(boolean incrementFlag) {		
		if ( (currentIterator == null) || !currentIterator.hasNext()) {
			if (incrementFlag)
				increment();
		}
		return currentIterator;
	}

	
	private void increment() {

		Iterator<E> next = null;

		do {
			next  = data[currentArrayPointer++];
		} while ( (next == null) && (currentArrayPointer < data.length) );

		if (next != null)
			currentIterator = next;

	}
}
