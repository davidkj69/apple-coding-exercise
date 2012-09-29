package com.apple.interview.iterator;


public class TwoDimArrayBackedIterator<E> extends MultiDimArrayBackedIterator<E> {

	private E[][] arrays;
	
	public TwoDimArrayBackedIterator(E[]...data) {
		this.arrays = data;
		getCurrentIterator();
	}

	protected void increment() {
		
		if ( (arrays == null) || (currentArrayPointer >= arrays.length) )
			currentIterator = new ArrayBackedIterator<E>((E[])null);
		else {
			
			E[] next = null;
			do {
				next = arrays[currentArrayPointer++];
			} while ( (next == null) && (currentArrayPointer < arrays.length));

			if (next != null)
				currentIterator = new ArrayBackedIterator<E>(next);
		}
		
	}
}
