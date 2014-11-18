/** Implementation of a Gain Filter
  * @author Billy Barbaro
  */

public class GainFilter extends FIRFilter {

	/** Calls the super class's constructor with a bParams array containing only the single multiplier
	  * @param multiplier	the gain constant for the filter
	  */
	public GainFilter(Double multilpier) {
		super(new Double[] {multilpier});
	}
}