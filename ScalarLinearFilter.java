import java.util.ArrayList;

public class ScalarLinearFilter implements Filter<Double> {
	
	private ArrayList<Double> aParams;
	private ArrayList<Double> bParams;
	private ArrayList<Double> inputHistory;
	private ArrayList<Double> outputHistory;
	private int inputWriteIndex;
	private int outputWriteIndex;

	public ScalarLinearFilter(ArrayList<Double> aParams, ArrayList<Double> bParams) {
		this.aParams = aParams;
		this.bParams = bParams;
		this.inputHistory = new ArrayList<Double>(bParams.size());
		this.outputHistory = new ArrayList<Double>(aParams.size());
	}

	private void incrementInputWrite() {
		inputWriteIndex = (inputWriteIndex++) % inputHistory.size();
	}

	private void incrementOutputWrite() {
		outputWriteIndex = (outputWriteIndex++) % inputHistory.size();
	}

	public Double filter(Double data) {
		inputHistory.set(inputWriteIndex, data);
		incrementInputWrite();

		Double newOutput = 0.0;
		for (int i = 0; i < inputHistory.size(); i++) {
			int currentIndex = (inputWriteIndex + i) % inputHistory.size();
			newOutput += bParams.get(currentIndex) * inputHistory.get(currentIndex);
		}

		for (int i = 0; i < outputHistory.size(); i++) {
			int currentIndex = (outputWriteIndex + i) % outputHistory.size();
			newOutput -= aParams.get(currentIndex) * outputHistory.get(currentIndex);
		}

		outputHistory.set(outputWriteIndex, newOutput);
		incrementOutputWrite();

		return newOutput;
	}

	private Double calculateOutputReset(Double resetValue) {
		Double bSum = 0.0;
		for (int i = 0; i < bParams.size(); i++) {
			bSum += bParams.get(i);
		}

		Double aSum = 0.0;
		for (int i = 0; i < aParams.size(); i++) {
			aSum += aParams.get(i);
		}

		return aSum == -1 ? 0 : resetValue * (bSum / (aSum + 1));
	}

	public void reset(Double resetValue) {

		for (int i = 0; i < inputHistory.size(); i++)
			inputHistory.set(i, resetValue);

		Double outputResetValue = calculateOutputReset(resetValue);

		for (int i = 0; i < outputHistory.size(); i++)
			outputHistory.set(i, outputResetValue);
	}
}