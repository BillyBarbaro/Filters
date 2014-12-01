/** Implementation of a Min Filter N
  * @param T	the type of input for the filter
  */

import java.util.LinkedList;

public class MinFilterN<T extends Comparable<T>> extends NScalarFilter<T> {

	/** Calls the constructor of the super class
	  * @param N	the number of past values the filter stores
	  */
	private MinFilterN(int N) {
		super(N);
	}

	/** Static factory to create a MinFilterN
	  * @param N 	the number of past values the filter stores
	  * @return MinFilterN 	an instance of MinFilterN with specified size
	  * @throws IllegalArgumentException 	thrown if n is less than 1
	  */
	public static MinFilterN minFilterWithN(int N) {
		if (N < 1)
			throw new IllegalArgumentException("A filter must have a size of at least 1");

		return new MinFilterN(N);
	}
	
	/** Implementation of calculateFilterValue, takes the min value of the history
	  * @return T	the minimum value in the history of inputs
	  */
	protected final T calculateFilterValue() {
		// Gets input history
		LinkedList<T> history = super.getInputHistory();

		// Loops through the history keeping the largest value it's seen
		T min = history.getFirst();
		for (T data : history) {
			min = (min.compareTo(data) < 0) ? min : data;
		}
		return min;
	}
}