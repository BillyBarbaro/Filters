import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MaxFilterNTest {

	@Test
	public void testFilterSize3() {
		MaxFilterN testFilter = new MaxFilterN(3);
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
		MaxFilterN testFilter = new MaxFilterN(1);
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
	public void testFilterSize0() {
		MaxFilterN testFilter = new MaxFilterN(0);
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
	public void testFilterSizeNegative() {
		MaxFilterN testFilter = new MaxFilterN(-27);
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
		MaxFilterN testFilter = new MaxFilterN(3);
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
}