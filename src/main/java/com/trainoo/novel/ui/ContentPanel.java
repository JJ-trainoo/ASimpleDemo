package com.trainoo.novel.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import com.trainoo.novel.chapterParser.ChapterParser;
import com.trainoo.novel.chapterParser.TitleInfo;
import com.trainoo.novel.pageParser.PageConstant;
import com.trainoo.novel.pageParser.PageParser;

public class ContentPanel extends JPanel {

	private static final long serialVersionUID = 4782809279184852230L;

	// 左侧容器需要的参数
	private static JList<String> jList = null;
	private static DefaultListModel<String> dlm = null;

	// 右侧容器需要的参数
	private BufferedImage buffImage = null;
	private String chapterName = ""; // 需要寻找的章节名
	private String curChapName = "curChapter"; // 当前显示的章节名

	private static List<TitleInfo> titles = null;
	private static List<String> pages = null;
	private Map<String, Object> chapterInfo = null;
	private String charset;
	private static int curPage = -1, toPage, pageSize;
	private static int flag = 0;

	private File file;
	ChapterPanel chapterPanel = null;
	ImagePanel imagePanel = null;

	/**
	 * 构造函数
	 * 
	 * @author zhout
	 * @date 2017年5月19日
	 */
	public ContentPanel() {
		// 创建左侧章节列表栏目
		dlm = new DefaultListModel<String>();
		jList = new JList<String>(dlm);
		jList.setVisibleRowCount(30);
		// 给章节点击添加监听事件
		jList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					chapterName = jList.getSelectedValue();
					System.out.println("chapterClicked_____________: " + chapterName);
					toPage = 0; // 获取当前页
					flag = 0;
					init(dlm, jList);
				}
			}
		});
		
		// 初始化章节面板跟内容面板
		init(dlm, jList);
	}

	/**
	 * 初始化章节面板跟内容面板
	 * 
	 * @author zhout
	 * @date 2017年5月19日
	 * @param dlm2
	 * @param jList2
	 */
	public void init(DefaultListModel<String> dlm2, JList<String> jList2) {
		// 如果不为空，先移除内容，在新增
		if (imagePanel != null) {
			this.remove(imagePanel);
		}
		if (chapterPanel != null) {
			this.remove(chapterPanel);
		}
		// 设置布局
		this.setLayout(new GridLayout());
		this.setPreferredSize(new Dimension(100, 635));
		
		// 如果当前显示的章节跟选择的章节不一样，则去寻找chapterName
		if (!curChapName.equals(chapterName)) {
			findCurChapter(chapterName);
		}
		// 分页
		System.out.println("flag:" + flag);
		parserPage(chapterName, flag);
		//获取图片
		getCurrentPage(chapterName);

		// 新建容器
		chapterPanel = new ChapterPanel(jList);
		imagePanel = new ImagePanel(buffImage, chapterName);
		this.add(chapterPanel);
		this.add(imagePanel);

		// 初始化按钮点击事件
		initButton();
		// 重新绘制窗口
		resetChapterPanel();
	}

	/**
	 * 按钮添加事件监听
	 */
	private void initButton() {
		chapterPanel.up.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					if (toPage <= 0) {
						chapterName = (String) chapterInfo.get(PageConstant.PREVIEW);
						flag = 1;
					} else {
						flag = 2;
					}
					System.out.println("toPage_up: " + toPage);
					init(dlm, jList);
				}
			}
		});
		chapterPanel.down.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					if (toPage >= pageSize - 1) {
						chapterName = (String) chapterInfo.get(PageConstant.NEXT);
						flag = -1;
					} else {
						flag = -2;
					}
					System.out.println("toPage_down: " + toPage);
					init(dlm, jList);
				}
			}
		});
	}

	/**
	 * 重绘章节
	 * 
	 * @author zhout
	 * @date 2017年5月19日
	 */
	public void resetChapterPanel() {
		System.out.println("resetChapterPanel===>>" + chapterName);
		chapterPanel.revalidate();
	}

	/**
	 * 重绘内容
	 * 
	 * @author zhout
	 * @date 2017年5月19日
	 */
	public void resetImagePanel() {
		imagePanel.revalidate();
	}

	/**
	 * 解析章节目录，动态添加到左侧面板列表中
	 * 
	 * @author zhout
	 * @date 2017年5月19日
	 * @param filePath
	 * @param charset
	 */
	public void parserChapter(File fileF, String charset) {
		this.file = fileF;
		this.charset = charset;
		ChapterParser parser = new ChapterParser(file, charset);
		titles = parser.parseTitleInfo();
		for (TitleInfo info : titles) {
			dlm.addElement(info.getTitle());
		}
		resetChapterPanel();
	}

	/***
	 * 根据章节名称查询章节信息
	 * 
	 * @authod zhoutao
	 */
	private void findCurChapter(String chapterNeeded) {
		System.out.println("开始查询===>> : " + chapterNeeded);
		if (titles == null) {
			return;
		}

		long startIndex = 0;
		String current = "";
		String next = "END~";
		String preview = "";
		// 循环遍历title
		for (int i = 0; i < titles.size(); i++) {
			TitleInfo info = titles.get(i);
			if (chapterNeeded.equals(info.getTitle())) {
				startIndex = info.getStartLength();
				// 防止数组越界
				if (i + 1 < titles.size()) {
					next = titles.get(i + 1).getTitle();
				}
				if (i - 1 > 0) {
					preview = titles.get(i - 1).getTitle();
				}
				// 如果查询成功，保存信息，并设置当前章节=被查找的章节
				chapterInfo = new HashMap<String, Object>();
				chapterInfo.put(PageConstant.START_INDEX, startIndex);
				chapterInfo.put(PageConstant.CURRENT, current);
				chapterInfo.put(PageConstant.NEXT, next);
				chapterInfo.put(PageConstant.PREVIEW, preview);
				break;
			}
		}
	}

	/**
	 * 根据章节名称，解析、分页正文内容
	 * 
	 * @author zhout
	 * @date 2017年5月19日
	 */
	private void parserPage(String curName, int flag) {
		// 如果当前章节不为空，则解析当前章节信息
		if (chapterInfo != null) {
			long start = (long) chapterInfo.get(PageConstant.START_INDEX);
			String next = (String) chapterInfo.get(PageConstant.NEXT);
			if (pages == null || !curChapName.equals(curName)) {
				PageParser paser = new PageParser();
				pages = paser.parser(file, charset, start, next);
				pageSize = pages.size();
				System.out.println("pageSize changed:" + pageSize + " , curPage:" + curPage);
			}
			if (flag == 1) {
				toPage = pageSize - 1;
			} else if (flag == -1) {
				toPage = 0;
			} else if (flag == 2) {
				toPage = toPage - 1;
			} else if (flag == -2) {
				toPage = toPage + 1;
			}else {
			}
		}
	}

	/**
	 * 获取当前页显示的内容
	 * 
	 * @author zhout
	 * @date 2017年5月19日
	 * @return
	 */
	private void getCurrentPage(String curName) {
		if (curPage != -1) {
			if (!curChapName.equals(curName)) {
				System.out.println("get Page11111:" + toPage);
				buffImage = new PageParser().outputImage(pages.get(toPage));
				this.curChapName = curName;
				curPage = toPage;
			} else {
				System.out.println("get Page22222:" + toPage);
				buffImage = new PageParser().outputImage(pages.get(toPage));
			}
		} else {
			curPage = 0;
		}
	}

	/**
	 * 测试
	 * 
	 * @authod zhoutao
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(new ContentPanel());
		jf.setVisible(true);
	}
}
