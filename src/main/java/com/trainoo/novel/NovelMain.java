package com.trainoo.novel;

import java.io.File;
import java.util.List;

import com.trainoo.novel.chapterParser.ChapterParser;
import com.trainoo.novel.chapterParser.TitleInfo;
import com.trainoo.novel.pageParser.PageParser;
import com.trainoo.novel.utils.EncodingDetect;

public class NovelMain {

	public static void main(String[] args) throws Exception {
		
		String path = "C:/Users/Administrator/Desktop/Book/book01.txt";
		String charset = EncodingDetect.getJavaEncode(path);
		// NovelParser parser = new
		// NovelParser("C:/Users/Administrator/Desktop/Book/book02.txt");

		ChapterParser parser = new ChapterParser(path, charset);
		List<TitleInfo> title = parser.parseTitleInfo();
		
		int chapter = 28;
		String endString = "END";
		if(chapter >= title.size() || chapter < 0){
			System.out.println("error~");
			return;
		}
		if(chapter + 1 < title.size()){
			endString = title.get(chapter+1).getTitle();
		}
		PageParser pageParser = new PageParser();
		pageParser.parser(new File(path), charset, title.get(chapter).getStartLength(), endString);
		pageParser.outputImage(0);
	}
}
