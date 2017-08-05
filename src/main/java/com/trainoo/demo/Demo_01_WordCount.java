package com.trainoo.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * 统计出现次数最多的单词
 * 需求：
 * 	给定一个文本，统计里面出现次数最多的单词
 * 	1.忽略大小写
 * 	2.假定文中只有，。？ 以及空格 四种字符需要处理。
 *  eg:  如：“How are you, how is everything going?”
 *		 return： how
 * @author zhoutao
 * @date 2017年7月13日
 */
public class Demo_01_WordCount {

	public static String maxCountWord(String text) {
		// 分割字符串,此处为了方便， 使用多次replace简单处理
		String[] wordList = text.replace(",", " ").replace(".", " ").replace("?", " ").split(" ");
		// 单词做key 次数作为 value 存入map
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < wordList.length; i++) {
			String word = wordList[i].toLowerCase();
			if(null == map.get(word)){
				map.put(word, 1);
			}else{
				map.put(word, map.get(word) + 1);
			}
		}
		//System.out.println(map);
		// 遍历map,取出出现最多次的单词
		int maxCount = 0;
		String result = "";
		for (String key : map.keySet()){
			if(maxCount < map.get(key) && !"".equals(key)){
				maxCount = map.get(key);
				result = key;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		String text = "How are you, i am fin thank you, and you?";
		System.out.println(maxCountWord(text));
	}

}
