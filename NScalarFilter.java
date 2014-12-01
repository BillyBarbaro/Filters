/** Interface for filters only acknowledging the last N entries
  * @param T	the type of input for the filter
  */

import java.util.LinkedList;

public abstract class NScalarFilter<T extends Comparable<T>> extends ScalarFilter<T> {
	
	/** Linked List of the past N inputs */
	private LinkedList<T> inputs;
	/** The maximum size of the linked list */
	private int maxSize;

	/** Constructor to create a Scalar Filter keeping track of N values
	  * @param N 	the number of past vales to be stored in memory.
	  */
	public NScalarFilter(int N) {
		// Added just to be safe. All subclasses throw excpetions in this case
		if (N < 1)
			N = 1;

		inputs = new LinkedList<T>();
		maxSize = N;
	}

	/** Adds data to the history, removing past data if it exceeds N
	  * @param data 	the data to be added to the filter
	  */
	private final void addData(T data) {
		inputs.add(data);
		if (inputs.size() > maxSize)
			inputs.pollFirst();
		assert(maxSize <= inputs.size());
	}

	/** A calculates a return value from the filter's history
	  * @return T	the value produced by the filter's operation
	  */
	abstract protected T calculateFilterValue();

	/** Given data, adds it to the history and returns the next output
	  * @param data 	the data to be filtered
	  * @return T 	the filtered data
	  * @throws IllegalArgumentException	if data is null
	  */
	public final T filter(T data) {
		checkNullFilterValue(data);
		addData(data);
		return calculateFilterValue();
	}

	/** Resets the filter's history and writes the reset value to its history
	  * @param resetValue 	the value to be written to the empty history
	  * @throws IllegalArgumentException	if resetValue is null
	  */
	public final void reset(T resetValue) {
		checkResetNull(resetValue);
		inputs = new LinkedList<T>();
		inputs.add(resetValue);
	}

	/** Getter for the input history
	  * @return LinkedList<T> 	the history of inputs
	  */
	protected final LinkedList<T> getInputHistory() {
		return inputs;
	}
}