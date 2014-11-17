import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BinomialFilterTest {
	
	@Test
	public void testFilter() {
		BinomialFilter test = new BinomialFilter(4);
		assertEquals(test.filter(-1.0), -1.0, 0.1);
		assertEquals(test.filter(1.0), 3.0, 0.1);
		assertEquals(test.filter(2.0), 15.0, 0.001);
		test.reset(0.0);
		assertEquals(test.filter(-1.0), -4.0, 0.001);
		assertEquals(test.filter(3.0), -1.0, 0.001);
		assertEquals(test.filter(1.0), 3.0, 0.001);
		assertEquals(test.filter(2.0), 15.0, 0.0001);
		assertEquals(test.filter(1.0), 23.0, 0.00001);
	}

	@Test
	public void testFilterOne() {
		BinomialFilter testFilter = new BinomialFilter(1);
		assertEquals("Binomial filter fails for 0.0", testFilter.filter(0.0), 0.0, 0.1);
		assertEquals("Binomial filter fails for 1.0", testFilter.filter(1.0), 1.0, 0.1);
		assertEquals("Binomial filter fails for -1.0", testFilter.filter(-1.0), -1.0, 0.1);
		assertEquals("Binomial filter fails for 0.5", testFilter.filter(0.5), 0.5, 0.1);
		assertEquals("Binomial filter fails for -0.5", testFilter.filter(-0.5), -0.5, 0.1);
		assertEquals("Binomial filter fails for 27.2", testFilter.filter(27.2), 27.2, 0.1);
		assertEquals("Binomial filter fails for -27.2", testFilter.filter(-27.2), -27.2, 0.1);
		assertEquals("Binomial filter fails for 123847261.1124", testFilter.filter(123847261.1124), 123847261.1124, 0.1);
		assertEquals("Binomial filter fails for -22238492.4423", testFilter.filter(-22238492.4423), -22238492.4423, 0.1);
	}

	@Test
	public void testFilterZero() {
		BinomialFilter test = new BinomialFilter(0);
		assertEquals(test.filter(-1.0), 0.0, 0.1);
		assertEquals(test.filter(1.0), 0.0, 0.1);
		assertEquals(test.filter(2.0), 0.0, 0.001);
		test.reset(0.0);
		assertEquals(test.filter(-1.0), 0.0, 0.001);
		assertEquals(test.filter(3.0), 0.0, 0.001);
		assertEquals(test.filter(1.0), 0.0, 0.001);
		assertEquals(test.filter(2.0), 0.0, 0.0001);
		assertEquals(test.filter(1.0), 0.0, 0.00001);
	}

	@Test
	public void testFilterNegative() {
		BinomialFilter test = new BinomialFilter(-4);
		assertEquals(test.filter(-1.0), -1.0, 0.1);
		assertEquals(test.filter(1.0), 3.0, 0.1);
		assertEquals(test.filter(2.0), 15.0, 0.001);
		test.reset(0.0);
		assertEquals(test.filter(-1.0), -4.0, 0.001);
		assertEquals(test.filter(3.0), -1.0, 0.001);
		assertEquals(test.filter(1.0), 3.0, 0.001);
		assertEquals(test.filter(2.0), 15.0, 0.0001);
		assertEquals(test.filter(1.0), 23.0, 0.00001);
	}
}