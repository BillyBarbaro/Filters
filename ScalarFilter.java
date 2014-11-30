/** Generic ScalarFilter class
  * @author Billy Barbaro
  */

public abstract class ScalarFilter<T> implements Filter<T> {

	/** Field takes in data and outputs filtered data */
	abstract public T filter(T data);

	/** Resets the filter */
	abstract public void reset(T resetValue);

	protected void checkNullFilterValue(T data) {
		if (data == null)
			throw new IllegalArgumentException("Cannot filter a null value");
	}

	protected void checkResetNull(T resetValue) {
		if (resetValue == null)
			throw new IllegalArgumentException("Cannot reset to a null value");
	}
}