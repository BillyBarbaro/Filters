public class IdentityFilter implements Filter<Double> {
	
	public IdentityFilter() {
		super();
	}

	public Double filter(Double data) {
		return data;
	}
	public void reset(Double resetValue) {
		return;
	}
}