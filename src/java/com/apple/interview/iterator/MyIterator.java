package com.apple.interview.iterator;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class MyIterator<T> implements Iterator<T> {

	private IteratorProvider<T> provider;
	
	public MyIterator(T...array) {
		provider = new ArrayBasedIteratorProvider<T>(array);
	}
	
	public MyIterator(T[]...arrays) {
		provider = new TwoDimensionalArrayBasedIteratorProvider<T>(arrays);
	}
	
	public MyIterator(T[][]...arrays) {
		provider = new ThreeDimensionalArrayBasedIteratorProvider<T>(arrays);
	}
	
	public MyIterator(Iterator<T>[] iterators) {
		provider = new IteratorBasedIteratorProvider<T>(iterators);
	}
	
	public MyIterator(List<LinkedHashSet<T>> list) {
		provider = new ListOfSetsBasedIteratorProvider<T>(list);
	}

	private Iterator<T> getCurrentIterator() {
		return provider.getCurrentIterator();
	}
	
	@Override
	public boolean hasNext() {	
		if (getCurrentIterator() != null)
			return getCurrentIterator().hasNext();
		else
			return false;
	}

	@Override
	public T next() {			
		if (getCurrentIterator() != null)
			return getCurrentIterator().next();
		else
			return null;
	}

	@Override
	public void remove() {
		if (getCurrentIterator() != null)
			getCurrentIterator().remove();
	}

}
