package algorithms;

import java.util.Random;
import library.algorithms.*;

public class Percolation {
	private QuickFindUF uf;
	private Random rand = new Random();
	private double probability = 0;
	private int len;
	private boolean[] isOpen;
	private int top;
	private int bottom;
	private int size;

	// create N-by-N grid, with all sites blocked
	public Percolation(int N) {
		size = N;
		this.len = (N * N) + 2;
		this.isOpen = new boolean[len];
		this.uf = new QuickFindUF(len);
		top = (N * N);
		bottom = len - 1;
		for (int i = 0; i < len; i++) {
			this.isOpen[i] = false;
		}
		/*
		 * 
		 */
		for (int i = 0; i < N; i++) {
			uf.union(top, i);
			uf.union(bottom, (((N * N) - 1) - i));
		}
		this.isOpen[top] = true;
		this.isOpen[bottom] = true;
	}

	public double getProbability() {
		return probability / (size * size);
	}

	public int getLen() {
		return len;
	}

	public void checkAdjacents(int i, int j)
			throws ArrayIndexOutOfBoundsException {
		int len = size;
		if (this.isOpen(i + 1, j) && ((findPosition(i + 1, j)) < top)) {
			this.uf.union(findPosition(i, j), (findPosition(i + 1, j)));
		}
		if (this.isOpen(i - 1, j)) {
			this.uf.union(findPosition(i, j), (findPosition(i, j) - len));
		}
		if (this.isOpen(i, j + 1) && (findPosition(i, j + 1) % len != 0)) {
			this.uf.union(findPosition(i, j), findPosition(i, j) + 1);
		}
		if (this.isOpen(i, j - 1) && (findPosition(i, j) % len != 0)) {
			this.uf.union(findPosition(i, j), (findPosition(i, j) - 1));
		}
	}

	// open site (row i, column j) if it is not already
	public void open(int i, int j) {
		int position = findPosition(i, j);
		try {
			if (isOpen(i, j)) {
			} else {
				this.isOpen[position] = true;
				this.probability++;
				checkAdjacents(i, j);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}

	// is site (row i, column j) open?
	public boolean isOpen(int i, int j) {
		try {
			int position = findPosition(i, j);
			return this.isOpen[position];
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		return false;
	}

	// is site (row i, column j) full? connected to top
	public boolean isFull(int i, int j) {
		int position = findPosition(i, j);
		if (position < len - 2) {
			return this.uf.connected(top, position);
		}
		return false;
	}

	/*
	 * when virtual site on top connected to virtual site on bottom len-1 is
	 * virtual top, len - 2 is virtual bottom
	 */
	public boolean percolates() {
		return uf.connected(top, bottom);
	}

	public void start() {
		while (!percolates()) {
			int row = rand.nextInt(size);
			int col = rand.nextInt(size);
			open(row, col);
		}
	}

	private int findPosition(int i, int j) {
		double position = (i * size) + j;
		return (int) position;
	}

	public void displayTable() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (isFull(i, j) && isOpen(i, j)) {
					System.out.print("-A");
				} else {
					System.out.print("-" + (isOpen(i, j) ? "X" : "O"));
				}
			}
			System.out.print("\t\t");
			for (int j = 0; j < size; j++) {
				int position = findPosition(i, j);
				System.out
						.print("  "
								+ ((this.uf.find(position) < 10) ? (this.uf
										.find(position) + " ") : this.uf
										.find(position)));
			}
			System.out.println();
		}
		System.out.println("probability is: " + probability);
	}
}
