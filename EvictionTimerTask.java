package com.sapient.cache;

import java.util.TimerTask;

public class EvictionTimerTask<K, V> extends TimerTask {

	ExpirationBasedCacheImpl<K, V> cacheObject;

	public EvictionTimerTask(ExpirationBasedCacheImpl<K, V> cacheImplObject) {
		this.cacheObject = cacheImplObject;
	}

	@Override
	public void run() {
		cacheObject.invalidateCache();
	}
}
