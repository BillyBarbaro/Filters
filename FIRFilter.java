/** Implementation of a FIR Filter
  * @author Billy Barbaro
  */

public class FIRFilter implements Filter<Double> {
	
	ScalarLinearFilter parent;

	/** Calls the super class's constructor with aParams as an empty array
	  * @param bParams	the coefficients for the FIR Filter
	  */
	private FIRFilter(Double[] bParams) {
		parent = ScalarLinearFilter.makeScalarLinearFilter(new Double[0], bParams);
	}

	public static FIRFilter makeFIRFilter(Double[] bParams) {
		if (bParams == null)
			throw new IllegalArgumentException("Parameter list cannot be null");

		return new FIRFilter(bParams);
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