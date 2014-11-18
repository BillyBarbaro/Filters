import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/** Test Bench for Max Filter
  * @author Billy Barbaro
  */

public class MaxFilterTest {

	@Test
	public void testFilter() {
		MaxFilter testFilter = new MaxFilter();
		assertEquals("Max filter fails for 0.0", testFilter.filter(0.0), 0.0, 0.1);
		assertEquals("Max filter fails for 1.0", testFilter.filter(1.0), 1.0, 0.1);
		assertEquals("Max filter fails for 1.0 repeated", testFilter.filter(1.0), 1.0, 0.1);
		assertEquals("Max filter fails for -1.0", testFilter.filter(-1.0), 1.0, 0.1);
		assertEquals("Max filter fails for -1.0 repeated", testFilter.filter(-1.0), 1.0, 0.1);
		assertEquals("Max filter fails for 0.5", testFilter.filter(0.5), 1.0, 0.1);
		assertEquals("Max filter fails for -0.5", testFilter.filter(-0.5), 1.0, 0.1);
		assertEquals("Max filter fails for 27.2", testFilter.filter(27.2), 27.2, 0.1);
		assertEquals("Max filter fails for -27.2", testFilter.filter(-27.2), 27.2, 0.1);
		assertEquals("Max filter fails for 123847261.1124", testFilter.filter(123847261.1124), 123847261.1124, 0.1);
		assertEquals("Max filter fails for -22238492.4423", testFilter.filter(-22238492.4423), 123847261.1124, 0.1);
	}

	@Test
	public void testReset() {
		MaxFilter testFilter = new MaxFilter();
		testFilter.reset(0.0);
		assertEquals("Max filter fails for reset of 0.0", testFilter.filter(-1.0), 0.0, 0.1);
		assertEquals("Max filter fails for 0.0", testFilter.filter(1.0), 1.0, 0.1);
		testFilter.reset(-14.0);
		assertEquals("Max filter fails for -14.0", testFilter.filter(0.0), 0.0, 0.1);
		testFilter.reset(14.0);
		assertEquals("Max filter fails for 14.0", testFilter.filter(0.0), 14.0, 0.1);
		assertEquals("Max filter fails for 14.0", testFilter.filter(14.1), 14.1, 0.05);
	}
}