package algorithms.puzzle8;

import java.util.ArrayList;
import java.util.List;

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
		int N = dimension();
		int hamming = 0;
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				int valueExpected = ((row * N) + col) + 1;
				if (((row + 1) * (1 + col)) - 1 == ((N * N) - 1)) {
					// do nothing, last position
				} else if (blocks[row][col] != valueExpected) {
					hamming++;
				}
			}
		}
		return hamming;
	}

	// sum of Manhattan distances between blocks and goal
	// manhattan = floor(ABS(value-expected)/N)+(ABS(value-expected)%N)
	public int manhattan() {
		int N = dimension();
		int manhattan = 0;
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				int valueExpected = ((row * N) + col) + 1;
				if (((row + 1) * (1 + col)) - 1 == ((N * N) - 1)) {
					// do nothing, last position
				} else if (blocks[row][col] != valueExpected) {
					int pos = Math.abs(blocks[row][col]);
					manhattan += Math.floor(pos / N) + pos % N;
				}
			}
		}
		return manhattan;
	}

	// is this board the goal board?
	public boolean isGoal() {
		int N = dimension();
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				int valueExpected = ((row * N) + col) + 1;
				if (((row + 1) * (1 + col)) - 1 == ((N * N) - 1)) {

				} else if (blocks[row][col] != valueExpected) {
					return false;
				}
			}
		}
		return true;
	}

	// a board that is obtained by exchanging two adjacent blocks in the same
	// row
	public Board twin() {
		return null;
	}

	// does this board equal y?
	public boolean equals(Object y) {
		return false;
	}

	// string representation of this board (in the output format specified
	// below)
	public String toString() {
		int N = dimension();
		StringBuilder board = new StringBuilder();
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				board.append(blocks[row][col] + " ");
			}
			board.append("\n");
		}
		return board.toString();
	}

	// -------------------+ Utility Functions +-----------------
	public static int[][] populateGrid(int N) {
		int[][] grid = new int[N][N];
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				grid[row][col] = ((row * N) + col) + 1;
			}
		}
		grid[1][2] = 2;
		grid[0][1] = 6;
		return grid;
	}

	private int findBlankSpace() {
		int N = dimension();
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
		int N = dimension();
		int[][] neighboardBoard = blocks.clone();
		try {
			int blankRow = (blankSpace % N == 0) ? blankSpace / N - 1
					: (int) Math.floor(blankSpace / N);
			int blankCol = (int) Math.floor(blankSpace / N);
			int adjacentRow = (adjacentSpace % N == 0) ? adjacentSpace / N - 1
					: (int) Math.floor(adjacentSpace / N - 1);
			int adjacentCol = adjacentSpace % N;
			int temp = adjacentSpace;
			System.out.println("blank: " + blankRow + "/" + blankCol
					+ "\tadjacent: " + adjacentRow + "/" + adjacentCol);
			neighboardBoard[blankRow][blankCol] = adjacentSpace;
			neighboardBoard[adjacentRow][adjacentCol] = temp;

		} catch (IndexOutOfBoundsException e) {
		}
		return new Board(neighboardBoard);
	}

	// all neighboring boards
	public Iterable<Board> neighbors() {
		List<Board> neighbors = new ArrayList<Board>();
		int N = dimension();
		int blankSpace = findBlankSpace();
		neighbors.add(swap(blankSpace, blankSpace - 1));
		neighbors.add(swap(blankSpace, blankSpace + 1));
		neighbors.add(swap(blankSpace, blankSpace - N));
		neighbors.add(swap(blankSpace, blankSpace + N));
		return neighbors;
	}

	public void test() {
		System.out.println(toString());
		System.out.println("Goal: " + isGoal());
		System.out.println("Space at: " + findBlankSpace());
		System.out.println("hamming is: " + hamming());
		System.out.println("manhattan is: " + manhattan());
	}

	// unit tests (not graded)
	public static void main(String[] args) {
		int[][] test = Board.populateGrid(3);
		Board b = new Board(test);
		System.out.println(b.toString());
		System.out.println(b.isGoal());
		System.out.println("hamming is: " + b.hamming());
		System.out.println("manhattan is: " + b.manhattan());
		b.swap(9, 6);

	}
}
