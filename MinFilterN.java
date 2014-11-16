import java.util.Collections;

public class MinFilterN extends NScalarFilter {

	public MinFilterN(int N) {
		super(N);
	}
	
	protected Double calculateFilterValue() {
		return Collections.min(super.getInputHistory());
	}
}