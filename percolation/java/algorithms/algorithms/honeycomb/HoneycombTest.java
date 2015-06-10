package algorithms.honeycomb;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import algorithms.constants.Constants;

public class HoneycombTest {
	Honeycomb solution;

	@Before
	public void setUp() throws Exception {
		solution = new Honeycomb(Constants.HONEY_INPUT, Constants.HONEY_WORDS);
	}

	@After
	public void tearDown() throws Exception {
		solution = null;
	}

	@Test
	public void test() {
		int layer = solution.findLayer(20);
		System.out.println(layer);
		assertEquals(layer, 2);
	}

}
