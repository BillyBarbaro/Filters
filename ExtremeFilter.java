public abstract class ExtremeFilter extends ScalarFilter {
	
	abstract public Double filter(Double data);
	abstract public void reset(Double resetValue);
}