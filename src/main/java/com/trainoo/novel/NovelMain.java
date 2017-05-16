package com.trainoo.novel;

import java.io.RandomAccessFile;
import java.util.List;

import com.trainoo.novel.chapterParser.ChapterParser;
import com.trainoo.novel.chapterParser.TitleInfo;

public class NovelMain {

	public static void main(String[] args) throws Exception {
		String path = "C:/Users/Administrator/Desktop/凡人修仙传__作者：忘语/心理罪：画像Ⅰ[精校版]（全）.txt";
		// NovelParser parser = new
		// NovelParser("C:/Users/Administrator/Desktop/凡人修仙传__作者：忘语/凡人.txt");

		/*ChapterParser parser = new ChapterParser(path);
		List<TitleInfo> list = parser.parseTitleInfo();*/
		
		RandomAccessFile readFile = new RandomAccessFile(path, "r");
		readFile.seek(18400);
		while(true){
			System.out.println(new String(readFile.readLine().getBytes("ISO-8859-1"), "GBK"));
			Thread.sleep(5000);
		}

		/*PageParser parser = new PageParser(new File(path), 0);
		String str = parser.getNext();
		System.out.println(str);
		System.out.println();
		System.out.println();
		String str1 = parser.getNext();
		System.out.println(str1);
		System.out.println();
		System.out.println();
		String str2 = parser.getNext();
		System.out.println(str2);*/
		
	}
}
