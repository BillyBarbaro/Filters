/** Implementation of a Max Filter N
  * @param T	the type of input for the filter
  */

import java.util.LinkedList;

public class MaxFilterN<T extends Comparable<T>> extends NScalarFilter<T> {

	/** Calls the constructor of the super class
	  * @param N	the number of past values the filter stores
	  */
	private MaxFilterN(int N) {
		super(N);
	}

	/** Static factory to create a MaxFilterN
	  * @param N 	the number of past values the filter stores
	  * @return MaxFilterN 	an instance of MaxFilterN with specified size
	  * @throws IllegalArgumentException 	thrown if n is less than 1
	  */
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
			max = (max.compareTo(data) > 0) ? max : data;
		}
		return max;
	}
}