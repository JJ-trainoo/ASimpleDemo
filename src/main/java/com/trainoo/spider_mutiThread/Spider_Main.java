package com.trainoo.spider_mutiThread;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

public class Spider_Main {

	private static Logger logger = LogManager.getLogger(Spider_Main.class);

	// 起始URl
	// private static String URL = "http://www.83zw.com/book/24/24595/";

	private static Spider_UrlManager urlManager;
	private static Spider_HtmlDownloader htmlDownload;
	private static Spider_HtmlParser htmlParser;
	private static Spider_HtmlOutput htmlOutput;

	public static void main(String[] args) throws Exception {
		if (args.length <= 0) {
			logger.error("参数错误");
			return;
		}
		// 起始url
		String URL = args[0];
		
		// 初始化各模块
		urlManager = new Spider_UrlManager();
		htmlDownload = new Spider_HtmlDownloader();
		htmlParser = new Spider_HtmlParser();
		
		// 获取全部章节的url
		Document doc = htmlDownload.download(URL);
		List<String> listUrl = htmlParser.parserUrl(URL, doc);
		String fileName = htmlParser.parserFileName(doc);
		urlManager.add_new_urls(listUrl);

		// 开始抓取网页
		Spider_Main spider = new Spider_Main();
		spider.crawl(URL, fileName);
	}

	public void crawl(String url, String fileName) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		long startTime = System.currentTimeMillis();

		logger.info("==============开始================");
		Spider_UrlManager um = new Spider_UrlManager();
		int countUrl = urlManager.getNewUrlSize();
		int countThread = 1;
		for (int i = 1; i <= countUrl; i++) {
			String newUrl = urlManager.get_new_url();
			um.add_new_url(newUrl);
			if (i % 10 == 0 || urlManager.getNewUrlSize() == 0) {
				crawlThread thread = new crawlThread(um, countThread, fileName);
				countThread++;
				um = new Spider_UrlManager();
				exec.execute(thread);
			}
		}
		exec.shutdown();
		htmlOutput = new Spider_HtmlOutput(fileName);
		while (true) {
			if (exec.isTerminated()) {
				long endTime = System.currentTimeMillis();
				logger.info("抓取完毕，用时[" + (endTime - startTime) / 1000 + "]秒");
				htmlOutput.mergeFiles(fileName);
				break;
			}
			Thread.sleep(1000);
		}
	}

	class crawlThread implements Runnable {

		private Spider_UrlManager urlManager = new Spider_UrlManager();
		private Spider_HtmlDownloader htmlDownload = new Spider_HtmlDownloader();
		private Spider_HtmlParser htmlParser = new Spider_HtmlParser();
		private Spider_HtmlOutput htmlOutput = null;

		int threadNum = 0;

		crawlThread(Spider_UrlManager um, int threadNum, String fileName) {
			this.urlManager = um;
			this.threadNum = threadNum;
			this.htmlOutput = new Spider_HtmlOutput(fileName);
		}

		@Override
		public void run() {
			while (urlManager.has_new_url()) {
				try {
					String newUrl = urlManager.get_new_url();
					logger.info("抓取网页:" + newUrl);
					Document doc = htmlDownload.download(newUrl); // 下载网页
					Map<String, String> map = htmlParser.parser(newUrl, doc); // 解析网页内容
					htmlOutput.collectData(map); // 保存收集的信息
				} catch (Exception e) {
					logger.error("抓取网页异常~", e);
				}
			}
			htmlOutput.outputData(threadNum);
		}

	}
}
