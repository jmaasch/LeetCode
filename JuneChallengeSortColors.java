/**
 * 11 June 2020 / June LeetCode Challenge
 * 
 * Given an array with n objects colored red, white or blue, 
 * sort them in-place so that objects of the same color are 
 * adjacent, with the colors in the order red, white and blue.
 * 
 * Here, we will use the integers 0, 1, and 2 to represent the 
 * colors red, white, and blue respectively.
 * 
 * Note: You are not supposed to use the library's sort function 
 * for this problem.
 * 
 * Example input: [2,0,2,1,1,0]
 * Example output: [0,0,1,1,2,2]
 * 
 * @author razel
 *
 */
public class JuneChallengeSortColors {

	/**
	 * Two-pass approach, counting elements per
	 * color and repopulating input array.
	 * 
	 * Time complexity = O(n).
	 * Space complexity = O(1).
	 * 
	 * @param int[] nums
	 * @return void
	 */
    public static void sortColors(int[] nums) {
        
    	// First pass: count elements per color.
    	int totalRed = 0;
    	int totalWhite = 0;
    	
    	for (int num : nums) {
    		if (num == 0) totalRed++;
    		else if (num == 1) totalWhite++;
    	}
    	
    	// Second pass: repopulate int array.
    	int index = 0;
    	while (index < totalRed) {
    		nums[index] = 0;
    		index++;
    	}
    	while (index < totalRed + totalWhite) {
    		nums[index] = 1;
    		index++;
    	}
    	while (index < nums.length) {
    		nums[index] = 2;
    		index++;
    	}
    	
    }

    // Test in main.
	public static void main(String[] args) {
		
		int[] testArray = {2, 0, 2, 1, 1, 0};
		JuneChallengeSortColors.sortColors(testArray);
		for (int element : testArray) {
			System.out.print(element + " ");
		}

	}

}
