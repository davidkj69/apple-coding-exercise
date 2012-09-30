package com.apple.interview.iterator.mutable;

import java.util.Iterator;

/**
 * Implementation of the Null Object Pattern :
 * 
 * Provide an object as a surrogate for the lack of an object of a given type. 
 * The Null Object provides intelligent "do nothing" behavior, hiding the details 
 * from its collaborators.
 * 
 * This object serves as a stub implementation of an Iterator<E> when we are
 * given an empty list or array.
 *
 * @param <E>
 */
public class NullIterator<E> implements Iterator<E> {

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public E next() {
		return null;
	}

	@Override
	public void remove() {
	
	}

}
