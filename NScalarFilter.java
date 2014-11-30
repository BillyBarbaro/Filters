/** Interface for filters only acknowledging the last N entries
  * @author Billy Barbaro
  */

import java.util.LinkedList;

public abstract class NScalarFilter<T extends Comparable<T>> extends ScalarFilter<T> {
	
	/** Array of the past N inputs */
	private LinkedList<T> inputs;
	/** The current index to write in the array */
	private int maxSize;

	/** Constructor to create a Scalar Filter keeping track of N values
	  * @param N 	the number of past vales to be stored in memory
	  */
	public NScalarFilter(int N) {
		if (N < 1)
			N = 1;
		inputs = new LinkedList<T>();
		maxSize = N;
	}

	/** Addes data to the history, overwriting past data if it exceeds N
	  * @param data 	the data to be added to the filter
	  */
	private final void addData(T data) {
		inputs.add(data);
		if (inputs.size() > maxSize)
			inputs.pollFirst();
		assert(maxSize <= inputs.size());
	}

	/** A calculates a return value from the filter's history */
	abstract protected T calculateFilterValue();

	/** Given data, adds it to the history and returns the next output
	  * @param data 	the data to be filtered
	  * @return T 	the filtered data
	  */
	public final T filter(T data) {
		checkNullFilterValue(data);
		addData(data);
		return calculateFilterValue();
	}

	/** Resets the filter's history and writes the reset value to it's history
	  * @param resetValue 	the value to be written to the empty history
	  */
	public final void reset(T resetValue) {
		checkResetNull(resetValue);
		inputs = new LinkedList<T>();
		inputs.add(resetValue);
	}

	/** Getter for the input history
	  * @return T[] 	the history of inputs
	  */
	protected final LinkedList<T> getInputHistory() {
		return inputs;
	}
}