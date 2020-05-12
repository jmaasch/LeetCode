/**
 * 12 May 2020 / May LeetCode Challenge
 * 
 * You are given a sorted array consisting of 
 * only integers where every element appears 
 * exactly twice, except for one element which 
 * appears exactly once. Find this single 
 * element that appears only once.
 * 
 * Note: Your solution should run in O(log n) 
 * time and O(1) space.
 * 
 * Example:
 * Input: [3,3,7,7,10,11,11]
 * Output: 10
 * 
 * @author razel
 *
 */
public class MayChallengeFindSingleElement {

	/**
	 * Find the single non-duplicate element
	 * in a sorted integer array in O(n/2) = 
	 * O(n) time and O(1) space.
	 * 
	 * LeetCode results:
	 * 12 / 12 test cases passed.
	 * Runtime: 0 ms (beats 100% of Java submissions)
	 * Memory Usage: 39.8 MB
	 * 
	 * @param int[] nums
	 * @return single non-duplicate int
	 */
    public static int singleNonDuplicate(int[] nums) {
    	
    	for (int i = 0; i < nums.length - 1; i += 2) {
    		if (nums[i] != nums[i + 1]) return nums[i];
    	}
    	
    	// Else, must be final element.
    	return nums[nums.length - 1];
    }
    
    /**
     * O(log n) time submission by another
     * LeetCoder.
     * 
	 * @param int[] nums
	 * @return single non-duplicate int
     */
    public static int singleNonDuplicateLogTime(int[] nums) {
        int lo = 0, len = nums.length, hi = len / 2;

        while (lo < hi){
            int mid = lo + ((hi - lo) >> 1); // >> 1 is equivalent to halving
            if (nums[2 * mid] == nums[2 * mid + 1]){
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return nums[2 * lo];
    }
	
	// Test in main.
	public static void main(String[] args) {
		int[] a = {1, 1, 2, 3, 3, 6, 6, 8, 8};
		System.out.println(MayChallengeFindSingleElement.singleNonDuplicate(a));
		System.out.println(MayChallengeFindSingleElement.singleNonDuplicateLogTime(a));
	}

}
