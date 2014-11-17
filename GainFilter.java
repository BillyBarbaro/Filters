import java.util.Arrays;
import java.util.ArrayList;

public class GainFilter extends FIRFilter {

	public GainFilter(Double multilpier) {
		super(new Double[] {multilpier});
	}
}