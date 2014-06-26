import java.util.Random;
import java.util.Arrays;

public class EightQueen implements SharingFunctionsI {
	public String[][] board = new String[8][8];
	public int[] initialQueenPlace = new int[8];
	public int[] finalQueenPlace = new int[8];// will record the last
												// coordinates for all queens
	Random rand = new Random();
	private String queen = "X";
	private int x;
	Node current;

	// constructor of the grid
	public EightQueen() {
		final int N = 8;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = "O";
			}
		}
	}// end of constructor

	public int[] getInitialQueenPlace() {
		return initialQueenPlace;
	}

	// Initialize queens on the board amd records their position with queenPlace
	// Array
	public void initialState() {
		for (int i = 0; i < 8; i++) {
			x = rand.nextInt(8);
			this.board[i][x] = queen;
			initialQueenPlace[i] = x;
		}
	}// end of state function

	// print the board and current state
	public void printBoard() {
		System.out
				.println("-------|-------|-------|-------|-------|-------|-------|-------|");
		for (int i = 0; i < 8; i++) {
			System.out.println("\n");
			for (int j = 0; j < 8; j++) {
				System.out.print("---" + this.board[i][j] + "---|");
			}
		}
		System.out
				.println("\n\n"
						+ "-------|-------|-------|-------|-------|-------|-------|-------|"
						+ "\n");
		System.out.print("Positions = ");
		for (int i = 0; i < 8; i++) {
			System.out.print("-(" + (1 + i) + "," + (1 + initialQueenPlace[i])
					+ ")");
		}
	}// end of printBoard function

	// check to see how many more queens are in the given column
	public int checkColumn(int row, int[] array) {
		int queenPosition = array[row];
		int sum = 0;
		for (int i = row + 1; i < array.length; i++) {
			if (array[i] == queenPosition) {
				sum++;
			}
		}
		return sum;
	}// end of checkColumn function

	// check to see how many diagonal queens are from the given position
	public int checkDiagonal(int row, int[] array) {
		int column = array[row] + 1;
		row += 1;
		int sum = 0;
		for (int i = row; i < array.length; i++) {
			int column2 = array[i] + 1;
			int y2 = column2 - column;
			int x1 = i + 1;
			int x2 = x1 - row;
			Double slope = y2 * 1.0 / x2 * 1.0;// need to make it a double
			if (Math.abs(slope) == 1) {
				sum++;
			}
		}
		return sum;
	}// end of checkDiagonal function

	// scores the current state based on how many queens are attacking each
	// other
	public int heuristicFunction(int[] array) {
		int hf = 0;
		for (int i = 0; i < 8; i++) {
			hf += checkColumn(i, array);
			hf += checkDiagonal(i, array);
		}
		//System.out.println("\n\nScore =" + hf);
		return hf;
	}// end of heuristicFunction

	// hillClimb algorithm
	public void hillClimb() {
		current = new Node(this.board,
				this.heuristicFunction(initialQueenPlace));
		boolean doable = true;
		while (doable) {
			Node neighboor = new Node();
			String[][] grid;
			if (current.value <= neighboor.value) {

			} else {
				doable = false;
			}
		}
		printBoard();
	}// end of hillClimb algorithm

	@Override
	// takes and array which has recorded the positions and scores of the next
	// possible states and pick the best one
	public String pickBest(String[] array) {
		String bestChoice = null;
		int score = 200;
		for (int i = 0; i < array.length; i++) {
			String[] choice = array[i].split(",");
			int hf = Integer.parseInt(choice[2]);
			if (hf <= score) {
				bestChoice = array[i];
			}
		}
		return bestChoice;
	}// end of pickBest function

	@Override
	// Updates the new queen position on the board
	public String[][] changeQueens(int[] coordinates, int row, String[][] grid,
			int newPosition) {
		int currentQueen = coordinates[row];
		String array[][] = grid;
		array[row][currentQueen] = "O";
		array[row][newPosition] = "X";
		System.out.println("\n\n new position: " + newPosition
				+ "\nPosition before: " + currentQueen);
		return array;
	}

	@Override
	// save all the states into an array and uses pickBest function to pick the
	// best next state
	public String[] successor(int[] coordinates) {
		int[] positions = coordinates;
		String[] allStates = new String[16];
		int hf = 0;
		int plus = 1;
		int minus = -1;
		for (int i = 0; i < allStates.length / 2; i++) {
			int y = positions[i];
			if (y < 7) {
				positions[i] = y + plus;
				hf = this.heuristicFunction(positions);
				allStates[i] = i + "," + plus + "," + hf;
				positions = coordinates;
			} else {
				allStates[i] = i + "," + minus + "," + minus;
				positions = coordinates;
			}
		}// end of first for loop
		int j = 0;
		for (int i = allStates.length / 2; i < allStates.length; i++) {
			int y = positions[j]-1;
			if (y > 0) {
				positions[j] = y + minus;
				hf = this.heuristicFunction(positions);
				allStates[i] = (i-8)  + "," + minus + "," + hf;
				j++;
				positions = coordinates;
			} else {
				allStates[i] = (i-8) + "," + minus + "," + minus;
				j++;
				positions = coordinates;
			}
		}// end of second for loop
		return allStates; // returns all states saved in an array of strings
	}// end of successor function

}// end of class
