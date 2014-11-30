/** Implementation of a Min Filter
  * @author Billy Barbaro
  */

public class MinFilter extends ExtremeFilter {
	
	/** The last output and also the smallest value seen by the filter */
	private Double output;

	/** Given data, returns the smallest value it's seen since the last reset
	  * @param data	The input to the filter
	  * @return Double	The smallest value fed to the filter since the last reset
	  */
	public final Double filter(Double data) {
		checkNullFilterValue(data);
		if (output == null)
			output = data;

		// Compares to current largest value. If smaller, it replaces
		output = (data < output) ? data : output;
		return output;
	}

	/** Sets the filter's min to the reset value
	  * @param resetValue	the new min for the filter
	  */
	public final void reset(Double resetValue) {
		checkResetNull(resetValue);
		if (output == null)
			output = new Double(resetValue);
		else
			output = resetValue;
	}
}