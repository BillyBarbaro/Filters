/** Implementation of a Binomial Filter
  * @author Billy Barbaro
  */

public class BinomialFilter implements Filter<Double> {

	FIRFilter parent;

	/** Creates an array with the value at index i as N choose i
	  * @param N 	the sample size we're choosing from
	  * @return Double[] the array of binomials
	  */
	private static final Double[] createBinomial(int N) {
		// If we're given a negative value, we just reverse it
		if (N < 0)
			N *= -1;

		assert(N >= 0);

		Double[] bParams = new Double[N];
		// A table to draw our combinations from
		int[][] binomial = Combinations.nChoosek(N, N);

		// Stores the combination table value into the paramter list
		for (int i = 0; i < N; i++) {
			bParams[i] = new Double(binomial[N][i]);
		}
		return bParams;
	}
	
	/** Calls the super class's constructor with a bParams array being the binomial array
	  * @param N	the number of values we choose from
	  */
	private BinomialFilter(int N) {
		parent = FIRFilter.makeFIRFilter(createBinomial(N));
	}

	public static BinomialFilter makeBinomialFilter(int N) {
		return new BinomialFilter(N);
	}

	/** Filters the given data
	  * @param data	the data to be run through the filter
	  * @return Double	the filtered data
	  */
	public final Double filter(Double data) {
		return parent.filter(data);
	}

	/** Resets the filter
	  * @param resetValue 	the value to calculate the reset values for the input/output histories
	  */
	public final void reset(Double resetValue) {
		parent.reset(resetValue);
	}
}