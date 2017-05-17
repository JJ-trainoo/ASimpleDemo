package com.trainoo.novel.ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.trainoo.novel.chapterParser.ChapterParser;
import com.trainoo.novel.chapterParser.TitleInfo;
import com.trainoo.novel.pageParser.PageParser;
import com.trainoo.novel.utils.EncodingDetect;

public class MainUI implements ActionListener {

	private JFrame frame = new JFrame("hello world");
	private Container con = new Container();
	private Container panel = new Container();
	private List<TitleInfo> title= null;
	private JList<String> jList = null;
	DefaultListModel<String> dlm = null;
	
	public static void main(String args[]) {
		new MainUI();
	}

	public MainUI() {
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 40, 20);
		JMenu menu = new JMenu("打开");
		JMenuItem item = new JMenuItem("导入");
		item.addActionListener(this);
		menu.add(item);
		menuBar.add(menu);
		con.setLayout(new VFlowLayout());
		con.add(menuBar);
		
		dlm = new DefaultListModel<String>();
		this.jList = new JList<String>(dlm);
		this.jList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
				}
			}
		});
		jList.setVisibleRowCount(40);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(jList);
		panel.add(new JScrollPane(this.jList)); // 对list1添加滚动条
		
		this.frame.add(con);
		this.frame.setSize(480, 780);
		this.frame.setResizable(false);
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		jfc.showDialog(new JLabel(), "选择");
		File file = jfc.getSelectedFile();
		String filePath = file.getAbsolutePath();
		String charset = EncodingDetect.getJavaEncode(filePath);
		parserChapter(filePath, charset);
		parserPage(filePath, charset, 0);
	}
	
	private void parserChapter(String filePath, String charset){
		ChapterParser parser = new ChapterParser(filePath, charset);
		title = parser.parseTitleInfo();
		for(TitleInfo info : title){
			dlm.addElement(info.getTitle());
		}
	}
	
	private void parserPage(String filePath, String charset, int chapter){
		String endString = "END";
		if(chapter >= title.size() || chapter < 0){
			System.out.println("error~");
			return;
		}
		if(chapter + 1 < title.size()){
			endString = title.get(chapter+1).getTitle();
		}
		PageParser pageParser = new PageParser();
		pageParser.parser(new File(filePath), charset, title.get(chapter).getStartLength(), endString);
		getPageImage(0);
	}
	
	public void getPageImage(int page){
		BufferedImage buffImg;
		try {
			buffImg = PageParser.outputImage(0);
			ImagePanel image = new ImagePanel(buffImg);
			image.setPreferredSize(new Dimension(480, 720));
			panel.add(image);
			con.add(panel);
			frame.setSize(900, 820);
			con.revalidate();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	class ImagePanel extends JPanel {  
		private static final long serialVersionUID = 760543506204806560L;
		private BufferedImage image;  
		
	    public ImagePanel(BufferedImage buf) {
	    	image = buf;
	    }  
	    @Override  
	    public void paintComponent(Graphics g) {  
	        g.drawImage(image, 0, 0, null);   
	    }  
	} 
}
