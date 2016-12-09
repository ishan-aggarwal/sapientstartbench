package com.sapient.collections;

import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Map<String, String> map = new WeakHashMap<>();
		map.put(new String("Ishan"), "Aggarwal");
		map.put(new String("Vaibhav"), "Aggarwal");
		String s2 = new String("Suhas");
		map.put(s2, "Value");
		map.put("Value111", "Value111");
		System.gc();
		System.out.println("Weak HashMap : " + map);
	}

}
