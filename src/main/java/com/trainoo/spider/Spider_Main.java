package com.trainoo.spider;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

/**
 * 简单的网络爬虫程序，爬小说网站内容
 * 单线程
 * @author zhout
 * @date 2017年5月10日
 */
public class Spider_Main {
	
	private static Logger logger = LogManager.getLogger(Spider_Main.class);
	// 最大爬取次数
	private static int MAX_CRAW_NUM = 10000;
	// 起始URl
	//private static String URL = "http://www.83zw.com/book/26/26879/10299158.html";  // 五行天
	//private static String URL = "http://www.83zw.com/book/37/37277/12988902.html";  // 一念永恒
	private static String URL = "http://www.83zw.com/book/0/224/6204555.html";   // 凡人修仙
	
	private static Spider_UrlManager urlManager;
	private static Spider_HtmlDownloader htmlDownload;
	private static Spider_HtmlParser htmlParser;
	private static Spider_HtmlOutput htmlOutput;

	public static void main(String[] args) throws Exception {
		// 初始化
		urlManager = new Spider_UrlManager();
		htmlDownload = new Spider_HtmlDownloader();
		htmlParser = new Spider_HtmlParser();
		htmlOutput = new Spider_HtmlOutput();
		// 开始抓取网页
		Spider_Main spider = new Spider_Main();
		long startTime = System.currentTimeMillis();
		spider.crawl(URL);
		long endTime = System.currentTimeMillis();
		logger.info("抓取完毕，用时[" + (endTime - startTime) / 1000 + "]秒");
	}
	
	public void crawl(String url){
		int count = 1; 
		logger.info(url);
		urlManager.add_new_url(url);
		while (urlManager.has_new_url()){
			try {
				String newUrl = urlManager.get_new_url();
				logger.info("抓取网页:" + count + ", " + newUrl);
				Document doc = htmlDownload.download(newUrl);              // 下载网页
				Map<String, String> map = htmlParser.parser(newUrl, doc);  // 解析网页内容
				urlManager.add_new_url(map.get(Spider_Constant.Element.URL.getValue()));  // 把新的url添加到url管理器中
				htmlOutput.collectData(map);  // 保存收集的信息
				if(count >= MAX_CRAW_NUM){
					break;
				}
				count ++;
			} catch (Exception e) {
				logger.error("抓取网页异常~", e);
			}
		}
		// 输出
		htmlOutput.outputData();
	}
}
