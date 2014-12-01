/** Generic Filter Interface
  * @param T the type of input for the filter
  */

public interface Filter<T> {

	/** Field takes in data and outputs filtered data
	  * @param data	the data to be input to the filter
	  * @return T 	the filtered output
	  */
	public T filter(T data);

	/** Resets the filter
	  * @param resetValue	the value to reset the filter to
	  */
	public void reset(T resetValue);
}