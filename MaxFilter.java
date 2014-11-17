public class MaxFilter extends ExtremeFilter {
	
	private Double output;

	public Double filter(Double data) {
		if (output == null)
			output = data;

		output = (data > output) ? data : output;
		return output;
	}

	public void reset(Double resetValue) {
		if (output == null)
			output = new Double(resetValue);
		else
			output = resetValue;
	}
}