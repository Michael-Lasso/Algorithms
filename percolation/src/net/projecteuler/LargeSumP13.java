package net.projecteuler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LargeSumP13 {

	private static String readFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}

	private static int addNumbers(String numbers) {
		int x = 0;
		int y = 0;
		for (int i = 0; i < numbers.length(); i++) {
			if (i % 40 == 0) {
				x += Character.getNumericValue(numbers.charAt(i));
				System.out.println("(" + i + ") N: " + numbers.charAt(i)
						+ " S: " + x);
			} else {
				x += Character.getNumericValue(numbers.charAt(i));
				System.out.print(" N: " + numbers.charAt(i) + " S: " + x);
			}
			y++;
		}
		System.out.println("\nnumber of iterations: " + y);
		return x;
	}

	public static void main(String[] args) {
		try {
			String numbers = readFile("LargeSumP13.txt");
			System.out.println(addNumbers(numbers));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
