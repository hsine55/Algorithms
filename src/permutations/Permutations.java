package permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {
	
	/**
	 * generates recursively all the possible permutations of a list of integers and prints then to the console
	 * @param result
	 * @param tempPerm
	 * @param inputArray
	 * @param start
	 */
	public static void generatePermutations(List<Integer> inputArray, Integer start) {
		if (inputArray.size() - 1 == start) { // we have a finished permutation 
			System.out.println(inputArray.toString());
			return;
		}
		
		for(int i=start; i<inputArray.size(); i++) {
			swap(start,i, inputArray);
			generatePermutations(inputArray, start+1);
			swap(i, start, inputArray); //backtrack
		}
	}
	
	public static void main(String[] argv) {
		List<Integer> inputArray = Arrays.asList(1,2,5);
		Integer start = 0;
		generatePermutations(inputArray, start);
	}
	
	private static void swap(Integer firstIndex, Integer secondIndex, List<Integer> inputArray) {
		Integer temp = inputArray.get(firstIndex);
		inputArray.set(firstIndex, inputArray.get(secondIndex));
		inputArray.set(secondIndex, temp);
	}
}
