import java.util.ArrayList;

public class FilterCascade implements Filter<Double> {

	public ArrayList<Filter<Double>> filters;

	public FilterCascade(ArrayList<Filter<Double>> filters) {
		this.filters = filters;
	}

	public Double filter(Double data) {
		for (Filter<Double> filter : filters) {
			data = filter.filter(data);
		}
		return data;
	}

	public void reset(Double resetValue) {
		for (int i = 0; i < filters.size(); i++)
			resetFilterAtIndex(i, resetValue);
	}

	public void resetFilterAtIndex(int index, Double resetValue) {
		filters.get(index).reset(resetValue);
	}
}