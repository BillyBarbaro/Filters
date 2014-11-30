/** Generic ScalarFilter class
  * @author Billy Barbaro
  */

public abstract class ScalarFilter implements Filter<Double> {

	/** Field takes in data and outputs filtered data */
	abstract public Double filter(Double data);

	/** Resets the filter */
	abstract public void reset(Double resetValue);

	protected void checkNullFilterValue(Double data) {
		if (data == null)
			throw new IllegalArgumentException("Cannot filter a null value");
	}

	protected void checkResetNull(Double resetValue) {
		if (resetValue == null)
			throw new IllegalArgumentException("Cannot reset to a null value");
	}
}