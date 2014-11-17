public class Combinations {
	
	public static Double nChoosek(int N, int k) {
		if (k < 0)
			return 0.0;

		int numberNeeded = N - k;
		if (numberNeeded < 0)
			return 0.0;

		int numerator = 1;
		for (int i = 0; i  < k; i++) {
			numerator *= N - i;
		}

		int denominator = 1;
		for (int i = 1; i <= k; i++)
			denominator *= i;

		return new Double((numerator/denominator));
	}
}