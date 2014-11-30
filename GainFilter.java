/** Implementation of a Gain Filter
  * @author Billy Barbaro
  */

public class GainFilter implements Filter<Double> {

	FIRFilter parent;

	/** Calls the super class's constructor with a bParams array containing only the single multiplier
	  * @param multiplier	the gain constant for the filter
	  */
	private GainFilter(Double multiplier) {
		parent = FIRFilter.makeFIRFilter(new Double[] {multiplier});
	}

	public static GainFilter makeGainFilter(Double multiplier) {
		if (multiplier == null)
			throw new IllegalArgumentException("Parameter list cannot be null");

		return new GainFilter(multiplier);
	}

	/** Filters the given data
	  * @param data	the data to be run through the filter
	  * @return Double	the filtered data
	  */
	public final Double filter(Double data) {
		return parent.filter(data);
	}

	/** Resets the filter
	  * @param resetValue 	the value to calculate the reset values for the input/output histories
	  */
	public final void reset(Double resetValue) {
		parent.reset(resetValue);
	}
}