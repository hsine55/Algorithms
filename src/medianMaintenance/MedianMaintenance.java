package medianMaintenance;

import java.util.PriorityQueue;

import utils.Utils;

public class MedianMaintenance {
	
	private static final String FILE_PATH = "testFiles/medianMaintenance/Median.txt";
	private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
	private PriorityQueue<Integer> maxHeap = new PriorityQueue<>((Integer a1, Integer a2) -> a2 - a1);
	
	public void add(Integer n) {
		if (maxHeap.isEmpty()  || n <= maxHeap.peek()) {
			maxHeap.add(n);
		} else {
			minHeap.add(n);
		}
	}
	
	public void balance() {
		while(maxHeap.size() > minHeap.size()+1 ) {
			Integer extracted = maxHeap.poll();
			minHeap.add(extracted);
		}
		while(minHeap.size() > maxHeap.size()+1 ) {
			Integer extracted = minHeap.poll();
			maxHeap.add(extracted);
		}
	}
	
	public Integer median() {
		if (minHeap.size() > maxHeap.size()) {
			return minHeap.peek();
		} else {
			return maxHeap.peek();
		}
	}
	
	public static void main(String[] argv) {
		Integer[] streamOfIntegers = Utils.readArray(FILE_PATH);
		MedianMaintenance m = new MedianMaintenance();
		long sumOfMedians = 0;
		for(int i=0; i<streamOfIntegers.length; i++) {
			m.add(streamOfIntegers[i]);
			m.balance();
			sumOfMedians += m.median();
		}
		System.out.println("sum of medians is : "+sumOfMedians);
		System.out.println("last 4 digits are : " + sumOfMedians % 10000);
	}
}
