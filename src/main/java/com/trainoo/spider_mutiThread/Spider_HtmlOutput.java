package com.trainoo.spider_mutiThread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 输出
 * 
 * @author zhoutao
 * @date 2017年5月8日
 */
public class Spider_HtmlOutput {

	private Logger logger = LogManager.getLogger(Spider_HtmlOutput.class);

	private List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

	private String TEMP_PATH = "C:/Users/Administrator/Desktop/temp";
	
	Spider_HtmlOutput(){}
	
	Spider_HtmlOutput(String fileName){
		TEMP_PATH = "C:/Users/Administrator/Desktop/" + fileName;
	}

	/**
	 * 保存抓取的内容
	 * 
	 * @authod zhoutao
	 * @param map
	 */
	public void collectData(Map<String, String> map) {
		if (null != map) {
			dataList.add(map);
		}
	}

	/**
	 * 输出到文本
	 * 
	 * @authod zhoutao
	 */
	public void outputData(int partNum) {
		// 判断文件夹是否存在，不存在则创建文件夹
		File file = new File(TEMP_PATH);
		if (!file.exists()) {
			file.mkdir();
		}
		// 输出文本
		FileOutputStream fos = null;
		PrintStream ps = null;
		try {
			fos = new FileOutputStream(new File(TEMP_PATH + "/part" + partNum + ".txt"));
			for (Map<String, String> map : dataList) {
				String title = map.get(Spider_Constant.Element.TITLE.getValue());
				String content = map.get(Spider_Constant.Element.CONTENT.getValue());
				// 输入到文本文档
				ps = new PrintStream(fos);
				ps.print(title);
				ps.println();
				ps.println();
				ps.print(content);
				ps.println();
				ps.println();
			}
			ps.close();
		} catch (Exception e) {
			logger.error("输出文件异常!", e);
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e1) {
					logger.error("输出文件异常!", e1);
				}
			}
		}
	}

	@SuppressWarnings("resource")
	public void mergeFiles(String fileName) {
		int partNum = 1;
		String filePath = TEMP_PATH + "/part" + partNum + ".txt";
		File file = new File(filePath);
		FileChannel outChannel = null;
		try {
			outChannel = new FileOutputStream(new File(TEMP_PATH + "/" + fileName + ".txt")).getChannel();
			while (file.exists()) {
				logger.info("合并文件：" + filePath);
				Charset charset = Charset.forName("utf-8");
				CharsetDecoder charDecoder = charset.newDecoder();
				CharsetEncoder charEncoder = charset.newEncoder();
				FileChannel fc = new FileInputStream(filePath).getChannel();
				ByteBuffer byteBuf = ByteBuffer.allocate(1024*1000);
				CharBuffer charBuffer = charDecoder.decode(byteBuf);
				ByteBuffer newByteBuf = charEncoder.encode(charBuffer);
				while (fc.read(newByteBuf) != -1) {
					byteBuf.flip();
					newByteBuf.flip();
					outChannel.write(newByteBuf);
					byteBuf.clear();
					newByteBuf.clear();
				}
				logger.info("删除临时文件!" + filePath);
				fc.close(); // 必须先关闭通道之后，文件才能删除成功
				file.delete();
				partNum ++;
				filePath = TEMP_PATH + "/part" + partNum + ".txt";
				file = new File(filePath);
			}
		} catch (Exception e) {
			logger.error("输出文件异常!", e);
		} finally {
			try {
				if (outChannel != null) {
					outChannel.close();
				}
			} catch (IOException e) {
				logger.error("输出文件异常!", e);
			}
		}
	}
}
