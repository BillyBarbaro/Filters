public abstract class ExtremeFilter implements Filter<Double> {
	
	abstract public Double filter(Double data);
	abstract public void reset(Double resetValue);
}