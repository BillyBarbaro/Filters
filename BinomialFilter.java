import java.util.ArrayList;

public class BinomialFilter extends FIRFilter {

	private static ArrayList<Double> createBinomial(int N) {

		ArrayList<Double> bParams = new ArrayList<Double>(N);

		for (int i = 0; i < N; i++) {
			bParams.add(Combinations.nChoosei(N, i));
		}

		return bParams;
	}
	
	public BinomialFilter(int N) {
		super(createBinomial(N));
	}
}