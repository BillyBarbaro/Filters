/** Implementation of a Average Filter N
  * @author Billy Barbaro
  */

public class AverageFilterN extends NScalarFilter {

	/** Calls the constructor of the super class */
	public AverageFilterN(int N) {
		super(N);
	}
	
	/** Implementation of calculateFilterValue, takes the min value of the history
	  * @return Double	the average value in the history of inputs
	  */
	protected Double calculateFilterValue() {
		assert(getInputHistory().length != 0);

		// Keeps the running total of all entries in the list
		Double runningTotal = 0.0;
		for (Double d : super.getInputHistory()) {
			if (d != null)
				runningTotal += d;
		}
		assert(super.getCurrentSize() != 0);
		// Divides them out to give the average
		return runningTotal / super.getCurrentSize();
	}	
}