package algorithms;

import java.util.Comparator;
import library.algorithms.StdDraw;

public class Point implements Comparable<Point> {

	// compare points by slope
	public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {

		@Override
		public int compare(final Point q, final Point r) {
			final Point t = Point.this;
			final double qSlope = t.slopeTo(q);
			final double rSlope = t.slopeTo(r);
			if (qSlope < rSlope) {
				return -1;
			} else if (qSlope == rSlope) {
				return 0;
			} else {
				return 1;
			}
		}
	};

	private final int x; // x coordinate
	private final int y; // y coordinate

	// create the point (x, y)
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// plot this point to standard drawing
	public void draw() {
		StdDraw.point(x, y);
	}

	// draw line between this point and that point to standard drawing
	public void drawTo(Point that) {
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	// slope between this point and that point
	public double slopeTo(Point p1) {
		double slope;
		if (compareTo(p1) == 0) {
			slope = Double.NEGATIVE_INFINITY;
		} else if (this.y == p1.y) {
			slope = Double.NaN;
		} else if (this.x == p1.x) {
			slope = Double.POSITIVE_INFINITY;
		} else {
			slope = (p1.y - this.y) / (p1.x - this.x);
		}

		return slope;
	}

	// is this point lexicographically smaller than that one?
	// comparing y-coordinates and breaking ties by x-coordinates
	// -1 if less, 0 if equal, 1 if greater. p0 this, p1 that
	public int compareTo(Point p1) {
		if (this.y < p1.y || (this.y == p1.y && this.x < p1.x)) {
			return -1;
		} else if (this.y == p1.y && this.x == p1.x) {
			return 0;
		} else
			return 1;
	}

	// return string representation of this point
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	// unit test
	public static void main(String[] args) {
		Point p1 = new Point(1, 1);
		Point p2 = new Point(1, 1);
		System.out.println(p1.compareTo(p2));
		System.out
				.println(Double.POSITIVE_INFINITY == Double.NEGATIVE_INFINITY);
	}
}
