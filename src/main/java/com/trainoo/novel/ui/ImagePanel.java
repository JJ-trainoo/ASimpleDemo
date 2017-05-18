package com.trainoo.novel.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.trainoo.novel.chapterParser.TitleInfo;
import com.trainoo.novel.pageParser.PageParser;

class ImagePanel extends JPanel {
	private static final long serialVersionUID = 760543506204806560L;
	private int curPage = 0;
	private int pageSize = 0;
	private List<TitleInfo> title = null;
	private BufferedImage image = null;
	private String curChapter = "";
	private String filePath = null;
	private String charset = null;
	private Map<String, Object> chapterInfo = null;

	public ImagePanel(ChapterPanel chapterPanel, String filePath, List<TitleInfo> title, String chapterName, String charset,
			int curPage) {
		System.out.println("1111111111:"+chapterName);
		this.filePath = filePath;
		this.charset = charset;
		this.title = title;
		this.curPage = curPage;
		findCurChapter(chapterName);
		init();
	}

	private void init() {
		this.setBorder(new TitledBorder(new EtchedBorder(), curChapter, TitledBorder.CENTER, TitledBorder.TOP));
		if (null != chapterInfo && filePath != null && charset != null) {
			long startIndex = (long) chapterInfo.get("startIndex");
			String endString = (String) chapterInfo.get("endString");
			PageParser paser = new PageParser();
			System.out.println("endString:"+endString);
			pageSize = paser.parser(new File(filePath), charset, startIndex, endString).size();
			image = paser.outputImage(curPage);
		}

	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(image, 10, 25, null);
	}

	/***
	 * 根据章节名称查询章节信息
	 * 
	 * @authod zhoutao
	 */
	private void findCurChapter(String name) {
		long startIndex = 0;
		String endString = "END~";
		String preview = null;
		if (!name.equals(curChapter) && title != null) {
			for (int i = 0; i < title.size(); i++) {
				TitleInfo info = title.get(i);
				if (name.equals(info.getTitle())) {
					startIndex = info.getStartLength();
					if (i + 1 < title.size()) {
						endString = title.get(i + 1).getTitle();
					}
					if (i - 1 > 0){
						preview = title.get(i - 1).getTitle();
					}
					break;
				}
			}
		}
		chapterInfo = new HashMap<String, Object>();
		chapterInfo.put("startIndex", startIndex);
		chapterInfo.put("endString", endString);
		chapterInfo.put("preview", preview);
		this.curChapter = name;
	}

	public int getCurPage() {
		return curPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
		init();
	}
	
	public Map<String, Object> getChapterInfo() {
		return chapterInfo;
	}

	public void setChapterInfo(Map<String, Object> chapterInfo) {
		this.chapterInfo = chapterInfo;
	}
}