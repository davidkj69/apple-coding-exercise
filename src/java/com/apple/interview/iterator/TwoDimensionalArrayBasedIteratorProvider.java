package com.apple.interview.iterator;

import java.util.Iterator;

public class TwoDimensionalArrayBasedIteratorProvider<T> implements IteratorProvider<T> {

	private T[][] arrays;
	
	/**
	 * Keep track of the current array we are using
	 */
	private int currentArrayPointer = 0;
	
	/**
	 * The current provider for the array we are on.
	 */
	private ArrayBasedIteratorProvider<T> currentProvider;
	
	public TwoDimensionalArrayBasedIteratorProvider(T[]...stringArrays) {
		this.arrays = stringArrays;
	}
	
	@Override
	public Iterator<T> getCurrentIterator() {		
		if ( (currentProvider == null) || !currentProvider.getCurrentIterator().hasNext()) {
			increment();
		}
		return currentProvider.getCurrentIterator();
	}
	
	private void increment() {
		
		if ( (arrays == null) || (currentArrayPointer >= arrays.length) )
			currentProvider = new ArrayBasedIteratorProvider<T>((T[])null);
		else {
			
			T[] next = null;
			do {
				next = arrays[currentArrayPointer++];
			} while ( (next == null) && (currentArrayPointer < arrays.length));

			if (next != null)
				currentProvider = new ArrayBasedIteratorProvider<T>(next);
		}
		
	}

}
