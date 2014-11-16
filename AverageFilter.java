public class AverageFilter extends ScalarFilter {
	
	private int currentInputCount;
	private Double output;

	public AverageFilter() {
		super();
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