import java.util.Random;

public class Percolation {
	private QuickFindUF uf;
	private Random rand = new Random();
	private int probability = 0;
	private int len;
	private boolean[] isOpen;
	private int top;
	private int bottom;

	// TODO connect top nodes to virtual top and vice-versa (check)
	// test findPosition and replace values for function (check)
	// check adjacent nodes if open (check)
	// found bug on start function, it percolates every time 36 is open
	// open nodes that are not open
	// are nodes connected to top

	// create N-by-N grid, with all sites blocked
	public Percolation(int N) {
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

	public int getProbability() {
		return probability;
	}

	public int getLen() {
		return len;
	}

	public void checkAdjacents(int i, int j) {
		double size = Math.floor(Math.sqrt((double) len));
		int len = (int) size;
		System.out.print("connections: ");
		if (this.isOpen(i + 1, j) && (findPosition(i, j) + len) != top) {
			this.uf.union(findPosition(i, j), (findPosition(i, j) + len));
			System.out.print((findPosition(i, j) + 1) + " -1- " + i + "/" + j);
		}
		if (this.isOpen(i, j + 1)) {
			this.uf.union(findPosition(i, j), findPosition(i, j) + 1);
			System.out.print((findPosition(i, j) + 1) + " ");
		}
		if (this.isOpen(i - 1, j)) {
			this.uf.union(findPosition(i, j), (findPosition(i, j) - len));
			System.out.print((findPosition(i, j) - len) + " ");
		}
		if (this.isOpen(i, j - 1)) {
			this.uf.union(findPosition(i, j), (findPosition(i, j) - 1));
			System.out.print((findPosition(i, j) - 1));
		}
		System.out.println();
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
			System.out.println(e + " out of bounds in open function");
		}
	}

	// is site (row i, column j) open?
	public boolean isOpen(int i, int j) {
		int position = findPosition(i, j);
		return this.isOpen[position];
	}

	// is site (row i, column j) full? connected to top
	public boolean isFull(int i, int j) {
		int position = findPosition(i, j);
		return this.uf.connected(top, position);
	}

	/*
	 * when virtual site on top connected to virtual site on bottom len-1 is
	 * virtual top, len - 2 is virtual bottom
	 */
	public boolean percolates() {
		return uf.connected(top, bottom);
	}

	public void start() {

		int size = (int) Math.floor(Math.sqrt((double) len));
		while (!percolates()) {
			int row = rand.nextInt(size);
			int col = rand.nextInt(size);
			open(row, col);
			System.out.println(findPosition(row, col));
		}
		System.out.println(percolates());
	}

	private int findPosition(int i, int j) {
		double size = Math.floor(Math.sqrt((double) len));
		double position = (i * size) + j;
		return (int) position;
	}

	private void displayTable() {
		double size = Math.floor(Math.sqrt((double) len));
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print("-" + (isOpen(i, j) ? "X" : "O"));
			}
			System.out.print("\t\t");
			for (int j = 0; j < size; j++) {
				int position = findPosition(i, j);
				System.out.print("-" + this.uf.find(position));
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Percolation p = new Percolation(6);
		p.start();
		// p.uf.union(0, 5);

		// p.open(5, 0);
		// System.out.println(p.percolates());
		// System.out.println(p.findPosition(5, 0));

		p.displayTable();
		// System.out.println(p.isFull(0, 0));
	}
}
