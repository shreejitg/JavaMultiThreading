package threads.simplethreads.threadpool;

import java.util.ArrayList;
import java.util.List;


public class ThreadPool {
	private static ThreadPool instance;
	List<Thread> threadPool;
	int MAX_THREADS = 30;
	
	public ThreadPool() {
		threadPool = new ArrayList<Thread>();
	}
	
	public synchronized static ThreadPool getInstance() {
		if(instance == null) {
			return new ThreadPool();
		} else {
			return instance;
		}
	}
	
	public void start() {
		for(Thread thread : threadPool) {
			thread.start();
		}
	}
	
	public void join() {
		for(Thread thread : threadPool) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void addComputation(Runnable computation) throws ThreadPoolFullError {
		if(threadPool.size() < MAX_THREADS) {
			Thread newThread = new Thread(computation);
			threadPool.add(newThread);
		} else if (threadPool.size() == MAX_THREADS) {
			runGarbageCollector();
			Thread newThread = new Thread(computation);
			threadPool.add(newThread);
		} else {
			throw new ThreadPoolFullError("ThreadPool is full!");
		}
		
	}
	
	public void runGarbageCollector() {
		System.out.println("Garbage collector running!");
		for(int i=0;i<threadPool.size();i++) {
			if(!threadPool.get(i).isAlive()) {
				threadPool.remove(i);
			}
		}
	}

}
