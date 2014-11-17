import java.util.Collections;

public class AverageFilterN extends NScalarFilter {

	public AverageFilterN(int N) {
		super(N);
	}
	
	protected Double calculateFilterValue() {
		if (super.getInputHistory().length == 0)
			return 0.0;
		
		Double runningTotal = 0.0;
		for (Double d : super.getInputHistory()) {
			if (d != null)
				runningTotal += d;
		}
		return runningTotal / super.getCurrentSize();
	}	
}