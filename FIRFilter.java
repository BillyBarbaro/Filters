/** Implementation of a FIR Filter
  * @author Billy Barbaro
  */

public class FIRFilter extends ScalarLinearFilter {
	
	/** Calls the super class's constructor with aParams as an empty array
	  * @param bParams	the coefficients for the FIR Filter
	  */
	public FIRFilter(Double[] bParams) {
		super(new Double[0], bParams);
	}
}