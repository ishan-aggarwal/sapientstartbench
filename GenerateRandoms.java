package com.sapient.collections;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GenerateRandoms {

	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < 15; i++) {
			set.add(getRandomNumberInRange(0, 10));
		}
		
		System.out.println(set.size());
		System.out.println("set : " + set);

	}

}
