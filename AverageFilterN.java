/** Implementation of a Average Filter N */

import java.util.LinkedList;

public class AverageFilterN extends NScalarFilter<Double> {

	/** Calls the constructor of the super class
	  * @param N	the number of past values the filter stores
	  */
	private AverageFilterN(int N) {
		super(N);
	}

	/** Static factory to create a AverageFilterN
	  * @param N 	the number of past values the filter stores
	  * @return AverageFilterN 	an instance of AverageFilterN with specified size
	  * @throws IllegalArgumentException 	thrown if n is less than 1
	  */
	public static AverageFilterN averageFilterWithN(int N) {
		if (N < 1)
			throw new IllegalArgumentException("A filter must have a size of at least 1");

		return new AverageFilterN(N);
	}
	
	/** Implementation of calculateFilterValue, takes the min value of the history
	  * @return Double	the average value in the history of inputs
	  */
	protected final Double calculateFilterValue() {
		assert(super.getInputHistory().size() != 0);

		// Keeps the running total of all entries in the list
		Double runningTotal = 0.0;
		for (Double d : super.getInputHistory()) {
			runningTotal += d;
		}
		
		// Divides them out to give the average
		return runningTotal / super.getInputHistory().size();
	}	
}