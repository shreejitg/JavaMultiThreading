package threads.simplethreads.executor;

import java.util.List;

public class SumDistributor {
	private List<Integer> integerList;
	private int partitions, partitionSize, currentPartition;
	
	public synchronized List<Integer> getNextPartition() {
		int fromIndex = currentPartition * partitionSize;
		if(fromIndex > integerList.size()) {
			fromIndex = integerList.size();
		}
		int toIndex = (currentPartition + 1) * partitionSize;
		if(toIndex > integerList.size()) {
			toIndex = integerList.size();
		}
		List<Integer> listPartition = integerList.subList(fromIndex, toIndex);
		currentPartition++;
		return listPartition;
	}
	
	public SumDistributor(List<Integer> integerList, int numThreads) throws ThreadPoolFullError {
		this.integerList = integerList;
		partitions = numThreads;
		partitionSize = integerList.size() / partitions;
		currentPartition = 0;
	}
}
