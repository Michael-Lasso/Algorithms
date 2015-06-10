package algorithms.honeycomb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import library.algorithms.BreadthFirstPaths;
import library.algorithms.Graph;
import library.algorithms.In;
import algorithms.constants.Constants;

public class Honeycomb {

	private HoneycombGraph graph;
	private BreadthFirstPaths bfs;
	private int layers;
	private Map<Character, List<String>> wordsMap;
	private int grapSize = 61;

	public int graphSize() {
		return grapSize;
	}

	public Honeycomb(String honeycombTextFile, String wordsTextFile) {
		this.graph = new HoneycombGraph(new In(honeycombTextFile));
		this.wordsMap = populateMap(new In(wordsTextFile));
		layers = findLayer(graph.getCharacters().length) - 2;
		System.out.println("layers: " + layers);
	}

	private Map<Character, List<String>> populateMap(In in) {
		List<String> words = Arrays.asList(in.readAllStrings());
		Map<Character, List<String>> wordsMap = new HashMap<>();
		for (String word : words) {
			if (wordsMap.containsKey(word.charAt(0))) {
				wordsMap.get(word.charAt(0)).add(word);
			} else {
				List<String> newWordList = new ArrayList<>();
				newWordList.add(word);
				wordsMap.put(word.charAt(0), newWordList);
			}
		}
		return wordsMap;
	}

	private int calculateLayerSize(int layers) {
		// this.layers = layers;
		int size = 0;
		for (int i = 1; i < layers; i++) {
			size += 6 * i;
		}
		// this.grapSize = size;
		return size;
	}

	private List<Integer> findAdjacents(int vertex) {
		List<Integer> list = new ArrayList<>();
		if (vertex == 0) {
			for (int i = 1; i <= 6; i++) {
				list.add(i);
			}
			return list;
		}

		int layer = findLayer(vertex);
		if (vertex == calculateLayerSize(layer + 1)) {
			list.add(calculateLayerSize(layer) + 1);
		} else {
			list.add(vertex + 1);
		}

		if (isLastLayer(vertex)) {
			// do nothing
			System.out.println("Do Nothing");
		} else if (isOffSet(vertex)) {
			// add 3
			int pos = findNumfor3(vertex);
			list.add(pos);
			list.add(pos + 1);
			if (pos == calculateLayerSize(layer - 1) + 1) {
				System.out.println((calculateLayerSize(layer - 1) + 1) + "\t"
						+ pos);
				list.add(calculateLayerSize(layer));
			} else {
				System.out.println(pos - 1);
				list.add(pos - 1);
			}
		} else {
			// add 2
			int pos = findNumfor2(vertex);
			list.add(pos);
			list.add(pos + 1);
		}

		return list;

	}

	public int findLayer(int vertex) {
		int sum = 0;
		System.out.println("sum: " + sum);
		for (int i = 0; i < layers; i++) {
			sum += 6 * i;
			if (vertex <= sum) {
				return i;
			}
		}
		return sum;
	}

	private boolean isOffSet(int vertex) {
		double layer = findLayer(vertex);
		if (layer == 1) {
			return true;
		}

		int extract = calculateLayerSize((int) layer) + 1;
		vertex = vertex - extract;

		if (vertex % layer == 0) {
			int start = calculateLayerSize((int) layer + 1) + 1;
			int nextLayer = (int) layer + 1;
			int multiplier = (vertex / (int) layer);
			System.out.println(start + "\t" + nextLayer + "\t" + multiplier
					+ "\t" + (start + (nextLayer * multiplier)));
			return true;
		} else {
			int start = calculateLayerSize((int) layer + 1) + 1;
			int quadrant = (vertex / (int) layer) * ((int) layer + 1);
			int modifier = vertex % (int) layer;
			System.out.println(start + quadrant + modifier);
			return false;
		}
	}

	// TODO calculate 2
	private int findNumfor3(int vertex) {
		double layer = findLayer(vertex);
		int extract = calculateLayerSize((int) layer) + 1;
		vertex = vertex - extract;
		int start = calculateLayerSize((int) layer + 1) + 1;
		int nextLayer = (int) layer + 1;
		int multiplier = (vertex / (int) layer);
		System.out.println(start + "\t" + nextLayer + "\t" + multiplier + "\t"
				+ (start + (nextLayer * multiplier)));
		return (start + (nextLayer * multiplier));
	}

	private int findNumfor2(int vertex) {
		double layer = findLayer(vertex);
		int extract = calculateLayerSize((int) layer) + 1;
		vertex = vertex - extract;
		int start = calculateLayerSize((int) layer + 1) + 1;
		int quadrant = (vertex / (int) layer) * ((int) layer + 1);
		int modifier = vertex % (int) layer;
		return (start + quadrant + modifier);
	}

	private boolean isLastLayer(int vertex) {
		System.out.println(vertex + "\t" + (layers));
		return findLayer(vertex) == layers - 1;
	}

	private class HoneycombGraph extends Graph {
		char[] characters;

		public char[] getCharacters() {
			return characters;
		}

		HoneycombGraph(In in) {
			this(calculateLayerSize(in.readInt()) + 1);
			characters = new char[graphSize()];
			StringBuilder sb = new StringBuilder();
			String[] allChars = in.readAllStrings();

			for (String s : allChars) {
				sb.append(s);
			}

			this.characters = sb.toString().toCharArray();
			for (int i = 0; i < characters.length; i++) {

				// List<Integer> adjacents = findAdjacents(i);
				// for (Integer adj : adjacents) {
				// addEdge(i, adj);
				// }
			}

		}

		HoneycombGraph(int V) {
			super(V);
		}

	}

	public static void main(String[] args) {
		Honeycomb solution = new Honeycomb(Constants.HONEY_INPUT,
				Constants.HONEY_WORDS);
		System.out.println(solution.findLayer(20));

	}
}
