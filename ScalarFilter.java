/** Generic ScalarFilter class
  * @author Billy Barbaro
  */

public abstract class ScalarFilter implements Filter<Double> {

	/** Field takes in data and outputs filtered data */
	abstract public Double filter(Double data);

	/** Resets the filter */
	abstract public void reset(Double resetValue);
}