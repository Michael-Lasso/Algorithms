package algorithms;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private int N; // number of elements on queue
	private Node<Item> first; // beginning of queue
	private Node<Item> last; // end of queue

	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
	}

	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	// construct an empty randomized queue
	public RandomizedQueue() {
		first = null;
		last = null;
		N = 0;
	}

	// return the number of items on the queue
	public int size() {
		return N;
	}

	// is the queue empty?
	public boolean isEmpty() {
		return first == null;
	}

	// add the item
	public void enqueue(Item item) {
		if (item == null) {
			throw new NullPointerException("Can't add null");
		}
		Node<Item> oldlast = last;
		last = new Node<Item>();
		last.item = item;
		last.next = oldlast;
		if (isEmpty()) {
			first = last;
		} else {
			oldlast.next = last;
		}
		N++;
	}

	// remove and return a random item
	public Item dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("Empty Queue");
		}
		Node<Item> node = first;
		Item removed;
		if (N == 1) {
			removed = node.item;
			node = null;
			N--;
			return removed;
		}
		Random rand = new Random();
		int removePosition = rand.nextInt(N) + 1;
		for (int i = 0; i < removePosition - 2; i++) {
			node = node.next;
		}
		removed = node.next.item;
		if (removePosition == N) {
			node.next = null;
		} else {
			node.next = node.next.next;
		}
		N--;
		return removed;
	}

	// return (but do not remove) a random item
	public Item sample() {
		if (isEmpty()) {
			throw new NoSuchElementException("Empty Queue");
		}
		Node<Item> node = first;
		if (N == 1) {
			return node.item;
		}
		Random rand = new Random();
		int removePosition = rand.nextInt(N);
		for (int i = 0; i < removePosition; i++) {
			node = node.next;
		}
		return node.item;
	}

	// unit testing
	public static void main(String[] args) {
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		rq.enqueue("1");
		rq.enqueue("2");
		rq.enqueue("3");
		rq.enqueue("4");
		rq.enqueue("5");
		System.out.println(rq.dequeue());
		System.out.println(rq.sample());

	}
}
