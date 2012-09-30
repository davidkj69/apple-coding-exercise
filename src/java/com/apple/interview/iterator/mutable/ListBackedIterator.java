package com.apple.interview.iterator.mutable;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class ListBackedIterator<E> implements Iterator<E> {

	List<LinkedHashSet<E>> data;
	
	private Iterator<LinkedHashSet<E>> listIterator;
	
	private Iterator<E> currentIterator;
	
	public ListBackedIterator(List<LinkedHashSet<E>> list) {
		this.data = list;
		if (data != null)
			listIterator = data.listIterator();
	}
	
	@Override
	public boolean hasNext() {
		return getCurrentIterator() != null ? getCurrentIterator().hasNext() : false;
	}

	@Override
	public E next() {
		E next;
		
		do {
			next = getCurrentIterator().next();
		} while ( (next == null) && (currentIterator.hasNext()));
		
		if ((next == null) && listIterator.hasNext()) {
			increment();
			return next();
		}
		
		return next;
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
										
		LinkedHashSet<E> next = null;
		while ( (next == null) && (listIterator != null) && (listIterator.hasNext())) {
			next = listIterator.next();
		}

		if (next != null)
			currentIterator = next.iterator();

	}

}
