/** Implementation of a Max Filter N
  * @author Billy Barbaro
  */

public class MaxFilterN extends NScalarFilter {

	/** Calls the constructor of the super class */
	public MaxFilterN(int N) {
		super(N);
	}
	
	/** Implementation of calculateFilterValue, takes the max value of the history
	  * @return Double	the maximum value in the history of inputs
	  */
	protected final Double calculateFilterValue() {
		// Gets input history
		Double[] history = super.getInputHistory();

		// Loops through the history keeping the largest value it's seen
		Double max = history[0];
		for (int i = 1; i < history.length; i++) {
			if (history[i] != null)
				max = (max > history[i]) ? max : history[i];
		}
		return max;
	}
}