/** Implementation of an Identity Filter
  * @param T	the type of input for the filter
  */
public class IdentityFilter<T> extends ScalarFilter<T> {
	
	/** No real setup needed */
	public IdentityFilter() {
		super();
	}

	/** Returns the input value
	  * @param data	the data to be filtered
	  * @return T	the filtered value
	  * @throws IllegalArgumentException	if data is null
	  */
	public final T filter(T data) {
		checkNullFilterValue(data);
		return data;
	}

	/** Resets the filter. For the identity filter really does nothing
	  * @param resetValue	The value for the filter to be reset to
	  * @throws IllegalArgumentException	if resetValue is null
	  */
	public final void reset(T resetValue) {
		checkResetNull(resetValue);
		return;
	}
}