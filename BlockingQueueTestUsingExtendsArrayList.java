package com.sapient.collections;

import java.util.ArrayList;
import java.util.List;

public class BlockingQueueTestUsingExtendsArrayList {

	public class CustomBlockingQueue<T> extends ArrayList<T> {

		/**
		 * 
		 */
		private static final long serialVersionUID = -6986812872185274822L;
		// public final int MAX = 5;
		public int size;
//		List<T> list;

		public CustomBlockingQueue(int size) {
			super(size);
			this.size = size;
//			list = new ArrayList<>(size);
		}

		public synchronized void put(T element) throws InterruptedException {
			while (size() == this.size) {
				System.out.println("Queue is full. It contains " + this.size
						+ " elements!");
				wait();
			}

			Thread.sleep(1000);
			add(element);
			System.out.println("Inserted element : " + element);
			notifyAll();
		}

		public synchronized T get() throws InterruptedException {
			while (size() == 0) {
				System.out.println("Queue is empty. It contains 0 elements!");
				wait();
			}

			Thread.sleep(500);
			T removedElement = remove(size() - 1);
			notifyAll();
			return removedElement;
		}

	}

	public static void main(String[] args) throws InterruptedException {

		BlockingQueueTestUsingExtendsArrayList blockingQueueTest = new BlockingQueueTestUsingExtendsArrayList();
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
