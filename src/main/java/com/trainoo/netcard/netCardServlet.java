package com.trainoo.netcard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.trainoo.utils.DateUtil;
import com.trainoo.utils.HttpUtil;

import nl.bitwalker.useragentutils.UserAgent;

/**
 * 在线生成ip名片
 * @author zhoutao
 * @date 2017年3月10日
 */
public class netCardServlet extends HttpServlet {

	private static final long serialVersionUID = -2971903955977636139L;
	

	@SuppressWarnings({ "unchecked" })
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取系统版本、浏览器名、版本号等
		UserAgent userAgent = UserAgent.parseUserAgentString(req.getHeader("User-Agent"));
		String operatingSystem = userAgent.getOperatingSystem().getName();
		String browserName = userAgent.getBrowser().getName();
		String browVersion = userAgent.getBrowserVersion().getVersion().split("\\.")[0];
		System.out.println(operatingSystem + " " + browserName + " " + browVersion);
		// 获取ip地址
		/*
		 * HttpUtil.get("http://ip.chinaz.com/getip.aspx", "UTF-8")
		 * "{\"address\":\"广东深圳 电信\",\"ip\":\"202.104.140.90\"}"
		 */
		String json = HttpUtil.get("http://ip.chinaz.com/getip.aspx", "UTF-8");
		Map<String, String> map = (Map<String, String>) JSONObject.parse(json);
		String ip = map.get("ip");
		String address = map.get("address");
		System.out.println(map);
		// 获取输出流
		OutputStream os = resp.getOutputStream();
		try {
			// 得到背景图片路径
			String path = req.getScheme() + "://" + req.getServerName()+ ":" + req.getServerPort() ;
			// 在背景图片山进行编辑
			createImg(os, path, operatingSystem, browserName, browVersion, ip, address);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(os != null){
				os.close();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	/**
	 * 生成图片
	 * @authod zhoutao
	 * @param os
	 * @param path
	 * @param operatingSystem
	 * @param browserName
	 * @param browVersion
	 * @param ip
	 * @param address
	 * @throws IOException
	 */
	private static void createImg(OutputStream os, String path, String operatingSystem, String browserName, String browVersion,
			String ip, String address) throws IOException {
		BufferedImage buffImg = new BufferedImage(300, 126, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) buffImg.getGraphics();
		// 设置背景颜色
		/*Color bColor = new Color(175, 175, 175, 100);
		Color bColor2 = new Color(244, 244, 244, 200);
		g.setColor(bColor);
		RoundRectangle2D roundedRectangle1 = new RoundRectangle2D.Float(0, 0, 300, 126, 10, 10);
		g.fill(roundedRectangle1);
		g.setColor(bColor2);
		RoundRectangle2D roundedRectangle2 = new RoundRectangle2D.Float(3, 3, 294, 120, 10, 10);
		g.fill(roundedRectangle2);*/
		// 通过文件生成一个图片buffer
		URL input = new URL(path + "/ASimpleDemo/static/images/netcard/netCard.png");
		BufferedImage bImg = ImageIO.read(input);
		g.drawImage(bImg, 0, 0, null);
		// 设置字体颜色，大小
		g.setColor(new Color(110, 110, 110));
		g.setFont(new Font("微软雅黑", Font.PLAIN, 11));
		// 地址
		g.drawString("您来自:" + address + "...", 30, 32);
		g.drawString(operatingSystem, 249 - operatingSystem.length()*5 , 32);
		// ip
		g.drawString("IP:" + ip, 30, 54);
		g.drawString(browserName + " " + browVersion+" ", 249 - (2+browserName.length()+browVersion.length())*5, 54);
		// 日期
		String yMD = DateUtil.getYMD_CN();
		g.drawString("今天是" + yMD, 30, 76);
		// 星期
		String dayOfWeek = DateUtil.getDayOfWeek();
		g.drawString(dayOfWeek, 200, 76);
		// 版权
		g.setColor(new Color(216, 65, 65));
		g.drawString("在线工具", 30, 100);
		g.setColor(new Color(72, 142, 72));
		g.drawString("zhoutao.com", 200, 100);
		// 输出流
		ImageIO.write(buffImg, "png", os);
		// 关闭流
		os.close();
	}
	
	public static void main(String[] args) {
	}
}
