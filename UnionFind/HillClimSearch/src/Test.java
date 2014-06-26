
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EightQueen test = new EightQueen();
		test.initialState();
		test.printBoard();
		int hf = test.heuristicFunction(test.getInitialQueenPlace());
		System.out.println("\nCurrent score: "+ hf+"\n");
		//test.board = test.changeQueens(test.getInitialQueenPlace(), 1, test.board, 3);
		//test.printBoard();
		String[] places= test.successor(test.getInitialQueenPlace());
		
		for(int i = 0; i<places.length;i++){
			System.out.print(" ("+places[i]+")");
		}
	}

}
