package algorithms.puzzle8;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import library.algorithms.In;
import algorithms.constants.Constants;

public class Board implements Comparable<Board> {
	int[][] blocks;
	int N;
	int manhattan;
	int hamming;
	int priotity;

	public int getPriotity() {
		return priotity;
	}

	public void setPriotity(int priotity) {
		this.priotity = priotity;
	}

	// construct a board from an N-by-N array of blocks (where blocks[i][j] =
	// block in row i, column j)
	public Board(int[][] blocks) {
		this.blocks = blocks;
		N = blocks.length;
		calcHamming();
		calcManhattan();
	}

	// board dimension N
	public int dimension() {
		return N;
	}

	// number of blocks out of place
	private void calcHamming() {
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
	}

	public int hamming() {
		return hamming;
	}

	// sum of Manhattan distances between blocks and number of moves
	private void calcManhattan() {
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
	}

	public int manhattan() {
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
		int[] swapping = addNeighbors(blankSpace);
		for (int i = 0; i < 4; i++) {
			if (isValidPosition(blankSpace, swapping[i])) {
				int adjacent = swapping[i];
				Board b = swap(blankSpace, adjacent);
				neighbors.add(b);
			}
		}
		return neighbors;
	}

	private int[] addNeighbors(int blankSpace) {
		int[] swapping = new int[4];
		swapping[0] = blankSpace - 1;
		swapping[1] = blankSpace + 1;
		swapping[2] = blankSpace - N;
		swapping[3] = blankSpace + N;
		return swapping;
	}

	private boolean isValidPosition(int blankSpace, int adjacent) {
		int blankRow = findRow(blankSpace);
		int blankCol = findCol(blankSpace);
		int adjacentRow = findRow(adjacent);
		int adjacentCol = findCol(adjacent);

		if (blankRow == 0 && blankSpace - 1 < blankRow) {
			return false;
		} else if (blankRow == N - 1 && adjacentRow > blankRow) {
			return false;
		} else if (adjacentCol >= N || adjacentCol < 0 || adjacent <= 0
				|| adjacent > (N * N)) {
			return false;
		} else if (blankCol == 0 && blankSpace - 1 == adjacent
				|| (blankCol == N - 1 && blankSpace + 1 == adjacent)) {
			return false;
		}
		return true;
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

	// TODO need to check for out of bound elements
	private Board swap(int blankSpace, int adjacentSpace)
			throws IndexOutOfBoundsException {
		int[][] board = cloneBoard();
		int blankRow = findRow(blankSpace);
		int blankCol = findCol(blankSpace);
		int adjacentRow = findRow(adjacentSpace);
		int adjacentCol = findCol(adjacentSpace);
		int temp = blocks[blankRow][blankCol];
		board[blankRow][blankCol] = blocks[adjacentRow][adjacentCol];
		board[adjacentRow][adjacentCol] = temp;
		Board b = new Board(board);
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
		// System.out.println("Twin " + twin().toString());
	}

	@Override
	public int compareTo(Board b2) {
		int m1 = this.manhattan;
		int m2 = b2.manhattan;
		int h1 = this.hamming;
		int h2 = b2.hamming;
		if (m1 > m2) {
			return 1;
		} else if (m1 < m2) {
			return -1;
		} else if (m1 == m2) {
			if (h1 > h2) {
				return 1;
			} else if (h1 < h2) {
				return -1;
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		In in = new In(Constants.PUZZLE);
		int N = in.readInt();
		int[][] blocks = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				blocks[i][j] = in.readInt();
		Board initial = new Board(blocks);
		initial.neighbors();
	}
}
