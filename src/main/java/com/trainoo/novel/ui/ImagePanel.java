package com.trainoo.novel.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

class ImagePanel extends JPanel {
	private static final long serialVersionUID = 760543506204806560L;
	private BufferedImage image;
	private String borderName = null;

	private void init() {
		this.setBorder(new TitledBorder(new EtchedBorder(), borderName, TitledBorder.CENTER, TitledBorder.TOP));
	}
	
	public ImagePanel(String name) throws IOException {
		this(ImageIO.read(new File("C:/Users/Administrator/Desktop/bk.jpg")), name);
	}

	public ImagePanel(BufferedImage buf, String name) {
		this.borderName = name;
		this.image = buf;
		init();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(image, 10, 25, null);
	}

	public static void main(String[] args) throws Exception {
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		panel.add(new ImagePanel(""));
		panel.setPreferredSize(new Dimension(320, 480));
		jf.add(panel);
		jf.setSize(360, 600);
		jf.setVisible(true);
	}
}