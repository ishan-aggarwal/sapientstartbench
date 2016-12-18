package deadlock;

/**
 * @author iaggar MyThread thread class providing runnable functionality
 */
public class MyThread extends Thread {

	Object obj1;
	Object obj2;

	public MyThread(Object obj1, Object obj2) {
		this.obj1 = obj1;
		this.obj2 = obj2;
	}

	/*
	 * acquiring nested locks over multiple objects
	 */
	public void run() {

		System.out.println("Thread started : "
				+ Thread.currentThread().getName());
		synchronized (obj1) {
			System.out.println(Thread.currentThread().getName()
					+ " acquierd lock on object : " + obj1);
			doWork();
			synchronized (obj2) {
				System.out.println(Thread.currentThread().getName()
						+ " acquierd lock on object : " + obj2);
				doWork();
			}
			System.out.println(Thread.currentThread().getName()
					+ " released lock on object : " + obj2);
		}
		System.out.println(Thread.currentThread().getName()
				+ " released lock on object : " + obj1);
	}

	/**
	 * performing work by different threads
	 */
	private void doWork() {
		try {
			System.out.println(Thread.currentThread().getName()
					+ " started doing work!");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
