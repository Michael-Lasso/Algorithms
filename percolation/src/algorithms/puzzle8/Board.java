package algorithms.puzzle8;

public class Board {
	int[][] blocks;

	// construct a board from an N-by-N array of blocks (where blocks[i][j] =
	// block in row i, column j)
	public Board(int[][] blocks) {
		this.blocks = blocks;
	}

	// board dimension N
	public int dimension() {
		return blocks.length;
	}

	// number of blocks out of place
	public int hamming() {
		return 0;
	}

	// sum of Manhattan distances between blocks and goal
	public int manhattan() {
		return 0;
	}

	// is this board the goal board?
	public boolean isGoal() {
		int N = dimension();
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				int valueExpected = ((row * N) + col) + 1;
				if (blocks[row][col] != valueExpected) {
					return false;
				}
			}
		}
		return true;
	}

	// a boadr that is obtained by exchanging two adjacent blocks in the same
	// row
	public Board twin() {
		return null;
	}

	// does this board equal y?
	public boolean equals(Object y) {
		return false;
	}

	// all neighboring boards
	public Iterable<Board> neighbors() {
		return null;
	}

	// string representation of this board (in the output format specified
	// below)
	public String toString() {
		for (int i = 0; i < blocks.length; i++) {

		}
		return "";
	}

	// unit tests (not graded)
	public static void main(String[] args) {
		int[][] test = new int[4][4];
		Board b = new Board(test);
		System.out.println(b.blocks.length);

	}
}