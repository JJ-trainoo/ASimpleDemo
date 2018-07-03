package com.trainoo.novelReader.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {

    private static final int WIDTH = 720;
    private static final int HEIGHT = 1080;
    private static final int PT = 20;

    public static void main(String[] args) throws Exception {
        outputImage("444444");
    }

    public static void outputImage(String aticle) throws Exception {
        String is = ClassLoader.getSystemClassLoader().getResource("backGround.png").getPath();
        System.out.println(is);
        String outputPath = "C:/Users/Administrator/Desktop/novelReader.png";

        BufferedImage buffImg = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2D = (Graphics2D) buffImg.getGraphics();
        // 通过文件生成一个图片buffer
        BufferedImage bImg = ImageIO.read(new File(is));
        g2D.drawImage(bImg, 0, 0, null);
        // 设置字体颜色，大小
        g2D.setColor(new Color(110, 110, 110));
        g2D.setFont(new Font("微软雅黑", Font.PLAIN, PT));
        // 计算每行显示的字数
        int charNum = (WIDTH - 80) / PT;
        int rowNum = 1;
        // 输出文字
        while (aticle.length() > 0) {
            if (charNum > aticle.length()) {
                charNum = aticle.length();
            }
            String outStr = aticle.substring(0, charNum);
            aticle = aticle.substring(charNum, aticle.length());
            g2D.drawString(outStr, 40, 40 * rowNum);
            rowNum++;
        }
        // 输出流
        FileOutputStream fos = new FileOutputStream(new File(outputPath));
        ImageIO.write(buffImg, "png", fos);
    }

    public static BufferedImage getImage(String aticle) {
        String is = ClassLoader.getSystemClassLoader().getResource("backGround.png").getPath();
        BufferedImage buffImg = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2D = (Graphics2D) buffImg.getGraphics();
        // 通过文件生成一个图片buffer
        BufferedImage bImg;
        try {
            bImg = ImageIO.read(new File(is));
            g2D.drawImage(bImg, 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 设置字体颜色，大小
        g2D.setColor(new Color(110, 110, 110));
        g2D.setFont(new Font("微软雅黑", Font.PLAIN, PT));
        // 计算每行显示的字数
        int charNum = (WIDTH - 80) / PT;
        int rowNum = 1;
        // 输出文字
        while (aticle.length() > 0) {
            if (charNum > aticle.length()) {
                charNum = aticle.length();
            }
            String outStr = aticle.substring(0, charNum);
            aticle = aticle.substring(charNum, aticle.length());
            g2D.drawString(outStr, 40, 40 * rowNum);
            rowNum++;
        }
        // 输出流
        return buffImg;
    }
}
