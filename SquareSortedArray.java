import java.util.Arrays;

/**
 * 
 * 16 December 2020 / December LeetCode Challenge
 * 
 * Squares of a Sorted Array:
 * Given an integer array sorted in non-decreasing order, 
 * return an array of the squares of each number sorted 
 * in non-decreasing order.
 * 
 * Example:
 * Input: [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 * 
 * @author razel
 *
 */
public class SquareSortedArray {

	/**
	 * 
	 * This method squares each integer in an
	 * array and sorts in non-decreasing order.
	 * The input array is not altered.
	 * 
	 * This method operates in O(n) time by exploiting 
	 * the fact that all squares are non-negative. This 
	 * satisfies the assumptions of counting sort, a 
	 * linear time sorting algorithm, obviating the need 
	 * for an O(n log n) time sort.
	 * 
	 * However, if array elements are very large, memory
	 * may become a concern using counting sort (see
	 * in-line comments on Arrays.sort()).
	 * 
	 * @param int[] inputArray
	 * @return int[] outputArray
	 * 
	 */
	public static int[] squareSort(int[] inputArray) {

		// Edge cases.
		if (inputArray == null || inputArray.length == 0) return null;

		// Square every element (O(n)).
		int[] outputArray = new int[inputArray.length];
		System.arraycopy(inputArray, 0, outputArray, 0, outputArray.length);
		for (int i = 0; i < outputArray.length; i++) {
			outputArray[i] = outputArray[i] * outputArray[i];
		}

		// Sort via counting sort (O(n)).
		outputArray = countingSort(outputArray);
		
		// Alternatively, if memory limits are a concern 
		// with large element values, opt for Arrays.sort(): 
		// "a tuned quicksort... This algorithm  offers 
		// n*log(n) performance on many data sets that 
		// cause other quicksorts to degrade to quadratic 
		// performance."
		// Arrays.sort(outputArray);
 
		// Return squared, sorted array.
		return outputArray;
	}


	/**
	 * This method implements counting sort. Elements of
	 * the input array must be non-negative.
	 * 
	 * Time complexity = Theta(k + n), i.e. linear.
	 * 
	 * @param int[] inputArray
	 * @return int[] sortedArray
	 */
	public static int[] countingSort(int[] inputArray) {

		// Edge cases.
		if (inputArray == null) return null;
		if (inputArray.length < 2) return inputArray;

		// Identify maximum value in input array.
		int k = Integer.MIN_VALUE;
		for (int element : inputArray) {
			if (element < 0) return null; // Input array was invalid.
			if (element > k) k = element + 1;
		}

		// Init data structures.
		int[] sortedArray = new int[inputArray.length];
		int[] tempArray = new int[k];

		// tempArray[i] = total elements equal to i.
		for (int j = 0; j < inputArray.length; j++) {
			tempArray[inputArray[j]] = ++tempArray[inputArray[j]];
		}

		// tempArray[i] = total elements less than or equal to i.
		for (int i = 1; i < k; i++) {
			tempArray[i] = tempArray[i] + tempArray[i - 1];
		}

		// Populate output array.
		for (int j = inputArray.length - 1; j > -1; j--) {
			sortedArray[tempArray[inputArray[j]] - 1] = inputArray[j];
			tempArray[inputArray[j]] = tempArray[inputArray[j]] - 1;
		}

		return sortedArray;
	}
	
	/**
	 * 
	 * Fast method submitted by another LeetCoder.
	 * 
	 * @param int[] nums
	 * @return int[] res
	 */
	public int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        
        int pivot = -1;
        for(int i=0; i< nums.length; i++) {
            if(nums[i] > 0) {
                pivot = i;
                break;
            }
        }
        // If all negative
        pivot = pivot == -1 ? nums.length - 1 : pivot;
        
        int l = pivot - 1, r = pivot, idx = 0;
        while(l >= 0 && r < nums.length) {
            if(nums[l]*nums[l] < nums[r]*nums[r]) {
                res[idx] = nums[l]*nums[l];
                l--;
            } else {
                res[idx] = nums[r]*nums[r];
                r++;
            }
            idx++;
        }
        
        while(l >= 0) {
            res[idx++] = nums[l]*nums[l];
            l--;
        }
        
        while(r < nums.length) {
            res[idx++] = nums[r]*nums[r];
            r++;
        }
        
        return res;
    }


	// Test in main.
	public static void main(String[] args) {

		int[] testArray = {-4, -1, 0, 3, 10};	
		int[] sortedArray = squareSort(testArray);

		System.out.println("Unchanged input array:");
		for (int element : testArray) {
			System.out.print(element + " ");
		}
		System.out.println("\n\nSorted array:");
		for (int element : sortedArray) {
			System.out.print(element + " ");
		}
		
		int[] nullTest = null;
		int[] nullTest2 = squareSort(nullTest);
		System.out.println("\n\nNull array returns: " + nullTest2);
		
	}

}
