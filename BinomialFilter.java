public class BinomialFilter extends FIRFilter {

	private static Double nChoosei(int N, int i) {
		
	}

	private static ArrayList<Double> createBinomial(int N) {

		ArrayList<Double> bParams = new ArrayList<Double>(N);

		for (int i = 0; i < N; i++) {
			bParams.add(nChoosei(N, i));
		}
	}
	
	public BinomialFilter(int N) {
		super(createBinomial(N));
	}
}