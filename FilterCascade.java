/** Implementation of a chain of Filters
  * @param T	the type of input for the filter
  */

import java.util.ArrayList;

public class FilterCascade<T extends Comparable<T>> implements Filter<T> {

	/** The filters to run an input value through */
	public ArrayList<Filter<T>> filters;

	/** A static inner calls used to create the cascade of filters
	  * @param Q the type of object to be filtered. Must implement Comparable
	  */
	public static class FilterCascadeBuilder<Q extends Comparable<Q>> {

		/** An array list of filters to be passed to the FilterCascade constructor */
		private ArrayList<Filter<Q>> filters;

		/** Creates a builder. If handed null, begins with an identity filter
		  * @param filter 	the first filter for the cascade
		  */
		public FilterCascadeBuilder(Filter<Q> filter) {
			if (filter == null)
				filter = new IdentityFilter<Q>();

			filters = new ArrayList<Filter<Q>>();
			filters.add(filter);
		}

		/** Adds a filter to the builder
		  * @param filter 	the next filter in the cascade
		  * @return	FilterCascadeBuilder 	the instance of the builder so calls can be chained
		  */
		public FilterCascadeBuilder<Q> addFilter(Filter<Q> filter) {
			if (filter == null)
				throw new IllegalArgumentException("Filters may not be null");

			filters.add(filter);
			return this;
		}

		/** Calls the construtor for a FilterCascade with the current filter list
		  * @return FilterCascade 	an instance of FilterCascade with the filters added to the builder
		  */
		public FilterCascade<Q> build() {
			return new FilterCascade<Q>(filters);
		}
	}

	/** Called through the builder.  Sets the list of Filters to the input list.
	  * @param filters an ArrayList of filters to run the input through
	  */
	private FilterCascade(ArrayList<Filter<T>> filters) {
		this.filters = filters;
	}

	/** Runs the input through the cascade of filters
	  * @param data	the data to be filtered
	  * @return T	the filtered data
	  * @throws IllegalArgumentException	if data is null
	  */
	public final T filter(T data) {
		if (data == null)
			throw new IllegalArgumentException("Cannot filter a null value");

		for (Filter<T> filter : filters) {
			data = filter.filter(data);
		}
		return data;
	}

	/** Resets all filters in the arraylist to the given value
	  * @param resetValue	the resetvalue for the filter
	  * @throws IllegalArgumentException	if resetValue is null
	  */
	public final void reset(T resetValue) {
		if (resetValue == null)
			throw new IllegalArgumentException("Cannot reset to a null value");

		for (int i = 0; i < filters.size(); i++)
			resetFilterAtIndex(i, resetValue);
	}

	/** Resets a filters at the given input
	  * @param index 	the index of the filter to be reset
	  * @param resetValue	the resetvalue for the filter
	  * @throws IllegalArgumentException	if resetValue is null
	  */
	public final void resetFilterAtIndex(int index, T resetValue) {
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