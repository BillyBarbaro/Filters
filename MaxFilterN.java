import java.util.Collections;

public class MaxFilterN extends NScalarFilter {

	public MaxFilterN(int N) {
		super(N);
	}
	
	protected Double calculateFilterValue() {
		return Collections.max(super.getInputHistory());
	}
}