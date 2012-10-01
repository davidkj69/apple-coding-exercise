package com.apple.interview;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Copyright (C) 2010 Apple Inc.
 *
 * Version 2.7
 *
 * Answer as many or as few questions as you choose.
 * Don't spend too much time on any one problem.
 */
public class AppleExercise {


    /**
     * BEFORE STARTING: please add your name below so that we know who you are.
     *
     * NAME: David Kjerrumgaard
     * DATE: 09/28/2012
     *
     */

    /**
     * Using {@link AppleExercise.Problem1.SampleCache} as a starting point, answer questions 1-3 by
     * creating 3 new inner classes within {@link AppleExercise.Problem1} below.
     */
    public static class Problem1 {

        public interface Cache {
            public void put(Object key, Object value);
            public Object get(Object key);
        }

        public static class SampleCache implements Cache {

            private Map map;

            public SampleCache() {
                map = new HashMap();
            }

            @SuppressWarnings("unchecked")
            public void put(Object key, Object value) {
                map.put(key, value);
            }

            public Object get(Object key) {
                return map.get(key);
            }
        }

        /**
         * Problem 1.1
         *
         * Re-implement {@link AppleExercise.Problem1.SampleCache} as a singleton which implements the Cache interface.
         * Do not use any @SuppressWarnings annotations, and ensure that your implementation compiles without warnings
         * under Java 1.6.
         *
         * Name it {@link AppleExercise.Problem1.SingletonCache}
         * 
         * Solution: A single-element enum implementation of a singleton
         * 
         * Rational : This approach implements the singleton by taking advantage of Java's guarantee that any enum value is 
         *            instantiated only once in a Java program. 
         *            
         * Pros: It uses a simple enumeration, and is therefore simple to implement.
         * 	     It has no special needs for serialization, i.e. no need to implement a readResolve() method. 
         *       It is immune to reflection based attacks. 
         *       Since Java enum values are globally accessible, so is the singleton. 
         *       
         * Cons: The drawback is that the enum type is somewhat inflexible; for example, it does not allow lazy initialization 
         *       or subclassing.
         *            
         * References : Joshua Bloch: Effective Java 2nd edition, ISBN 978-0-321-35668-0, 2008, p. 18
         */
        public enum SingletonCache implements Cache {
        	INSTANCE;               

        	private HashMap<Object, Object> dataStore;

        	private SingletonCache() {
        		dataStore = new HashMap<Object,Object> ();
        	}

        	@Override
        	public void put(Object key, Object value) {	
        		if ( (key != null) && (value != null) )
        			dataStore.put(key, value);
        	}

        	@Override
        	public Object get(Object key) {
        		if (key != null)
        			return dataStore.get(key);
        		else
        			return null;
        	}
       
        };        	                	
        	
        	

        /**
         * Problem 1.2
         *
         * The HashMap class backing the {@link AppleExercise.Problem1.SampleCache} class is not thread-safe.
         * Duplicate and modify your {@link AppleExercise.Problem1.SingletonCache} implementation to ensure
         * that both Cache.put and Cache.get methods are safe to be called by multi-threads.
         * Do not use any @SuppressWarnings annotations, and ensure that your implementation compiles
         * without warnings under Java 1.6.
         *
         * Name it {@link AppleExercise.Problem1.ThreadSafeCache}
         * 
         * Solution: Leverage the {@code java.util.concurrent.ConcurrentHashMap}'s built-in Thread safety mechanisms
         *           by using that class as the underlying dataStore for the cache implementation. (See {@codeSingletonCache}
         *           for singleton implementation rational.)
         * 
         * Rational: Achieving thread-safety without introducing performance bottlenecks is tricky and requires a deep 
         *           understanding of the details of the Java Memory Model, and since these issues have been addressed
         *           in the {@code java.util.concurrent.ConcurrentHashMap} class, we will just leverage their solution.  
         * 
         * Pros: Provides true thread-safety, scalability, and reduced lock granularity. 
         *       ConcurrentHashMap is designed  to optimize retrieval operations; "successful get() operations usually succeed with no 
         *       locking at all." and since in a the typical cache workload retrievals are much more common than updates, a cache should 
         *       be designed to offer very good get() performance.
         * 
         * Cons: The memory footprint of a ConcurrentHashMap (at the instance level) is somewhat larger than a HashMap.
         * 
         * References: Brian Goetz, "Java theory and practice: Concurrent collections classes", 
         * 			   http://www.ibm.com/developerworks/java/library/j-jtp07233/index.html
         */
        public enum ThreadSafeCache implements Cache {
        	INSTANCE;               

        	private ConcurrentHashMap<Object, Object> dataStore;

        	private ThreadSafeCache() {
        		dataStore = new ConcurrentHashMap<Object,Object> ();
        	}

        	@Override
        	public void put(Object key, Object value) {	
        		if ( (key != null) && (value != null) )
        			dataStore.put(key, value);
        	}

        	@Override
        	public Object get(Object key) {
        		if (key != null)
        			return dataStore.get(key);
        		else
        			return null;
        	}
       
        };    
        
        

        /**
         * Problem 1.3
         *
         * Use Java Generics to implement a non-singleton GenericCache class to improve compile-time type checking.
         *
         * Name it {@link AppleExercise.Problem1.GenericCache}
         * 
         * Assumptions: The instructions infer that we can only use 1 class to implement this, so no helper classes, interfaces,
         *              or methods are allowed.
         * 
         * Solution: A parameterized class that implements the methods defined in the Cache interface.
         * 
         * Rational: Based on the requirements, this seems to be the simplest solution, so following the "Keep it simple" principle.
         * 
         * Pros: Least complex solution, easiest to understand, maintain, etc.
         * 
         * Cons: Doesn't explicitly implement the Cache interface defined above, since that interface isn't parameterized  
         *       and we cannot override the interface method definitions in a sub-interface.
         * 
         */
        public static class GenericCache<K,V> {
        	
        	// Making it thread safe.
        	private ConcurrentHashMap<K, V> dataStore = new ConcurrentHashMap<K,V> ();
        	        	
        	// Cache methods 
			public void put(K key, V value) {	
				if ( (key != null) && (value != null) ) 
					dataStore.put(key, value);
			}

			public V get(Object key) {	
				if (key == null)
					return null;
				
				return dataStore.get(key);
			}
        	
        }
    }

    /**
     * Using any Java 1.6 classes or language features, implement these five methods.
     * Create any helper classes or methods that you think contribute to an elegant and
     * efficient solution.
     *
     * If you feel like there is more than one solution to the problem,
     * implement only the one that you feel is "best", and please explain
     * why you think it is a better choice than the alternatives.
     * List any assumptions that you made for your best solution.
     */
    public static class Problem2 {

        /**
         * Problem 2.1
         *
         * Return an Iterator that iterates through of each String in the order that they are provided,
         * but skips {@code null} values.
         *
         * @param strings an array of nullable values
         * @return an iterator of strings
         * 
         * Solution: Provide a thread-safe variant of Iterator that uses a reference to the state of the array at the point that the iterator 
         * 			 was created, by making a fresh copy of the data passed in. This 'snapshot' array never changes during the lifetime of 
         * 			 the {@code Iterator}, so interference is impossible and the iterator is guaranteed not to throw ConcurrentModificationException
         * 			 or reflect any changes to the original array during the course of it's traversal of the array. 
         * 
         * Rational: We want to mimic the fail-fast behavior of the {@code Iterator} interface, but are given an Array object, which 
         *           is NOT part of the {@java.util.Collection} family, so we are still vulnerable to all mutative operations on the 
         *           Array itself (even is the array is wrapped in a {@code Collections.synchronizedList}. Therefore, we need to make
         *           a 'snapshot' of the data as described above.
         * 
         * Pros: Doesn't throw any Exception if underlying data is modified structurally while user is Iterating over it.
         * 		 No need to synchronize traversals, yet still thread-safe.  
         * 
         * Cons: Keeping a copy of the original contents increases memory usage.
         *       Mutations to the underlying Array are not reflected in the Iterator.
         *       
         * Note: The iterator will not reflect additions, removals, or changes to the list since the iterator was created. 
         *       Element-changing operations on iterator itself (remove, set, and add) are not supported and will throw UnsupportedOperationException. 
         * 
         */
        public static Iterator<String> iterateStrings(String... strings) {
            return InteratorFactory.iterator(strings);
        }

        /**
         * Problem 2.2
         *
         * Return a single Iterator<String> that chains together the array of Iterator<String>s in the order
         * that they are provided, but skips {@code null} values.
         *
         * @param stringIterators an array of nullable {@code Iterator<String>}s of nullable {@code String}s
         * @return an {@link java.util.Iterator} of {@code String}s
         * 
         * Solution: Provide a fail-fast variant of Iterator that keeps a list of the Iterators it is passed in. These iterators
         * 			 will be traversed, with the outer Iterator implementation delegating to the internal Iterators for basic Iterator
         * 			 methods (hasNext(), next(), and remove()). Some additional bookkeeping will be performed by the outer Iterator in
         * 			 order to traverse the list of internal Iterators, check for null String values inside the internal Iterators, etc.
         * 
         * Rational: Since the Iterator<String> implementation is fail-fast, we should provide a fail-fast Iterator<String>
         * 			 as well since we are just combining the Iterators into a larger collection. Presumably, this method would
         * 			 be an alternative to Iterating each iterator individually within a for loop.
         * 
         * Pros: Uses less memory since it does not make a 'snapshot' of the data.
         * 		 Conforms to the expected fail-fast behavior that would occur if the user had used the Iterators
         * 		 provided within a loop rather than using our method.
         * 
         * Cons: Requires the user to synchronize traversals in order to be thread-safe as mutations to ANY of the passed in Iterators
         * 	     underlying collection will result in a ConcurrentModificationException.
         * 		 
         * 		 Worse yet, mutations won't at the 'far end' of the array won't be detected until that particular Iterator is processed
         * 		 potentially delaying a fatal condition caused by a ConcurrentModificationException, resulting in lots of unnecessary work,
         * 		 by not failing fast enough.
         * 
         */
        public static Iterator<String> iterateStrings(Iterator<String>... stringIterators) {
            return InteratorFactory.iterator(stringIterators);
        }

        /**
         * Problem 2.3
         *
         * Return an Iterator that iterates through strings of each String[] in the order that they are
         * provided, but skips {@code null} values.
         *
         * @param stringArrays an array of nullable {@code String[}s of nullable {@code String}s
         * @return an {@link java.util.Iterator} of {@code String}s
         * 
         * Solution: Provide a thread-safe variant of Iterator that uses a reference to the state of the array at the point that the iterator 
         * 			 was created, by making a fresh copy of the data passed in. This 'snapshot' array never changes during the lifetime of 
         * 			 the {@code Iterator}, so interference is impossible and the iterator is guaranteed not to throw ConcurrentModificationException
         * 			 or reflect any changes to the original array during the course of it's traversal of the array. 
         * 
         * Rational: We want to mimic the fail-fast behavior of the {@code Iterator} interface, but are given an Array object, which 
         *           is NOT part of the {@java.util.Collection} family, so we are still vulnerable to all mutative operations on the 
         *           Array itself (even is the array is wrapped in a {@code Collections.synchronizedList}. Therefore, we need to make
         *           a 'snapshot' of the data as described above.
         * 
         * Pros: Doesn't throw any Exception if underlying data is modified structurally while user is Iterating over it.
         * 		 No need to synchronize traversals, yet still thread-safe.  
         * 
         * Cons: Keeping a copy of the original contents increases memory usage.
         *       Mutations to the underlying Array are not reflected in the Iterator.
         *       
         * Note: The iterator will not reflect additions, removals, or changes to the list since the iterator was created. 
         *       Element-changing operations on iterator itself (remove, set, and add) are not supported and will throw UnsupportedOperationException. 
         * 
         * 
         */
        public static Iterator<String> iterateStrings(String[]... stringArrays) {
        	return InteratorFactory.iterator(stringArrays);
        }

        /**
         * Problem 2.4
         *
         * Return an Iterator that iterates through all Integers within each two-dimensional Integer array in
         * the order that they are provided, but skips {@code null} values.
         *
         * @param twoDimensionalIntArrays an array of nullable {@code Integer[][]}s of nullable values
         * @return an {@link java.util.Iterator} of {@code Integer}s
         * 
         * Solution: Provide a thread-safe variant of Iterator that uses a reference to the state of the array at the point that the iterator 
         * 			 was created, by making a fresh copy of the data passed in. This 'snapshot' array never changes during the lifetime of 
         * 			 the {@code Iterator}, so interference is impossible and the iterator is guaranteed not to throw ConcurrentModificationException
         * 			 or reflect any changes to the original array during the course of it's traversal of the array. 
         * 
         * Rational: We want to mimic the fail-fast behavior of the {@code Iterator} interface, but are given an Array object, which 
         *           is NOT part of the {@java.util.Collection} family, so we are still vulnerable to all mutative operations on the 
         *           Array itself (even is the array is wrapped in a {@code Collections.synchronizedList}. Therefore, we need to make
         *           a 'snapshot' of the data as described above.
         * 
         * Pros: Doesn't throw any Exception if underlying data is modified structurally while user is Iterating over it.
         * 		 No need to synchronize traversals, yet still thread-safe.  
         * 
         * Cons: Keeping a copy of the original contents increases memory usage.
         *       Mutations to the underlying Array are not reflected in the Iterator.
         *       
         * Note: The iterator will not reflect additions, removals, or changes to the list since the iterator was created. 
         *       Element-changing operations on iterator itself (remove, set, and add) are not supported and will throw UnsupportedOperationException. 
         * 
         */
        public static Iterator<Integer> iterateInts(Integer[][]... twoDimensionalIntArrays) {            
            return InteratorFactory.iterator(twoDimensionalIntArrays);
        }

        /**
         * Problem 2.5
         *
         * Return an Iterator that iterates through all Integers within each set in the order that they are
         * provided, but skips {@code null} values.
         *
         * @param integerSetList a list of nullable {@code LinkedHashSet}s of nullable {@code Integer}s
         * @return an {@link java.util.Iterator} of {@code Integer}s
         * 
         * Solution: Provide a fail-fast variant of Iterator that keeps a list of the Iterators it is passed in. These iterators
         * 			 will be traversed, with the outer Iterator implementation delegating to the internal Iterators for basic Iterator
         * 			 methods (hasNext(), next(), and remove()). Some additional bookkeeping will be performed by the outer Iterator in
         * 			 order to traverse the list of internal Iterators, check for null String values inside the internal Iterators, etc.
         * 
         * Rational: Since the Iterator<String> implementation is fail-fast, we should provide a fail-fast Iterator<String>
         * 			 as well since we are just combining the Iterators into a larger collection. Presumably, this method would
         * 			 be an alternative to Iterating each iterator individually within a for loop.
         * 
         * Pros: Uses less memory since it does not make a 'snapshot' of the data.
         * 		 Conforms to the expected fail-fast behavior that would occur if the user had used the Iterators
         * 		 provided within a loop rather than using our method.
         * 
         * Cons: Requires the user to synchronize traversals in order to be thread-safe as mutations to ANY of the passed in Iterators
         * 	     underlying collection will result in a ConcurrentModificationException.
         * 		 
         * 		 Worse yet, mutations won't at the 'far end' of the array won't be detected until that particular Iterator is processed
         * 		 potentially delaying a fatal condition caused by a ConcurrentModificationException, resulting in lots of unnecessary work,
         * 		 by not failing fast enough.
         * 
         * Note: The IteratorFactory will combine the Iterators from the List<LinkedHashSet<Integer>> to the FailFastIterator by first
         * 	     traversing the List of LinkedHashSet<T> and grabbing each LinkHashSet's Iterator, and eliminating any nulls.
         * 
         */
        public static Iterator<Integer> iterateInts2(List<LinkedHashSet<Integer>> integerSetList) {       
            return InteratorFactory.iterator(integerSetList);
        }

        /**
         * Problem 2.6
         *
         * Bonus question!
         *
         * Despite using generics, invoking one of the methods above in Problem 2 is not guaranteed to be
         * type-safe.
         *
         * Answer the following questions:
         *
         *   a) Which method is it?
         *
         *   b) Does the java compiler let you know? If not, how can you tell? If so, what warning or error
         *      will the compiler give you?
         *
         *   c) What can you do to avoid the problem?
         */
    }


    
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
    public static class InteratorFactory {

    	/* ---------------------------------------------------
    	 * Factory methods
    	 * ---------------------------------------------------
    	 */
    	public static <T> Iterator<T> iterator(T...array) {    		
    		List<T> list = combineData(array);
    		return (list != null) ? new FailSafeIterator<T>(list) : new NullIterator<T>();
    	}
    	
    	public static <T> Iterator<T> iterator(T[]...array) {
    		List<T> list = combineData(array);
    		return (list != null) ? new FailSafeIterator<T>(list) : new NullIterator<T>();
    	}
    	
    	public static <T> Iterator<T> iterator(T[][]...array) {
    		List<T> list = combineData(array);
    		return (list != null) ? new FailSafeIterator<T>(list) : new NullIterator<T>();
    	}
    	
    	public static <T> Iterator<T> iterator(Iterator<T>...iterators) {
    		List<Iterator<T>> list = combineData(iterators);    		
    		return (list != null) ? new FailFastIterator<T>(list) : new NullIterator<T>();
    	}
    	
    	public static <T> Iterator<T> iterator(List<LinkedHashSet<T>> hlist) {
    		List<Iterator<T>> list = combineData(hlist);    		
    		return (list != null) ? new FailFastIterator<T>(list) : new NullIterator<T>();
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
    		
    		if (array == null)
    			return null;
    		
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
    		if (array == null)
    			return null;
    		
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
    		
    		if (array == null)
    			return null;
    		
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
    		
    		if (iterators == null)
    			return null;
    		
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
    		if (iterators == null)
    			return null;
    		
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
    			try {
    				E next = listIterator.next();
    				listIdx++;
    				return next;
    			} catch (final NoSuchElementException nsEx) {
    				return null;
    			}
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
}
