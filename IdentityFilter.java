public class IdentityFilter extends ScalarFilter {
	
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