
public interface SharingFunctionsI {
	public String[][] changeQueens(int[] column, int row, String[][] grid, int newPosition);
	public String pickBest(String[] array);
	public int heuristicFunction(int[] array);
	public String[] successor(int[] coordinates);
}
