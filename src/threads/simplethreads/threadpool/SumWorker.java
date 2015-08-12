package threads.simplethreads.threadpool;

import java.util.List;

public class SumWorker implements Runnable {
	
	public SumWorker(SumDistributor distributor) {
		
		List<Integer> list = distributor.getNextPartition();
//		System.out.println("Working on partition: " + list.toString());
		Long result = 0L;
		for(Integer item : list) {
			result += item;
		}
		distributor.addToResult(result);
	}
	
	public void run() {
		
	}
	

}
