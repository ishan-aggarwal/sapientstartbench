package com.sapient.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class FruitBasketTest {

	public class Fruit {

		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return this.name.length();
		}

		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof Fruit)) {
				return false;
			}
			Fruit fruit = (Fruit) obj;
			if (fruit.name.equalsIgnoreCase(this.name)) {
				return true;
			}
			return false;
		}

		public String name;

		Fruit(String name, String flavour) {
			this.name = name;
			this.flavour = flavour;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getFlavour() {
			return flavour;
		}

		public void setFlavour(String flavour) {
			this.flavour = flavour;
		}

		public String flavour;

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "Fruit { Name: " + name + ", flavour: " + flavour + " }";
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FruitBasketTest fruitBasketTest = new FruitBasketTest();
		String nextLine;
		Scanner scanner = new Scanner(System.in);
		List<Fruit> fruits = new ArrayList<>();
		int count = 0;
		while (scanner.hasNext()) {
			nextLine = scanner.nextLine();
			String[] strings = nextLine.split(" ");
			Fruit fruit = fruitBasketTest.new Fruit(strings[0], strings[1]);
			fruits.add(fruit);
//			System.out.println(fruit.hashCode());
			count++;
			if (count > 2) {
				break;
			}
		}
		System.out.println(fruits);

		Collections.sort(fruits, new Comparator<Fruit>() {

			@Override
			public int compare(Fruit o1, Fruit o2) {
				// TODO Auto-generated method stub
				return o1.name.compareTo(o2.name);
			}

		});

		System.out.println("Sorted Fruits");
		System.out.println(fruits);

		Fruit checkFruit = fruitBasketTest.new Fruit("Orange", "Sweet");
		// String fruitName = "Orange";
//		System.out.println("CheckFruit: " + checkFruit.hashCode());

		// fruits.
		System.out.println(fruits.contains(checkFruit));
		
		
		
	}
}
