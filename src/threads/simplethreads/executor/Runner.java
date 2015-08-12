package threads.simplethreads.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Runner {

	/**
	 * @param args
	 * @throws ThreadPoolFullError 
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws ThreadPoolFullError, InterruptedException, ExecutionException {
		List<Integer> integerList = new ArrayList<Integer>();
		for(int i=0;i<10000000;i++) {
			integerList.add((int) Math.round(Math.random() * 100 + 1));
		}
		/*Integer[] integerArray = {24, 69, 40, 89, 18, 54, 86, 73, 7, 28};
		List<Integer> integerList = Arrays.asList(integerArray);*/
//		System.out.println("Integer list is: " + integerList.toString());
		int nThreads = Runtime.getRuntime().availableProcessors();
		ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
		List<Callable<Long>> tasks = new ArrayList<Callable<Long>>();
		long startMillis = System.currentTimeMillis();
		SumDistributor sumD = new SumDistributor(integerList, nThreads);
		for(int i=0;i<nThreads;i++) {
			tasks.add(new SumWorker(sumD.getNextPartition()));
		}
		List<Future<Long>> resultList = new ArrayList<Future<Long>>();
		
		
		try {
			resultList = executorService.invokeAll(tasks);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		executorService.shutdown();
		long endMillis = System.currentTimeMillis();
		
		Long finalResult = 0L;
		System.out.println("Individual sums:");
		for(Future<Long> future : resultList) {
			System.out.println(future.get() + " ");
			
			finalResult += future.get();
		}
		
		System.out.println("Sum is " + finalResult + " in time " + (endMillis - startMillis));

	}

}
