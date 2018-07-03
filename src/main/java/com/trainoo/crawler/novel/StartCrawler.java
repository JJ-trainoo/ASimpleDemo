package com.trainoo.crawler.novel;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhoutao on 2018/6/28 14:17
 */

public class StartCrawler {

    private static Logger LOG = LoggerFactory.getLogger(StartCrawler.class);

    private static int numberOfCrawlers = 1;
    private static int maxDepthOfCrawling = 1;
    private static String crawlStorageFolder = "E:/crawl";
    private static String URL = "http://www.6mao.com/html/53/53302/index.html";

    public void start() throws Exception {
        long startTime = System.currentTimeMillis();
        CrawlConfig config = new CrawlConfig();           // 定义爬虫配置
        config.setCrawlStorageFolder(crawlStorageFolder); // 设置爬虫文件存储位置
        config.setMaxDepthOfCrawling(maxDepthOfCrawling); // 设置爬虫爬取深度

        PageFetcher pageFetcher = new PageFetcher(config);       // 实例化页面获取器
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig(); // 实例化爬虫机器人配置 比如可以设置 user-agent

        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        controller.addSeed(URL);
        controller.start(ChapterCrawler.class, numberOfCrawlers);
        long endTime = System.currentTimeMillis();
        LOG.info("爬取数据成功，总计用时：{}s", (endTime - startTime)/1000);
    }

    public static void main(String[] args) {
        try {
            new StartCrawler().start();
        } catch (Exception e) {
            LOG.error("启动爬虫失败，异常信息：{}", e);
        }
    }
}
