package com.trainoo.spider_mutiThread;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 抓取网页内容
 * @author zhoutao
 * @date 2017年5月8日
 */
public class Spider_HtmlDownloader {

	private Logger logger = LogManager.getLogger(Spider_HtmlDownloader.class);
	private final static int RETRY = 5; //默认5次重连

	public Document download(String newUrl) throws RuntimeException{
		return download(newUrl, RETRY);
	}

	/**
	 * 网络超时情况下，采用重连的策略
	 * @param newUrl 爬url
	 * @param n  重连次数
	 * @return Document
	 * @throws RuntimeException
     */
	private Document download(String newUrl, int n) throws RuntimeException{
		if(n > 1) {
			try {
				return Jsoup.connect(newUrl).get();
			} catch (IOException e) {
				logger.error(String.format("第%d次抓取失败，正在尝试第%d次重连...", RETRY - n + 1, RETRY - n + 2));
				return download(newUrl, n - 1);
			}
		}else{
			throw new RuntimeException("网络连接超时，请重试！");
		}
	}
}
