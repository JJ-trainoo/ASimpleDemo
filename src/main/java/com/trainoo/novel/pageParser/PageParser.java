package com.trainoo.novel.pageParser;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class PageParser {

	private static final int MARGIN = 50;
	private static int WIDTH = 320;
	private static int HEIGHT = 480;
	private static int FONT_SIZE = 20;
	private static int LINE_HEIGHT = 40;
	private static int PAGE_SIZE = 0;
	private static int COL_SIZE = 0;
	private static String DEFAULT_CHARSET = "ISO-8859-1";
	
	private static List<String> pageList = null;
	private RandomAccessFile readFile;

	public PageParser(){
		PAGE_SIZE = getPageSize();
	}
	
	public PageParser(int width, int height, int fontSize, int lineHeight){
		WIDTH = width;
		HEIGHT = height;
		FONT_SIZE = fontSize;
		LINE_HEIGHT = lineHeight;
		PAGE_SIZE = getPageSize();
	}
	
	public int getPageSize(){
		COL_SIZE = (WIDTH - MARGIN * 2) / FONT_SIZE;
		return COL_SIZE * (int)((HEIGHT - MARGIN) / LINE_HEIGHT);
	}
	
	public int getPageNumber(){
		return pageList.size();
	}
	
	// 解析  传入文件，起始位置，结束位置
	public List<String> parser(File file, String charset, long startIndex, String endString) {
		try {
			pageList = new ArrayList<String>();
			readFile = new RandomAccessFile(file, "r");
			readFile.seek(startIndex);
			
			String newLine = readFile.readLine();
			newLine = newLine != null ? new String(newLine.getBytes(DEFAULT_CHARSET), charset) : null;
			StringBuilder sb = new StringBuilder();
			while(newLine != null){
				newLine = readFile.readLine();
				newLine = newLine != null ? new String(newLine.getBytes(DEFAULT_CHARSET), charset) : null;
				if(newLine.contains(endString)){
					break;
				}
				sb.append(newLine);
			}
			String pageContent = sb.toString();
			int length = sb.toString().length();
			int start = 0, end = 0;
			for(int i = 0; i < length/PAGE_SIZE + 1; i++){
				start = i*PAGE_SIZE;
				end = (i+1)*PAGE_SIZE;
				end = end > length ? length : end;
				String page = pageContent.substring(start, end);
				pageList.add(page);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageList;
	}
	
	public BufferedImage outputImage(int pageNum){
		InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("novel.png");
		
		BufferedImage buffImg = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2D = (Graphics2D) buffImg.getGraphics();
		// 通过文件生成一个图片buffer
		BufferedImage bImg = null;
		try {
			bImg = ImageIO.read(is);
			g2D.drawImage(bImg, 0, 0, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 设置字体颜色，大小
		g2D.setColor(new Color(110, 110, 110));
		g2D.setFont(new Font("微软雅黑", Font.PLAIN, FONT_SIZE));
		// 输出文字
		int rowNum = 0;
		System.out.println("===> parser page number: " + pageNum + "===>>pageList Size: " + pageList.size());
		String aticle = pageList.get(pageNum);
		while(aticle.length() > 0){
			if(COL_SIZE > aticle.length()){
				COL_SIZE = aticle.length();
			}
			String outStr = aticle.substring(0, COL_SIZE);
			aticle = aticle.substring(COL_SIZE, aticle.length());
			g2D.drawString(outStr, MARGIN, MARGIN + LINE_HEIGHT * rowNum);
			rowNum ++;
		}
		// 输出流
		//String outputPath = "C:/Users/Administrator/Desktop/novel.png";
		//FileOutputStream fos = new FileOutputStream(new File(outputPath));
		//ImageIO.write(buffImg, "png", fos);
		return buffImg;
	}
	
	public BufferedImage outputImage(String content){
		//InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("novel.png");
		InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("backGround.png");
		
		BufferedImage buffImg = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2D = (Graphics2D) buffImg.getGraphics();
		// 通过文件生成一个图片buffer
		BufferedImage bImg = null;
		try {
			bImg = ImageIO.read(is);
			g2D.drawImage(bImg, 0, 0, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 设置字体颜色，大小
		g2D.setColor(new Color(110, 110, 110));
		g2D.setFont(new Font("微软雅黑", Font.PLAIN, FONT_SIZE));
		// 输出文字
		int rowNum = 0;
		String aticle = content;
		while(aticle.length() > 0){
			if(COL_SIZE > aticle.length()){
				COL_SIZE = aticle.length();
			}
			String outStr = aticle.substring(0, COL_SIZE);
			aticle = aticle.substring(COL_SIZE, aticle.length());
			g2D.drawString(outStr, MARGIN, MARGIN + LINE_HEIGHT * rowNum);
			rowNum ++;
		}
		return buffImg;
	}
}
