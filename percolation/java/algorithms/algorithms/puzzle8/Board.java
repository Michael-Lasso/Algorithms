package algorithms.puzzle8;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
	int[][] blocks;
	int N;

	// construct a board from an N-by-N array of blocks (where blocks[i][j] =
	// block in row i, column j)
	public Board(int[][] blocks) {
		this.blocks = blocks;
		N = blocks.length;
	}

	// board dimension N
	public int dimension() {
		return N;
	}

	// number of blocks out of place
	public int hamming() {
		int hamming = 0;
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				int valueExpected = ((row * N) + col) + 1;
				if (blocks[row][col] == 0) {
					// do nothing, block 0 not counted
				} else if (blocks[row][col] != valueExpected) {
					hamming++;
				}
			}
		}
		return hamming;
	}

	// sum of Manhattan distances between blocks and number of moves
	public int manhattan() {
		int manhattan = 0;
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				int valueExpected = ((row * N) + col) + 1;
				if (blocks[row][col] == 0) {
					// do nothing, block 0 not counted
				} else if (blocks[row][col] != valueExpected) {
					int pos = Math.abs(blocks[row][col]);
					manhattan += Math
							.abs(findRow(pos) - findRow(valueExpected))
							+ Math.abs(findCol(pos) - findCol(valueExpected));
				}
			}
		}
		return manhattan;
	}

	// is this board the goal board?
	public boolean isGoal() {
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				int valueExpected = ((row * N) + col) + 1;
				if (((row + 1) * (1 + col)) - 1 == ((N * N) - 1)) {
					// last position, do nothing
				} else if (blocks[row][col] != valueExpected) {
					return false;
				}
			}
		}
		return true;
	}

	// does this board equal y?
	public boolean equals(Object y) {
		if (y == this)
			return true;
		if (y == null)
			return false;
		if (y.getClass() != this.getClass())
			return false;

		final Board that = (Board) y;
		if (this.blocks.length != that.blocks.length)
			return false;

		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (this.blocks[row][col] != that.blocks[row][col]) {
					return false;
				}
			}
		}
		return true;
	}

	// string representation of this board (in the output format specified
	// below)
	public String toString() {

		StringBuilder s = new StringBuilder();
		s.append(N + "\n");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				s.append(String.format("%2d ", blocks[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
	}

	// all neighboring boards
	public Iterable<Board> neighbors() {
		List<Board> neighbors = new ArrayList<Board>();
		int blankSpace = findBlankSpace();
		neighbors.add(swap(blankSpace, blankSpace - 1));
		neighbors.add(swap(blankSpace, blankSpace + 1));
		neighbors.add(swap(blankSpace, blankSpace - N));
		neighbors.add(swap(blankSpace, blankSpace + N));
		return neighbors;
	}

	// a board that is obtained by exchanging two adjacent blocks in the same
	// row
	public Board twin() {
		Random r = new Random();
		int randomBlock = r.nextInt((N * N) + 1);
		// check blocks are not 0
		while (randomBlock == findBlankSpace() || randomBlock % N == 0
				|| randomBlock == N * N
				|| (randomBlock + 1) == findBlankSpace()) {
			randomBlock = r.nextInt((N * N) + 1);
		}
		return swap(randomBlock, randomBlock + 1);
	}

	// -------------------+ Utility Functions +-----------------

	private int findBlankSpace() {
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (blocks[row][col] == 0) {
					return ((row * N) + col) + 1;
				}
			}
		}
		return -1;
	}

	private Board swap(int blankSpace, int adjacentSpace) {
		Board b = new Board(cloneBoard());
		int blankRow = findRow(blankSpace);
		int blankCol = findCol(blankSpace);
		int adjacentRow = findRow(adjacentSpace);
		int adjacentCol = findCol(adjacentSpace);
		try {
			int temp = blocks[blankRow][blankCol];
			b.blocks[blankRow][blankCol] = blocks[adjacentRow][adjacentCol];
			b.blocks[adjacentRow][adjacentCol] = temp;
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return b;
	}

	private int findRow(int pos) {
		return pos % N == 0 ? pos / N - 1 : (int) Math.floor(pos / N);
	}

	private int findCol(int pos) {
		return pos % N == 0 ? N - 1 : (pos % N) - 1;
	}

	private int[][] cloneBoard() {
		int[][] copy = new int[N][N];
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				copy[row][col] = blocks[row][col];
			}
		}
		return copy;
	}

	// for the sake of testing, delete after done
	public void test() {
		System.out.println(toString());
		System.out.println("Goal: " + isGoal());
		System.out.println("hamming is: " + hamming());
		System.out.println("manhattan is: " + manhattan() + "\n");
		System.out.println("Twin " + twin().toString());
	}
}
