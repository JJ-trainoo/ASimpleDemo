package com.trainoo.novel.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

class ChapterPanel extends JPanel {  
	
	private static final long serialVersionUID = -522552371576831365L;
	
	@SuppressWarnings("rawtypes")
	JList jList = null;
	JButton up = new JButton("上一页");
	JButton down = new JButton("下一页");
	
	private void init(){
		this.setBounds(0, 0, 100, 720);
		this.setBorder(new TitledBorder(new EtchedBorder(), "章节", TitledBorder.CENTER, TitledBorder.TOP));
		this.setLayout(new VFlowLayout());
		this.add(jList);
		this.add(new JScrollPane(jList));
		JPanel jpanel = new JPanel();
		jpanel.add(up);
		jpanel.add(down);
		this.add(jpanel);
	}
	
	public <E> ChapterPanel(JList<E> jList) {
		this.jList = jList;
		init();
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DefaultListModel dlm = new DefaultListModel<String>();
		JList jList = new JList<String>(dlm);
		jList.setVisibleRowCount(35);
		jf.add(new ChapterPanel(jList));
		jf.setVisible(true);
	}
	
}
