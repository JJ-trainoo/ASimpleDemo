package com.trainoo.novelReader.chapterParser;

import java.util.regex.Pattern;

/**
 * 这个类用来判断某一行是否为新章节
 * 判定条件:
 * 1.如果是新章节则必定以"第"开头，且至少包含关键字数组key中的一个元素，且"第"到该关键字中的内容匹配正则p
 * 2.如果是额外章节，则其单行长度(去掉空格之后)不得超过3，且至少满足下列条件中的一条
 * 	a.其第一个或者第二个字为"序"(e.g.序,序言，序章，魔序)且字符长度不超过2
 * 	b.以extra_key_start关键字数组中任意一项开头(e.g.前言，附录1，后记1)
 */
public class TitleMatches {

	// 匹配的优先度依次递减
	public static final String[] KEY = { "部", "卷", "章", "节", "集", "回", "幕", "计" };
	public static final String REGEX = "^[第][0-9零一二两三四五六七八九十百千]+[部卷章节集回幕计][\\s|　| ]$";

	public static void main(String[] args) {
		
		String str1 = "第7章 开卷有喜";  // 此处有瑕疵，返回false
		String str = "正文 第两千零四章 百转迁回";

		System.out.println(isZhang(str1) + "--" + str1);
		System.out.println(isZhang(str) + "--" + str);
	}
	/**
	 * 是否正文章节
	 * @author zhout
	 * @date 2017年5月15日
	 * @param line
	 * @return
	 */
	public static boolean isZhang(String line) {
		// 判断是否含有“第”关键字
		String regEx = ".*第.*";
		if (!Pattern.compile(regEx).matcher(line).matches()) {
			return false;
		}
		// 得到“第”字的位置
		int start = line.indexOf("第");
		// 判断是否包含KEY中关键字
		int index = -1;
		for (int i = 0; i < KEY.length; i++) {
			index = line.indexOf(KEY[i]);
			if (index != -1) {
				// 保证"第"在前，key在后，防止《正文   卷一  第一千零一回》这种情况
				if(start != -1 && index < start){
					continue;
				}else{
					break;
				}
			}
		}
		// 如果“第”后面没有跟KEY，那么返回false
		if (index == -1 || index < start) {
			return false;
		}
		// 截取“第”到KEY+1部分，KEY需要是空格
		index = line.length() < index + 2 ? index + 1 : index + 2;
		line = line.substring(start, index);
		//System.out.println(line);
		return Pattern.compile(REGEX).matcher(line).matches();
	}

	public static final String[] extra_key = { "序" };
	public static final String[] extra_key_start = { "前言", "后记", "楔子", "附录", "外传" };

	/**
	 * 是否额外章节
	 * @author zhout
	 * @date 2017年5月15日
	 * @param line
	 * @return
	 */
	public static boolean isExtra(String line) {
		if (line.length() > 3) {
			return false;
		}
		int index = line.indexOf(extra_key[0]);
		if (index != -1) {
			return (index == 0 || index == 1) && line.length() <= 2;
		} else {
			for (int i = 0; i < extra_key_start.length; i++) {
				if (line.startsWith(extra_key_start[i])) {
					return true;
				}
			}
			return false;
		}
	}
}