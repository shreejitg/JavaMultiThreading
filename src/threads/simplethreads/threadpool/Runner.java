package threads.simplethreads.threadpool;

import java.util.ArrayList;
import java.util.List;

public class Runner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> integerList = new ArrayList<Integer>();
		for(int i=0;i<10000000;i++) {
			integerList.add((int) Math.round(Math.random() * 100 + 1));
		}
		/*Integer[] integerArray = {24, 69, 40, 89, 18, 54, 86, 73, 7, 28};
		List<Integer> integerList = Arrays.asList(integerArray);*/
//		System.out.println("Integer list is: " + integerList.toString());
		SumDistributor sumD = null;
		long startMillis = System.currentTimeMillis();
		try {
			sumD = new SumDistributor(integerList, Runtime.getRuntime().availableProcessors());
		} catch (ThreadPoolFullError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sumD.start();
		long endMillis = System.currentTimeMillis();
		
		System.out.println("Sum is " + sumD.getResult() + " in time " + (endMillis - startMillis));

	}

}
