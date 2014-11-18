/** Abstract Class for filters taking extreme values
  * @author Billy Barbaro
  */

public abstract class ExtremeFilter extends ScalarFilter {
	
	/** Field takes in data and outputs filtered data */
	abstract public Double filter(Double data);

	/** Resets the filter */
	abstract public void reset(Double resetValue);
}