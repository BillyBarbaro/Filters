/** Implementation of an Identity Filter
  * @author Billy Barbaro
  */

public class IdentityFilter<T> extends ScalarFilter<T> {
	
	/** No real setup needed */
	public IdentityFilter() {
		super();
	}

	/** Returns the input value
	  * @param data	the data to be filtered
	  * @return Double	the filtered value
	  */
	public final T filter(T data) {
		checkNullFilterValue(data);
		return data;
	}

	/** Resets the filter. For the identity filter really does nothing
	  * @param resetValue	The value for the filter to be reset to
	  */
	public final void reset(T resetValue) {
		checkResetNull(resetValue);
		return;
	}
}