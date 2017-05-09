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
	
	public Document download(String newUrl) throws RuntimeException{
		try {
			return Jsoup.connect(newUrl).get();
		} catch (IOException e) {
			try {
				logger.error("第1次抓取失败，正在尝试第2次重连...");
				return Jsoup.connect(newUrl).get();
			} catch (IOException e1) {
				try {
					logger.error("第2次抓取失败，正在尝试第3次重连...");
					return Jsoup.connect(newUrl).get();
				} catch (IOException e2) {
					logger.error("连接超时，网页抓取失败...");
					throw new RuntimeException("网络连接超时，请重试！");
				}
			}
		}
	}
}
