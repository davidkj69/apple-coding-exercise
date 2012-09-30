package com.apple.interview.iterator.mutable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MultiFailFastIterator<E> implements Iterator<E>  {

	private List<OneDimArrayIterator<E>> data;
	private Iterator<OneDimArrayIterator<E>> listIterator;
	protected Iterator<E> currentIterator;
	
	
	public MultiFailFastIterator(E[]...a) {
		
		data = new ArrayList<OneDimArrayIterator<E>> ();
		
		for (E[] e : a) {
			if (e != null) {
				data.add(new OneDimArrayIterator<E>(e));
			}
		}
		
		listIterator = data.listIterator();
	}

	@Override
	public boolean hasNext() {
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
	
	protected Iterator<E> getCurrentIterator() {
		return getCurrentIterator(true);
	}
	
	protected Iterator<E> getCurrentIterator(boolean incrementFlag) {		
		if ( (currentIterator == null) || !currentIterator.hasNext()) {
			if (listIterator.hasNext())
				currentIterator = listIterator.next();
		}
		return currentIterator;
	}
	
}
