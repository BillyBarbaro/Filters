/** Generic Filter Interface
  * @author Billy Barbaro
  */

public interface Filter<T> {

	/** Field takes in data and outputs filtered data */
	public T filter(T data);

	/** Resets the filter */
	public void reset(T resetValue);
}