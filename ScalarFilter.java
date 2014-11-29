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

	protected Double replaceResetNull(Double resetValue) {
		return (resetValue == null) ? 0.0 : resetValue;
	}
}