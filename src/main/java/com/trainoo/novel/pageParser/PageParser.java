package com.trainoo.novel.pageParser;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class PageParser {

	private static final int MARGIN = 50;
	private static int WIDTH = 720;
	private static int HEIGHT = 1080;
	private static int FONT_SIZE = 20;
	private static int LINE_HEIGHT = 40;
	private static int PAGE_SIZE = 0;
	private static int COL_SIZE = 0;
	private static String DEFAULT_CHARSET = "ISO-8859-1";
	
	private static List<String> pageList = new ArrayList<String>();
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
	
	public List<String> getPageList(){
		return pageList;
	}

	// 解析  传入文件，起始位置，结束位置
	public void parser(File file, String charset, long startIndex, String endString) {
		try {
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
	}
	
	public static BufferedImage outputImage(int pageNum) throws Exception{
		String imagePath = "C:/Users/Administrator/Desktop/backGround.png";
		String outputPath = "C:/Users/Administrator/Desktop/novel.png";
		
		BufferedImage buffImg = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2D = (Graphics2D) buffImg.getGraphics();
		// 通过文件生成一个图片buffer
		BufferedImage bImg = ImageIO.read(new File(imagePath));
		g2D.drawImage(bImg, 0, 0, null);
		// 设置字体颜色，大小
		g2D.setColor(new Color(110, 110, 110));
		g2D.setFont(new Font("微软雅黑", Font.PLAIN, FONT_SIZE));
		// 输出文字
		int rowNum = 0;
		String aticle = pageList.get(pageNum);
		System.out.println(aticle);
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
		FileOutputStream fos = new FileOutputStream(new File(outputPath));
		ImageIO.write(buffImg, "png", fos);
		return buffImg;
	}
}
