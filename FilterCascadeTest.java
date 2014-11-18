import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.util.ArrayList;

/** Test Bench for Filter Cascade
  * @author Billy Barbaro
  */

public class FilterCascadeTest {

	@Test
	public void testCascadeFromSpec() {
		ArrayList<Filter<Double>> filters = new ArrayList<Filter<Double>>();
		filters.add(new MaxFilterN(2));
		filters.add(new MinFilterN(3));
		FilterCascade test = new FilterCascade(filters);
		assertEquals(test.filter(-1.0), -1.0, 0.1);
		assertEquals(test.filter(3.0), -1.0, 0.1);
		assertEquals(test.filter(1.0), -1.0, 0.001);
		assertEquals(test.filter(2.0), 2.0, 0.001);
		assertEquals(test.filter(1.0), 2.0, 0.001);
		test.reset(0.0);
		assertEquals(test.filter(-1.0), 0.0, 0.1);
		assertEquals(test.filter(3.0), 0.0, 0.1);
		assertEquals(test.filter(1.0), 0.0, 0.001);
		assertEquals(test.filter(2.0), 2.0, 0.001);
		assertEquals(test.filter(1.0), 2.0, 0.001);
	}

	@Test
	public void testCascadeWithScalarLinear() {
		ArrayList<Filter<Double>> filters = new ArrayList<Filter<Double>>();
		filters.add(new ScalarLinearFilter(new Double[]{0.1}, new Double[]{0.5, 0.5}));
		filters.add(new GainFilter(2.0));
		FilterCascade test = new FilterCascade(filters);
		assertEquals(test.filter(-1.0), -1.0, 0.1);
		assertEquals(test.filter(1.0), 0.1, 0.1);
		assertEquals(test.filter(2.0), 2.9, 0.1);
		test.resetFilterAtIndex(0, 0.0);
		assertEquals(test.filter(-1.0), -1.0, 0.001);
		assertEquals(test.filter(3.0), 2.1, 0.001);
		assertEquals(test.filter(1.0), 3.79, 0.001);
		assertEquals(test.filter(2.0), 2.621, 0.0001);
		assertEquals(test.filter(1.0), 2.7379, 0.00001);
	}
}