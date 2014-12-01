/** Abstract Class for filters taking extreme values
  * @param T	the type of input for the filter
  */

public abstract class ExtremeFilter<T extends Comparable<T>> extends ScalarFilter<T> {
	
	/** Field takes in data and outputs filtered data
	  * @param data	the data to be input to the filter
	  * @return T 	the filtered output
	  */
	abstract public T filter(T data);

	/** Resets the filter
	  * @param resetValue	the value to reset the filter to
	  */
	abstract public void reset(T resetValue);
}