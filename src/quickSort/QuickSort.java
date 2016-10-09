package quickSort;

import utils.Utils;

public class QuickSort {
	
	public static int sort(int low, int high, Integer[] inputArray) {
		if (low >= high) {
			return 0;
		}
		int pivotIndex = choosePivot(low, high, inputArray);
		swap(low, pivotIndex, inputArray);
		int newPivotIndex = partition(low, high,inputArray);
		return high - low + sort(low, newPivotIndex - 1, inputArray) + sort(newPivotIndex + 1, high, inputArray);
	}
	
	/**
	 * 
	 * @param inputArray
	 * @return returns index of chosen pivot in the array
	 */
	private static int choosePivot(int low, int high, Integer[] inputArray) {
		int middle = (low + high) / 2;
		return median(low, high, middle, inputArray);
	}
	
	private static int median(Integer a, Integer b, Integer c, Integer[] inputArray) {
		if ( inputArray[a] > inputArray[b] ) {
			if ( inputArray[a] > inputArray[c] ) {
				if (inputArray[b] > inputArray[c]) {
					return b;
				} else return c;
			} else return a ;
		} else {
			if ( inputArray[b] > inputArray[c] ) {
				if (inputArray[a] > inputArray[c]) {
					return a;
				} else return c;
			} else return b;
		}
	}
	
	/**
	 * 
	 * @param low
	 * @param high
	 * @param inputArray
	 * partition the array around the pivot element and returns its index
	 * @return
	 */
	private static int partition(int low, int high, Integer[] inputArray) {
		int j = low + 1;
		int i = low + 1;
		Integer pivotValue = inputArray[low];
		while(j <= high) {
			if (inputArray[j] < pivotValue) {
				swap(j, i, inputArray);
				i++;
			}
			j++;
		}
		swap(low, i-1, inputArray);
		return i-1;
	}
	
	private static void swap(int a, int b, Integer[] inputArray) {
		Integer temp = inputArray[a];
		inputArray[a] = inputArray[b];
		inputArray[b] = temp;
	}
	
	public static void main(String[] args) {
		Integer[] tableau = Utils.readArray("1000.txt");
		int count = 0;
		count = sort(0, tableau.length - 1, tableau);
		for(int i = 0; i < tableau.length; i++) {
			System.out.println(tableau[i]);
		}
		System.out.println("count is " + count);
	}

}
