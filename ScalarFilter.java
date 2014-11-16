public interface ScalarFilter implements Filter<Double> {
	
	public Double filter(Double data);
	public void reset(Double resetValue);
}