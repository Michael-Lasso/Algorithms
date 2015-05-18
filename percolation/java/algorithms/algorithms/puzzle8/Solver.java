package algorithms.puzzle8;

import library.algorithms.In;
import library.algorithms.MinPQ;
import algorithms.constants.Constants;

public class Solver {
	Board initial;
	int moves;

	// find a solution to the initial board (using the A* algorithm)
	public Solver(Board initial) {
		this.initial = initial;
		compute(initial);
	}

	private void compute(Board initial) {
		MinPQ<Board> p = new MinPQ<Board>();
		p.insert(initial);
		System.out.println("+++Initial board+++");
		initial.test();
		Board board = null;
		p.insert(initial);
		int moves = 0;
		for (int i = 0; i < 6; i++) {
			board = p.delMin();
			board.setPriotity(moves);
			for (Board b : board.neighbors()) {

				System.out.println("Added");
				b.test();
				p.insert(b);

			}
			System.out.println("+++Last board+++");
		
			System.out.println("Iteration: " + i);
			moves += 100;
		}
	}

	// is the initial board solvable?
	public boolean isSolvable() {
		return false;
	}

	// min number of moves to solve initial board; -1 if unsolvable
	public int moves() {
		return moves;
	}

	// sequence of boards in a shortest solution; null if unsolvable
	public Iterable<Board> solution() {
		return null;
	}

	// solve a slider puzzle (given below)
	public static void main(String[] args) {

		// create initial board from file
		In in = new In(Constants.PUZZLE);
		int N = in.readInt();
		int[][] blocks = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				blocks[i][j] = in.readInt();
		Board initial = new Board(blocks);

		// solve the puzzle
		Solver solver = new Solver(initial);

		/*
		 * // print solution to standard output if (!solver.isSolvable()) {
		 * StdOut.println("No solution possible"); } else {
		 * StdOut.println("Minimum number of moves = " + solver.moves()); for
		 * (Board board : solver.solution()) { StdOut.println(board); } }
		 */
	}

}
