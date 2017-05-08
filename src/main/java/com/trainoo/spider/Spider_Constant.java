package com.trainoo.spider;

/**
 * 常量
 * @author zhoutao
 * @date 2017年5月8日
 */
public class Spider_Constant {

	public enum Element{
		// 章节标题
		TITLE("title"),
		// 正文
		CONTENT("content"),
		// 下一章url
		URL("url");
		
		private String value;
		
		Element(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
	}
}
