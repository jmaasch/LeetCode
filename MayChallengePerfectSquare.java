/**
 * 9 May 2020 / May LeetCode Challenge
 * 
 * Assess whether an integer is a perfect square.
 * Return a boolean.
 * 
 * @author razel
 *
 */
public class MayChallengePerfectSquare {

	/**
	 * Assess whether an integer is a 
	 * perfect square.
	 * 
	 * @param int num
	 * @return boolean
	 */
	public boolean isPerfectSquare(int num) {

		int squareRoot = findSquareRootFloor(num);
		return (squareRoot * squareRoot == num) ? true : false;

	}

	/**
	 * Helper method to find square root. This
	 * method is based on the following formula,
	 * Newton's method for finding the integer
	 * square root:
	 * 
	 * Solve x^2 - n = 0 to solve sqrt(n)
	 * x_(k+1) = (x_k + (n / x_k)) / 2
	 * 
	 * https://en.wikipedia.org/wiki/Integer_square_root
	 * 
	 * @param int num
	 * @return double squareRoot
	 */
	public double findSquareRoot(int num) {

		if (num == 0 || num == 1) return num;

		double squareRoot = num / 2;
		double x;

		do {
			x = squareRoot;
			squareRoot = (x + (num / x)) / 2;
		} while ((x - squareRoot) != 0);

		return squareRoot;
	}
	
	/**
	 * Helper method to find square root. This
	 * method is based on the following formula,
	 * Newton's method for finding the integer
	 * square root:
	 * 
	 * Solve x^2 - n = 0 to solve sqrt(n)
	 * x_(k+1) = (x_k + (n / x_k)) / 2
	 * 
	 * https://en.wikipedia.org/wiki/Integer_square_root
	 * 
	 * @param int num
	 * @return int squareRoot
	 */
	public int findSquareRootFloor(int num) {

		if (num == 0 || num == 1) return num;

		double squareRoot = num / 2;
		double x;

		do {
			x = squareRoot;
			squareRoot = (x + (num / x)) / 2;
		} while ((x - squareRoot) != 0);

		return (int) squareRoot;
	}

	/**
	 * Alternative helper method that returns
	 * floor of square root. Test all i*i less
	 * than input num. Note: This method is much 
	 * slower than findSquareRootFloor(), as it
	 * is O(n) time complexity.
	 * 
	 * @param int num
	 * @return int squareRoot
	 */
	public int findSquareRootFloor2(int num) {
		
		if (num == 0 || num == 1) return num; 

		int currentInt = 1;
		int square = 1; 

		while (square <= num) { 
			currentInt++; 
			square = currentInt * currentInt; 
		}
		
		return currentInt - 1; 
	} 
	
	/**
	 * A 0ms solution submitted to LeetCode.
	 * @param int num
	 * @return boolean
	 */
	public boolean isPerfectSquareLeetCode1(int num) {
        long l = 1;
        long r = num;
        
        while (l <= r) {
            long mid = l - (l - r) / 2;
            
            if (mid * mid == num)
                return true;
            else if (mid * mid < num)
                l = mid + 1;
            else
                r = mid - 1;
        }
        
        return false;
    }
	
	/**
	 * A very succinct solution submitted to LeetCode.
	 * "i think what they're doing is using the fact 
	 * that the sum of the first n odd numbers = n^2
	 * - Josh Chopra"
	 * 
	 * @param int num
	 * @return boolean
	 */
	public boolean isPerfectSquareLeetCode2(int num) {
        int i = 1;
        
        while (num > 0) {
            num -= i;
            i += 2;
        }
        
        return num == 0;
    }

	// Test in main.
	public static void main(String[] args) {
		MayChallengePerfectSquare ps = new MayChallengePerfectSquare();
		System.out.println(ps.findSquareRoot(25));
		System.out.println(ps.findSquareRootFloor(25));
		System.out.println(ps.findSquareRootFloor2(25));
		System.out.println(ps.isPerfectSquare(25));
	}

}
