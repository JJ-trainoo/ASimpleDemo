package com.trainoo.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

public class MyTest1 {
	
	public static void main(String[] args) {
		int m = 17 << 2;
		System.out.println(m);
		
		System.out.println(0.2+0.1);
		/*ArrayList list = new ArrayList<>();
		LinkedList llist = new LinkedList<>();
		Vector<String> v = new Vector<String>();
		v.add("a");
		v.add("b");
		v.add("c");
		
		for (Iterator<String> iterator = v.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
		
		HashMap m1 = new HashMap<>();
		Hashtable m2 = new Hashtable<>();*/
		
		//静态内部类跟内部类的区别，一个是需要依赖外部类实例，一个不需要依赖外部实例
		new MyTest1().new A().fun1();
		new B().fun1();
		
	}

	class A{
		public void fun1(){
			System.out.println("A...");
		}
	}
	
	static class B{
		public void fun1(){
			System.out.println("B...");
		}
	}
	
}

