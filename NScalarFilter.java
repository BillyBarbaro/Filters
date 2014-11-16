import java.util.ArrayList;

public abstract class NScalarFilter extends ScalarFilter {
	
	private ArrayList<Double> inputs;
	private int writeIndex;
	private int currentSize;

	public NScalarFilter(int N) {
		if (N < 0)
			N = 0;
		inputs = new ArrayList<Double>(N);
		writeIndex = 0;
		currentSize = 0;
	}

	private void addData(Double data) {
		inputs.set(this.getWriteIndex(), data);
		this.incrementWriteIndex();
	}

	abstract protected Double calculateFilterValue();

	public Double filter(Double data) {
		addData(data);
		return calculateFilterValue();
	}

	public void reset(Double resetValue) {
		inputs = new ArrayList<Double>(inputs.size());
		writeIndex = 0;
		currentSize = 0;
	}

	protected ArrayList<Double> getInputHistory() {
		return inputs;
	}

	private int getWriteIndex() {
		return writeIndex;
	}

	private void incrementWriteIndex() {
		writeIndex = (writeIndex++) % inputs.size();
	}

	private int getCurrentSize() {
		return currentSize;
	} 
}