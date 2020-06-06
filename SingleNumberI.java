import java.util.HashMap;
import java.util.HashSet;

/**
 * Given a non-empty array of integers, every element 
 * appears twice except for one. Find that single one.
 * 
 * Note: Your algorithm should have a linear runtime 
 * complexity. Could you implement it without using 
 * extra memory?
 * 
 * Difficulty: Easy.
 * 
 * @author razel
 *
 */
public class SingleNumberI {

	/**
	 * Time complexity: O(2n) + O(1) = O(n).
	 * 
	 * Space complexity: O(n) + O(1) = O(n).
	 * 
	 * Runtime: 4 ms, faster than 41.02% of Java online 
	 * submissions for Single Number.
	 * 
	 * Memory Usage: 39.7 MB, less than 96.30% of Java 
	 * online submissions for Single Number.
	 * 
	 * @param int[] array
	 * @return int (single element that occurs once)
	 */
	public static int singleNumberI(int[] array) {

		HashSet<Integer> set = new HashSet<Integer>();
		int arraySum = 0;
		int setSum = 0;

		// Compute sum of elements in array.
		for (int value : array) {
			arraySum += value;
			set.add(value);
		}

		// Compute sum of elements in set.
		for (int value : set) {
			setSum += value;
		}

		// 2 * (a + b + c) − (a + a + b + b + c) = c
		int minuend = setSum * 2;
		return  minuend - arraySum;
	}

	/**
	 * A bit manipulation approach.
	 * 
	 * "If we take XOR of zero and some bit, it will return 
	 * that bit. If we take XOR of two same bits, it will 
	 * return 0. So we can XOR all bits together to find the 
	 * unique number."
	 * 
	 * Time complexity: O(n).
	 * 
	 * Space complexity: O(1).
	 * 
	 * Runtime: 0 ms, faster than 100.00% of Java online 
	 * submissions for Single Number.
	 * 
	 * Memory Usage: 40.7 MB, less than 74.08% of Java 
	 * online submissions for Single Number.
	 * 
	 * @param int[] array
	 * @return int xorProduct
	 */
	public static int singleNumberIXOR(int[] array) {
		int xorProduct = 0;
		for (int value : array) {
			xorProduct ^= value;
		}
		return xorProduct;
	}

	/**
	 * An approach using a HashMap.
	 * 
	 * Time complexity: O(n).
	 * 
	 * Space complexity: O(n).
	 * 
	 * Runtime: 8 ms, faster than 15.42% of Java online 
	 * submissions for Single Number.
	 * 
	 * Memory Usage: 40.7 MB, less than 74.08% of Java 
	 * online submissions for Single Number.
	 * 
	 * @param int[] array
	 * @return int (single element that occurs once)
	 */
	public static int singleNumberIMap(int[] array) {

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		// Populate map.
		for (int value : array) {
			map.put(value, map.getOrDefault(value, 0) + 1);
		}

		// Linear search for single number.
		for (int key : map.keySet()) {
			if (map.get(key) < 2) return key;
		}

		return -1;
	}

	// Test in main.
	public static void main(String[] args) {
		int[] testArray = {4, 1, 2, 1, 2};
		System.out.println(SingleNumberI.singleNumberI(testArray));
		System.out.println(SingleNumberI.singleNumberIXOR(testArray));
		System.out.println(SingleNumberI.singleNumberIMap(testArray));
	}

}
