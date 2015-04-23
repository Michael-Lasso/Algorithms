package algorithms.stacks;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	/*
	 * TODO need to work on: removeLast.
	 */

	private int N; // number of elements on queue
	private Node<Item> first; // beginning of queue
	private Node<Item> last; // end of queue

	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
	}

	// construct an empty deque
	public Deque() {
		first = null;
		last = null;
		N = 0;
	}

	// is the deque empty?
	public boolean isEmpty() {
		return first == null;
	}

	// return the number of items on the deque
	public int size() {
		return N;
	}

	// add the item to the front
	public void addFirst(Item item) {
		if (item == null) {
			throw new NullPointerException("Can't add null");
		}
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldfirst;
		if (last == null) {
			last = first;
		}
		N++;
	}

	// remove and return the item from the front
	public Item removeFirst() {
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
		Item item = first.item;
		first = first.next;
		N--;
		if (isEmpty())
			last = null; // to avoid loitering
		return item;
	}

	// add the item to the end
	public void addLast(Item item) {
		Node<Item> oldlast = last;
		last = new Node<Item>();
		last.item = item;
		last.next = null;
		if (isEmpty()) {
			first = last;
		} else {
			oldlast.next = last;
		}
		N++;
	}

	// remove and return the item from the end
	public Item removeLast() {
		Node<Item> current = first;
		Item item;
		if (last == null)
			throw new NoSuchElementException("Queue underflow");
		else if (first == last) {
			item = first.item;
			first = null;
			last = null;
			N--;
		} else {
			while (current.next != last) {
				item = current.item;
				current = current.next;
			}
			item = last.item;
			last = current;
			last.next = null;
			N--;
		}
		return item;
	}

	/**
	 * -return an iterator over items in order from front to end. -Returns an
	 * iterator that iterates over the items in this queue in FIFO order.
	 * 
	 * @return an iterator that iterates over the items in this queue in FIFO
	 *         order
	 */
	public Iterator<Item> iterator() {
		return new ListIterator<Item>(first);
	}

	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator<Item> implements Iterator<Item> {
		private Node<Item> current;

		public ListIterator(Node<Item> first) {
			current = first;
		}

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	public void peekFirst() {
		try {
			if (isEmpty()) {
				throw new NullPointerException("Queue underflow");
			} else {
				System.out.println("Peek First: " + first.item);
			}
		} catch (NullPointerException e) {
			e.getStackTrace();
		}
	}

	public void peekLast() {
		if (isEmpty()) {
			throw new NoSuchElementException("Queue underflow");
		} else {
			System.out.println("Peek Last: " + last.item);
		}
	}
}