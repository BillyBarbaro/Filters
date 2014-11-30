/** Implementation of a Min Filter N
  * @author Billy Barbaro
  */

import java.util.LinkedList;

public class MinFilterN<T extends Comparable<T>> extends NScalarFilter<T> {

	/** Calls the constructor of the super class */
	public MinFilterN(int N) {
		super(N);
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