public class PercolationStats {

	/**
	 * @param args
	 */
	// public PercolationStats(int N, int T) // perform T independent
	// computational experiments on an N-by-N grid
	// public double mean() // sample mean of percolation threshold
	// public double stddev() // sample standard deviation of percolation
	// threshold
	// public double confidenceLo() // returns lower bound of the 95% confidence
	// interval
	// public double confidenceHi()
	public static void main(String[] args) {
		Percolation percolate = new Percolation(5);
		percolate.open(0, 0);
		percolate.open(1, 0);
		percolate.open(3, 3);
		percolate.open(4, 3);
		percolate.open(3, 2);
		percolate.open(2, 0);
		percolate.open(3, 1);
		percolate.open(3, 0);
		percolate.open(1, 3);
		percolate.display();

		percolate.displayTable();

		if (percolate.connected(8, 0)) {
			System.out.println("they are connected");
		} else {
			System.out.println("they are not connected");
		}
		// System.out.println(percolate.getLen()+" / "+
		// percolate.getGrid().length);

	}

}
