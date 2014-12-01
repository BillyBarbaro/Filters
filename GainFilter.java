/** Implementation of a Gain Filter */

public class GainFilter implements Filter<Double> {

	/** Class the Gain Filter is made from */
	FIRFilter parent;

	/** Creates an instance of FIRFilter configured as a GainFilter.  Called through a static factory
	  * @param multiplier	the gain constant for the filter
	  */
	private GainFilter(Double multiplier) {
		parent = FIRFilter.makeFIRFilter(new Double[] {multiplier});
	}

	/** Static factory for GainFilter.  The argument must not be null
	  * @param multiplier	the gain for the input values
	  * @return GainFilter 	an instance of GainFilter with the requested gain
	  * @throws IllegalArgumentException	thrown when the double is null
	  */
	public static GainFilter makeGainFilter(Double multiplier) {
		if (multiplier == null)
			throw new IllegalArgumentException("Parameter list cannot be null");

		return new GainFilter(multiplier);
	}

	/** Filters the given data
	  * @param data	the data to be run through the filter
	  * @return Double	the filtered data
	  * @throws IllegalArgumentException	if data is null
	  */
	public final Double filter(Double data) {
		return parent.filter(data);
	}

	/** Resets the filter
	  * @param resetValue 	the value to calculate the reset values for the input/output histories
	  * @throws IllegalArgumentException	if resetValue is null
	  */
	public final void reset(Double resetValue) {
		parent.reset(resetValue);
	}
}