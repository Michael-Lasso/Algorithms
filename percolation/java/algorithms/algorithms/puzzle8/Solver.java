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
		Board board = initial;
		p.insert(initial);
		int moves = 0;
		while (!board.isGoal()) {
			board = p.delMin();
			board.setPriotity(moves);
			for (Board b : board.neighbors()) {
				p.insert(b);
			}
			board.test();
			System.out.println("Iteration: " + (int) moves / 100);
			moves += 100;
		}
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

		Solver solver = new Solver(initial);

	}

}
