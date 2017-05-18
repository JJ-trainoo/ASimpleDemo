package com.trainoo.novel.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import com.trainoo.novel.chapterParser.ChapterParser;
import com.trainoo.novel.chapterParser.TitleInfo;

public class ContentPanel extends JPanel {

	private static final long serialVersionUID = 4782809279184852230L;

	private JList<String> jList = null;
	private List<TitleInfo> title = null;
	private DefaultListModel<String> dlm = null;
	private String chapterName = "正文";
	private String filePath, charset;

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
		try {
			chapterPanel = new ChapterPanel(jList);
			imagePanel = new ImagePanel(chapterName);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.add(chapterPanel);
		this.add(imagePanel);
		
		resetImagePanel();
	}

	public ContentPanel() {
		// 创建左侧列表栏目
		dlm = new DefaultListModel<String>();
		jList = new JList<String>(dlm);
		jList.setVisibleRowCount(30);
		jList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					chapterName = jList.getSelectedValue();
					System.out.println(chapterName);
					init(dlm, jList);
				}
			}
		});
		// 初始化内容
		init(dlm, jList);
	}

	public void resetChapterPanel() {
		chapterPanel.revalidate();
	}

	public void resetImagePanel() {
		imagePanel.revalidate();
	}

	public static void main(String[] args) throws Exception {
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(new ContentPanel());
		jf.setVisible(true);
	}

	void parserChapter(String filePath, String charset) {
		this.filePath = filePath;
		this.charset = charset;
		ChapterParser parser = new ChapterParser(filePath, charset);
		title = parser.parseTitleInfo();
		for (TitleInfo info : title) {
			dlm.addElement(info.getTitle());
		}
		resetChapterPanel();
	}

	void parserChapter() {
		if (this.filePath != null && this.charset != null) {
			parserChapter(this.filePath, this.charset);
		}
	}
}
