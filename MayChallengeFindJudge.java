import java.util.ArrayList;
import java.util.HashMap;

/**
 * 10 May 2020 / May LeetCode Challenge
 * 
 * In a town, there are N people labelled from 1 to N.  
 * There is a rumor that one of these people is secretly 
 * the town judge. If the town judge exists, then:
 * 		1. The town judge trusts nobody.
 * 		2. Everybody (except for the town judge) trusts
 * 			the town judge.
 * 		3. There is exactly one person that satisfies
 * 			properties 1 and 2.
 * You are given trust, an array of pairs trust[i] = [a, b] 
 * representing that the person labelled a trusts the 
 * person labelled b. If the town judge exists and can be 
 * identified, return the label of the town judge.  Otherwise, 
 * return -1.
 * 
 * Examples:
 * Input: N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
 * Output: 3
 * Input: N = 3, trust = [[1,2],[2,3]]
 * Output: -1
 * Input: N = 2, trust = [[1,2]]
 * Output: 2
 * 
 * @author razel
 *
 */
public class MayChallengeFindJudge {

	/**
	 * An approach using arrays, capitalizing on
	 * the fact that individual IDs are analogous
	 * to index arrays, obviating need for HashMap.
	 * 
	 * O(n) time complexity when n = length of
	 * int[][].
	 * 
	 * 3ms run time on LeetCode, faster than ~80%
	 * of Java submissions (excluding print statements).
	 * 
	 * @param int N
	 * @param int[][] trust
	 * @return int
	 */
	public int findJudge(int N, int[][] trust) {
		
		if (N == 0) return -1;
		if (N == 1) return 1;
		if (N == 2 && trust.length == 1) return trust[0][1];

		// Tally trust / trusted by counts.
		int[] trustsArray = new int[N];
		int[] trustedByArray = new int[N];
		for (int[] pair : trust) {
			// Total people person trusts ++.
			trustsArray[pair[0] - 1] = ++trustsArray[pair[0] - 1];
			// Total people person is trusted by ++.
			trustedByArray[pair[1] - 1] = ++trustedByArray[pair[1] - 1];
		}
		
		// Print to test.
		int j = 0;
		for (int element : trustsArray) {
			System.out.println(j + " trusts: " + trustsArray[j]);
			j++;
		}
		int k = 0;
		for (int element : trustedByArray) {
			System.out.println(k + " is trusted by: " + trustedByArray[k]);
			k++;
		}
		
		// Scan for judge.
		for (int i = 0; i < N; i++) {
			if (trustsArray[i] == 0 && trustedByArray[i] == N - 1) return i + 1;
		}
		
		return -1;
	}
	
	/**
	 * Naive approach using HashMaps. Very high-overhead
	 * relative to array approach, i.e. slower to run
	 * (~35 ms excluding print statements) and worse space 
	 * complexity.
	 * 
	 * @param int N
	 * @param int[][] trust
	 * @return int
	 */
	public int findJudgeNaive(int N, int[][] trust) {

		if (N == 0) return -1;
		if (N == 1) return 1;
		if (N == 2 && trust.length == 1) return trust[0][1];

		HashMap<Integer, ArrayList<Integer>> trustMap = 
				new HashMap<Integer, ArrayList<Integer>>();
		HashMap<Integer, ArrayList<Integer>> trustedByMap = 
				new HashMap<Integer, ArrayList<Integer>>();

		for (int[] pair : trust) {

			// Populate trust map.
			ArrayList<Integer> trustList;
			if (!trustMap.containsKey(pair[0])) {
				trustList = new ArrayList<Integer>();
				trustList.add(pair[1]);
				trustMap.put(pair[0], trustList);
			}
			else {
				trustList = trustMap.get(pair[0]);
				trustList.add(pair[1]);
				trustMap.replace(pair[0], trustList);
			}


			// Populate trusted-by map.
			ArrayList<Integer> trustedByList;
			if (!trustedByMap.containsKey(pair[1])) {
				trustedByList = new ArrayList<Integer>();
				trustedByList.add(pair[0]);
				trustedByMap.put(pair[1], trustedByList);
			}
			else {
				trustedByList = trustedByMap.get(pair[1]);
				trustedByList.add(pair[0]);
				trustedByMap.replace(pair[1], trustedByList);
			}
		}

		// Print results to test.
		for (int key : trustMap.keySet()) {
			System.out.print(key + " trusts: ");
			ArrayList<Integer> list = trustMap.get(key);
			for (int id : list) {
				System.out.print(id + " ");
			}
			System.out.println("");
		}

		for (int key : trustedByMap.keySet()) {
			System.out.print(key + " is trusted by: ");
			ArrayList<Integer> list = trustedByMap.get(key);
			for (int id : list) {
				System.out.print(id + " ");
			}
			System.out.println("");
		}

		// Scan for judge: everyone trusts, trusts no one.
		for (int id : trustedByMap.keySet()) {
			if (trustedByMap.get(id).size() == (N - 1) &&
					!trustMap.containsKey(id)) {
				return id;
			}
		}

		return -1;
	}

	/**
	 * A very fast submission by another
	 * LeetCoder.
	 * 
	 * @param int N
	 * @param int[][] trust
	 * @return int
	 */
	public int findJudgeLeetCode1(int N, int[][] trust) {
		int[] count = new int[N];
		int[] trusted = new int[N]; 

		for(int i = 0; i < trust.length; i++)
		{
			int a = trust[i][0]; 
			int b = trust[i][1];
			count[a - 1]++;
			trusted[b - 1]++;
		}

		for(int i = 0; i < N; i++)
		{
			if(count[i] == 0 && trusted[i] == N - 1)
			{
				return i + 1;
			}    
		}
		return -1;
	}

	/**
	 * A very fast submission by another
	 * LeetCoder.
	 * 
	 * @param int N
	 * @param int[][] trust
	 * @return int
	 */
	public int findJudgeLeetCde2(int N, int[][] trust) {
		
		if(trust.length <  N-1){
			return -1;
		}

		int [] inEdges = new int[N+1];
		int [] outEdges = new int[N+1];
		for(int[] relation : trust){
			outEdges[relation[0]]++;
			inEdges[relation[1]]++;
		}
		for (int i = 1; i <= N; i++) {
			if (inEdges[i] == N - 1 && outEdges[i] == 0) {
				return i;
			}
		}
		
		return -1;
	}

	// Test in main.
	public static void main(String[] args) {

		MayChallengeFindJudge fj = new MayChallengeFindJudge();
		int[][] testArray = {{1,3},{1,4},{2,3},{2,4},{4,3}};
		System.out.println("judge = " + fj.findJudge(4, testArray));

	}

}
