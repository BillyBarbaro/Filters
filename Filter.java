public interface Filter<T> {
	
	public T filter(T data);
	public void reset(T resetValue);
}