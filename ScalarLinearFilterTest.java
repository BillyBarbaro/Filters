import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/** Test Bench for Scalar Linear Filter
  * @author Billy Barbaro
  */

public class ScalarLinearFilterTest {

	@Rule
    public ExpectedException filterNull = ExpectedException.none();
	@Test
	public void testNullFilter() {
		ScalarLinearFilter testFilter = ScalarLinearFilter.makeScalarLinearFilter(new Double[]{0.1}, new Double[]{0.5, 0.5});

		filterNull.expect(IllegalArgumentException.class);
		filterNull.expectMessage("Cannot filter a null value");

		testFilter.filter(null);
	}

	@Rule
    public ExpectedException resetNull = ExpectedException.none();
	@Test
	public void testResetNull() {
		ScalarLinearFilter testFilter = ScalarLinearFilter.makeScalarLinearFilter(new Double[]{0.1}, new Double[]{0.5, 0.5});

		resetNull.expect(IllegalArgumentException.class);
		resetNull.expectMessage("Cannot reset to a null value");

		testFilter.reset(null);
	}

	@Rule
    public ExpectedException constructorNull = ExpectedException.none();
	@Test
	public void testConstructorNull() {
		resetNull.expect(IllegalArgumentException.class);
		resetNull.expectMessage("Parameter list cannot be null");

		ScalarLinearFilter testFilter = ScalarLinearFilter.makeScalarLinearFilter(null, null);
	}

	@Test
	public void testFilter() {
		ScalarLinearFilter test = ScalarLinearFilter.makeScalarLinearFilter(new Double[]{0.1}, new Double[]{0.5, 0.5});
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
		ScalarLinearFilter test = ScalarLinearFilter.makeScalarLinearFilter(new Double[]{0.1}, new Double[]{0.5, 0.5});
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
		ScalarLinearFilter test = ScalarLinearFilter.makeScalarLinearFilter(new Double[]{0.1}, new Double[]{0.5, 0.5});
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
		ScalarLinearFilter test = ScalarLinearFilter.makeScalarLinearFilter(new Double[]{-1.0}, new Double[]{0.5});
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