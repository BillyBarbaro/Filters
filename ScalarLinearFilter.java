import java.util.ArrayList;

public class ScalarLinearFilter implements Filter<Double> {
	
	private Double[] aParams;
	private Double[] bParams;
	private Double[] inputHistory;
	private Double[] outputHistory;
	private int inputWriteIndex;
	private int outputWriteIndex;

	public ScalarLinearFilter(Double[] aParams, Double[] bParams) {
		this.aParams = aParams;
		this.bParams = bParams;
		this.inputHistory = new Double[bParams.length];
		this.outputHistory = new Double[aParams.length];
	}

	private void incrementInputWrite() {
		inputWriteIndex = (++inputWriteIndex) % inputHistory.length;
	}

	private void incrementOutputWrite() {
		outputWriteIndex = (++outputWriteIndex) % outputHistory.length;
	}

	// COMPLEXITY
	public Double filter(Double data) {
		if (inputHistory.length > 0) {
			inputHistory[inputWriteIndex] = data;
			incrementInputWrite();
		}

		Double newOutput = 0.0;
		for (int i = 0; i < inputHistory.length; i++) {
			int currentIndex = (inputWriteIndex + i) % inputHistory.length;
			if (inputHistory[currentIndex] != null)
				newOutput += bParams[currentIndex] * inputHistory[currentIndex];
		}

		for (int i = 0; i < outputHistory.length; i++) {
			int currentIndex = (outputWriteIndex + i) % outputHistory.length;
			if (outputHistory[currentIndex] != null)
				newOutput -= aParams[currentIndex] * outputHistory[currentIndex];
		}

		if (outputHistory.length > 0) {	
			outputHistory[outputWriteIndex] = newOutput;
			incrementOutputWrite();
		}

		return newOutput;
	}

	private Double calculateOutputReset(Double resetValue) {
		Double bSum = 0.0;
		for (int i = 0; i < bParams.length; i++) {
			bSum += bParams[i];
		}

		Double aSum = 0.0;
		for (int i = 0; i < aParams.length; i++) {
			aSum += aParams[i];
		}

		return aSum == -1 ? 0 : resetValue * (bSum / (aSum + 1));
	}

	public void reset(Double resetValue) {

		for (int i = 0; i < inputHistory.length; i++)
			inputHistory[i] = resetValue;

		Double outputResetValue = calculateOutputReset(resetValue);

		for (int i = 0; i < outputHistory.length; i++)
			outputHistory[i] = outputResetValue;
	}
}