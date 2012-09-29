package com.apple.interview.iterator;

import java.util.Iterator;

public class ArrayBackedIterator<T> implements Iterator<T> {

	private T[] data;
	private int currentArrayPointer;
	
	public ArrayBackedIterator(T...array) {
		this.data = array;
	}
	
	/**
	 * Need to look ahead in the array to see if any non-null elements remain
	 * past the currentArrayPointer position.
	 * 
	 */
	@Override
	public boolean hasNext() {
		
		if (data == null)
			return false;
		
		if (currentArrayPointer >= data.length)
			return false;
		
		return (nextNonNullIndex() < data.length);
	}

	@Override
	public T next() {	
		// TODO figure out how to use nextNonNullIndex() here
		T next = null;
		do {
			next = data[currentArrayPointer++];
		} while ( (next == null) && (currentArrayPointer < data.length));
		
		return next;
		
	}

	@Override
	public void remove() {
		currentArrayPointer++;		
	}
	
	/**
	 * Return the index of the next non-null element
	 */
	private int nextNonNullIndex() {
		int nonNullIndex = currentArrayPointer;
		T next = null;
		do {
			next = data[nonNullIndex];
		} while ( (next == null) && (++nonNullIndex < data.length));
		
		return nonNullIndex;
	}

}
