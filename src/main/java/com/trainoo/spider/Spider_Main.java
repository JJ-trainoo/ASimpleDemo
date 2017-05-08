package com.trainoo.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Spider_Main {

	public static void main(String[] args) throws Exception {
		// init start url
		String url = "http://m.83zw.com/book/26/26879/10299158.html";
		Spider_Main spider = new Spider_Main();
		spider.craw(url);
	}
	
	public void craw(String url){
		
	}
}
