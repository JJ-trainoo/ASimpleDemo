package com.trainoo.novelReader.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * 正文内容页面控件面板
 *
 * @author zhout
 * @date 2017年5月19日
 */
class ImagePanel extends JPanel {
	
	private static final long serialVersionUID = 760543506204806560L;
	private BufferedImage image = null;

	/**
	 * 构造函数
	 * @author zhout
	 * @date 2017年5月19日
	 * @param buffImage
	 * @param chapterName
	 */
	public ImagePanel(BufferedImage buffImage, String chapterName) {
		this.setBorder(new TitledBorder(new EtchedBorder(), chapterName, TitledBorder.CENTER, TitledBorder.TOP));
		image = buffImage;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(image, 10, 25, null);
	}

}