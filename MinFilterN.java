import java.util.Collections;

public class MinFilterN extends NScalarFilter {

	public MinFilterN(int N) {
		super(N);
	}
	
	protected Double calculateFilterValue() {
		Double[] history = super.getInputHistory();
		Double min = history[0];
		for (int i = 1; i < history.length; i++) {
			if (history[i] != null)
				min = (min < history[i]) ? min : history[i];
		}
		return min;
	}
}