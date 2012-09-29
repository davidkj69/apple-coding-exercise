package com.apple.interview;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;


public class IteratorTest {

	public static class MyIterator implements Iterator<String> {

		private List<Iterator<String>> iterators = new ArrayList<Iterator<String>> ();
		private Iterator<String> currentIterator;
		private int counter = 0;
		
		public MyIterator(Iterator<String>... stringIterators) {
			for (Iterator<String> itr : stringIterators) {
				iterators.add(itr);
			}			
		}
		
		private Iterator<String> getCurrentIterator() {
			if ((currentIterator == null) || (!currentIterator.hasNext())) {
				currentIterator = iterators.get(counter++);
			}
			
			return currentIterator;
		}
		
		@Override
		public boolean hasNext() {	
			if (getCurrentIterator() != null)
				return currentIterator.hasNext();
			else
				return false;
		}

		@Override
		public String next() {			
			if (getCurrentIterator() != null)
				return currentIterator.next();
			else
				return null;
		}

		@Override
		public void remove() {
			if (getCurrentIterator() != null)
				currentIterator.remove();
		}
		
	}
	
	public static Iterator<String> iterateStrings(Iterator<String>... stringIterators) {
        return new MyIterator(stringIterators);
    }
	
	@Test
	public void complexTest() {
		
		ArrayList<String> a = new ArrayList<String> ();
		ArrayList<String> b = new ArrayList<String> ();
		ArrayList<String> c = new ArrayList<String> ();
				
		Iterator<String> myItr = iterateStrings(a.iterator(), b.iterator(), c.iterator());
		
		StringWriter aw = new StringWriter(a);
		aw.start();
		StringWriter bw = new StringWriter(b);
		bw.start();		
		StringWriter cw = new StringWriter(c);
		cw.start();
		
		
		try {
			aw.join(9000);
			bw.join(9000);
			cw.join(9000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (myItr.hasNext()) {
			System.out.println(myItr.next());
		}
	}
	
	public static final class StringWriter extends Thread {
		
		private List<String> list;
		
		public StringWriter(List<String> list) {
			this.list = list;
		}
		
		public void run() {
			for (int idx = 0; idx < 100; idx++) {
				String s = "String # " + idx;
				System.out.println("Adding " + s);
				list.add(s);
			}
		}
	}
}
