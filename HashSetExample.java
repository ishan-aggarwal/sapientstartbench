package com.sapient.collections;

import java.util.HashSet;
import java.util.Set;

public class HashSetExample {

	public class Employee {

		int id;
		String name;

		Employee(int id, String name) {
			this.id = id;
			this.name = name;
		}

//		@Override
//		public int hashCode() {
//			return id;
//		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof Employee)) {
				return false;
			}
			Employee emp = (Employee) obj;

			if (emp.id == this.id) {
				return true;
			}
			return false;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "Employee [ id = " + id + ", Name= " + name + " ] ";
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashSetExample hastSetExample = new HashSetExample();

		Employee e1 = hastSetExample.new Employee(1, "Ishan");
		Employee e2 = hastSetExample.new Employee(2, "Vaibhav");
		Employee e3 = hastSetExample.new Employee(1, "Hello");

		Set<Employee> s = new HashSet<>();
		s.add(e1);
		s.add(e2);
		s.add(e3);

		System.out.println(s);

	}

}
