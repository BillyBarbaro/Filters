/** Implementation of a Average Filter N
  * @author Billy Barbaro
  */

import java.util.LinkedList;

public class AverageFilterN extends NScalarFilter<Double> {

	/** Calls the constructor of the super class */
	public AverageFilterN(int N) {
		super(N);
	}
	
	/** Implementation of calculateFilterValue, takes the min value of the history
	  * @return Double	the average value in the history of inputs
	  */
	protected final Double calculateFilterValue() {
		assert(super.getInputHistory().size() != 0);

		// Keeps the running total of all entries in the list
		Double runningTotal = 0.0;
		for (Double d : super.getInputHistory()) {
			if (d != null)
				runningTotal += d;
		}
		
		// Divides them out to give the average
		return runningTotal / super.getInputHistory().size();
	}	
}