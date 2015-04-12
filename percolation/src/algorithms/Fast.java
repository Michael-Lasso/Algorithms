package algorithms;

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
			System.out.println("\t\t++++points++++\n" + points.toString()
					+ "\n\n");
			for (int i = 0; i < points.size(); i++) {

				Collections.sort(sortedPoints, points.get(i).SLOPE_ORDER);

				System.out.println(sortedPoints.toString());
				displaySlope(points.get(i), sortedPoints);
			}
		} catch (IndexOutOfBoundsException e) {
		}
	}

	private void displaySlope(Point p, List<Point> ps) {
		for (Point point : ps) {
			System.out.print(p.slopeTo(point) + " --- ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Fast fast = new Fast(PointPlotter.FILENAME);
		fast.linePatterns();
	}
}
