package com.apple.interview.iterator.mutable;


public class ThreeDimArrayBackedIterator<E> extends MultiDimArrayBackedIterator<E> {

	private E[][][] arrays;
	
	public ThreeDimArrayBackedIterator(E[][][] data) {
		this.arrays = data;
		getCurrentIterator();
	}
	
	protected void increment() {
		
		if ( (arrays == null) || (currentArrayPointer >= arrays.length) )
			currentIterator = new TwoDimArrayBackedIterator<E>((E[][])null);
		else {
			
			E[][] next = null;
			do {
				next = arrays[currentArrayPointer++];
			} while ( (next == null) && (currentArrayPointer < arrays.length));

			if (next != null)
				currentIterator = new TwoDimArrayBackedIterator<E>(next);
		}
		
	}

}
