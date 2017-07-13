package com.trainoo.demo;

/**
 * 深入理解Integer源码
 * 
 * @author zhoutao
 * @date 2017年7月13日
 */
public class Demo_02 {

	/**
	 * 需要知道的是，包装类为了节省内存跟提高性能，在1.5引入了缓存功能。
	 * 所以在类加载的时候就已经创建好了这个范围的缓存，如果新建的对象在这个范围内，那么直接返回缓存里面的对象地址
	 * 
	 * Boolean (全部缓存) 
	 * Byte (全部缓存)
	 * Character (0 - 127缓存) 
	 * Short (-128 — 127缓存) 
	 * Long (-128 — 127缓存)
	 * Integer (-128 — 127缓存)
	 * 
	 * Float(没有缓存) 
	 * ﻿﻿﻿﻿﻿﻿Double(没有缓存)
	 * 
	 * @authod zhoutao
	 * @param args
	 */
	public static void main(String[] args) {
		Integer a = 1000;
		Integer b = 1000;
		System.out.println(a == b); // false
		Integer c = 100;
		Integer d = 100;
		System.out.println(c == d); // ture
	}

}
