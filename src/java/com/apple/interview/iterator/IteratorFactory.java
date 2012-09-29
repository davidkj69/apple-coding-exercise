package com.apple.interview.iterator;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class IteratorFactory {

	public static <T> Iterator<T> iterator(T...array) {
		return new ArrayBackedIterator<T>(array);
	}
	
	public static <T> Iterator<T> iterator(T[]...array) {
		return new TwoDimArrayBackedIterator<T>(array);
	}
	
	public static <T> Iterator<T> iterator(T[][]...array) {
		return new ThreeDimArrayBackedIterator<T>(array);
	}
	 
	public static <T> Iterator<T> iterator(Iterator<T>...iterators) {
		return new IteratorBackedIterator<T>(iterators);
	}
	
	public static <T> Iterator<T> iterator(List<LinkedHashSet<T>> list) {
		return null;
	}
}
