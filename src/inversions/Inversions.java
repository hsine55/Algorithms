package inversions;

import utils.Utils;

public class Inversions {
	
	public static void main(String[] args) {
		Integer[] tableau = Utils.readArray("Inversions.txt");
		//System.out.println(choosePivot(0, tableau.length - 1, tableau));
		long count = 0;
		count = sortAndCountInversions(0, tableau.length - 1, tableau);
		for(int i = 0; i < tableau.length; i++) {
			System.out.println(tableau[i]);
		}
		System.out.println("count is " + count);
	}
	
	public static long sortAndCountInversions(int low, int high, Integer[] inputArray) {
		if (low >= high) return 0;
		int middle = (low + high) / 2;
		long leftInversions = sortAndCountInversions(low, middle, inputArray);
		long rightInversions = sortAndCountInversions(middle + 1, high, inputArray);
		long splitInversions = mergeAndCountInversions(low, middle, high, inputArray);
		return leftInversions + rightInversions + splitInversions ;
	}
	
	private static long mergeAndCountInversions(int low, int middle, int high, Integer[] inputArray) {
		Integer[] copyArray = new Integer[high-low+1];
		
		for(int i = low; i <= high; i++) {
			copyArray[i-low] = inputArray[i];
		}
		
		int i = low;
		int j = middle + 1;
		int inversionsCount = 0;
		
		for(int k = low; k <= high; k++) {
			if ( leftHalfisNotEmpty(i, middle) && rightHalfisNotEmpty(j, high) ) {
				if (copyArray[i-low] <= copyArray[j-low]) {
					inputArray[k] = copyArray[i-low];
					i++;
				} else {
					inputArray[k] = copyArray[j-low];
					inversionsCount = inversionsCount + middle - i + 1;
					j ++; //total numbers remaining the left half
				}
			} else if (leftHalfisNotEmpty(i, middle)) {
				inputArray[k] = copyArray[i-low];
				i++;
			} else if (rightHalfisNotEmpty(j, high)) {
				inputArray[k] = copyArray[j-low];
				j++;
			} 
		}
		return inversionsCount;
	}
	
	private static boolean leftHalfisNotEmpty(int i, int middle) {
		return i <= middle;
	}
	
	private static boolean rightHalfisNotEmpty(int j, int high) {
		return j <= high;
	}

}
