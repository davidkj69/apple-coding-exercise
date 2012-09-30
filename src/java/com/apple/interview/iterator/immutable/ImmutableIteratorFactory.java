package com.apple.interview.iterator.immutable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import com.apple.interview.iterator.mutable.NullIterator;

public class ImmutableIteratorFactory {

	public static <T> Iterator<T> iterator(T...array) {
		if (null == array)
			return new NullIterator<T>();
		
		return new ImmutableIterator<T>(Arrays.asList(array));
	}
	
	public static <T> Iterator<T> iterator(T[]...array) {
		if (null == array)
			return new NullIterator<T>();
		
		return new ImmutableIterator<T>(combineData(array));
	}
	
	public static <T> Iterator<T> iterator(T[][]...array) {
		if (null == array)
			return new NullIterator<T>();
		
		List<T> list = combineData(array);
		
		return new ImmutableIterator<T>(list);
	}
	 
	public static <T> Iterator<T> iterator(Iterator<T>...iterators) {
		if (null == iterators)
			return new NullIterator<T>();
				
		return new ImmutableIterator<T>(combineData(iterators));
	}
	
	public static <T> Iterator<T> iterator(List<LinkedHashSet<T>> linkedHashSetList) {
		if (linkedHashSetList == null)
			return new NullIterator<T>();

		return new ImmutableIterator<T>(combineData(linkedHashSetList));
	}
	
	
	
	private static <T> List<T> combineData(T[]...array) {
		List<T> list = new ArrayList<T> ();
		for(T[] a : array) {			
			if (a != null)
				list.addAll(Arrays.asList(a));
		}
		return list;
	}
	
	private static <T> List<T> combineData(T[][]...array) {
		List<T> list = new ArrayList<T> ();
		for(T[][] a : array) {
			if (a != null)
				for(T[] b : a) {
					if (b != null)
						list.addAll(Arrays.asList(b));
				}
		}
		return list;
	}
	
	private static <T> List<T> combineData(Iterator<T>...iterators) {
		List<T> list = new ArrayList<T> ();
		for (Iterator<T> itr : iterators) {
			if (itr != null)
				while(itr.hasNext()) {
					list.add(itr.next());
			}
		}
		return list;
	}
	
	private static <T> List<T> combineData(List<LinkedHashSet<T>> linkedHashSetList) {
		List<T> list = new ArrayList<T> ();
		for(LinkedHashSet<T> lhs : linkedHashSetList) {
			if (lhs != null) {
				Iterator<T> itr = lhs.iterator();
				while(itr.hasNext()) {
					list.add(itr.next());
				}
			}
		}
		return list;
	}
}
