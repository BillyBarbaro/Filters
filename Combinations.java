/** A class providing a mehtod to calculate Combinations
  * @author Billy Barbaro
  */

public class Combinations {
	
	/** Calculates a combination of N, k
	  * @param N 	the things to choose from
	  * @param k 	the number of things to be chosen
	  * @return	Double	value of nChoosek
	  */
	public static Double nChoosek(int N, int k) {
		// Can't choose negative numbers
		if (k < 0)
			return 0.0;

		// Must be choosing less than we have
		int numberNeeded = N - k;
		if (numberNeeded < 0)
			return 0.0;

		// Calculates the factorials for the numerator
		int numerator = 1;
		for (int i = 0; i  < k; i++) {
			numerator *= N - i;
		}

		// Calculates the factorials for the denominator
		int denominator = 1;
		for (int i = 1; i <= k; i++)
			denominator *= i;

		return new Double((numerator/denominator));
	}
}