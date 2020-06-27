import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * 27 June 2020 / June LeetCode Challenge
 * 
 * Given a positive integer n, find the least number of perfect 
 * square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 * 
 * Example 1:
 * Input: n = 12
 * Output: 3 
 * Explanation: 12 = 4 + 4 + 4.
 * 
 * Example 2:
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 * 
 * @author razel
 *
 */
public class JuneChallengePerfectSquares {

	/**
	 * Find minimum number of perfect squares that sum
	 * to integer n. Logic inspired by the classic 
	 * least-coins-to-make-change dynamic programming 
	 * problem.
	 * 
	 * Runtime = 53 ms, faster than 41% of Java submits.
	 * Memory usage beats 17.51% of Java submits.
	 * 
	 * When using an ArrayList to store perfect squares:
	 * Runtime = 154 ms, faster than 20.69% of Java submits.
	 * 
	 * @param int n
	 * @return int memo[n]
	 */
	public static int numSquares(int n) {

		// Simple cases.
		if (n < 4) return n;

		// Another simple case: n is a perfect square.
		int rootN = (int) Math.sqrt(n);
		if (rootN * rootN == n) return 1;

		// Compute perfect squares less than n.
		int current = 1;
		int[] squareArray = new int[(int) Math.sqrt(n)];
		for (int i = 0; i < squareArray.length; i++) {
			squareArray[i] = current * current;
			current++;
		}

		// Init memoization structure.
		int[] memo = new int[n + 1];
		Arrays.fill(memo, n); // Max number of perfect squares used = 1 * n.

		// Base case.
		memo[0] = 0;

		// Memoize.
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < squareArray.length; j++) {
				if (squareArray[j] <= i) {
					// Recurrence relation.
					memo[i] = Math.min(memo[i], memo[i - squareArray[j]] + 1);
				}
			}
		}

		// Return global optimum for n.
		return memo[n];
	}

	/**
	 * Find minimum number of perfect squares that sum
	 * to integer n. Relies on helper functions, with logic
	 * inspired by the classic least-coins-to-make-change
	 * dynamic programming problem.
	 * 
	 * Quite slow.
	 * 
	 * @param int n
	 * @return int least number of perfect squares
	 */
	public static int numSquaresWithHelper(int n) {

		// Simple cases.
		if (n == 0 || n == 1) return 1;
		if (n == 2) return 2;

		// Get all perfect squares less than n.
		int current = 1;
		int currentSquare = 1;
		ArrayList<Integer> squareList = new ArrayList<Integer>();
		while ((currentSquare = current * current) <= n) {
			squareList.add(currentSquare);
			current++;
		}

		// Another simple case.
		if (squareList.contains(n)) return 1;

		// Recursive helper function.
		return findNumSquaresMemo(squareList, n);
	}

	/**
	 * A helper function that memoizes.
	 * 
	 * @param List<Integer> squareList
	 * @param int n
	 * @return int memo[n]
	 */
	private static int findNumSquaresMemo(List<Integer> squareList, int n) {

		// Init memoization structure.
		int[] memo = new int[n + 1];
		Arrays.fill(memo, n); // Max number of perfect squares used = 1 * n.

		// Base case.
		memo[0] = 0;

		// Memoize.
		int localOpt = n;  // Max number of perfect squares used = 1 * n.
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < squareList.size(); j++) {
				if (squareList.get(j) <= i) {
					localOpt = memo[i - squareList.get(j)];
					if (localOpt + 1 < memo[i]) memo[i] = localOpt + 1;
				}
			}
		}

		return memo[n];
	}

	/**
	 * A slow recursive helper function that does not memoize.
	 * 
	 * @param List<Integer> squareList
	 * @param int n
	 * @return int globalOpt
	 */
	private static int findNumSquares(List<Integer> squareList, int n) {

		// Base case.
		if (n == 0) return 0;

		// Recurse.
		int localOpt = n;  // Max number of perfect squares used = 1 * n.
		int globalOpt = n; // Max number of perfect squares used = 1 * n.
		int i = 0;
		while (i < squareList.size() && squareList.get(i) <= n) {
			localOpt = findNumSquares(squareList, n - squareList.get(i)) + 1;
			if (localOpt < globalOpt) globalOpt = localOpt;
			i++;
		}

		return globalOpt;
	}

	/**
	 * A stranger's LeetCode submission, runtime = 2ms.
	 * 
	 * @param int n
	 * @return int
	 */
	private boolean isSquare(int n){
		int sqrt = (int)(Math.sqrt(n));
		return sqrt*sqrt==n;
	}
	public int numSquaresFast(int n) {
		while(n%4==0){
			n/=4;
		}
		if(n%8==7){
			return 4; 
		}
		if(isSquare(n)){
			return 1;
		}
		for(int i=1;i*i<n;i++){
			if(isSquare(n-i*i)){
				return 2;
			}
		}
		return 3;
	}

	/**
	 * A stranger's LeetCode submission, runtime = 34ms.
	 * 
	 * @param int n
	 * @return int dp[n]
	 */
	public boolean isPerfectSquare(double x) { 
		// Find floating point value of 
		// square root of x. 
		double sr = Math.sqrt(x); 
		// If square root is an integer 
		return ((sr - Math.floor(sr)) == 0); 
	} 
	public int numSquaresFast2(int n) {
		int[]dp=new int[n+1];
		// int count=n;
		Arrays.fill(dp,n);
		dp[0]=0;
		dp[1]=1;

		for(int i=2;i<=n;i++){
			if(isPerfectSquare((double)i)  ){
				dp[i]=1;
			}
			else{
				double num=Math.sqrt(i);
				for(int k=1;k<=(int)num;k++)
					dp[i]=Math.min(1+dp[i-k*k],dp[i]);
			}
		}
		return dp[n];
	}

	/**
	 * A stranger's LeetCode submission, runtime = 81ms.
	 * 
	 * @param int n
	 * @return int dp[n]
	 */
	public int numSquaresAlt3(int n) {
		int dp[] = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		// bottom case
		dp[0] = 0;

		// pre-calculate the square numbers.
		int max_square_index = (int) Math.sqrt(n) + 1;
		int square_nums[] = new int[max_square_index];
		for (int i = 1; i < max_square_index; ++i) {
			square_nums[i] = i * i;
		}

		for (int i = 1; i <= n; ++i) {
			for (int s = 1; s < max_square_index; ++s) {
				if (i < square_nums[s])
					break;
				dp[i] = Math.min(dp[i], dp[i - square_nums[s]] + 1);
			}
		}
		return dp[n];
	}

	/**
	 * A stranger's LeetCode submission with high space
	 * efficiency.
	 * 
	 * @param int n
	 * @return int
	 */
	public static int numSquaresLowSpace(int n) {
		int min = Integer.MAX_VALUE;

		for (int i = (int) Math.sqrt(n); i >= 1; i--) {
			int remain = n;
			int sq = i * i;
			int cnt = 0;
			int mock = remain / sq;
			remain -= mock * sq;
			cnt += mock;

			cnt += numSquares(remain);

			min = Math.min(min, cnt);
		}

		return Math.min(n, min);
	}

	// Test in main.
	public static void main(String[] args) {
		System.out.println("Optimum = " + 
				JuneChallengePerfectSquares.numSquares(290));
		System.out.println("Optimum = " + 
				JuneChallengePerfectSquares.numSquaresWithHelper(290));
		System.out.println("Optimum = " + 
				JuneChallengePerfectSquares.numSquaresLowSpace(290));
	}

}
