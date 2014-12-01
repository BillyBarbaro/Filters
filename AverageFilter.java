/** Implementation of an Average Filter */

public class AverageFilter extends ScalarFilter<Double> {
	
	/** Keeps track of how many values have been pushed through since the last reset */
	private int currentInputCount;
	/** Keeps track of the last output */
	private Double output;

	/** Constructor, initializes the output to 0 */
	public AverageFilter() {
		output = new Double(0.0);
	}

	/** Takes the average of all values input since the last reset
	  * @param data	The next value fed to the filter
	  * @return Double	an average of all values input to the filter thus far
	  * @throws IllegalArgumentException	if data is null
	  */
	public final Double filter(Double data) {
		checkNullFilterValue(data);
		currentInputCount++;
		// Formula to calculate new average
		assert(currentInputCount != 0);
		output = ((output * (currentInputCount - 1)) + data) / currentInputCount;
		return output;
	}

	/** A reset value is not valid in this case, so all resets are sent to the generic reset method
	  * @param resetValue	ignored
	  * @throws IllegalArgumentException	if resetValue is null
	  */
	public final void reset(Double resetValue) {
		checkResetNull(resetValue);
		reset();
	}

	/** Resets the filter, setting the current number of inputs and current average to 0 */
	public final void reset() {
		currentInputCount = 0;
		output = 0.0;
	}
}