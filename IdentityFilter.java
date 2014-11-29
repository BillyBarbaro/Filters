/** Implementation of an Identity Filter
  * @author Billy Barbaro
  */

public class IdentityFilter extends ScalarFilter {
	
	/** No real setup needed */
	public IdentityFilter() {
		super();
	}

	/** Returns the input value
	  * @param data	the data to be filtered
	  * @return Double	the filtered value
	  */
	public final Double filter(Double data) {
		checkNullFilterValue(data);
		return data;
	}

	/** Resets the filter. For the identity filter really does nothing
	  * @param resetValue	The value for the filter to be reset to
	  */
	public final void reset(Double resetValue) {
		return;
	}
}