package com.sapient.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainClass {

	public static void main(String[] args) {

		MainClass testCache = new MainClass();

		ExpirationBasedCacheImpl<CacheObject, Object> expiryCache = new ExpirationBasedCacheImpl<>(
				10, 3600);

		CacheObject cacheObject = null;
		List<CacheObject> cacheList = new ArrayList<>(20);

		for (int i = 0; i < 15; i++) {
			cacheObject = generateSampleObject(i);
			cacheList.add(cacheObject);
			expiryCache.put(cacheObject);
			System.out.println("Inserted Element : " + cacheObject);
		}

		Map<CacheObject, Object> cacheMap = expiryCache.getCacheMap();
		System.out.println("Initial Size: " + cacheMap.size());

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// starting eviction timer task
		testCache.startTimerTask(expiryCache);

		System.out.println("Size after timer start: " + cacheMap.size());

		accessElements(expiryCache, cacheList);

		System.out.println("Size after access elements: " + cacheMap.size());

		removeElements(expiryCache, cacheList);

		System.out.println("Size after remove elements: " + cacheMap.size());

	}

	private static void removeElements(
			ExpirationBasedCacheImpl<CacheObject, Object> expiryCache,
			List<CacheObject> cacheList) {
		expiryCache.remove(cacheList.get(7));
	}

	private static void accessElements(
			ExpirationBasedCacheImpl<CacheObject, Object> expiryCache,
			List<CacheObject> cacheList) {
		for (int i = 2; i <= 6; i = i + 2) {
			expiryCache.get(cacheList.get(i));
			// expiryCache.get(cacheList.get(0));
			// expiryCache.get(cacheList.get(6));
		}
	}

	private void startTimerTask(
			ExpirationBasedCacheImpl<CacheObject, Object> cacheImplObject) {
		@SuppressWarnings("unchecked")
		TimerTask timerTask = new EvictionTimerTask(cacheImplObject);
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 2000, 30 * 100);
		System.out.println("EvictionTimerTask started");
	}

	private static CacheObject generateSampleObject(int element) {
		Long currentTime = System.currentTimeMillis();
		CacheObject cacheObject = new CacheObject(element, currentTime);
		return cacheObject;
	}
}
