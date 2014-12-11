import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/** Test Bench for FIR Filter */

public class FIRFilterTest {

	@Rule
    public ExpectedException filterNull = ExpectedException.none();
	@Test
	public void testNullFilter() {
		FIRFilter testFilter = FIRFilter.makeFIRFilter(new Double[]{0.5, 0.5});

		filterNull.expect(IllegalArgumentException.class);
		filterNull.expectMessage("Cannot filter a null value");

		testFilter.filter(null);
	}

	@Rule
    public ExpectedException resetNull = ExpectedException.none();
	@Test
	public void testResetNull() {
		FIRFilter testFilter = FIRFilter.makeFIRFilter(new Double[]{0.5, 0.5});

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

		FIRFilter testFilter = FIRFilter.makeFIRFilter(null);
	}

	@Test
	public void testFilter() {
		FIRFilter test = FIRFilter.makeFIRFilter(new Double[]{0.5, 0.5});
		assertEquals(test.filter(-1.0), -0.5, 0.1);
		assertEquals(test.filter(1.0), 0.05, 0.1);
		assertEquals(test.filter(2.0), 1.5, 0.001);
		test.reset(0.0);
		assertEquals(test.filter(-1.0), -0.5, 0.001);
		assertEquals(test.filter(3.0), 1.0, 0.001);
		assertEquals(test.filter(1.0), 2.0, 0.001);
		assertEquals(test.filter(2.0), 1.5, 0.0001);
		assertEquals(test.filter(1.0), 1.5, 0.00001);
	}

	@Test
	public void testFilterNegativeReset() {
		FIRFilter test = FIRFilter.makeFIRFilter(new Double[]{0.5, 0.5});
		assertEquals(test.filter(-1.0), -0.5, 0.1);
		assertEquals(test.filter(1.0), 0.05, 0.1);
		assertEquals(test.filter(2.0), 1.5, 0.001);
		test.reset(-0.3);
		assertEquals(test.filter(-1.0), -0.65, 0.001);
		assertEquals(test.filter(3.0), 1.0, 0.001);
		assertEquals(test.filter(1.0), 2.0, 0.001);
		assertEquals(test.filter(2.0), 1.5, 0.0001);
		assertEquals(test.filter(1.0), 1.5, 0.00001);
	}

	@Test
	public void testFilterPositiveRest() {
		FIRFilter test = FIRFilter.makeFIRFilter(new Double[]{0.5, -0.5});
		assertEquals(test.filter(-1.0), -0.5, 0.1);
		assertEquals(test.filter(1.0), -1.0, 0.1);
		assertEquals(test.filter(2.0), 0.5, 0.001);
		test.reset(0.7);
		assertEquals(test.filter(-1.0), 0.85, 0.001);
		assertEquals(test.filter(3.0), 2.0, 0.001);
		assertEquals(test.filter(1.0), 1.0, 0.001);
		assertEquals(test.filter(2.0), 0.5, 0.0001);
		assertEquals(test.filter(1.0), 0.5, 0.00001);
	}
}