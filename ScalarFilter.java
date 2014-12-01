/** Generic ScalarFilter class
  * @param T the type of input for the filter
  */

public abstract class ScalarFilter<T> implements Filter<T> {

	/** Field takes in data and outputs filtered data
	  * @param data	the data to be input to the filter
	  * @return T 	the filtered output
	  */
	abstract public T filter(T data);

	/** Resets the filter
	  * @param resetValue	the value to reset the filter to
	  */
	abstract public void reset(T resetValue);

	/** Checks that input data is not null.  If it is, throws an exception
	  * @param data	the data to be checked for null
	  * @throws IllegalArgumentException	if data is null
	  */
	protected void checkNullFilterValue(T data) {
		if (data == null)
			throw new IllegalArgumentException("Cannot filter a null value");
	}

	/** Checks that resetValue is not null.  If it is, throws an exception
	  * @param resetValue	the reset value to be checked for null
	  * @throws IllegalArgumentException	if resetValue is null
	  */
	protected void checkResetNull(T resetValue) {
		if (resetValue == null)
			throw new IllegalArgumentException("Cannot reset to a null value");
	}
}