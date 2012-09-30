package com.apple.interview.iterator.mutable;

import java.util.Iterator;


public class NestedIterator<E> implements Iterator<E> {

	Iterator<E> currentIterator;
	NestedIterator<E> nested;
	int dim;
	
	public NestedIterator(E...a) {
		dim = getDimensionCount(a);
		currentIterator = new OneDimArrayIterator<E> (a);
	}

	@Override
	public boolean hasNext() {
		// Saying go down to the bottom level, i.e. the one dimensional array level
		return getIterator().hasNext();
	}

	@Override
	public E next() {
		return getIterator().next();
	}

	@Override
	public void remove() {
		getIterator().remove();
	}
	
	private NestedIterator<E> getNestedIterator() {
		
		if (dim < 2)
			return null;
		
//		if ((nested == null) || !nested.currentIterator.hasNext()) {
//			nested = new NestedIterator<E> (currentIterator.next());
//		}
		
		return nested;
	}
	
	/**
	 * Walk down the child path to the bottom, then return the currentIterator,
	 * that is the one that Iterates over the single dimension array.
	 * @return
	 */
	private Iterator<E> getIterator() {
		
		NestedIterator<E> child = getNestedIterator();
		NestedIterator<E> parent = null;
		
		while (child == null) {
			parent = child;
			child = child.getNestedIterator();
		}
		
		return parent.currentIterator;
	}

	
	public static int getDimensionCount(Object array) {
		int count = 0;
		Class arrayClass = array.getClass();
		while ( arrayClass.isArray() ) {
			count++;
			arrayClass = arrayClass.getComponentType();
		}
		return count;
	}

	
}
