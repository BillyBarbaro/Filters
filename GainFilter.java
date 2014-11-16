import java.util.Arrays;
import java.util.ArrayList;

public class GainFilter extends FIRFilter {

	private ArrayList<Double> makeBParams(Double multilpier) {
		ArrayList<Double> bParams = new ArrayList<Double>(1);
		bParams.add(multilpier);
		return bParams;
	}

	public GainFilter(Double multilpier) {
		super(new ArrayList<Double>(Arrays.asList(multilpier)));
	}
}