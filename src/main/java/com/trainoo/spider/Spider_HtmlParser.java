package com.trainoo.spider;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

/**
 * html内容解析
 * @author zhoutao
 * @date 2017年5月8日
 */
public class Spider_HtmlParser {
	
	Logger logger = LogManager.getLogger(Spider_HtmlParser.class);
	
	public String getNewUrl(String url, Document doc){
		String prefix = url.substring(0, url.lastIndexOf("/"));
		String content = doc.getElementById("home").nextElementSibling().attr("href");
		return prefix + "/" + content;
	}

	public Map<String, String> parser(String url, Document doc) {
		if(null == doc){
			return null;
		}
		// 返回结果Map
		Map<String, String> resultMap = new HashMap<String, String>();
		// 获取新的url
		String newUrl = getNewUrl(url, doc);
		// 获取章节标题跟内容
		String title = doc.getElementById("chapers_title").getElementsByTag("h1").html();
		String content = doc.getElementById("chapter_content").html()
				.replace("<br>", "\n")                  // 除去网页里面的换行符
				.replace("&nbsp;", "")                  // 除去网页中的空格
				.replace("(83中文网 www.83zw.com)", "");  // 除去内容中的广告链接
		// 保存结果
		resultMap.put(Spider_Constant.Element.URL.getValue(), newUrl);
		resultMap.put(Spider_Constant.Element.TITLE.getValue(), title);
		resultMap.put(Spider_Constant.Element.CONTENT.getValue(), content);
		return resultMap;
	}

}
