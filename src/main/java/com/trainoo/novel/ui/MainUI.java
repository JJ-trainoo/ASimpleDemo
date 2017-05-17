package com.trainoo.novel.ui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
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
	private Container cont = new Container();
	private List<TitleInfo> title= null;
	private JList<String> jList = null;
	DefaultListModel<String> dlm = null;
	
	public static void main(String args[]) {
		new MainUI();
	}

	public MainUI() {
		
		this.frame.setLayout(new FlowLayout());
		cont.setLayout(new GridLayout(1, 2));
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("打开");
		JMenuItem item = new JMenuItem("导入");
		item.addActionListener(this);
		menu.add(item);
		menuBar.add(menu);
		this.frame.add(menuBar);

		dlm = new DefaultListModel<String>();
		this.jList = new JList<String>(dlm);
		this.jList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
				}
			}
		});
		this.cont.add(jList);
		this.cont.add(new JScrollPane(this.jList)); // 对list1添加滚动条
		//this.cont.add(new ImagePanel());

		this.frame.add(cont);
		this.frame.setSize(400, 200);
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
		showChapter(filePath);
	}
	
	private void showChapter(String filePath){
		String charset = EncodingDetect.getJavaEncode(filePath);
		ChapterParser parser = new ChapterParser(filePath, charset);
		title = parser.parseTitleInfo();
		for(TitleInfo info : title){
			dlm.addElement(info.getTitle());
		}
		
		BufferedImage buffImg;
		try {
			buffImg = PageParser.outputImage(0);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	class ImagePanel extends JPanel {  
		  
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
