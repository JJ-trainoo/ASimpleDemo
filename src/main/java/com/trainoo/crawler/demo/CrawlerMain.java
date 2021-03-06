package com.trainoo.crawler.demo;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * Created by Zt on 2018/5/29.
 */
public class CrawlerMain {

    private static final String URL = "http://www.6mao.com/html/53/53302/index.html";

    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "E:/crawl"; // 定义爬虫数据存储位置
        int numberOfCrawlers = 1;               // 定义爬虫线程数

        CrawlConfig config = new CrawlConfig();           // 定义爬虫配置
        config.setCrawlStorageFolder(crawlStorageFolder); // 设置爬虫文件存储位置

        /**
         * 实例化爬虫控制器
         */
        PageFetcher pageFetcher = new PageFetcher(config);       // 实例化页面获取器
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig(); // 实例化爬虫机器人配置 比如可以设置 user-agent

        /**
         * 实例化爬虫机器人对目标服务器的配置，每个网站都有一个robots.txt文件
         * 规定了该网站哪些页面可以爬，哪些页面禁止爬
         * 该类是对robots.txt规范的实现
         */
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        // 实例化爬虫控制器
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /**
         * 配置爬虫种子页面，就是规定的从哪里开始爬，可以配置多个种子页面
         */
        controller.addSeed(URL);

        /**
         * 启动爬虫，爬虫从此刻开始执行爬虫任务，根据以上配置
         */
        controller.start(MyCrawler.class, numberOfCrawlers);
    }
}
