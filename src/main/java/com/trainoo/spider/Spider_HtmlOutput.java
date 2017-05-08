package com.trainoo.spider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 输出
 * @author zhoutao
 * @date 2017年5月8日
 */
public class Spider_HtmlOutput {
	
	private Logger logger = LogManager.getLogger(Spider_HtmlOutput.class);

	private List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
	
	/**
	 * 保存抓取的内容
	 * @authod zhoutao
	 * @param map
	 */
	public void collectData(Map<String, String> map) {
		if(null != map){
			dataList.add(map);
		}
	}
	
	/**
	 * 输出到文本
	 * @authod zhoutao
	 */
	public void outputData(){
		FileOutputStream fos = null;
		PrintStream ps = null;
		try {
			fos = new FileOutputStream(new File("D://Spider_Output.txt"));
			for(Map<String, String> map : dataList){
				String title = map.get(Spider_Constant.Element.TITLE.getValue());
				String content = map.get(Spider_Constant.Element.CONTENT.getValue());
				//　输入到文本文档
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
			if(fos != null){
				try {
					fos.close();
				} catch (Exception e1) {
					logger.error("输出文件异常!", e1);
				}
			}
		}
	}

}
