import java.util.ArrayList;

/** Implementation of a chain of Filters
  * @author Billy Barbaro
  */

public class FilterCascade implements Filter<Double> {

	/** The filters to run an input value through */
	public ArrayList<Filter<Double>> filters;

	/** Sets the list of Filters to the input list
	  * @param filters an ArrayList of filters to run the input through
	  */
	public FilterCascade(ArrayList<Filter<Double>> filters) {
		this.filters = filters;
	}

	/** Runs the input through the cascade of filters
	  * @param data	the data to be filtered
	  * @return Double	the filtered data
	  */
	public final Double filter(Double data) {
		if (data == null)
			throw new IllegalArgumentException("Cannot filter a null value");
		for (Filter<Double> filter : filters) {
			data = filter.filter(data);
		}
		return data;
	}

	/** Resets all filters in the arraylist to the given value
	  * @param resetValue	the resetvalue for the filter
	  */
	public final void reset(Double resetValue) {
		if (resetValue == null)
			throw new IllegalArgumentException("Cannot reset to a null value");
		for (int i = 0; i < filters.size(); i++)
			resetFilterAtIndex(i, resetValue);
	}

	/** Resets a filters at the given input
	  * @param index 	the index of the filter to be reset
	  * @param resetValue	the resetvalue for the filter
	  */
	public final void resetFilterAtIndex(int index, Double resetValue) {
		if (resetValue == null)
			throw new IllegalArgumentException("Cannot reset to a null value");
		// Watch the indicies
		if (index < 0)
			index = 0;
		if (index >= filters.size())
			index = filters.size() - 1;
		filters.get(index).reset(resetValue);
	}
}