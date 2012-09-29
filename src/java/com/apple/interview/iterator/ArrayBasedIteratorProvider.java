package com.apple.interview.iterator;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayBasedIteratorProvider<T> implements IteratorProvider<T> {

	private Iterator<T> currentIterator;
	
	public ArrayBasedIteratorProvider(T...strings) {
		ArrayList<T> list;
    	
    	if ((strings == null) || strings.length < 1) {
    		list = new ArrayList<T> ();
    	} else {
    		list = new ArrayList<T> (strings.length);

    		for(T s : strings) {
    			if (s != null) {
    				list.add(s);
    			}
    		}
    	}
    	
    	currentIterator = list.iterator();
	}
	
	@Override
	public Iterator<T> getCurrentIterator() {		
		return currentIterator;
	}

}
