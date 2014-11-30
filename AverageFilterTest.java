import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/** Test Bench for Average Filter
  * @author Billy Barbaro
  */

public class AverageFilterTest {

	@Rule
    public ExpectedException filterNull = ExpectedException.none();
	@Test
	public void testNullFilter() {
		AverageFilter testFilter = new AverageFilter();

		filterNull.expect(IllegalArgumentException.class);
		filterNull.expectMessage("Cannot filter a null value");

		testFilter.filter(null);
	}

	@Rule
    public ExpectedException resetNull = ExpectedException.none();
	@Test
	public void testResetNull() {
		AverageFilter testFilter = new AverageFilter();

		resetNull.expect(IllegalArgumentException.class);
		resetNull.expectMessage("Cannot reset to a null value");

		testFilter.reset(null);
	}

	@Test
	public void testFilter() {
		AverageFilter testFilter = new AverageFilter();
		assertEquals("Average filter fails for 0.0", testFilter.filter(0.0), 0.0, 0.1);
		assertEquals("Average filter fails for 1.0", testFilter.filter(1.0), 0.5, 0.1);
		assertEquals("Average filter fails for -1.0", testFilter.filter(-1.0), 0.0, 0.1);
		assertEquals("Average filter fails for 0.5", testFilter.filter(0.5), 0.125, 0.1);
		assertEquals("Average filter fails for -0.5", testFilter.filter(-1.5), -0.2, 0.1);
		assertEquals("Average filter fails for 27.2", testFilter.filter(27.2), 4.367, 0.01);
		assertEquals("Average filter fails for -27.2", testFilter.filter(-27.2), -0.14286, 0.001);
	}

	@Test
	public void testReset() {
		AverageFilter testFilter = new AverageFilter();
		testFilter.reset(0.0);
		assertEquals("Average filter fails for reset of 0.0", testFilter.filter(1.0), 1.0, 0.1);
		assertEquals("Average filter fails for reset of 0.0", testFilter.filter(-1.0), 0.0, 0.1);
		testFilter.reset(14.0);
		assertEquals("Average filter fails for reset of 14.0", testFilter.filter(1.0), 1.0, 0.1);
		assertEquals("Average filter fails for 14.0", testFilter.filter(-1.0), 0.0, 0.1);
		testFilter.reset(-14.0);
		assertEquals("Average filter fails for reset of -14.0", testFilter.filter(1.0), 1.0, 0.1);
		assertEquals("Average filter fails for -14.0", testFilter.filter(-1.0), 0.0, 0.1);
	}
	
}