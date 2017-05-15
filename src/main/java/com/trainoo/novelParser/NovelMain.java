package com.trainoo.novelParser;

import java.io.RandomAccessFile;
import java.util.List;

public class NovelMain {
	
	public static void main(String[] args) {
		String path = "C:/Users/Administrator/Desktop/凡人修仙传__作者：忘语/心理罪：画像Ⅰ[精校版]（全）.txt";
		//NovelParser parser = new NovelParser("C:/Users/Administrator/Desktop/凡人修仙传__作者：忘语/凡人.txt");
		
		NovelParser parser = new NovelParser(path);
		List<TitleInfo> list = parser.parseTitleInfo();
		
		long a = list.get(3).getStartLength();
		
	}
}
