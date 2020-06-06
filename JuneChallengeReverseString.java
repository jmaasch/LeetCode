/**
 * 6 June 2020 / June LeetCode Challenge
 * 
 * Write a function that reverses a string. The input 
 * string is given as an array of characters char[].
 * Do not allocate extra space for another array, you 
 * must do this by modifying the input array in-place 
 * with O(1) extra memory. You may assume all the 
 * characters consist of printable ascii characters.
 * 
 * Difficulty: Easy.
 * 
 * @author razel
 *
 */
public class JuneChallengeReverseString {

	/**
	 * Time complexity = O(n)
	 * Space complexity = O(1)
	 * 
	 * Runtime: 0 ms (beats 100% of Java submissions).
	 * Memory Usage: 46 MB (beats 85.91% of Java submissions).
	 * 
	 * Note: Choosing not to store values in j and mid
	 * resulted in doubling of runtime.
	 * 
	 * @param charArray
	 * @return void
	 */
	public static void reverseString(char[] charArray) {

		if (charArray.length < 2) return;

		int j = charArray.length - 1;
		int mid = charArray.length / 2;
		for (int i = 0; i < mid; i++) {
			char temp = charArray[i];
			charArray[i] = charArray[j];
			charArray[j] = temp;
			j--;
		}

	}

	// Test in main.
	public static void main(String[] args) {
		
		char[] testArray = {'j', 'a', 'm'};
		char[] testArray2 = {'h', 'e', 'l', 'l', 'o'};
		JuneChallengeReverseString.reverseString(testArray);
		for (char c : testArray) {
			System.out.print(c);
		}
		System.out.println("");
		JuneChallengeReverseString.reverseString(testArray2);
		for (char c : testArray2) {
			System.out.print(c);
		}
		
	}
	
}
