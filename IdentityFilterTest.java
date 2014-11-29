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
		IdentityFilter testFilter = new IdentityFilter();

		filterNull.expect(IllegalArgumentException.class);
		filterNull.expectMessage("Cannot filter a null value");

		testFilter.filter(null);
	}
	
	@Test
	public void testFilter() {
		IdentityFilter testFilter = new IdentityFilter();
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
		IdentityFilter testFilter = new IdentityFilter();
		testFilter.reset(0.0);
		testFilter.reset(null);
		testFilter.reset(1.0);
		testFilter.reset(-1.0);
		testFilter.reset(0.5);
		testFilter.reset(-0.5);
		testFilter.reset(27.2);
		testFilter.reset(-27.2);
		testFilter.reset(123847261.1124);
		testFilter.reset(-22238492.4423);
	}

}