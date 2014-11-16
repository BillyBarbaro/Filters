public abstract class ScalarFilter implements Filter<Double> {

	abstract public Double filter(Double data);
	abstract public void reset(Double resetValue);
}