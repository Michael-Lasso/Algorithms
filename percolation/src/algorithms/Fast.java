package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fast {
	List<Point> points;
	List<Point> sortedPoints;

	public Fast(String fileName) {
		points = PointPlotter.DrawAndGetPointsFromFile(fileName);
		sortedPoints = PointPlotter.DrawAndGetPointsFromFile(fileName);
	}

	public void linePatterns() {
		try {
			for (int i = 0; i < points.size(); i++) {
				sortedPoints = shrinkList(points, i);
				Collections.sort(sortedPoints, points.get(i).SLOPE_ORDER);

				displaySlope(points.get(i), sortedPoints);
			}
		} catch (IndexOutOfBoundsException e) {
		}
	}

	private void displaySlope(Point p, List<Point> ps) {
		int count = 0;
		int repetitions = 1;
		double slope = Double.MAX_VALUE;
		for (Point point : ps) {

			if (p.slopeTo(point) == slope) {
				repetitions++;
			} else {
				repetitions = 1;
				slope = p.slopeTo(point);

			}
			if (repetitions == 3) {
				System.out.println("Found line pattern with:" + p.toString()
						+ "->" + ps.get(count - 2) + "->" + ps.get(count - 1)
						+ "->" + ps.get(count));
			}
			count++;
		}
	}

	// used to avoid finding repetitive slopes
	private List<Point> shrinkList(List<Point> p, int offset) {
		List<Point> newList = new ArrayList<>();
		for (int i = offset; i < p.size(); i++) {
			newList.add(p.get(i));
		}
		return newList;
	}

	public static void main(String[] args) {
		Fast fast = new Fast(PointPlotter.FILENAME);
		fast.linePatterns();
	}
}
