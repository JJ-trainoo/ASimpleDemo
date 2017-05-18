package com.trainoo.novel.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import com.trainoo.novel.chapterParser.ChapterParser;
import com.trainoo.novel.chapterParser.TitleInfo;

public class ContentPanel extends JPanel {

	private static final long serialVersionUID = 4782809279184852230L;

	private static JList<String> jList = null;
	private static List<TitleInfo> title = null;
	private static DefaultListModel<String> dlm = null;
	private String chapterName = "";
	private String filePath, charset;
	private static int curPage, pageSize;

	ChapterPanel chapterPanel = null;
	ImagePanel imagePanel = null;

	public void init(DefaultListModel<String> dlm2, JList<String> jList2) {

		if (imagePanel != null) {
			this.remove(imagePanel);
		}
		if (chapterPanel != null) {
			this.remove(chapterPanel);
		}

		this.setLayout(new GridLayout());
		this.setPreferredSize(new Dimension(100, 635));
		
		chapterPanel = new ChapterPanel(jList);
		imagePanel = new ImagePanel(chapterPanel, filePath, title, chapterName, charset, curPage);

		this.add(chapterPanel);
		this.add(imagePanel);
		
		curPage = imagePanel.getCurPage();   // 获取当前页
		pageSize = imagePanel.getPageSize(); // 总页数
		// 初始化按钮点击事件
		initButton();
		// 重新绘制窗口
		resetChapterPanel();
	}
	
	/**
	 * 按钮添加事件监听
	 */
	private void initButton(){
		chapterPanel.up.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					if(curPage <= 0){
						chapterName = (String) imagePanel.getChapterInfo().get("preview");
						ImagePanel tmp = new ImagePanel(chapterPanel, filePath, title, chapterName, charset, 0);
						pageSize = tmp.getPageSize();
						curPage = pageSize - 1;
					}else {
						curPage = curPage - 1;
					}
					System.out.println("pageSize:"+pageSize+" ,curPage:"+curPage);
					imagePanel.setCurPage(curPage);
					init(dlm, jList);
				}
			}
		});
		chapterPanel.down.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					if(curPage >= pageSize - 1){
						chapterName = (String) imagePanel.getChapterInfo().get("endString");
						ImagePanel tmp = new ImagePanel(chapterPanel, filePath, title, chapterName, charset, 0);
						pageSize = tmp.getPageSize();
						curPage = 0;
					}else {
						curPage = curPage + 1;
					}
					System.out.println("pageSize:"+pageSize+" ,curPage:"+curPage);
					imagePanel.setCurPage(curPage);
					init(dlm, jList);
				}
			}
		});
	}

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
					System.out.println(chapterName);
					curPage = 0;   // 获取当前页
					init(dlm, jList);
				}
			}
		});
		// 初始化内容
		init(dlm, jList);
	}

	public void resetChapterPanel() {
		System.out.println("chapterName"+chapterName);
		chapterPanel.revalidate();
	}

	public void resetImagePanel() {
		imagePanel.revalidate();
	}

	public void parserChapter(String filePath, String charset) {
		this.filePath = filePath;
		this.charset = charset;
		ChapterParser parser = new ChapterParser(filePath, charset);
		title = parser.parseTitleInfo();
		for (TitleInfo info : title) {
			dlm.addElement(info.getTitle());
		}
		resetChapterPanel();
	}
	
	
	/**
	 * 测试
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
