package algorithms;

public class PercolationStats {

	/**
	 * @param args
	 */
	private double mean;

	public PercolationStats(int N, int T) {
		double sum = 0;
		for (int i = 0; i < T; i++) {
			Percolation percolation = new Percolation(N);
			percolation.start();
			sum += percolation.getProbability();
			System.out.println("test: " + i);
		}
		mean = sum / T;

	} // perform T independent experiments on an N-by-N grid

	public double mean() {
		return mean;
	}

	public static void main(String[] args) {
		PercolationStats p = new PercolationStats(200, 100);
		System.out.println(p.mean);
	}

}
