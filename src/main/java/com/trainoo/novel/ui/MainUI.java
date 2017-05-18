package com.trainoo.novel.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.trainoo.novel.utils.EncodingDetect;

public class MainUI implements ActionListener {

	private JFrame frame = new JFrame("I'Reader");
	private Container con = new Container();
	private ContentPanel mainCont= null;
	
	public static void main(String args[]) {
		new MainUI();
	}
	
	private void init(){
		// 创建菜单栏
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 40, 20);
		JMenu menu = new JMenu("打开");
		JMenuItem item = new JMenuItem("导入");
		// 给菜单增加监听事件
		item.addActionListener(this);
		menu.add(item);
		menuBar.add(menu);
		
		con.setLayout(new VFlowLayout());
		con.add(menuBar);
	}

	public MainUI() {
		
		init();
		mainCont = new ContentPanel();
		con.add(mainCont);
		this.frame.add(con);
		this.frame.setSize(715, 710);
		//this.frame.setResizable(false);
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc = new JFileChooser();
		jfc.showDialog(new JLabel(), "选择");
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		File file = jfc.getSelectedFile();
		String filePath = file.getAbsolutePath();
		
		String charset = EncodingDetect.getJavaEncode(filePath);
		mainCont.parserChapter(filePath, charset);
	}
	
	
	
	
	
	/*private void parserPage(String filePath, String charset, int chapter){
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
			ImagePanel image = new ImagePanel();
			image.setPreferredSize(new Dimension(480, 720));
			panel.add(image);
			con.add(panel);
			frame.setSize(800, 820);
			con.revalidate();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}*/
	
	public void reset(){
		mainCont = new ContentPanel();
		con.revalidate();
	}
	
}