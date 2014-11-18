/** Interface for filters only acknowledging the last N entries
  * @author Billy Barbaro
  */

public abstract class NScalarFilter extends ScalarFilter {
	
	/** Array of the past N inputs */
	private Double[] inputs;
	/** The current index to write in the array */
	private int writeIndex;
	/** Tracks the number of values fed to the filter up to N */
	private int currentSize;

	/** Constructor to create a Scalar Filter keeping track of N values
	  * @param N 	the number of past vales to be stored in memory
	  */
	public NScalarFilter(int N) {
		if (N < 1)
			N = 1;
		inputs = new Double[N];
		writeIndex = 0;
		currentSize = 0;
	}

	/** Addes data to the history, overwriting past data if it exceeds N
	  * @param data 	the data to be added to the filter
	  */
	private void addData(Double data) {
		inputs[getWriteIndex()] = data;
		if (currentSize < inputs.length)
			currentSize++;
		assert(currentSize <= inputs.length);
		this.incrementWriteIndex();
	}

	/** A calculates a return value from the filter's history
	  * @return Double	the output of the filtering function
	  */
	abstract protected Double calculateFilterValue();

	/** Given data, adds it to the history and returns the next output
	  * @param data 	the data to be filtered
	  * @return Double 	the filtered data
	  */
	public Double filter(Double data) {
		addData(data);
		return calculateFilterValue();
	}

	/** Resets the filter's history and writes the reset value to it's history
	  * @param resetValue 	the value to be written to the empty history
	  */
	public void reset(Double resetValue) {
		inputs = new Double[inputs.length];
		inputs[0] = resetValue;
		writeIndex = 1;
		currentSize = 1;
	}

	/** Getter for the input history
	  * @return Double[] 	the history of inputs
	  */
	protected Double[] getInputHistory() {
		return inputs;
	}

	/** Getter for the write index
	  * @return int 	the write index
	  */
	private int getWriteIndex() {
		return writeIndex;
	}

	/** Increments the write index with wraparound */
	private void incrementWriteIndex() {
		writeIndex = (++writeIndex) % inputs.length;
		assert(writeIndex < inputs.length);
	}

	/** Gets the current number of inputs in the history
	  * @return int 	the number of values in the history
	  */
	protected int getCurrentSize() {
		return currentSize;
	} 
}