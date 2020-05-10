import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 6 May 2020 / May LeetCode Challenge
 * 
 * Given an array of size n, find the majority element. 
 * The majority element is the element that appears more 
 * than ⌊ n/2 ⌋ times. You may assume that the array is 
 * non-empty and the majority element always exist in the 
 * array.
 * 
 * Example 1:
 * Input: [3,2,3]
 * Output: 3
 * 
 * @author razel
 *
 */
public class MayChallengeMajorityElement {
	
	/**
	 * Find element in int array that occurs
	 * more than ⌊ n/2 ⌋ times.
	 * @param int[] array
	 * @return int majorityElement
	 */
	public int findMajorityElement(int[] array) {
		Integer majorityElement = null;
		HashMap<Integer, Integer> elementCountMap = new HashMap<Integer, Integer>();
		
		for (int number : array) {
			if (!elementCountMap.containsKey(number)) {
				elementCountMap.put(number, 1);
			}
			else {
				elementCountMap.put(number, elementCountMap.get(number) + 1);
			}
			if (elementCountMap.get(number) > Math.floor(array.length / 2)) {
				majorityElement = number;
			}
		}
		
		/*
		for (int key : elementCountMap.keySet()) {
			System.out.println(key + ": " + elementCountMap.get(key));
		}
		*/
		
		return (int) majorityElement;
	}
	
	/**
	 * Adjustments / improvements suggested by
	 * Josh Chopra.
	 * @param int[] array
	 * @return int majorityElement
	 */
	public int findMajorityElementJosh(int[] array) {
	    Integer majorityElement = null;
	    Map<Integer, Integer> elementCountMap = new HashMap<Integer, Integer>();
	    for (int number : array) {
	        elementCountMap.put(number, elementCountMap.getOrDefault(number, 0) + 1);
	        if (elementCountMap.get(number) > Math.floor(array.length / 2)) {
	            majorityElement = number;
	        }
	    }
	    return majorityElement != null ? majorityElement : -1;
	}
	
	/**
	 * Kelvin Cheung's solution:
	 * "sort then the answer must be either first element, 
	 * last element, or somewhere in the middle. this is 
	 * O(n log n) for sort, but no space."
	 * @param int[] nums
	 * @return int
	 */
	public int majorityElementKelvin(int[] nums) {
        Arrays.sort(nums);
        if(nums[0] == nums[nums.length / 2]) return nums[0];
        return nums[nums.length/2];
    }
	
	
	/**
	 * A superior submission (not by me) that does not use
	 * a HashMap and runs significantly faster.
	 * @param int[] nums
	 * @return int candidate
	 */
	public int superiorMajorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

	// Test in main.
	public static void main(String[] args) {
		MayChallengeMajorityElement m = new MayChallengeMajorityElement();
		int[] testArray = {2, 2, 1, 1, 3, 2, 2};
		System.out.println("Majority element = " + m.findMajorityElement(testArray));

	}

}
