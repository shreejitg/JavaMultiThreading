package threads.simplethreads.executor;

import java.util.List;
import java.util.concurrent.Callable;

public class SumWorker implements Callable {
	List<Integer> integerList;
	public SumWorker(List<Integer> integerList) {
		this.integerList = integerList;
	}
	
	@Override
	public Long call() throws Exception {
		Long result = 0L;
		for(Integer item : integerList) {
			result += item;
		}
		return result;
	}
	

}
