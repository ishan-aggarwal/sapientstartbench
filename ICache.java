package com.sapient.cache;

public interface ICache<K, V> {

	public V get(K key);

	public void put(K key);

	public V remove(K key);

	public int invalidateCache();
}
