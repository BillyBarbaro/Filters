import java.util.ArrayList;

public class BinomialFilter extends FIRFilter {

	private static Double[] createBinomial(int N) {

		if (N < 0)
			N *= -1;

		Double[] bParams = new Double[N];

		for (int i = 0; i < N; i++) {
			bParams[i] = Combinations.nChoosek(N, i);
		}
		return bParams;
	}
	
	public BinomialFilter(int N) {
		super(createBinomial(N));
	}
}