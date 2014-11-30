import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/** Test Bench for Identity Filter
  * @author Billy Barbaro
  */

public class IdentityFilterTest {

	@Rule
    public ExpectedException filterNull = ExpectedException.none();
	@Test
	public void testNullFilter() {
		IdentityFilter<Double> testFilter = new IdentityFilter<Double>();

		filterNull.expect(IllegalArgumentException.class);
		filterNull.expectMessage("Cannot filter a null value");

		testFilter.filter(null);
	}

	@Rule
    public ExpectedException resetNull = ExpectedException.none();
	@Test
	public void testResetNull() {
		IdentityFilter<Double> testFilter = new IdentityFilter<Double>();

		resetNull.expect(IllegalArgumentException.class);
		resetNull.expectMessage("Cannot reset to a null value");

		testFilter.reset(null);
	}
	
	@Test
	public void testFilter() {
		IdentityFilter<Double> testFilter = new IdentityFilter<Double>();
		assertEquals("Identity filter fails for 0.0", testFilter.filter(0.0), 0.0, 0.1);
		assertEquals("Identity filter fails for 1.0", testFilter.filter(1.0), 1.0, 0.1);
		assertEquals("Identity filter fails for -1.0", testFilter.filter(-1.0), -1.0, 0.1);
		assertEquals("Identity filter fails for 0.5", testFilter.filter(0.5), 0.5, 0.1);
		assertEquals("Identity filter fails for -0.5", testFilter.filter(-0.5), -0.5, 0.1);
		assertEquals("Identity filter fails for 27.2", testFilter.filter(27.2), 27.2, 0.1);
		assertEquals("Identity filter fails for -27.2", testFilter.filter(-27.2), -27.2, 0.1);
		assertEquals("Identity filter fails for 123847261.1124", testFilter.filter(123847261.1124), 123847261.1124, 0.1);
		assertEquals("Identity filter fails for -22238492.4423", testFilter.filter(-22238492.4423), -22238492.4423, 0.1);
	}

	@Test
	public void testReset() {
		IdentityFilter<Double> testFilter = new IdentityFilter<Double>();
		testFilter.reset(0.0);
		testFilter.reset(0.0);
		testFilter.reset(1.0);
		testFilter.reset(-1.0);
		testFilter.reset(0.5);
		testFilter.reset(-0.5);
		testFilter.reset(27.2);
		testFilter.reset(-27.2);
		testFilter.reset(123847261.1124);
		testFilter.reset(-22238492.4423);
	}

	@Test
	public void testGeneric() {
		IdentityFilter<String> testFilter = new IdentityFilter<String>();
		assertEquals("Identity filter fails for boy", testFilter.filter("boy"), "boy");
		assertEquals("Identity filter fails for girl", testFilter.filter("girl"), "girl");
		assertEquals("Identity filter fails for ", testFilter.filter(""), "");
		testFilter.reset("Cat");
	}

}