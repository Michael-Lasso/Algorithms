package algorithms.patterns;

import java.util.List;

public class Brute {

	List<Point> points;

	public Brute(String fileName) {
		points = PointPlotter.DrawAndGetPointsFromFile(fileName);
	}

	public void linePatterns() {
		try {
			for (int p1 = 0; p1 < points.size(); p1++) {
				for (int p2 = p1 + 1; p2 < points.size(); p2++) {
					for (int p3 = p2 + 1; p3 < points.size(); p3++) {
						double slope1 = points.get(p1).slopeTo(points.get(p2));
						double slope2 = points.get(p2).slopeTo(points.get(p3));
						if (slope1 == slope2) {
							for (int p4 = p3 + 1; p4 < points.size(); p4++) {
								double slope3 = points.get(p3).slopeTo(
										points.get(p4));
								if (slope3 == slope2) {
									System.out.println(points.get(p1)
											.toString()
											+ "->"
											+ points.get(p2).toString()
											+ "->"
											+ points.get(p3).toString()
											+ "->"
											+ points.get(p4).toString());
								}
							}
						}
					}
				}
			}
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Brute brute = new Brute(PointPlotter.FILENAME);
		brute.linePatterns();
	}
}
