package com.sapient.cache;

public class CacheObject {

	private int data;
	private Long accessTime;

	public CacheObject(int data, Long currentTime) {
		this.data = data;
		this.accessTime = currentTime;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Long getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Long currentTime) {
		this.accessTime = currentTime;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return data;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CacheObject)) {
			return false;
		}
		CacheObject sample = (CacheObject) obj;
		if (sample.data == this.data) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "CacheObject [ data : " + data + " , accessTime : " + accessTime
				+ " ] ";
	}
}
