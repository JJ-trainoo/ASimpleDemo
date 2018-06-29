package com.trainoo.crawler.novel;

import com.trainoo.ehcache.EhcacheKit;
import com.trainoo.novelReader.chapterParser.TitleMatches;
import com.trainoo.spider.Spider_Constant;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by zhoutao on 2018/6/28 13:45
 */

public class ChapterCrawler extends WebCrawler {

    private boolean isRootUrl = true;
    private List<String> chapterList = new ArrayList<>();

    private static Logger LOG = LoggerFactory.getLogger(ChapterCrawler.class);

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg|png|mp3|mp3|zip|gz))$");

    private static String rootUrl = "http://www.6mao.com/html/53/53302";

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();  // 得到小写的url
        return !FILTERS.matcher(href).matches()    // 正则匹配，过滤掉我们不需要的后缀文件
                && href.startsWith(rootUrl);       // url必须是rootUrl开头，规定站点
    }

    @Override
    public void visit(Page page) {
        // 判断是否是html数据, 不是则直接返回空
        if (!(page.getParseData() instanceof HtmlParseData)) {
            return;
        }
        String url = page.getWebURL().getURL();
        LOG.info("crawl url: {}", url);
        HtmlParseData htmlData = (HtmlParseData) page.getParseData();
        Document doc = Jsoup.parse(htmlData.getHtml());
        // 是否是第一个Url
        if(isRootUrl){
            isRootUrl = false;
            Elements lieBiaoList = doc.getElementsByClass("liebiao_bottom").first()
                    .getElementsByTag("dl").first()
                    .getElementsByTag("dd");
            for (Element element : lieBiaoList) {
                String title = element.text();
                if (TitleMatches.isZhang(title) || TitleMatches.isExtra(title)) {
                    chapterList.add(title);
                    LOG.info("chapter title：{}", title);
                }
            }
        }else{
            Element element = doc.getElementById("neirong");
            String title = doc.getElementById("content").getElementsByTag("h1").first().text();
            String content = element.text();
           // int titleNum = CNNum2ArabKit.cnNumericToArabic(title.s)
            Chapter chapter = new Chapter(title, content);
            ChapterDB.insert(chapter);
            LOG.info("insert chapter info：{}", chapter);
        }
    }

    public String subString(String str) {

        String strStart = "第";
        /* 找出指定的2个字符在 该字符串里面的 位置 */
        int strStartIndex = str.indexOf(strStart);
        int strEndIndex = str.indexOf("章");

        /* index 为负数 即表示该字符串中 没有该字符 */
        if (strStartIndex < 0) {
            LOG.error("截取章节数失败，章节：{}", str);
            return "零";
        }
        if (strEndIndex < 0) {
            LOG.error("截取章节数失败，章节：{}", str);
            return "零";
        }
        /* 开始截取 */
        String result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());
        return result;
    }

}
