/** Abstract Class for filters taking extreme values
  * @author Billy Barbaro
  */

public abstract class ExtremeFilter<T extends Comparable<T>> extends ScalarFilter<T> {
	
	/** Field takes in data and outputs filtered data */
	abstract public T filter(T data);

	/** Resets the filter */
	abstract public void reset(T resetValue);
}