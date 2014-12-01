/** Implementation of a FIR Filter */

public class FIRFilter implements Filter<Double> {
	
	/** Class the FIR Filter is made from */
	ScalarLinearFilter parent;

	/** Creates an instance of ScalarLinearFilter configured as a FIRFilter.  Called through a static factory
	  * @param bParams	the coefficients for the FIR Filter
	  */
	private FIRFilter(Double[] bParams) {
		parent = ScalarLinearFilter.makeScalarLinearFilter(new Double[0], bParams);
	}

	/** Static factory for FIRFilter.  The param array must not be null
	  * @param bParams	an array of Doubles representign the b coefficients
	  * @retrun FIRFilter 	an instance of FIRFilter with the requested parameters
	  * @throws IllegalArgumentException	thrown when the array is null
	  */
	public static FIRFilter makeFIRFilter(Double[] bParams) {
		if (bParams == null)
			throw new IllegalArgumentException("Parameter list cannot be null");

		return new FIRFilter(bParams);
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