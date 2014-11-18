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
	public Double filter(Double data) {
		return data;
	}

	/** Resets the filter. For the identity filter really does nothing
	  * @param resetValue	The value for the filter to be reset to
	  */
	public void reset(Double resetValue) {
		return;
	}
}