/** Implementation of an Max Filter
  * @author Billy Barbaro
  */

public class MaxFilter extends ExtremeFilter {
	
	/** The last output and also the biggest value seen by the filter */
	private Double output;

	/** Given data, returns the largest value it's seen since the last reset
	  * @param data	The input to the filter
	  * @return Double	The largest value fed to the filter since the last reset
	  */
	public final Double filter(Double data) {
		if (output == null)
			output = data;

		// Compares to current largest value. If bigger, it replaces
		output = (data > output) ? data : output;
		return output;
	}

	/** Sets the filter's max to the reset value
	  * @param resetValue	the new max for the filter
	  */
	public final void reset(Double resetValue) {
		if (output == null)
			output = new Double(resetValue);
		else
			output = resetValue;
	}
}