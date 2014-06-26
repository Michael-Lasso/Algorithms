public class Node implements SharingFunctionsI {

	public String[][] board;
	public int value;

	// constructor will get its values from the
	public Node(String[][] board, int value) {
		this.board = board;
		this.value = value;
	}

	// empty constructor
	public Node() {
	}

	@Override
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
	}

	@Override
	public int heuristicFunction(int[] array) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[][] changeQueens(int[] coordinates, int row, String[][] grid,
			int newPosition) {
		int currentQueen = coordinates[row];
		String array[][] = grid;
		array[row][currentQueen] = "O";
		array[row][newPosition] = "X";
		return array;

	}

	@Override
	public String[] successor(int[] coordinates) {
		// finalQueenPlace = initialQueenPlace;
		String[] allStates = new String[16];
		for (int i = 0; i < allStates.length; i++) {

		}
		return allStates;

	}

}// end of class
