public abstract class NScalarFilter extends ScalarFilter {
	
	private Double[] inputs;
	private int writeIndex;
	private int currentSize;

	public NScalarFilter(int N) {
		if (N < 1)
			N = 1;
		inputs = new Double[N];
		writeIndex = 0;
		currentSize = 0;
	}

	private void addData(Double data) {
		inputs[getWriteIndex()] = data;
		if (currentSize < inputs.length)
			currentSize++;
		this.incrementWriteIndex();
	}

	abstract protected Double calculateFilterValue();

	public Double filter(Double data) {
		addData(data);
		return calculateFilterValue();
	}

	public void reset(Double resetValue) {
		inputs = new Double[inputs.length];
		inputs[0] = resetValue;
		writeIndex = 1;
		currentSize = 1;
	}

	protected Double[] getInputHistory() {
		return inputs;
	}

	private int getWriteIndex() {
		return writeIndex;
	}

	private void incrementWriteIndex() {
		writeIndex = (++writeIndex) % inputs.length;
	}

	protected int getCurrentSize() {
		return currentSize;
	} 
}