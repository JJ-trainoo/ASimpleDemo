package com.trainoo.novelReader.ui;

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

import com.trainoo.novelReader.utils.EncodingDetect;

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
		// 设置布局
		con.setLayout(new VFlowLayout());
		con.add(menuBar);
	}

	public MainUI() {
		// 初始化菜单栏
		init();
		// 创建主窗口
		mainCont = new ContentPanel();
		con.add(mainCont);
		this.frame.add(con);
		// 默认窗口属性
		this.frame.setSize(715, 710);
		this.frame.setResizable(false);
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * 导入文件菜单监听事件
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// 新建文本选择器，选择文件
		JFileChooser jfc = new JFileChooser();
		jfc.showDialog(new JLabel(), "选择");
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		File file = jfc.getSelectedFile();
		String filePath = file.getAbsolutePath();
		// 根据文件的路径， 调用main的方法解析文本章节
		String charset = EncodingDetect.getJavaEncode(filePath);
		mainCont.parserChapter(new File(filePath), charset);
	}
	
}