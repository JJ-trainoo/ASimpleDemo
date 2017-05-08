package com.trainoo.spider;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 抓取网页内容
 * @author zhoutao
 * @date 2017年5月8日
 */
public class Spider_HtmlDownloader {

	public Document download(String newUrl) throws IOException {
		return Jsoup.connect(newUrl).get();
	}
}
