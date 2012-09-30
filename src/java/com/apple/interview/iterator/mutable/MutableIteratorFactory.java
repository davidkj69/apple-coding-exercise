package com.apple.interview.iterator.mutable;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class MutableIteratorFactory {

	public static <T> Iterator<T> iterator(T...array) {
		if (null == array)
			return new NullIterator<T>();
		
		return new OneDimArrayIterator<T>(array);
	}
	
	public static <T> Iterator<T> iterator(T[]...array) {
		if (null == array)
			return new NullIterator<T>();
		
		return new TwoDimArrayBackedIterator<T>(array);
	}
	
	public static <T> Iterator<T> iterator(T[][]...array) {
		if (null == array)
			return new NullIterator<T>();
		
		return new ThreeDimArrayBackedIterator<T>(array);
	}
	 
	public static <T> Iterator<T> iterator(Iterator<T>...iterators) {
		if (null == iterators)
			return new NullIterator<T>();
		
		return new IteratorBackedIterator<T>(iterators);
	}
	
	public static <T> Iterator<T> iterator(List<LinkedHashSet<T>> list) {
		if (null == list)
			return new NullIterator<T>();
		
		return new ListBackedIterator<T>(list);
	}
}
