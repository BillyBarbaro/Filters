import java.util.ArrayList;

public class FIRFilter extends ScalarLinearFilter {
	
	public FIRFilter(ArrayList<Double> bParams) {
		super(new ArrayList<Double>(), bParams);
	}
}