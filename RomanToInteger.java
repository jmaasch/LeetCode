/**
 * Given a roman numeral, convert it to an integer. 
 * Input is guaranteed to be within the range from 1
 * to 3999.
 * 
 * Difficulty: Easy.
 * 
 * @author razel
 *
 */
public class RomanToInteger {
	
	/*
	 * char[] roman = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
	 * int[] value = {1, 5, 10, 50, 100, 500, 1000};
	 */
	
	/**
	 * Time complexity = O(n).
	 * Runtime: 3 ms (beats 100% of Java submissions).
	 * Memory Usage: 39.6 MB (beats 78.5% of Java submissions).
	 * 
	 * @param String s
	 * @return int inputValue
	 */
	public static int romanToInt(String s) {

		char[] inputArray = s.toCharArray();
		int inputValue = 0;
		int i = inputArray.length - 1;
		
		while (inputArray[i] == 'I') {
			inputValue++;
			if (i > 0) i--;
			else return inputValue;
		}
		while (inputArray[i] == 'V' || inputArray[i] == 'I') {
			switch(inputArray[i]) {
			case 'V':
				inputValue += 5;
				break;
			case 'I':
				inputValue -= 1;
				break;
			}
			if (i > 0) i--;
			else return inputValue;
		}
		while (inputArray[i] == 'X' || inputArray[i] == 'I') {
			switch(inputArray[i]) {
			case 'X':
				inputValue += 10;
				break;
			case 'I':
				inputValue -= 1;
				break;
			}
			if (i > 0) i--;
			else return inputValue;
		}
		while (inputArray[i] == 'L' || inputArray[i] == 'X') {
			switch(inputArray[i]) {
			case 'L':
				inputValue += 50;
				break;
			case 'X':
				inputValue -= 10;
				break;
			}
			if (i > 0) i--;
			else return inputValue;
		}
		while (inputArray[i] == 'C' || inputArray[i] == 'X') {
			switch(inputArray[i]) {
			case 'C':
				inputValue += 100;
				break;
			case 'X':
				inputValue -= 10;
				break;
			}
			if (i > 0) i--;
			else return inputValue;
		}
		while (inputArray[i] == 'D' || inputArray[i] == 'C') {
			switch(inputArray[i]) {
			case 'D':
				inputValue += 500;
				break;
			case 'C':
				inputValue -= 100;
				break;
			}
			if (i > 0) i--;
			else return inputValue;
		}
		while (inputArray[i] == 'M' || inputArray[i] == 'C') {
			switch(inputArray[i]) {
			case 'M':
				inputValue += 1000;
				break;
			case 'C':
				inputValue -= 100;
				break;
			}
			if (i > 0) i--;
			else return inputValue;
		}

		return inputValue;
	}
	
	/**
	 * Submission by another LeetCoder, 3ms runtime.
	 * 
	 * @param String s
	 * @return int value
	 */
	public static int romanToInt3ms(String s) {
        char[] int_arr = s.toCharArray();
        int count = s.length() - 1;
        
        char prev_char = 'A';
        char curr_char = 'A';
        int value = 0;
        while(count > -1){
            curr_char = int_arr[count];
            if(curr_char == 'I'){
                if(prev_char == 'V' || prev_char == 'X'){
                    value -= 1;
                } else {
                    value += 1;
                }
            } else if(curr_char == 'V'){
                value +=5; 
            } else if(curr_char == 'X'){
                if(prev_char == 'L'|| prev_char == 'C'){
                    value -= 10;
                } else{
                    value += 10;
                }
            } else if(curr_char == 'L'){
                value += 50;
            } else if(curr_char == 'C'){
                if(prev_char == 'D' || prev_char == 'M'){
                    value -= 100;
                } else {
                    value +=100;
                }
            } else if(curr_char == 'D'){
                value += 500;
            } else if(curr_char == 'M'){
                value += 1000;
            }
            prev_char = curr_char;
            count -=1;
        }
        
        return value;
	}

	// Test in main.
	public static void main(String[] args) {
		String input = "MCMXCIV";
		System.out.println(RomanToInteger.romanToInt(input));
		String input2 = "LVIII";
		System.out.println(RomanToInteger.romanToInt(input2));
	}

}
