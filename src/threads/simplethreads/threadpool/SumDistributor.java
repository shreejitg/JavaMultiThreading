package threads.simplethreads.threadpool;

import java.util.List;

public class SumDistributor {
	private List<Integer> integerList;
	private Long result = 0L;
	private int partitions, partitionSize, currentPartition;
	private ThreadPool pool;
	
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
		pool = ThreadPool.getInstance();
		currentPartition = 0;
		
		for(int i=0;i<numThreads;i++) {
			pool.addComputation(new SumWorker(this));
		}
	}
	
	public void start() {
		pool.start();
		pool.join();
	}
	
	public synchronized void addToResult(Long item) {
		result += item;
	}
	
	public synchronized Long getResult() {
		return result;
	}
}
