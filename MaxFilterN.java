/** Implementation of a Max Filter N
  * @author Billy Barbaro
  */

import java.util.LinkedList;

public class MaxFilterN<T extends Comparable<T>> extends NScalarFilter<T> {

	/** Calls the constructor of the super class */
	private MaxFilterN(int N) {
		super(N);
	}

	public static MaxFilterN maxFilterWithN(int N) {
		if (N < 1)
			throw new IllegalArgumentException("A filter must have a size of at least 1");
		return new MaxFilterN(N);
	}
	
	/** Implementation of calculateFilterValue, takes the max value of the history
	  * @return T	the maximum value in the history of inputs
	  */
	protected final T calculateFilterValue() {
		// Gets input history
		LinkedList<T> history = super.getInputHistory();

		// Loops through the history keeping the largest value it's seen
		T max = history.getFirst();
		for (T data : history) {
			if (data != null)
				max = (max.compareTo(data) > 0) ? max : data;
		}
		return max;
	}
}