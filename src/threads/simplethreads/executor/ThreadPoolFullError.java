package threads.simplethreads.executor;

public class ThreadPoolFullError extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4678303985971843547L;

	public ThreadPoolFullError(String error) {
		super(error);
	}

}
