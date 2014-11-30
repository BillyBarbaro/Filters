/** Implementation of an Max Filter
  * @author Billy Barbaro
  */

public class MaxFilter<T extends Comparable<T>> extends ExtremeFilter<T> {
	
	/** The last output and also the biggest value seen by the filter */
	private T output;

	/** Given data, returns the largest value it's seen since the last reset
	  * @param data	The input to the filter
	  * @return T	The largest value fed to the filter since the last reset
	  */
	public final T filter(T data) {
		checkNullFilterValue(data);
		if (output == null)
			output = data;

		// Compares to current largest value. If bigger, it replaces
		output = (output.compareTo(data) < 0) ? data : output;
		return output;
	}

	/** Sets the filter's max to the reset value
	  * @param resetValue	the new max for the filter
	  */
	public final void reset(T resetValue) {
		checkResetNull(resetValue);
		output = resetValue;
	}
}