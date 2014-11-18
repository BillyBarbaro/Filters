import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/** Test Bench for Average Filter N
  * @author Billy Barbaro
  */

public class AverageFilterNTest {

	@Test
	public void testFilterSize3() {
		AverageFilterN testFilter = new AverageFilterN(3);
		assertEquals("Average N filter fails for 0.0", testFilter.filter(0.0), 0.0, 0.1);
		assertEquals("Average N filter fails for 1.0", testFilter.filter(1.0), 0.5, 0.1);
		assertEquals("Average N filter fails for -1.0", testFilter.filter(-1.0), 0.0, 0.1);
		assertEquals("Average N filter fails for 0.5", testFilter.filter(0.5), 0.166667, 0.001);
		assertEquals("Average N filter fails for -0.5", testFilter.filter(-0.5), -0.33333, 0.001);
		assertEquals("Average N filter fails for 27.2", testFilter.filter(27.2), 9.06667, 0.001);
		assertEquals("Average N filter fails for -27.2", testFilter.filter(-27.2), -0.166667, 0.001);
	}

	@Test
	public void testFilterSize1() {
		AverageFilterN testFilter = new AverageFilterN(1);
		assertEquals("Average N filter fails for 0.0", testFilter.filter(0.0), 0.0, 0.1);
		assertEquals("Average N filter fails for 1.0", testFilter.filter(1.0), 1.0, 0.1);
		assertEquals("Average N filter fails for -1.0", testFilter.filter(-1.0), -1.0, 0.1);
		assertEquals("Average N filter fails for 0.5", testFilter.filter(0.5), 0.5, 0.1);
		assertEquals("Average N filter fails for -0.5", testFilter.filter(-0.5), -0.5, 0.1);
		assertEquals("Average N filter fails for 27.2", testFilter.filter(27.2), 27.2, 0.1);
		assertEquals("Average N filter fails for -27.2", testFilter.filter(-27.2), -27.2, 0.1);
	}

	@Test
	public void testFilterSize0() {
		AverageFilterN testFilter = new AverageFilterN(0);
		assertEquals("Average N filter fails for 0.0", testFilter.filter(0.0), 0.0, 0.1);
		assertEquals("Average N filter fails for 1.0", testFilter.filter(1.0), 1.0, 0.1);
		assertEquals("Average N filter fails for -1.0", testFilter.filter(-1.0), -1.0, 0.1);
		assertEquals("Average N filter fails for 0.5", testFilter.filter(0.5), 0.5, 0.1);
		assertEquals("Average N filter fails for -0.5", testFilter.filter(-0.5), -0.5, 0.1);
		assertEquals("Average N filter fails for 27.2", testFilter.filter(27.2), 27.2, 0.1);
		assertEquals("Average N filter fails for -27.2", testFilter.filter(-27.2), -27.2, 0.1);
	}

	@Test
	public void testFilterSizeNegative() {
		AverageFilterN testFilter = new AverageFilterN(-27);
		assertEquals("Average N filter fails for 0.0", testFilter.filter(0.0), 0.0, 0.1);
		assertEquals("Average N filter fails for 1.0", testFilter.filter(1.0), 1.0, 0.1);
		assertEquals("Average N filter fails for -1.0", testFilter.filter(-1.0), -1.0, 0.1);
		assertEquals("Average N filter fails for 0.5", testFilter.filter(0.5), 0.5, 0.1);
		assertEquals("Average N filter fails for -0.5", testFilter.filter(-0.5), -0.5, 0.1);
		assertEquals("Average N filter fails for 27.2", testFilter.filter(27.2), 27.2, 0.1);
		assertEquals("Average N filter fails for -27.2", testFilter.filter(-27.2), -27.2, 0.1);
	}

	@Test
	public void testReset() {
		AverageFilterN testFilter = new AverageFilterN(3);
		assertEquals("Min N filter fails for 0.0", testFilter.filter(0.0), 0.0, 0.1);
		assertEquals("Min N filter fails for 1.0", testFilter.filter(1.0), 0.5, 0.1);
		testFilter.reset(0.0);
		assertEquals("Min N filter fails for -1.0", testFilter.filter(-1.0), -0.5, 0.1);
		assertEquals("Min N filter fails for 0.5", testFilter.filter(0.5), -0.166667, 0.001);
		assertEquals("Min N filter fails for -0.5", testFilter.filter(-0.5), -0.333333, 0.001);
		assertEquals("Min N filter fails for 27.2", testFilter.filter(27.2), 9.0666667, 0.1);
		testFilter.reset(33.0);
		assertEquals("Min N filter fails for -27.2", testFilter.filter(38.229), 35.6145, 0.00001);
	}
}