import java.util.Arrays;

/**
 * 
 * 25 June 2020 / June LeetCode Challenge
 * 
 * Find the Duplicate Number:
 * Given an array nums containing n + 1 integers where each 
 * integer is between 1 and n (inclusive), prove that at least 
 * one duplicate number must exist. Assume that there is only 
 * one duplicate number, find the duplicate one.
 * 
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could 
 * be repeated more than once.
 * 
 * Example:
 * Input: [1,3,4,2,2]
 * Output: 2
 * 
 * @author razel
 *
 */
public class JuneChallengeFindDuplicate {

	/**
	 * Identify the replicated value in an integer
	 * array. Exactly one value is replicated.
	 * 
	 * This solution violates the O(1) space complexity
	 * stipulation.
	 * 
	 * Time complexity = O(n).
	 * Space complexity = O(n).
	 * Runtime = 2ms, faster than 48.68% of Java submits.
	 * 
	 * @param int[] intArray
	 * @return int duplicate
	 */
	public static int findDuplicate(int[] intArray) {

		// Initialize array to store value counts.
		int[] countArray = new int[intArray.length];
		int duplicate = -1;

		// Scan for duplicate.
		for (int element : intArray) {
			countArray[element] += 1;
			if (countArray[element] > 1) duplicate = element;
		}

		return duplicate;
	}

	/**
	 * Submission made by another LeetCoder.
	 * 
	 * @param int[] nums
	 * @return int representing replicated value.
	 */
	public int findDuplicateTurtle(int[] nums) {
		// turtle && hare
		// as hare goes twice, turtle move one => intersection find
		// hare starts at intersection point, turtle starts from 
		// starting point, both go for one step
		// corner case
		if (nums == null || nums.length == 0) return 0;
		int hare = nums[0];
		int turtle = nums[0];
		while (true) {
			hare = nums[nums[hare]];
			turtle = nums[turtle];
			// terminating point hare == turtle
			if (turtle == hare) break;
		}
		// turtle and hare are both at intersection point
		turtle = nums[0];
		while(turtle != hare) {
			turtle = nums[turtle];
			hare = nums[hare];
		}
		return turtle;
	}

	/**
	 * Submission made by another LeetCoder.
	 * 
	 * Violates no-modification rule.
	 * 
	 * @param int[] nums
	 * @return int representing replicated value.
	 */
	public int findDuplicateSort(int[] nums) {
		Arrays.sort(nums);
		for (int i=0;i<nums.length-1;i++)
			if (nums[i]==nums[i+1])
				return nums[i];

		return 0;
	}

	/**
	 * Submission made by another LeetCoder.
	 * 
	 * @param int[] nums
	 * @return int representing replicated value.
	 */
	public int findDuplicateRunner(int[] nums) {
		int len = nums.length;
		int walker = 0;
		int runner = 0;
		while(runner < len && nums[runner] < len) {
			walker = nums[walker];
			runner = nums[nums[runner]];
			if(walker == runner) {
				walker = 0;
				while(walker != runner) {
					walker = nums[walker];
					runner = nums[runner];
				}
				return walker;
			}
		}
		return -1;
	}

	// Test in main.
	public static void main(String[] args) {
		int[] testArray = {1, 3, 4, 2, 2};
		int[] testArray2 = {3, 1, 3, 4, 2, 3};
		System.out.println(JuneChallengeFindDuplicate.findDuplicate(testArray));
		System.out.println(JuneChallengeFindDuplicate.findDuplicate(testArray2));
	}

}
