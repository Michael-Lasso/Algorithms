package algorithms.patterns;

import java.util.ArrayList;
import java.util.List;

import library.algorithms.In;
import library.algorithms.StdDraw;

public class PointPlotter {

	public final static String FILENAME = "input6.txt";

	public static List<Point> DrawAndGetPointsFromFile(String filename) {

		List<Point> points = new ArrayList<Point>();
		// rescale coordinates and turn on animation mode
		StdDraw.setXscale(0, 30);
		StdDraw.setYscale(0, 30);
		StdDraw.show(0);
		StdDraw.setPenRadius(0.01); // make the points a bit larger

		In in = new In(filename);
		int N = in.readInt();
		for (int i = 0; i < N; i++) {
			int x = in.readInt();
			int y = in.readInt();
			Point p = new Point(x, y);
			p.draw();
			points.add(p);
		}

		// display to screen all at once
		StdDraw.show(0);

		// reset the pen radius
		StdDraw.setPenRadius();
		return points;
	}
}