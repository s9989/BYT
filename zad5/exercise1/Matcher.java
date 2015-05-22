package exercise1;

public class Matcher {
	public Matcher() {
	}

	public boolean match(int[] expected, int[] actual, int clipLimit, int delta) {

		/* this is quick check we can do at start with 
		 * no need to go through loop
		 */
		// Check for length differences
		if (actual.length != expected.length)
			return false;

		// Check that each entry within expected +/- delta
		for (int i = 0; i < actual.length; i++) {
			int value = (actual[i] > clipLimit) ? clipLimit : actual[i];
			if (Math.abs(expected[i] - value) > delta)
				return false;
		}

		return true;
	}
}