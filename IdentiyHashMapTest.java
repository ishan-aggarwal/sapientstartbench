package com.sapient.collections;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/*
 * Where you want to put values against references not the key values.
 */

public class IdentiyHashMapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<>();
		Map<String, String> identityMap = new IdentityHashMap<>();

		map.put(new String("Ishan"), new String("Aggarwal"));
		map.put(new String("Ishan1"), new String("Aggarwal"));
		map.put(new String("Ishan"), new String("Aggarwal11"));

		identityMap.put("Vaibhav", "Jain");
		identityMap.put("Vaibhav", "Aggarwal1");
		identityMap.put(new String("Ishan"), new String("Aggarwal"));
		identityMap.put(new String("Ishan"), new String("Aggarwal"));

		System.out.println("Map : " + map);
		System.out
				.println("==================================================================================");
		System.out.println("Identity HashMap : " + identityMap);
		System.out.println(identityMap.get("Vaibhav"));
	}
}
