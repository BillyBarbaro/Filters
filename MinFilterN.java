/** Implementation of a Min Filter N
  * @author Billy Barbaro
  */

public class MinFilterN extends NScalarFilter {

	/** Calls the constructor of the super class */
	public MinFilterN(int N) {
		super(N);
	}
	
	/** Implementation of calculateFilterValue, takes the min value of the history
	  * @return Double	the minimum value in the history of inputs
	  */
	protected final Double calculateFilterValue() {
		// Gets input history
		Double[] history = super.getInputHistory();

		// Loops through the history keeping the smallest value it's seen
		Double min = history[0];
		for (int i = 1; i < history.length; i++) {
			if (history[i] != null)
				min = (min < history[i]) ? min : history[i];
		}
		return min;
	}
}