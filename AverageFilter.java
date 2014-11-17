public class AverageFilter extends ScalarFilter {
	
	private int currentInputCount;
	private Double output;

	public AverageFilter() {
		output = new Double(0.0);
	}

	public Double filter(Double data) {
		currentInputCount++;
		output = ((output * (currentInputCount - 1)) + data) / currentInputCount;
		return output;
	}

	public void reset(Double resetValue) {
		reset();
	}

	public void reset() {
		currentInputCount = 0;
		output = 0.0;
	}
}