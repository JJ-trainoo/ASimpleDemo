package com.trainoo.novel.chapterParser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.trainoo.novel.utils.EncodingDetect;

public class ChapterParser {
	
	private Logger logger = LogManager.getLogger(ChapterParser.class);
	
	// 文件路径
	private String path;
	// 文件编码
	private String charset;
	// 章节列表
	private List<TitleInfo> titleList;

	public static final int MAX_PARSE_NUMBER = 200;

	public ChapterParser(String path){
		this.path = path;
		this.charset = getCharset(path);
		titleList = new ArrayList<TitleInfo>();
	}

	/**
	 * 获取文件编码格式
	 * @author zhout
	 * @date 2017年5月15日
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String getCharset(String filePath){
		return EncodingDetect.getJavaEncode(filePath);
	}

	/**
	 * 解析章节信息
	 * 
	 * @author zhout
	 * @date 2017年5月15日
	 */
	public List<TitleInfo> parseTitleInfo() {

		long time = System.currentTimeMillis();
		int count = 0;
		BufferedReader reader = null;
		InputStreamReader inputStreamReader = null;
		FileInputStream inputStream = null;
		String line = "";
		try {
			inputStream = new FileInputStream(path);
			inputStreamReader = new InputStreamReader(inputStream, charset);
			reader = new BufferedReader(inputStreamReader);

			// 之所以设置这个变量是因为有的TXT文档会在一章的开头将标题重复一遍，造成一章内容被解析成两章
			// 所以设置一个最小行数，两个章节之间的行数差距最小为5
			int number = 5;

			TitleInfo titleInfo = new TitleInfo();
			StringBuilder builder = new StringBuilder();
			int parseLength = 0;
			while ((line = reader.readLine()) != null && count < 50) {
				line = line.trim();
				if (line.equals("")) {
					parseLength += 2;// 这里的+2是因为要加上换行的长度
					continue;
				}
				if (line.length() < 4) {
					if (number >= 5 && TitleMatches.isExtra(line)) {// 如果是额外章节
						count++;
						parseLength += builder.toString().getBytes(charset).length;
						builder.delete(0, builder.length());
						titleInfo = new TitleInfo(count, line, parseLength);
						titleList.add(titleInfo);
						number = 0;
						logger.info("检测到额外章节" + titleInfo.toString());
					}
				} else {
					if (number >= 5 && TitleMatches.isZhang(line)) {// 如果是正文章节
						count++;
						parseLength += builder.toString().getBytes(charset).length;
						builder.delete(0, builder.length());
						titleInfo = new TitleInfo(count, line, parseLength);
						titleList.add(titleInfo);
						number = 0;
						logger.info("检测到新章节" + titleInfo.toString());
					}else{
						// 如果第一行不是章节名称，那么默认一个开始章节，因为有的小说开始会有一些废话或楔子啥的
						if(parseLength == 0){
							titleInfo.setTitle("开始");
							titleInfo.setIndex(0);
							titleInfo.setStartLength(0);
							titleList.add(titleInfo);
							logger.info("书籍开始章节 : " + titleInfo.toString());
						}
					}
				}

				builder.append(line);
				parseLength += 2;
				number++;
				if (number >= MAX_PARSE_NUMBER) {
					// 为了避免某个文档一直没有匹配到新章节而不停的向StringBuilder中添加内容,导致Android内存溢出，这里对StringBuilder的大小进行了一定的限制
					// 即解析的行数达到一定的数目之后，即使没有匹配到新章节也将StringBuilder清空，同时更新parseLength。
					// 注意：这个数目的设定会影响到解析的时间，请谨慎设置!!!!
					parseLength += builder.toString().getBytes(charset).length;
					builder.delete(0, builder.length());
					number = 5;
				}
			}
		} catch (Exception e) {
			System.out.println(line);
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			logger.info("执行完毕，耗时 : " + (System.currentTimeMillis() - time) + " mm,检测到" + titleList.size() + "章");
		}
		return titleList;
	}
}
