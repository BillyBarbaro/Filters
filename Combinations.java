/** A class providing a mehtod to calculate Combinations
  * @author Billy Barbaro
  */

public class Combinations {
	
	/** Creates a table of N choose K values
	  * @param N 	the Max things to choose from
	  * @param k 	the Max number of things to be chosen
	  * @return	int[][]	table of combinations
	  */
	public static int[][] nChoosek(int N, int K) {
		int binomial[][] = new int[N+1][K+1];

		// If choosing from N=0, always 0 options
		for (int k = 1; k <= K; k++)
			binomial[0][k] = 0;

		// If choosing k=0 items, always 0
		for (int n = 0; n <= N; n++)
			binomial[n][0] = 1;

		// Loops through creating a table by a definition of combinations
		for (int n = 1; n <= N; n++)
		   for (int k = 1; k <= K; k++)
		      binomial[n][k] = binomial[n-1][k-1] + binomial[n-1][k];

		return binomial;
	}
}