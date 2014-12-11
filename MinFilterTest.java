import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/** Test Bench for Min Filter */

public class MinFilterTest {

	@Rule
    public ExpectedException filterNull = ExpectedException.none();
	@Test
	public void testNullFilter() {
		MinFilter<Double> testFilter = new MinFilter<Double>();

		filterNull.expect(IllegalArgumentException.class);
		filterNull.expectMessage("Cannot filter a null value");

		testFilter.filter(null);
	}

	@Rule
    public ExpectedException resetNull = ExpectedException.none();
	@Test
	public void testResetNull() {
		MinFilter<Double> testFilter = new MinFilter<Double>();

		resetNull.expect(IllegalArgumentException.class);
		resetNull.expectMessage("Cannot reset to a null value");

		testFilter.reset(null);
	}

	@Test
	public void testFilter() {
		MinFilter<Double> testFilter = new MinFilter<Double>();
		assertEquals("Min filter fails for 0.0", testFilter.filter(0.0), 0.0, 0.1);
		assertEquals("Min filter fails for 1.0", testFilter.filter(1.0), 0.0, 0.1);
		assertEquals("Min filter fails for 1.0 repeated", testFilter.filter(1.0), 0.0, 0.1);
		assertEquals("Min filter fails for -1.0", testFilter.filter(-1.0), -1.0, 0.1);
		assertEquals("Min filter fails for -1.0 repeated", testFilter.filter(-1.0), -1.0, 0.1);
		assertEquals("Min filter fails for 0.5", testFilter.filter(0.5), -1.0, 0.1);
		assertEquals("Min filter fails for -0.5", testFilter.filter(-1.5), -1.5, 0.1);
		assertEquals("Min filter fails for 27.2", testFilter.filter(27.2), -1.5, 0.1);
		assertEquals("Min filter fails for -27.2", testFilter.filter(-27.2), -27.2, 0.1);
		assertEquals("Min filter fails for 123847261.1124", testFilter.filter(123847261.1124), -27.2, 0.1);
		assertEquals("Min filter fails for -22238492.4423", testFilter.filter(-22238492.4423), -22238492.4423, 0.1);
	}

	@Test
	public void testReset() {
		MinFilter<Double> testFilter = new MinFilter<Double>();
		testFilter.reset(0.0);
		assertEquals("Min filter fails for reset of 0.0", testFilter.filter(1.0), 0.0, 0.1);
		assertEquals("Min filter fails for 0.0", testFilter.filter(-1.0), -1.0, 0.1);
		testFilter.reset(14.0);
		assertEquals("Min filter fails for -14.0", testFilter.filter(0.0), 0.0, 0.1);
		testFilter.reset(-14.0);
		assertEquals("Min filter fails for 14.0", testFilter.filter(0.0), -14.0, 0.1);
		assertEquals("Min filter fails for 14.0", testFilter.filter(-14.1), -14.1, 0.05);
	}

	@Test
	public void testGeneric() {
		MinFilter<String> testFilter = new MinFilter<String>();
		assertEquals("Min filter fails for boy", testFilter.filter("boy"), "boy");
		assertEquals("Min filter fails for girl", testFilter.filter("girl"), "boy");
		assertEquals("Min filter fails for ", testFilter.filter(""), "");
		testFilter.reset("Cat");
		assertEquals("Min filter fails for ", testFilter.filter("Dog"), "Cat");
	}
}