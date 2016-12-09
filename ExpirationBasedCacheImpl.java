package com.sapient.cache;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ExpirationBasedCacheImpl<K, V> implements ICache<K, V> {

	private long ttl;
	private Map<K, Object> cacheMap;
	private final Object object = new Object();

	public ExpirationBasedCacheImpl(int size, long ttl) {
		this.ttl = ttl;
		cacheMap = new ConcurrentHashMap<>(size);
	}

	public void setCacheMap(Map<K, Object> cacheMap) {
		this.cacheMap = cacheMap;
	}

	@Override
	public int invalidateCache() {
		System.out.println("Clean up called...");
		int numberOfElementsRemoved = 0;
		// System.out.println("Size: " + cacheMap.size());
		if (cacheMap.size() == 0) {
			System.out.println("All elements removed.");
		} else {
			Long currentSystemTime = System.currentTimeMillis();
			@SuppressWarnings({ "unchecked" })
			Set<CacheObject> keySet = (Set<CacheObject>) cacheMap.keySet();
			for (CacheObject key : keySet) {
				Long lastAccessTime = key.getAccessTime();
				if (Math.abs(currentSystemTime - lastAccessTime) > ttl) {
					cacheMap.remove(key);
					numberOfElementsRemoved++;
					System.out.println("Cleaning on KEY : " + key);
				}
			}
		}
		return numberOfElementsRemoved;
	}

	@Override
	public void put(K key) {
		System.out.println("Putting Element : " + key);
		cacheMap.put(key, object);
	}

	@Override
	public V remove(K key) {
		System.out.println("Removed called on Element : " + key);
		cacheMap.remove(key);
		return null;
	}

	@Override
	public V get(K obj) {
		Object c = cacheMap.get(obj);
		System.out.println("Accessed Element: " + obj);
		if (c == null) {
			return null;
		} else {
			CacheObject cacheObj = (CacheObject) obj;
			cacheObj.setAccessTime(System.currentTimeMillis());
			return (V) c;
		}
	}

	public Map<K, Object> getCacheMap() {
		// TODO Auto-generated method stub
		return cacheMap;
	}
}
