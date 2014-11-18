/** Implementation of a Scalar Linear Filter
  * @author Billy Barbaro
  */

public class ScalarLinearFilter implements Filter<Double> {
	
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

	/** Constructor taking the arrays for parameters for the filter
	  * @param aParams	coefficients for the outputs
	  * @param bParams	coefficients for the inputs
	  */
	public ScalarLinearFilter(Double[] aParams, Double[] bParams) {
		this.aParams = aParams;
		this.bParams = bParams;
		// Input and output histories must be the same length as their parameters
		this.inputHistory = new Double[bParams.length];
		this.outputHistory = new Double[aParams.length];
	}

	/** Increments the input write index with wraparound */
	private void incrementInputWrite() {
		inputWriteIndex = (++inputWriteIndex) % inputHistory.length;
		assert(inputWriteIndex < inputHistory.length);
	}

	/** Increments the output write index with wraparound */
	private void incrementOutputWrite() {
		outputWriteIndex = (++outputWriteIndex) % outputHistory.length;
		assert(outputWriteIndex < outputHistory.length);
	}

	/** Performs a write operation for the given input
	  * @param data	the data to be written
	  */
	private void writeInput(Double data) {
		if (inputHistory.length > 0) {
			inputHistory[inputWriteIndex] = data;
			incrementInputWrite();
		}
	}

	/** Performs a write operation for the given output
	  * @param data	the data to be written
	  */
	private void writeOutput(Double data) {
		if (outputHistory.length > 0) {	
			outputHistory[outputWriteIndex] = data;
			incrementOutputWrite();
		}
	}

	/** Filters the given data
	  * @param data	the data to be run through the filter
	  * @return Double	the filtered data
	  */
	public Double filter(Double data) {

		writeInput(data);

		// Iterates through the inputs and their corresponding coefficients
		Double newOutput = 0.0;
		for (int i = 0; i < inputHistory.length; i++) {
			int currentIndex = (inputWriteIndex + i) % inputHistory.length;
			if (inputHistory[currentIndex] != null)
				newOutput += bParams[currentIndex] * inputHistory[currentIndex];
		}

		// Iterates through the outputs and their corresponding coefficients
		for (int i = 0; i < outputHistory.length; i++) {
			int currentIndex = (outputWriteIndex + i) % outputHistory.length;
			if (outputHistory[currentIndex] != null)
				newOutput -= aParams[currentIndex] * outputHistory[currentIndex];
		}

		writeOutput(newOutput);

		return newOutput;
	}

	/** Calculates the reset value for the input history
	  * @param resetValue 	the value the user chose to reset to
	  * @return Double 	the value to set the output history to
	  */
	private Double calculateOutputReset(Double resetValue) {
		
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
		return aSum == -1 ? 0 : resetValue * (bSum / (aSum + 1));
	}

	/** Resets the filter
	  * @param resetValue 	the value to calculate the reset values for the input/output histories
	  */
	public void reset(Double resetValue) {

		// Sets the input history to the resetValue
		for (int i = 0; i < inputHistory.length; i++)
			inputHistory[i] = resetValue;

		Double outputResetValue = calculateOutputReset(resetValue);

		// Sets the output history to the calculated resetValue
		for (int i = 0; i < outputHistory.length; i++)
			outputHistory[i] = outputResetValue;
	}
}