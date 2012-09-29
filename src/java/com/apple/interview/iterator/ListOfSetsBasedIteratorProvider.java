package com.apple.interview.iterator;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class ListOfSetsBasedIteratorProvider<T> implements IteratorProvider<T> {

	
	public ListOfSetsBasedIteratorProvider(List<LinkedHashSet<T>> list) {
		
	}
	
	@Override
	public Iterator<T> getCurrentIterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
