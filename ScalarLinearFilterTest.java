import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ScalarLinearFilterTest {

	@Test
	public void testFilter() {
		ScalarLinearFilter test = new ScalarLinearFilter(new Double[]{0.1}, new Double[]{0.5, 0.5});
		assertEquals(test.filter(-1.0), -0.5, 0.1);
		assertEquals(test.filter(1.0), 0.05, 0.1);
		assertEquals(test.filter(2.0), 1.495, 0.001);
		test.reset(0.0);
		assertEquals(test.filter(-1.0), -0.5, 0.001);
		assertEquals(test.filter(3.0), 1.05, 0.001);
		assertEquals(test.filter(1.0), 1.895, 0.001);
		assertEquals(test.filter(2.0), 1.3105, 0.0001);
		assertEquals(test.filter(1.0), 1.36895, 0.00001);
	}

	@Test
	public void testFilterNegativeReset() {
		ScalarLinearFilter test = new ScalarLinearFilter(new Double[]{0.1}, new Double[]{0.5, 0.5});
		assertEquals(test.filter(-1.0), -0.5, 0.1);
		assertEquals(test.filter(1.0), 0.05, 0.1);
		assertEquals(test.filter(2.0), 1.495, 0.001);
		test.reset(-0.3);
		assertEquals(test.filter(-1.0), -0.6227, 0.001);
		assertEquals(test.filter(3.0), 1.06227, 0.001);
		assertEquals(test.filter(1.0), 1.89377, 0.001);
		assertEquals(test.filter(2.0), 1.3106, 0.0001);
		assertEquals(test.filter(1.0), 1.3689377, 0.00001);
	}

	@Test
	public void testFilterPositiveRest() {
		ScalarLinearFilter test = new ScalarLinearFilter(new Double[]{0.1}, new Double[]{0.5, 0.5});
		assertEquals(test.filter(-1.0), -0.5, 0.1);
		assertEquals(test.filter(1.0), 0.05, 0.1);
		assertEquals(test.filter(2.0), 1.495, 0.001);
		test.reset(0.7);
		assertEquals(test.filter(-1.0), -0.2136, 0.001);
		assertEquals(test.filter(3.0), 1.02136, 0.001);
		assertEquals(test.filter(1.0), 1.89786, 0.001);
		assertEquals(test.filter(2.0), 1.3102136, 0.0001);
		assertEquals(test.filter(1.0), 1.368978, 0.00001);
	}

	@Test
	public void testFilterAParamsSum0() {
		ScalarLinearFilter test = new ScalarLinearFilter(new Double[]{-1.0}, new Double[]{0.5});
		assertEquals(test.filter(-1.0), -0.5, 0.1);
		assertEquals(test.filter(1.0), 0.0, 0.1);
		assertEquals(test.filter(2.0), 1.0, 0.001);
		test.reset(2.5);
		assertEquals(test.filter(-1.0), -0.5, 0.001);
		assertEquals(test.filter(3.0), 1.0, 0.001);
		assertEquals(test.filter(1.0), 1.5, 0.001);
		assertEquals(test.filter(2.0), 2.5, 0.0001);
		assertEquals(test.filter(1.0), 3.0, 0.00001);
	}
}