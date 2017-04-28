package com.trainoo.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.junit.Test;

/**
 * 文件读写，特殊字符过滤
 *
 * @author zhout
 * @date 2017年4月28日
 */
public class MyTest2_File {

	// @Test
	public void test() throws Exception {
		InputStreamReader isr = new InputStreamReader(new FileInputStream("C:/Users/Administrator/Desktop/file.txt"),
				"GBK");
		BufferedReader br = new BufferedReader(isr);
		String str = null;

		FileOutputStream fos = new FileOutputStream("C:/Users/Administrator/Desktop/file_new1.txt");
		while ((str = br.readLine()) != null) {
			// 过滤特殊字符|以及字母
			String regEx = "[\\\\|`~!@#$%^&*()+=|{}':;',\\[\\].<>《》/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|[a-zA-Z]";
			String[] ss = str.split(regEx);
			// 逐行输出
			for (int i = 0; i < ss.length; i++) {
				if (ss[i].length() != 0) {
					// 输出到控制台
					System.out.println(ss[i]);
					// 写入文件
					fos.write(ss[i].getBytes());
					// 换行
					fos.write("\n".getBytes());
				}
			}
		}
		fos.close();
		br.close();
		isr.close();
	}

	@Test
	public void test2() throws Exception {
		InputStreamReader isr = new InputStreamReader(new FileInputStream("C:/Users/Administrator/Desktop/file.txt"),
				"GBK");
		BufferedReader br = new BufferedReader(isr);
		String str = null;

		FileOutputStream fos = new FileOutputStream("C:/Users/Administrator/Desktop/file_new.txt");
		while ((str = br.readLine()) != null) {
			// 过滤特殊字符|以及字母
			String regEx = "[\\\\|`~!@#$%^&*()+=|{}':;',\\[\\].<>《》/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
			str = str.replaceAll(regEx, "");
			fos.write(str.getBytes());
			fos.write("\n".getBytes());
		}
		fos.close();
		br.close();
		isr.close();
	}

	//@Test
	public void test3() throws Exception {
		// 纯中文文本，没有其他特殊符号，已使用test2过滤
		RandomAccessFile aFile = new RandomAccessFile("C:/Users/Administrator/Desktop/file_new.txt", "r");
		FileChannel channel = aFile.getChannel();
		
		Charset charset = Charset.forName("UTF-8");
		CharsetDecoder decoder = charset.newDecoder();
		ByteBuffer buffer = ByteBuffer.allocate(15);
		CharBuffer charBuffer = CharBuffer.allocate(15);
		int i = channel.read(buffer);
		while (i != -1) {
			buffer.flip(); // 切换到读模式
			decoder.decode(buffer, charBuffer, false);
			charBuffer.flip(); // 切换到读模式
			while (charBuffer.hasRemaining()) {
				char c = charBuffer.get();
				System.out.print(c);
				Thread.sleep(100);
			}
			System.out.println();
			charBuffer.clear();
			buffer.clear();
			i = channel.read(buffer);
		}
		aFile.close();
	}

}
