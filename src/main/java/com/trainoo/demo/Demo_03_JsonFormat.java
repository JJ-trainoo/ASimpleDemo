package com.trainoo.demo;

import org.junit.Test;

/**
 * 格式化输出给定的一个json字符串
 * 
 * @author zhoutao
 * @date 2017年8月5日
 */
public class Demo_03_JsonFormat {

	private static String JSON = "[{\"CityId\":,\"CityName\":\"西安\",\"ProvinceId\":27,\"CityOrder\":1},{\"CityId\":53,\"CityName\":\"广州\",\"ProvinceId\":27,\"CityOrder\":{\"CityId\":53,\"CityName\":\"广州\",\"ProvinceId\":27,\"CityOrder\":1}}]";
	
	private static final char NEWLINE = '\n';
	private static final char TABLE = '\t';
	private static final char COLON = ':';
	private static final char SPACE = ' ';
	
	public static String jsonFormat(String jsonStr){
		// 空串直接返回
		if( null == jsonStr || "".equals(jsonStr))
			return "";
		// 判断是否是正确是json格式，不是的话可以输出异常信息
		try {
			com.alibaba.fastjson.JSON.parse(jsonStr);
		} catch (Exception e) {
			System.out.println(e);
		}
		// 格式化json字符串
		return format(jsonStr);
	}

	/**
	 * 格式化输出：换行、缩进
	 * ‘{’ -> append、然后换行、缩进
	 * ‘}’ -> 先换行、缩进，然后append
	 * ':' -> 冒号后面加空格
	 * ‘ ’ -> 忽略空格
	 * @authod zhoutao
	 * @param jsonStr
	 * @return
	 */
	private static String format(String jsonStr) {
		StringBuilder sb = new StringBuilder();
		int level = 0;
		for(int i = 0; i < jsonStr.length(); i++){
			char ch = jsonStr.charAt(i);
			switch (ch) {
			case '{':
				level ++;
				append(sb, ch, level);
				break;
			case '}':
				level --;
				_append(sb, ch, level);
				break;
			case ',':
				append(sb, ch, level);
				break;
			case ' ':
				break;
			default:
				append_default(sb, ch);
				break;
			}
		}
		return sb.toString();
	}

	private static void append(StringBuilder sb, char ch, int level) {
		sb.append(ch).append(NEWLINE);
		for(int i = 0; i < level; i++){
			sb.append(TABLE);
		}
	}
	
	private static void _append(StringBuilder sb, char ch, int level) {
		sb.append(NEWLINE);
		for(int i = 0; i < level; i++){
			sb.append(TABLE);
		}
		sb.append(ch);
	}
	
	private static void append_default(StringBuilder sb, char ch) {
		sb.append(ch);
		if(COLON == ch) {
			sb.append(SPACE);
		}
	}

	@Test
	public void outputConsole(){
		System.out.println(jsonFormat(JSON));
	}
}
