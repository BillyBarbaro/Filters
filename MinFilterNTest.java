import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/** Test Bench for Min Filter N
  * @author Billy Barbaro
  */

public class MinFilterNTest {

	@Rule
    public ExpectedException filterNull = ExpectedException.none();
	@Test
	public void testNullFilter() {
		MinFilterN<Double> testFilter = new MinFilterN<Double>(3);

		filterNull.expect(IllegalArgumentException.class);
		filterNull.expectMessage("Cannot filter a null value");

		testFilter.filter(null);
	}

	@Rule
    public ExpectedException resetNull = ExpectedException.none();
	@Test
	public void testResetNull() {
		MinFilterN<Double> testFilter = new MinFilterN<Double>(3);

		resetNull.expect(IllegalArgumentException.class);
		resetNull.expectMessage("Cannot reset to a null value");

		testFilter.reset(null);
	}

	@Test
	public void testFilterSize3() {
		MinFilterN<Double> testFilter = new MinFilterN<Double>(3);
		assertEquals("Min N filter fails for 0.0", testFilter.filter(0.0), 0.0, 0.1);
		assertEquals("Min N filter fails for 1.0", testFilter.filter(1.0), 0.0, 0.1);
		assertEquals("Min N filter fails for -1.0", testFilter.filter(-1.0), -1.0, 0.1);
		assertEquals("Min N filter fails for 0.5", testFilter.filter(0.5), -1.0, 0.1);
		assertEquals("Min N filter fails for -0.5", testFilter.filter(-0.5), -1.0, 0.1);
		assertEquals("Min N filter fails for 27.2", testFilter.filter(27.2), -0.5, 0.1);
		assertEquals("Min N filter fails for -27.2", testFilter.filter(-27.2), -27.2, 0.1);
		assertEquals("Min N filter fails for -22238492.4423", testFilter.filter(-22238492.4423), -22238492.4423, 0.1);
		assertEquals("Min N filter fails for 123847261.1124", testFilter.filter(123847261.1124), -22238492.4423, 0.1);
	}

	@Test
	public void testFilterSize1() {
		MinFilterN<Double> testFilter = new MinFilterN<Double>(1);
		assertEquals("Min N filter fails for 0.0", testFilter.filter(0.0), 0.0, 0.1);
		assertEquals("Min N filter fails for 1.0", testFilter.filter(1.0), 1.0, 0.1);
		assertEquals("Min N filter fails for -1.0", testFilter.filter(-1.0), -1.0, 0.1);
		assertEquals("Min N filter fails for 0.5", testFilter.filter(0.5), 0.5, 0.1);
		assertEquals("Min N filter fails for -0.5", testFilter.filter(-0.5), -0.5, 0.1);
		assertEquals("Min N filter fails for 27.2", testFilter.filter(27.2), 27.2, 0.1);
		assertEquals("Min N filter fails for -27.2", testFilter.filter(-27.2), -27.2, 0.1);
		assertEquals("Min N filter fails for -22238492.4423", testFilter.filter(-22238492.4423), -22238492.4423, 0.1);
		assertEquals("Min N filter fails for 123847261.1124", testFilter.filter(123847261.1124), 123847261.1124, 0.1);
	}

	@Test
	public void testFilterSize0() {
		MinFilterN<Double> testFilter = new MinFilterN<Double>(0);
		assertEquals("Max N filter fails for 0.0", testFilter.filter(0.0), 0.0, 0.1);
		assertEquals("Min N filter fails for 1.0", testFilter.filter(1.0), 1.0, 0.1);
		assertEquals("Min N filter fails for -1.0", testFilter.filter(-1.0), -1.0, 0.1);
		assertEquals("Min N filter fails for 0.5", testFilter.filter(0.5), 0.5, 0.1);
		assertEquals("Min N filter fails for -0.5", testFilter.filter(-0.5), -0.5, 0.1);
		assertEquals("Min N filter fails for 27.2", testFilter.filter(27.2), 27.2, 0.1);
		assertEquals("Min N filter fails for -27.2", testFilter.filter(-27.2), -27.2, 0.1);
		assertEquals("Min N filter fails for -22238492.4423", testFilter.filter(-22238492.4423), -22238492.4423, 0.1);
		assertEquals("Min N filter fails for 123847261.1124", testFilter.filter(123847261.1124), 123847261.1124, 0.1);
	}

	@Test
	public void testFilterSizeNegative() {
		MinFilterN<Double> testFilter = new MinFilterN<Double>(-27);
		assertEquals("Max N filter fails for 0.0", testFilter.filter(0.0), 0.0, 0.1);
		assertEquals("Min N filter fails for 1.0", testFilter.filter(1.0), 1.0, 0.1);
		assertEquals("Min N filter fails for -1.0", testFilter.filter(-1.0), -1.0, 0.1);
		assertEquals("Min N filter fails for 0.5", testFilter.filter(0.5), 0.5, 0.1);
		assertEquals("Min N filter fails for -0.5", testFilter.filter(-0.5), -0.5, 0.1);
		assertEquals("Min N filter fails for 27.2", testFilter.filter(27.2), 27.2, 0.1);
		assertEquals("Min N filter fails for -27.2", testFilter.filter(-27.2), -27.2, 0.1);
		assertEquals("Min N filter fails for -22238492.4423", testFilter.filter(-22238492.4423), -22238492.4423, 0.1);
		assertEquals("Min N filter fails for 123847261.1124", testFilter.filter(123847261.1124), 123847261.1124, 0.1);
	}

	@Test
	public void testReset() {
		MinFilterN<Double> testFilter = new MinFilterN<Double>(3);
		assertEquals("Min N filter fails for 0.0", testFilter.filter(0.0), 0.0, 0.1);
		assertEquals("Min N filter fails for 1.0", testFilter.filter(1.0), 0.0, 0.1);
		testFilter.reset(0.0);
		assertEquals("Min N filter fails for -1.0", testFilter.filter(-1.0), -1.0, 0.1);
		assertEquals("Min N filter fails for 0.5", testFilter.filter(0.5), -1.0, 0.1);
		assertEquals("Min N filter fails for -0.5", testFilter.filter(-0.5), -1.0, 0.1);
		assertEquals("Min N filter fails for 27.2", testFilter.filter(27.2), -0.5, 0.1);
		testFilter.reset(33.0);
		assertEquals("Min N filter fails for -27.2", testFilter.filter(38.229), 33.0, 0.1);
		testFilter.reset(-22238492.4423);
		assertEquals("Min N filter fails for 123847261.1124", testFilter.filter(123847261.1124), -22238492.4423, 0.1);
		assertEquals("Min N filter fails for -22238492.4423", testFilter.filter(-32238492.4423), -32238492.4423, 0.1);
	}

	@Test
	public void testGenerics() {
		MinFilterN<String> testFilter = new MinFilterN<String>(2);
		assertEquals("Max N filter fails for Bat", testFilter.filter("Bat"), "Bat");
		assertEquals("Min N filter fails for Cat", testFilter.filter("Cat"), "Bat");
		assertEquals("Min N filter fails for Apple", testFilter.filter("Apple"), "Apple");
		assertEquals("Min N filter fails for Albatross", testFilter.filter("Albatross"), "Albatross");
		assertEquals("Min N filter fails for Ape", testFilter.filter("Ape"), "Albatross");
		assertEquals("Min N filter fails for Zebra", testFilter.filter("Zebra"), "Ape");
		testFilter.reset("Mongoose");
		assertEquals("Min N filter fails for Playtpus", testFilter.filter("Playtpus"), "Mongoose");
	}
}