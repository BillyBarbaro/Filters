import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/** Test Bench for Gain Filter */

public class GainFilterTest {

	@Rule
    public ExpectedException filterNull = ExpectedException.none();
	@Test
	public void testNullFilter() {
		GainFilter testFilter = GainFilter.makeGainFilter(0.5);

		filterNull.expect(IllegalArgumentException.class);
		filterNull.expectMessage("Cannot filter a null value");

		testFilter.filter(null);
	}

	@Rule
    public ExpectedException resetNull = ExpectedException.none();
	@Test
	public void testResetNull() {
		GainFilter testFilter = GainFilter.makeGainFilter(0.5);

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

		GainFilter testFilter = GainFilter.makeGainFilter(null);
	}
	
	@Test
	public void testFilterDecimal() {
		GainFilter test = GainFilter.makeGainFilter(0.5);
		assertEquals(test.filter(-1.0), -0.5, 0.1);
		assertEquals(test.filter(1.0), 0.5, 0.1);
		assertEquals(test.filter(2.0), 1.0, 0.001);
		test.reset(0.0);
		assertEquals(test.filter(-1.0), -0.5, 0.001);
		assertEquals(test.filter(3.0), 1.5, 0.001);
		assertEquals(test.filter(1.0), 0.5, 0.001);
		assertEquals(test.filter(2.0), 1.0, 0.0001);
		assertEquals(test.filter(1.0), 0.5, 0.00001);
	}

	@Test
	public void testFilterOne() {
		GainFilter testFilter = GainFilter.makeGainFilter(1.0);
		assertEquals("Gain filter fails for 0.0", testFilter.filter(0.0), 0.0, 0.1);
		assertEquals("Gain filter fails for 1.0", testFilter.filter(1.0), 1.0, 0.1);
		assertEquals("Gain filter fails for -1.0", testFilter.filter(-1.0), -1.0, 0.1);
		assertEquals("Gain filter fails for 0.5", testFilter.filter(0.5), 0.5, 0.1);
		assertEquals("Gain filter fails for -0.5", testFilter.filter(-0.5), -0.5, 0.1);
		assertEquals("Gain filter fails for 27.2", testFilter.filter(27.2), 27.2, 0.1);
		assertEquals("Gain filter fails for -27.2", testFilter.filter(-27.2), -27.2, 0.1);
		assertEquals("Gain filter fails for 123847261.1124", testFilter.filter(123847261.1124), 123847261.1124, 0.1);
		assertEquals("Gain filter fails for -22238492.4423", testFilter.filter(-22238492.4423), -22238492.4423, 0.1);
	}

	@Test
	public void testFilterNegative() {
		GainFilter test = GainFilter.makeGainFilter(-1.0);
		assertEquals(test.filter(-1.0), 1.0, 0.1);
		assertEquals(test.filter(1.0), -1.0, 0.1);
		assertEquals(test.filter(2.0), -2.0, 0.001);
		test.reset(22.0);
		assertEquals(test.filter(-1.0), 1.0, 0.001);
		assertEquals(test.filter(3.0), -3.0, 0.001);
		assertEquals(test.filter(1.0), -1.0, 0.001);
		assertEquals(test.filter(2.0), -2.0, 0.0001);
		assertEquals(test.filter(1.0), -1.0, 0.00001);
	}

	@Test
	public void testFilterDouble() {
		GainFilter test = GainFilter.makeGainFilter(2.0);
		assertEquals(test.filter(-1.0), -2.0, 0.1);
		assertEquals(test.filter(1.0), 2.0, 0.1);
		assertEquals(test.filter(2.0), 4.0, 0.001);
		test.reset(-4567.222);
		assertEquals(test.filter(-1.0), -2.0, 0.001);
		assertEquals(test.filter(3.0), 6.0, 0.001);
		assertEquals(test.filter(1.0), 2.0, 0.001);
		assertEquals(test.filter(2.0), 4.0, 0.0001);
		assertEquals(test.filter(1.0), 2.0, 0.00001);
	}	
}