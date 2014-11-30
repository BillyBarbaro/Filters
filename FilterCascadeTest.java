import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.util.ArrayList;

/** Test Bench for Filter Cascade
  * @author Billy Barbaro
  */

public class FilterCascadeTest {

	@Rule
    public ExpectedException filterNull = ExpectedException.none();
	@Test
	public void testNullFilter() {
		ArrayList<Filter<Double>> filters = new ArrayList<Filter<Double>>();
		filters.add(new MaxFilterN(2));
		filters.add(new MinFilterN(3));
		FilterCascade<Double> testFilter = new FilterCascade<Double>(filters);

		filterNull.expect(IllegalArgumentException.class);
		filterNull.expectMessage("Cannot filter a null value");

		testFilter.filter(null);
	}

	@Rule
    public ExpectedException resetNull = ExpectedException.none();
	@Test
	public void testResetNull() {
		ArrayList<Filter<Double>> filters = new ArrayList<Filter<Double>>();
		filters.add(new MaxFilterN<Double>(2));
		filters.add(new MinFilterN<Double>(3));
		FilterCascade<Double> testFilter = new FilterCascade<Double>(filters);

		resetNull.expect(IllegalArgumentException.class);
		resetNull.expectMessage("Cannot reset to a null value");

		testFilter.reset(null);
	}

	@Test
	public void testCascadeFromSpec() {
		ArrayList<Filter<Double>> filters = new ArrayList<Filter<Double>>();
		filters.add(new MaxFilterN<Double>(2));
		filters.add(new MinFilterN<Double>(3));
		FilterCascade<Double> test = new FilterCascade<Double>(filters);
		assertEquals(test.filter(-1.0), -1.0, 0.1);
		assertEquals(test.filter(3.0), -1.0, 0.1);
		assertEquals(test.filter(1.0), -1.0, 0.001);
		assertEquals(test.filter(2.0), 2.0, 0.001);
		assertEquals(test.filter(1.0), 2.0, 0.001);
		test.reset(0.0);
		assertEquals(test.filter(-1.0), 0.0, 0.1);
		assertEquals(test.filter(3.0), 0.0, 0.1);
		assertEquals(test.filter(1.0), 0.0, 0.001);
		assertEquals(test.filter(2.0), 2.0, 0.001);
		assertEquals(test.filter(1.0), 2.0, 0.001);
	}

	@Test
	public void testCascadeWithScalarLinear() {
		ArrayList<Filter<Double>> filters = new ArrayList<Filter<Double>>();
		filters.add(new ScalarLinearFilter(new Double[]{0.1}, new Double[]{0.5, 0.5}));
		filters.add(new GainFilter(2.0));
		FilterCascade<Double> test = new FilterCascade<Double>(filters);
		assertEquals(test.filter(-1.0), -1.0, 0.1);
		assertEquals(test.filter(1.0), 0.1, 0.1);
		assertEquals(test.filter(2.0), 2.9, 0.1);
		test.resetFilterAtIndex(0, 0.0);
		assertEquals(test.filter(-1.0), -1.0, 0.001);
		assertEquals(test.filter(3.0), 2.1, 0.001);
		assertEquals(test.filter(1.0), 3.79, 0.001);
		assertEquals(test.filter(2.0), 2.621, 0.0001);
		assertEquals(test.filter(1.0), 2.7379, 0.00001);
	}

	
	@Test
	public void testGenerics() {
		ArrayList<Filter<String>> filters = new ArrayList<Filter<String>>();
		filters.add(new MaxFilterN<String>(2));
		filters.add(new MinFilterN<String>(3));
		FilterCascade<String> test = new FilterCascade<String>(filters);
		assertEquals(test.filter("Gorilla"), "Gorilla");
		assertEquals(test.filter("Cat"), "Gorilla");
		assertEquals(test.filter("Hamster"), "Gorilla");
		assertEquals(test.filter("Mouse"), "Gorilla");
		assertEquals(test.filter("Lizard"), "Hamster");
		test.reset("Frog");
		assertEquals(test.filter("Dog"), "Frog");
		assertEquals(test.filter("Lion"), "Frog");
		assertEquals(test.filter("Buffalo"), "Frog");
		assertEquals(test.filter("Monkey"), "Lion");
		assertEquals(test.filter("Sheep"), "Lion");
	}
}