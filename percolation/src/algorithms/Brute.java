package algorithms;

import library.algorithms.In;
import library.algorithms.StdDraw;

public class Brute {

	public static Point[] start(final In in, int N) {
		final Point[] points = new Point[N];
		int c = 0;
		while (c < N) {
			final int x = in.readInt();
			final int y = in.readInt();
			points[c] = new Point(x, y);
			c++;
		}
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		return points;
	}

	public static void main(String[] args) {
		In in = new In("input6.txt");
		start(in, 6);
	}
}
