package com.apple.interview.iterator.immutable;

import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

public class ImmutableIterator<E> implements Iterator<E> {

	/* -----------------------------------------------------------------------
	 * Instance Variables
	 * -----------------------------------------------------------------------
	 */
	private List<E> data;
	private Iterator<E> listIterator;
	
	/**
	 * Used to keep track of where we are in the list, w.r.t
	 * the iterator so we can 'peek' ahead for nulls in the
	 * hasNext() method
	 */
	private int listIdx = 0;
	
	
	/* -----------------------------------------------------------------------
	 * Constructor
	 * -----------------------------------------------------------------------
	 */
	public ImmutableIterator(List<E> list) {
		data = Collections.synchronizedList(list);
		listIterator = data.listIterator();
	}
	
	@Override
	public boolean hasNext() {		
		for (int idx = listIdx; idx < data.size(); idx++) {
			if (data.get(idx) != null)
				return true;
		}
		
		return false;
	}

	/* -----------------------------------------------------------------------
	 * Methods defined in the Iterator interface
	 * -----------------------------------------------------------------------
	 */
	/**
	 * Scan forward thru the list until we find a non null value and return it.
	 */
	@Override
	public E next() {		
		E next = getNextAndIncrement();
				
		while ( (next == null) && listIterator.hasNext()) {
			next = getNextAndIncrement();			
		} 
		
		return next;
	}

	@Override
	public void remove() {	
		throw new ConcurrentModificationException("");
	}
	
	
	/* -----------------------------------------------------------------------
	 * Utility Methods 
	 * -----------------------------------------------------------------------
	 */
	private E getNextAndIncrement() {
		E next = listIterator.next();
		listIdx++;
		return next;
	}

}
