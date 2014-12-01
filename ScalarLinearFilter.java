/** Implementation of a Scalar Linear Filter */

public class ScalarLinearFilter extends ScalarFilter<Double> {
	
	/** A parameters for the filter */
	private Double[] aParams;
	/** B parameters for the filter */
	private Double[] bParams;
	/** Array of past input values */
	private Double[] inputHistory;
	/** Array of past output values */
	private Double[] outputHistory;
	/** Current write index for inputs */
	private int inputWriteIndex;
	/** Current write index for outputs */
	private int outputWriteIndex;

	/** Constructor taking the arrays for parameters for the filter.  Called through the factory
	  * @param aParams	coefficients for the outputs
	  * @param bParams	coefficients for the inputs
	  */
	private ScalarLinearFilter(Double[] aParams, Double[] bParams) {
		this.aParams = aParams;
		this.bParams = bParams;
		// Input and output histories must be the same length as their parameters
		this.inputHistory = new Double[bParams.length];
		this.outputHistory = new Double[aParams.length];
	}

	/** Static factory for ScalarLinearFilter.  Both param arrays must not be null
	  * @param aParams 	an array of Doubles representing the a coefficients
	  * @param bParams	an array of Doubles representign the b coefficients
	  * @retrun ScalarLinearFilter 	an instance of ScalarLinear Filter with the requested parameters
	  * @throws IllegalArgumentException	thrown when either array is null
	  */
	public static ScalarLinearFilter makeScalarLinearFilter(Double[] aParams, Double[] bParams) {
		if (aParams == null || bParams == null)
			throw new IllegalArgumentException("Parameter list cannot be null");

		return new ScalarLinearFilter(aParams, bParams);
	}

	/** Increments the input write index with wraparound */
	private final void incrementInputWrite() {
		inputWriteIndex = (++inputWriteIndex) % inputHistory.length;
		assert(inputWriteIndex < inputHistory.length);
	}

	/** Increments the output write index with wraparound */
	private final void incrementOutputWrite() {
		outputWriteIndex = (++outputWriteIndex) % outputHistory.length;
		assert(outputWriteIndex < outputHistory.length);
	}

	/** Performs a write operation for the given input
	  * @param data	the data to be written
	  */
	private final void writeInput(Double data) {
		if (inputHistory.length > 0) {
			inputHistory[inputWriteIndex] = data;
			incrementInputWrite();
		}
	}

	/** Performs a write operation for the given output
	  * @param data	the data to be written
	  */
	private final void writeOutput(Double data) {
		if (outputHistory.length > 0) {	
			outputHistory[outputWriteIndex] = data;
			incrementOutputWrite();
		}
	}

	/** Calculates the sum of the input history and their corresponding coefficients
	  * @return Double 	the sum of the inputs * coefficients
	  */
	private Double calculateInputSum() {
		Double inputSum = 0.0;

		for (int i = 0; i < inputHistory.length; i++) {
			int currentIndex = (inputWriteIndex + i) % inputHistory.length;
			if (inputHistory[currentIndex] != null)
				inputSum += bParams[currentIndex] * inputHistory[currentIndex];
		}

		return inputSum;
	}

	/** Iterates through the outputs and their corresponding coefficients
	  * @return Double 	the sum of the inputs * coefficients
	  */
	private Double calculateOutputSum() {
		Double outputSum = 0.0;

		for (int i = 0; i < outputHistory.length; i++) {
			int currentIndex = (outputWriteIndex + i) % outputHistory.length;
			if (outputHistory[currentIndex] != null)
				outputSum += aParams[currentIndex] * outputHistory[currentIndex];
		}
		return outputSum;
	}

	/** Filters the given data
	  * @param data	the data to be run through the filter
	  * @return Double	the filtered data
	  * @throws IllegalArgumentException	if data is null
	  */
	public final Double filter(Double data) {
		checkNullFilterValue(data);
		writeInput(data);

		// Output according to the spec
		Double newOutput = calculateInputSum() - calculateOutputSum();

		writeOutput(newOutput);

		return newOutput;
	}

	/** Calculates the reset value for the input history
	  * @param resetValue 	the value the user chose to reset to
	  * @return Double 	the value to set the output history to
	  */
	private final Double calculateOutputReset(Double resetValue) {
		
		// Sums the b coefficients
		Double bSum = 0.0;
		for (int i = 0; i < bParams.length; i++) {
			bSum += bParams[i];
		}

		// Sums the a coefficients
		Double aSum = 0.0;
		for (int i = 0; i < aParams.length; i++) {
			aSum += aParams[i];
		}

		// Calculates the return value per the formula in the spec.
		// If aSum is 0, we cannot divide by 0, so we just reseet to 0
		return (aSum == -1) ? 0 : resetValue * (bSum / (aSum + 1));
	}

	/** Resets the filter
	  * @param resetValue 	the value to calculate the reset values for the input/output histories
	  * @throws IllegalArgumentException	if resetValue is null
	  */
	public final void reset(Double resetValue) {
		checkResetNull(resetValue);
		
		// Sets the input history to the resetValue
		for (int i = 0; i < inputHistory.length; i++)
			inputHistory[i] = resetValue;

		Double outputResetValue = calculateOutputReset(resetValue);

		// Sets the output history to the calculated resetValue
		for (int i = 0; i < outputHistory.length; i++)
			outputHistory[i] = outputResetValue;
	}
}