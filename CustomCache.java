package com.sapient.collections;

import java.util.Iterator;
import java.util.Map;

public class CustomCache<K, V> {

	private long timeToLive;
	private Map<K, CacheObject> myCacheObjectMap;

	class CacheObject<T> {
		public long lastAccessed = System.currentTimeMillis();
		public T value;

		protected CacheObject(T value) {
			this.value = value;
		}
	}

	public CustomCache(long timeToLive) {
		this.timeToLive = timeToLive * 1000;
	}

	public synchronized void put(K key, V value) {
		myCacheObjectMap.put(key, new CacheObject(value));
	}

	public synchronized <T> T get(K key) {
		CacheObject c = (CacheObject) myCacheObjectMap.get(key);
		if (c == null) {
			return null;
		} else {
			c.lastAccessed = System.currentTimeMillis();
			return (T) c.value;
		}
	}

	public synchronized void remove(K key) {
		myCacheObjectMap.remove(key);
	}

	public void cleanup() {
		long currentTime = System.currentTimeMillis();
		Iterator itr = myCacheObjectMap.entrySet().iterator();

		while (itr.hasNext()) {
			String key = (String) itr.next();
			CacheObject c = myCacheObjectMap.get(key);

			if (c != null && (currentTime > (timeToLive + c.lastAccessed))) {
				myCacheObjectMap.remove(key);
			}
		}
	}
}
