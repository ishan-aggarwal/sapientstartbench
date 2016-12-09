package com.sapient.collections;

import java.util.ArrayList;
import java.util.List;

public class BlockingQueueTest {

	public class CustomBlockingQueue<T> {

		public final int MAX = 5;
		public int size;
		List<T> list;

		public CustomBlockingQueue(int size) {
			this.size = size;
			list = new ArrayList<>(size);
		}

		public /*synchronized*/ void put(T element) throws InterruptedException {
			while (list.size() == MAX) {
				synchronized (list) {
					System.out.println("Queue is full. It contains " + MAX
							+ " elements!");
					list.wait();
				}
			}

			Thread.sleep(1000);
			synchronized (list) {
				list.add(element);
				System.out.println("Inserted element : " + element);
				list.notifyAll();
			}
		}

		public /*synchronized*/ T get() throws InterruptedException {
			while (list.size() == 0) {
				synchronized (list) {
					System.out
							.println("Queue is empty. It contains 0 elements!");
					list.wait();
				}
			}

			Thread.sleep(500);
			synchronized (list) {
				T removedElement = list.remove(list.size() - 1);
				list.notifyAll();
				return removedElement;
			}
		}

	}

	public static void main(String[] args) throws InterruptedException {

		BlockingQueueTest blockingQueueTest = new BlockingQueueTest();
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
