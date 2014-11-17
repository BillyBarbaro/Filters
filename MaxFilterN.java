import java.util.Collections;
import java.util.Arrays;

public class MaxFilterN extends NScalarFilter {

	public MaxFilterN(int N) {
		super(N);
	}
	
	protected Double calculateFilterValue() {
		Double[] history = super.getInputHistory();
		Double max = history[0];
		for (int i = 1; i < history.length; i++) {
			if (history[i] != null)
				max = (max > history[i]) ? max : history[i];
		}
		return max;
	}
}