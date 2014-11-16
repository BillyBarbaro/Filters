public class MinFilter extends ExtremeFilter {
	
	private Double output;

	public Double filter(Double data) {
		output = data < output ? data : output;
		return output;
	}

	public void reset(Double resetValue) {
		output = resetValue;
	}
}