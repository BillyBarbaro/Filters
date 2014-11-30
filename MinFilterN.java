/** Implementation of a Min Filter N
  * @author Billy Barbaro
  */

import java.util.LinkedList;

public class MinFilterN<T extends Comparable<T>> extends NScalarFilter<T> {

	/** Calls the constructor of the super class */
	private MinFilterN(int N) {
		super(N);
	}

	public static MinFilterN minFilterWithN(int N) {
		if (N < 1)
			throw new IllegalArgumentException("A filter must have a size of at least 1");
		return new MinFilterN(N);
	}
	
	/** Implementation of calculateFilterValue, takes the min value of the history
	  * @return Double	the minimum value in the history of inputs
	  */
	protected final T calculateFilterValue() {
		// Gets input history
		LinkedList<T> history = super.getInputHistory();

		// Loops through the history keeping the largest value it's seen
		T min = history.getFirst();
		for (T data : history) {
			if (data != null)
				min = (min.compareTo(data) < 0) ? min : data;
		}
		return min;
	}
}