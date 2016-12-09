package com.sapient.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * http://docs.oracle.com/javase/1.5.0/docs/api/java/util/concurrent/locks/Condition.html
 */
public class BlockingQueueTestUsingLockInterface {

	public class CustomBlockingQueue<T> {

		public final int MAX = 5;
		public int size;
		List<T> list;
		final Lock lock = new ReentrantLock();
		final Condition notFull = lock.newCondition();
		final Condition notEmpty = lock.newCondition();

		public CustomBlockingQueue(int size) {
			this.size = size;
			list = new ArrayList<>(size);
		}

		public void put(T element) throws InterruptedException {
			lock.lock();
			try {
				while (list.size() == MAX) {
					System.out.println("Queue is full. It contains " + MAX
							+ " elements!");
					notFull.await();
				}

				Thread.sleep(1000);
				list.add(element);
				System.out.println("Inserted element : " + element);
				notEmpty.signal();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}

		public T get() throws InterruptedException {
			T removedElement = null;
			lock.lock();
			try {
				while (list.size() == 0) {
					System.out
							.println("Queue is empty. It contains 0 elements!");
					notEmpty.await();
				}

				Thread.sleep(500);
				removedElement = list.remove(list.size() - 1);
				notFull.signal();
				return removedElement;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
			return removedElement;
		}

	}

	public static void main(String[] args) throws InterruptedException {

		BlockingQueueTestUsingLockInterface blockingQueueTest = new BlockingQueueTestUsingLockInterface();
		CustomBlockingQueue<Integer> blockingQueue = blockingQueueTest.new CustomBlockingQueue<Integer>(
				5);

		Thread writerThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 1000; i++) {
					try {
						blockingQueue.put(i);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});

		Thread readerThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						System.out.println("Removed element : "
								+ blockingQueue.get());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});

		readerThread.start();
		writerThread.start();

		// blockingQueue.put(10);
		// blockingQueue.put(20);
		// System.out.println("Removed element : " + blockingQueue.get());
		// blockingQueue.put(50);
		// blockingQueue.put(40);
		// blockingQueue.put(30);
		// System.out.println("Removed element : " + blockingQueue.get());
		// System.out.println("Removed element : " + blockingQueue.get());
		// blockingQueue.put(60);
		// blockingQueue.put(70);
		// blockingQueue.put(80);
		// blockingQueue.put(90);
		// blockingQueue.put(100);
		// blockingQueue.put(110);

	}
}
