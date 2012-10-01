package com.apple.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Utilize the Static Factory pattern that enables me to 
 * change the underlying implementation without affecting the 
 * clients.
 * 
 * If a null parameter is passed into any of the factory's iterator() methods
 * we will return a NullIterator, which serves as a stub implementation of
 * the Iterator interface.
 * 
 * Data passed into any of the factory's iterator() methods is combined into
 * a data type required by the underlying constructor and purged of any null values. 
 *
 */
public class InteratorFactory {

	/* ---------------------------------------------------
	 * Factory methods
	 * ---------------------------------------------------
	 */
	public static <T> Iterator<T> iterator(T...array) {
		if (null == array)
			return new NullIterator<T>();
		
		return new FailSafeIterator<T>(combineData(array));
	}
	
	public static <T> Iterator<T> iterator(T[]...array) {
		if (null == array)
			return new NullIterator<T>();
		
		List<T> list = combineData(array);
		
		return new FailSafeIterator<T>(list);
	}
	
	public static <T> Iterator<T> iterator(T[][]...array) {
		if (null == array)
			return new NullIterator<T>();
		
		List<T> list = combineData(array);
		
		return new FailSafeIterator<T>(list);
	}
	
	public static <T> Iterator<T> iterator(Iterator<T>...iterators) {
		if (null == iterators)
			return new NullIterator<T>();
		
		return new FailFastIterator<T>(combineData(iterators));
	}
	
	public static <T> Iterator<T> iterator(List<LinkedHashSet<T>> list) {
		if (null == list)
			return new NullIterator<T>();
		
		return new FailFastIterator<T>(combineData(list));
	}
		
	
	/* ---------------------------------------------------
	 * Helper methods
	 * ---------------------------------------------------
	 */
	/**
	 * Combine all of the arrays, purging any null arrays from the input.
	 * 
	 * @param array - The arrays to be combined
	 * @return - A List containing all the non null arrays passed in the via array parameter
	 */
	private static <T> List<T> combineData(T...array) {
		List<T> list = new ArrayList<T> ();
		for (T a : array) {
			if (a != null) {
				list.add(a);
			}
		}
		
		return list;
	}
	
	/**
	 * Combine all of the arrays, purging any null arrays from the input.
	 * 
	 * @param array - The arrays to be combined
	 * @return - A List containing all the non null arrays passed in the via array parameter
	 */
	private static <T> List<T> combineData(T[]...array) {
		List<T> list = new ArrayList<T> ();
		for(T[] a : array) {			
			if (a != null)
				list.addAll(Arrays.asList(a));
		}
		return list;
	}
	
	/**
	 * Combine all of the arrays, purging any null arrays from the input.
	 * 
	 * @param array - The arrays to be combined
	 * @return - A List containing all the non null arrays passed in the via array parameter
	 */
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
	
	/**
	 * Combine all of the Iterators into a list, purging any null Iterators from the input.
	 * 
	 * @param iterators - The array to be combined
	 * @return - A list containing all the non-null Iterators passed in via the iterators parameter.
	 */
	private static <T> List<Iterator<T>> combineData(Iterator<T>...iterators) {
		List<Iterator<T>> list = new ArrayList<Iterator<T>> ();
		
		for (Iterator<T> itr : iterators) {
			if (itr != null) {
				list.add(itr);
			}
		}
		return list;
	}
	
	/**
	 * Combine all of the iterators, purging any null iterators from the input.
	 * 
	 * @param iterators - The Iterators to be combined
	 * @return - A List containing all the non null Iterators passed in the via iterators parameter
	 */
	private static <T> List<Iterator<T>> combineData(List<LinkedHashSet<T>> iterators) {
		List<Iterator<T>> list = new ArrayList<Iterator<T>> ();
				
		Iterator<LinkedHashSet<T>> listIterator = iterators.iterator();
		while(listIterator.hasNext()) {
			Set<T> s = listIterator.next();
			if (s != null)
				list.add(s.iterator());
		}
		
		
		return list;
	}
	
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
	private static class NullIterator<E> implements Iterator<E> {

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
			// no op
		}

	}
	
	/**
	 * An Iterator which follows the fail-safe paradigm, meaning it makes a 
	 * copy of the data it is iterating thereby eliminating the possibility
	 * of ConcurrentModificationExceptions and providing thread-safety without
	 * the need of explicit locking / synchronization by the user.
	 *
	 * @param <E>
	 */
	private static class FailSafeIterator<E> implements Iterator<E> {

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
		public FailSafeIterator(List<E> list) {
			data = new CopyOnWriteArrayList<E>(list);
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
			throw new UnsupportedOperationException("Fail-safe implementation does not allow for mutation of the underlying data strucutre.");
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
		
	/**
	 * An Iterator which follows the fail-fast paradigm, meaning uses the collections
	 * directly which introduces the possibility of ConcurrentModificationExceptions and 
	 * is NOT thread-safe. This closely matches the behavior of most java.util.Collection
	 * based Iterators.
	 * 
	 * ASSUMPTION: Removals of the last item in an underlying Iterator will NOT result in a 
	 * 		       ConcurrentModificationException being thrown. See Note below.
	 * 
	 * NOTE: There is one slight variation in the behavior of my Iterator vs. the behavior in
	 * default fail-fast implementations in the java.util package. Specifically, if last item is
	 * removed from an underlying Iterator, a subsequent call to the next() method will NOT raise
	 * a ConcurrentModificationException. Since my implementation is iterating over a list of 
	 * Iterators, it will have moved on to the next Iterator in the list and therefore consider
	 * the deleted element a non-fatal condition, as we have already iterated past it.
	 *
	 * @param <E>
	 */
	private static class FailFastIterator<E> implements Iterator<E> {
		
		/* -----------------------------------------------------------------------
		 * Instance Variables
		 * -----------------------------------------------------------------------
		 */
		/**
		 * A list of all the Iterator<E>'s that we are going to iterate over.
		 */
		private List<Iterator<E>> iterators;

		/**
		 * An Iterator over the iterators variable
		 */
		private Iterator<Iterator<E>> listIterator;

		/**
		 * The current Iterator<E> we are using to provide values.
		 */
		private Iterator<E> currentIterator;
		
		/**
		 * Kind of a kludge, but since we are dealing with Iterators, the only way
		 * to check for nulls on the hasNext() method is to walk ahead until we find
		 * a non-null value. The problem with that is that once you read a non-null
		 * value, you can't move back one, so we have to store it and return it on the
		 * next call to next()
		 */
		private E peek;


		/* -----------------------------------------------------------------------
		 * Constructors
		 * -----------------------------------------------------------------------
		 */
		public FailFastIterator(List<Iterator<E>> list) {
			this.iterators = list;
			listIterator = iterators.listIterator();
		}


		/* -----------------------------------------------------------------------
		 * Methods defined in the Iterator interface
		 * -----------------------------------------------------------------------
		 */
		@Override
		public boolean hasNext() {
			if ( getCurrentIterator() == null )
				return false;
			
			E lookAhead = null;
			
			while ( (lookAhead == null) && (currentIterator != null)) {
				lookAhead = next();
			}
			
			if (lookAhead != null)
				peek = lookAhead;
			
			return (lookAhead != null);
		}

		@Override
		public E next() {
			
			E next;
			
			if (peek != null) {
				next = peek;
				peek = null;
				return next;
			}
			
			// Handle the case where we have traversed ALL the interators.
			if (getCurrentIterator() == null)
				return null;
			
			// Otherwise, scan ahead to find the next non-null object.
			

			do {
				next = currentIterator.next();
			} while ( (next == null) && (currentIterator.hasNext()));

			// Nothing was found on the current sub-iterator, so increment the sub-iterator and try again.
			if ((next == null) && listIterator.hasNext()) {				
				return next();
			}

			return next;
		}

		@Override
		public void remove() {
			throw new ConcurrentModificationException();
		}


		/* -----------------------------------------------------------------------
		 * Utility Methods 
		 * -----------------------------------------------------------------------
		 */
		protected Iterator<E> getCurrentIterator() {		
			/* There is a slight bug here when the last element of an Iterator is removed,
			 * the hasNext() method incorrectly returns false. This causes my implementation
			 * to skip to the next Iterator in the list, thereby missing the ConcurrentModificationExceptions
			 * that may be thrown if you were to call .next() on the underlying iterator.
			 * 
			 * See the Problem22Test.concurrentModEx() test case for a detailed explanation. 
			 */
			if ( (currentIterator == null) || !currentIterator.hasNext() ) {			
				currentIterator = getNextIterator();
			}
			return currentIterator;
		}

		/**
		 * Increment the listIterator forward.
		 */
		private Iterator<E> getNextIterator() {

			Iterator<E> next = null;
			while ( (next == null) && (listIterator.hasNext())) {
				next = listIterator.next();
			}

			return next;
		}
	}


}
