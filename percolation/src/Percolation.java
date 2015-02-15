import java.util.Random;

public class Percolation {

	private int[] grid;
	private int[] sz;
	private Random rand = new Random();
	private int probability = 0;
	private int len;
	private int[] isOpen;

	// create N-by-N grid, with all sites blocked
	public Percolation(int N) {
		this.grid = new int[(N * N) + 2];
		this.sz = new int[(N * N) + 2];
		this.isOpen = new int[(N * N)];
		this.len = N;
		for (int i = 0; i < this.grid.length - 2; i++) {
			this.grid[i] = i;
			sz[i] = 1;
			this.isOpen[i] = -1;
		}
		this.grid[(len * len)] = len - 1;// virtual top
		this.grid[(len * len) + 1] = len - 2;// virtual bottom
	}

	public int getProbability() {
		return probability;
	}

	public int getLen() {
		return len;
	}

	public int[] getGrid() {
		return grid;
	}

	// open site (row i, column j) if it is not already
	public void open(int i, int j) {
		try {
			if (isOpen(i, j)) {
				System.out.println("is open");
			} else {
				// open square
				System.out.println("is not open");
				this.isOpen[i * len + j] = 1;
				this.probability++;
				connect(i, j);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e + " out of bounds");
		}
	}

	// is site (row i, column j) open?
	public boolean isOpen(int i, int j) {

		try {
			int o = i * this.len + j;
			System.out.println("\nrow/column" + i + "/" + j + " : " + o);
			return (this.isOpen[o] == -1) ? false : true;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e + " out of bounds");
		}
		return false;
	}

	// is site (row i, column j) full?
	public boolean isFull(int i, int j) {
		return false;
	}

	// does the system percolate?
	public boolean percolates() {
		/*
		 * when virtual site on top connected to virtual site on bottom len-1 is
		 * virtual top, len - 2 is virtual bottom
		 */
		return connected(this.grid[this.len - 1], this.grid[this.len - 2]);
	}

	/*
	 * union find algorithm after this line
	 * -------------------------------------------------------
	 */

	private int root(int i) {
		while (i != grid[i]) {
			grid[i] = grid[grid[i]];
			i = grid[i];
		}
		return i;
	}

	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}

	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		if (i == j)
			return;
		if (sz[i] < sz[j]) {
			grid[i] = j;
			sz[j] += sz[i];
		} else {
			grid[j] = i;
			sz[i] += sz[j];
		}
	}

	/*
	 * function to start percolation
	 */

	public void start() {

		while (!percolates()) {
			int row = rand.nextInt(this.grid.length) - 1;
			int col = rand.nextInt(this.grid.length) - 1;

			open(row, col);
		}
	}

	// row i, col j connects to its adjacents
	public void connect(int i, int j) {
		int o = (i * this.len) + j;
		if (i == 0) {
			// union(i, this.grid[len * len]);
		}
		if (i == this.len - 1) {
			// union(i, this.grid[(len * len) + 1]);
		}
		try {
			if (isOpen(i, j + 1)) {
				System.out.println("first " + i + "/" + j);
				union(o, o + 1);
			}
			if (isOpen(i, j - 1)) {
				System.out.println("second " + i + "/" + j);
				union(o, o - 1);
			}
			if (isOpen(i + 1, j)) {
				System.out.println("third " + i + "/" + j);
				union(o, o + this.len);
			}
			if (isOpen(i - 1, j)) {
				System.out.println("fourth " + i + "/" + j);
				union(o, o - this.len);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e + " out of bounds");
		}
	}

	public void displayTable() {
		for (int i = 0; i < this.len; i++) {
			for (int j = 0; j < this.len; j++) {
				System.out.print("\t" + this.grid[(this.len * i) + j] + " ");
			}
			System.out.println();
		}
	}

	public void display() {
		for (int i = 0; i < this.len; i++) {
			for (int j = 0; j < this.len; j++) {
				if (this.isOpen[(i * this.len) + j] == -1) {
					System.out.print("- ");
				} else {
					System.out.print("o ");
				}
			}
			System.out.println();
		}

	}
}
