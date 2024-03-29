import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/** Test Bench for Max Filter N */

public class MaxFilterNTest {

	@Rule
    public ExpectedException filterNull = ExpectedException.none();
	@Test
	public void testNullFilter() {
		MaxFilterN<Double> testFilter = MaxFilterN.maxFilterWithN(3);

		filterNull.expect(IllegalArgumentException.class);
		filterNull.expectMessage("Cannot filter a null value");

		testFilter.filter(null);
	}

	@Rule
    public ExpectedException resetNull = ExpectedException.none();
	@Test
	public void testResetNull() {
		MaxFilterN<Double> testFilter = MaxFilterN.maxFilterWithN(3);

		resetNull.expect(IllegalArgumentException.class);
		resetNull.expectMessage("Cannot reset to a null value");

		testFilter.reset(null);
	}

	@Rule
	public ExpectedException negativeN = ExpectedException.none();
	@Test
	public void testFilterSizeNegative() {
		negativeN.expect(IllegalArgumentException.class);
		negativeN.expectMessage("A filter must have a size of at least 1");
		MaxFilterN<Double> testFilter = MaxFilterN.maxFilterWithN(-27);
	}

	@Test
	public void testFilterSize3() {
		MaxFilterN<Double> testFilter = MaxFilterN.maxFilterWithN(3);
		assertEquals("Max N filter fails for 0.0", testFilter.filter(0.0), 0.0, 0.1);
		assertEquals("Max N filter fails for 1.0", testFilter.filter(1.0), 1.0, 0.1);
		assertEquals("Max N filter fails for -1.0", testFilter.filter(-1.0), 1.0, 0.1);
		assertEquals("Max N filter fails for 0.5", testFilter.filter(0.5), 1.0, 0.1);
		assertEquals("Max N filter fails for -0.5", testFilter.filter(-0.5), 0.5, 0.1);
		assertEquals("Max N filter fails for 27.2", testFilter.filter(27.2), 27.2, 0.1);
		assertEquals("Max N filter fails for -27.2", testFilter.filter(-27.2), 27.2, 0.1);
		assertEquals("Max N filter fails for -22238492.4423", testFilter.filter(-22238492.4423), 27.2, 0.1);
		assertEquals("Max N filter fails for -22238492.4423", testFilter.filter(-22238492.4423), -27.2, 0.1);
		assertEquals("Max N filter fails for 123847261.1124", testFilter.filter(123847261.1124), 123847261.1124, 0.1);
	}

	@Test
	public void testFilterSize1() {
		MaxFilterN<Double> testFilter = MaxFilterN.maxFilterWithN(1);
		assertEquals("Max N filter fails for 0.0", testFilter.filter(0.0), 0.0, 0.1);
		assertEquals("Max N filter fails for 1.0", testFilter.filter(1.0), 1.0, 0.1);
		assertEquals("Max N filter fails for -1.0", testFilter.filter(-1.0), -1.0, 0.1);
		assertEquals("Max N filter fails for 0.5", testFilter.filter(0.5), 0.5, 0.1);
		assertEquals("Max N filter fails for -0.5", testFilter.filter(-0.5), -0.5, 0.1);
		assertEquals("Max N filter fails for 27.2", testFilter.filter(27.2), 27.2, 0.1);
		assertEquals("Max N filter fails for -27.2", testFilter.filter(-27.2), -27.2, 0.1);
		assertEquals("Max N filter fails for -22238492.4423", testFilter.filter(-22238492.4423), -22238492.4423, 0.1);
		assertEquals("Max N filter fails for 123847261.1124", testFilter.filter(123847261.1124), 123847261.1124, 0.1);
	}

	@Test
	public void testReset() {
		MaxFilterN<Double> testFilter = MaxFilterN.maxFilterWithN(3);
		assertEquals("Max N filter fails for 0.0", testFilter.filter(0.0), 0.0, 0.1);
		assertEquals("Max N filter fails for 1.0", testFilter.filter(1.0), 1.0, 0.1);
		testFilter.reset(0.0);
		assertEquals("Max N filter fails for -1.0", testFilter.filter(-1.0), 0.0, 0.1);
		assertEquals("Max N filter fails for 0.5", testFilter.filter(0.5), 0.5, 0.1);
		assertEquals("Max N filter fails for -0.5", testFilter.filter(-0.5), 0.5, 0.1);
		assertEquals("Max N filter fails for 27.2", testFilter.filter(27.2), 27.2, 0.1);
		testFilter.reset(33.0);
		assertEquals("Max N filter fails for -27.2", testFilter.filter(-27.2), 33.0, 0.1);
		testFilter.reset(-32238492.4423);
		assertEquals("Max N filter fails for -22238492.4423", testFilter.filter(-22238492.4423), -22238492.4423, 0.1);
		assertEquals("Max N filter fails for 123847261.1124", testFilter.filter(123847261.1124), 123847261.1124, 0.1);
	}

	@Test
	public void testGenerics() {
		MaxFilterN<String> testFilter = MaxFilterN.maxFilterWithN(2);
		assertEquals("Max N filter fails for Bat", testFilter.filter("Bat"), "Bat");
		assertEquals("Min N filter fails for Cat", testFilter.filter("Cat"), "Cat");
		assertEquals("Min N filter fails for Apple", testFilter.filter("Apple"), "Cat");
		assertEquals("Min N filter fails for Albatross", testFilter.filter("Albatross"), "Apple");
		assertEquals("Min N filter fails for Ape", testFilter.filter("Ape"), "Ape");
		assertEquals("Min N filter fails for Zebra", testFilter.filter("Zebra"), "Zebra");
		testFilter.reset("Mongoose");
		assertEquals("Min N filter fails for Frog", testFilter.filter("Frog"), "Mongoose");
	}
}